package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.Crypto;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Admin;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

// Modified from CS320 Lab06

public class DerbyDatabase implements IDatabase
{
	static
	{
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		}
		catch(Exception e)
		{
			throw new IllegalStateException("Could not load Derby driver!");
		}
	}

	private interface Transaction<ResultType>
	{
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	@Override
	public ArrayList<User> getUsers()
	{
		return executeTransaction(new Transaction<ArrayList<User>>()
			{
				@Override
				public ArrayList<User> execute(Connection conn) throws SQLException
				{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try
					{
						// retrieve all accounts and populate into the list
						stmt = conn.prepareStatement("select * from users");
						ArrayList<User> result = new ArrayList<User>();

						resultSet = stmt.executeQuery();

						while(resultSet.next()){
							User user = loadUser(resultSet);
							result.add(user);
						}
						return result;
					}
					finally
					{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password)
	{
		return executeTransaction(new Transaction<User>()
		{
			@Override
			public User execute(Connection conn) throws SQLException
			{
				Crypto crypto = new Crypto();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try
				{
					// First get the password
					stmt = conn.prepareStatement("SELECT users.password FROM users WHERE users.username = ?");
					stmt.setString(1, username);
					
					resultSet = stmt.executeQuery();
					Boolean match = false;
					while(resultSet.next())
					{
						match = crypto.match(password, resultSet.getString(1));
					}
					if(match)
					{
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet);
						//create statement to get user
						stmt = conn.prepareStatement(
								"SELECT users.* FROM users " +
								"WHERE users.username = ?");
						stmt.setString(1, username);
						
						User result = null;
						resultSet = stmt.executeQuery();
						Boolean found = false;
						while(resultSet.next())
						{
							found = true;
							result = loadUser(resultSet);
						}

						if(!found)
						{
							return null;
						}
						return result;
					}
					else
					{
						return null;
					}
				}
				finally
				{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public User getUserByUsernameAndEmail(String username, String email)
	{
		return executeTransaction(new Transaction<User>()
		{
			@Override
			public User execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try
				{
					//create statement to get user
					stmt = conn.prepareStatement(
							"SELECT users.* FROM users " +
							"WHERE users.username = ? AND users.email = ?");
					stmt.setString(1, username);
					stmt.setString(2, email);
					
					User result = new StandardUser();

					resultSet = stmt.executeQuery();
					Boolean found = false;
					while(resultSet.next())
					{
						found = true;
						result = loadUser(resultSet);
					}

					if(!found)
					{
						return null;
					}
					return result;
				}
				finally
				{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public User getUserByUsername(String username) {
		return executeTransaction(new Transaction<User>(){
			@Override
			public User execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try{
					//create statement to get user
					stmt = conn.prepareStatement(
							"SELECT users.* FROM users " +
							"WHERE users.username = ?");
					stmt.setString(1, username);

					User result = new StandardUser();

					resultSet = stmt.executeQuery();
					Boolean found = false;
					while(resultSet.next()){
						found = true;
						result = loadUser(resultSet);
					}

					if(!found){
						System.out.println("User not found.");
						return null;
					}
					return result;
				}
				finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public String getVerificationCodeByUsername(String username)
	{
		return executeTransaction(new Transaction<String>()
		{
			@Override
			public String execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try
				{
					stmt = conn.prepareStatement("SELECT users.verify FROM users WHERE users.username = ?");
					stmt.setString(1, username);

					resultSet = stmt.executeQuery();

					String result = null;

					if(resultSet.next())
					{
						result = resultSet.getString(1);
					}

					if(result == null)
					{
						System.out.println("Result was null.");
						return null;
					}

					return result;

				}
				finally
				{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean addUser(User user, boolean ver)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt = null;
				PreparedStatement getUserInfo = null;

				ResultSet resultSet = null;

				Boolean found = false;

				try
				{
					//first see if a user with the same username already exists
					stmt = conn.prepareStatement(
							"SELECT users.* FROM users " +
							"WHERE users.username = ?");
					stmt.setString(1, user.getUsername());
					
					resultSet = stmt.executeQuery();
					
					found = resultSet.next();
					if(!found)
					{
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet);
						
						// also check if a user is already using the supplied email
						stmt = conn.prepareStatement("SELECT users.* FROM users WHERE users.email = ?");
						stmt.setString(1, user.getEmail());

						resultSet = stmt.executeQuery();

						//if the user with the requested username does not already exist, continue
						found = resultSet.next();
						if(!found){
							
							Crypto crypto = new Crypto();
							
							DBUtil.closeQuietly(stmt);
							DBUtil.closeQuietly(resultSet);

							// Hash the password
							String hashedPassword = crypto.encrypt(user.getPassword());
							
							// now add the user if the username does not already exist
							stmt = conn.prepareStatement("INSERT INTO users (firstname, lastname, username, password, email, verify, isVerified) VALUES (?, ?, ?, ?, ?, ?, ?)");
							stmt.setString(1, user.getFirstname());
							stmt.setString(2, user.getLastname());
							stmt.setString(3, user.getUsername());
							stmt.setString(4, hashedPassword);
							stmt.setString(5, user.getEmail());
							stmt.setString(6, user.getVerificationCode());
							stmt.setBoolean(7, ver);

							//now ensure that the user was added correctly
							int result = stmt.executeUpdate();

							if(result == 1)
							{
								getUserInfo = conn.prepareStatement("SELECT users.* FROM users WHERE users.username = ? AND users.password = ?");
								getUserInfo.setString(1, user.getUsername());
								getUserInfo.setString(2, hashedPassword);

								resultSet = getUserInfo.executeQuery();
							}
							while(resultSet.next() && !found)
							{
								//if the user was found, quit out of the loop and set found to true
								found = true;
							}
						}
					}
					
				}
				finally
				{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(getUserInfo);
				}
				return found;
			}
		}
		);
	}

	@Override
	public boolean saveFractal(Fractal fractal, String name, String username){
		//make sure data isn't null
		if(fractal == null || name == null || username == null) return false;

		return executeTransaction(new Transaction<Boolean>(){
			@Override
			public Boolean execute(Connection conn) throws SQLException{
				//objects for executing the statement
				PreparedStatement stmt = null;

				ResultSet resultSet = null;

				Boolean found = false;

				int userId = -1;

				try{
					//get the user id of the user
					//statement to get the id
					stmt = conn.prepareStatement("SELECT users.user_id FROM users "
							+ " WHERE users.username = ?");
					//add in the username
					stmt.setString(1, username);

					//Execute query
					resultSet = stmt.executeQuery();

					//get the id only if one is found
					if(resultSet.next()){
						try{
							userId = Integer.parseInt(resultSet.getString(1));
						}catch(NumberFormatException e){}
					}

					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);

					//only add the fractal if the user Id was found
					if(userId != -1){

						//get data from fractal
						String[] params = fractal.getParameters();
						//insert the fractal
						stmt = conn.prepareStatement("INSERT INTO fractal "
								+ " ( "
								+ " name, type, user_id, "
								+ " param0, param1, param2, param3, param4, param5, param6, param7, param8, param9, "
								+ " gradientType, colorBaseR, colorBaseG, colorBaseB, colorEndR, colorEndG, colorEndB "
								+ " ) "
								+ " VALUES (?, ?, ?, "
								+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
								+ " ?, ?, ?, ?, ?, ?, ? "
								+ " )");
						//set attributes of statement
						stmt.setString(1, name);
						stmt.setString(2, fractal.getType());
						stmt.setInt(3, userId);
						for(int i = 0; i < 10; i++) stmt.setString(4 + i, params[i]);
						stmt.setString(14, fractal.getGradientType());
						Color c = fractal.getGradient().getBaseColor();
						stmt.setInt(15, c.getRed());
						stmt.setInt(16, c.getGreen());
						stmt.setInt(17, c.getBlue());
						c = fractal.getGradient().getSecondaryColor();
						stmt.setInt(18, c.getRed());
						stmt.setInt(19, c.getGreen());
						stmt.setInt(20, c.getBlue());

						//execute the statement
						stmt.executeUpdate();

						//close statements
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet);

						//now check if the fractal was added
						stmt = conn.prepareStatement("SELECT fractal.name FROM fractal "
								+ " where fractal.name = ? AND fractal.user_id = ?");
						stmt.setString(1, name);
						stmt.setInt(2, userId);

						resultSet = stmt.executeQuery();
						found = resultSet.next();
					}
					else return false;
				}
				//closing objects
				finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				//return if the statement was found
				return found;
			}
		});
	}

	@Override
	public boolean deleteFractal(Fractal fractal, String username){
		//make sure data isn't null
		if(fractal == null || username == null) return false;

		return executeTransaction(new Transaction<Boolean>(){
			@Override
			public Boolean execute(Connection conn) throws SQLException{
				//objects for executing the statement
				PreparedStatement stmt = null;

				ResultSet resultSet = null;

				Boolean deleted = false;

				int userId = -1;

				try{
					//get the user id of the user
					//statement to get the id
					stmt = conn.prepareStatement("SELECT users.user_id FROM users "
							+ " WHERE users.username = ?");
					//add in the username
					stmt.setString(1, username);

					//Execute query
					resultSet = stmt.executeQuery();

					//get the id only if one is found
					if(resultSet.next()){
						try{
							userId = Integer.parseInt(resultSet.getString(1));
						}catch(NumberFormatException e){}
					}

					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);

					//only add the fractal if the user Id was found
					if(userId != -1){

						//get id of fractal to remove
						int fractalId = fractal.getId();
						//insert the fractal
						stmt = conn.prepareStatement("Delete from fractal "
													+ " where fractal.fractal_id = ?");
						//set attributes of statement
						stmt.setInt(1, fractalId);

						//execute the statement
						stmt.executeUpdate();

						//close statements
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet);

						//now check if the fractal was removed
						stmt = conn.prepareStatement("SELECT fractal.* FROM fractal "
								+ " where fractal.fractal_id = ? AND fractal.user_id = ?");
						stmt.setInt(1, fractalId);
						stmt.setInt(2, userId);

						resultSet = stmt.executeQuery();
						deleted = !resultSet.next();
					}
					else return false;
				}
				//closing objects
				finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				//return if the statement was found
				return deleted;
			}
		});
	}

	@Override
	public boolean changeStateOfVerification(User username, boolean state)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try
				{
						stmt = conn.prepareStatement("UPDATE users SET isVerified = ? WHERE users.username = ?");
						stmt.setBoolean(1, state);
						stmt.setString(2, username.getUsername());

						int result = stmt.executeUpdate();
						if(result > 0)
						{
							username.setIsVerified(true);
							return true;
						}
					return false;
				}
				finally
				{
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}
	
	@Override
	public boolean changePassword(User user, String password)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				Crypto crypt = new Crypto();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try
				{
					// Hash the new password
					String hashedPass = crypt.encrypt(password);
					stmt = conn.prepareStatement("UPDATE users SET password = ? WHERE users.username = ?");
					stmt.setString(1, hashedPass);
					stmt.setString(2, user.getUsername());
					
					int result = stmt.executeUpdate();
					if(result > 0)
					{
						user.setPassword(hashedPass);
						return true;
					}
					return false;
				}
				finally
				{
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}

	@Override
	public ArrayList<Fractal> getAllFractals(){
		return executeTransaction(new Transaction<ArrayList<Fractal>>(){
				@Override
				public ArrayList<Fractal> execute(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try{
						//retrieve all fractals and populate into the list
						stmt = conn.prepareStatement("select * from fractal");
						ArrayList<Fractal> result = new ArrayList<Fractal>();

						resultSet = stmt.executeQuery();

						while(resultSet.next()){
							Fractal fractal = null;
							FractalController controller = null;
							int fractalId = -1;
							try{
								fractalId = Integer.parseInt(resultSet.getObject(1).toString());
							}catch(NumberFormatException e){}

							//only continue if the fractal id was valid
							if(fractalId != -1){
								String name = resultSet.getObject(3).toString();
								String type = resultSet.getObject(4).toString();
								String[] params = new String[MainPageServlet.NUM_PARAMS];
								for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++){
									params[i] = resultSet.getObject(5 + i).toString();
								}

								//determine which fractal should be loaded in
								fractal = Fractal.getDefaultFractal(type);

								if(fractal != null){
									controller = fractal.createApproprateController();
									fractal.setName(name);
									fractal.setId(fractalId);
									//if the parameters couldn't be added, return null
									if(!controller.acceptParameters(params)) return null;

									//set up gradient for fractal
									try{
										loadFractalGradient(fractal, resultSet);
									}catch(IllegalArgumentException e){
										return null;
									}

									result.add(fractal);
								}
							}

						}
						return result;
					}
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}

	@Override
	public ArrayList<Fractal> getAllFractalsByType(String type){
		return executeTransaction(new Transaction<ArrayList<Fractal>>(){
				@Override
				public ArrayList<Fractal> execute(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try{
						//retrieve all fractals and populate into the list
						stmt = conn.prepareStatement("select * from fractal " +
													 "where fractal.type = ?");

						stmt.setString(1, type);

						ArrayList<Fractal> result = new ArrayList<Fractal>();

						resultSet = stmt.executeQuery();

						while(resultSet.next()){
							Fractal fractal = null;
							FractalController controller = null;
							int fractalId = -1;
							try{
								fractalId = Integer.parseInt(resultSet.getObject(1).toString());
							}catch(NumberFormatException e){}

							//only continue if the fractal id was valid
							if(fractalId != -1){
								String name = resultSet.getObject(3).toString();
								String type = resultSet.getObject(4).toString();
								String[] params = new String[MainPageServlet.NUM_PARAMS];
								for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++){
									params[i] = resultSet.getObject(5 + i).toString();
								}

								//determine which fractal should be loaded in
								fractal = Fractal.getDefaultFractal(type);

								if(fractal != null){
									controller = fractal.createApproprateController();
									fractal.setName(name);
									fractal.setId(fractalId);
									//if the parameters couldn't be added, return null
									if(!controller.acceptParameters(params)) return null;


									//set up gradient for fractal
									try{
										loadFractalGradient(fractal, resultSet);
									}catch(IllegalArgumentException e){
										return null;
									}


									result.add(fractal);
								}
							}

						}
						return result;
					}
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}

	@Override
	public ArrayList<Fractal> getAllFractalsWithCharSeq(final String charSeq){
		return executeTransaction(new Transaction<ArrayList<Fractal>>(){
				@Override
				public ArrayList<Fractal> execute(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					try{
						//retrieve all fractals and populate into the list
						stmt = conn.prepareStatement("select fractal.* from fractal " +
													 "where fractal.name like ?");

						stmt.setString(1, "%" + charSeq + "%");

						ArrayList<Fractal> result = new ArrayList<Fractal>();

						resultSet = stmt.executeQuery();

						while(resultSet.next()){
							Fractal fractal = null;
							FractalController controller = null;
							int fractalId = -1;
							try{
								fractalId = Integer.parseInt(resultSet.getObject(1).toString());
							}catch(NumberFormatException e){}

							//only continue if the fractal id was valid
							if(fractalId != -1){
								String name = resultSet.getObject(3).toString();
								String type = resultSet.getObject(4).toString();
								String[] params = new String[MainPageServlet.NUM_PARAMS];
								for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++){
									params[i] = resultSet.getObject(5 + i).toString();
								}

								//determine which fractal should be loaded in

								fractal = Fractal.getDefaultFractal(type);

								if(fractal != null){
									controller = fractal.createApproprateController();
									fractal.setName(name);
									fractal.setId(fractalId);
									//if the parameters couldn't be added, return null
									if(!controller.acceptParameters(params)) return null;

									//set up gradient for fractal
									try{
										loadFractalGradient(fractal, resultSet);
									}catch(IllegalArgumentException e){
										return null;
									}
									result.add(fractal);
								}
							}

						}
						return result;
					}
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}

	@Override
	public ArrayList<Fractal> getAllFractalsByGradientType(String gradientType){
		return executeTransaction(new Transaction<ArrayList<Fractal>>(){
				@Override
				public ArrayList<Fractal> execute(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try{
						//retrieve all fractals and populate into the list
						stmt = conn.prepareStatement("select * from fractal " +
													 "where fractal.gradientType = ?");

						stmt.setString(1, gradientType);

						ArrayList<Fractal> result = new ArrayList<Fractal>();

						resultSet = stmt.executeQuery();

						while(resultSet.next()){
							Fractal fractal = null;
							FractalController controller = null;
							int fractalId = -1;
							try{
								fractalId = Integer.parseInt(resultSet.getObject(1).toString());
							}catch(NumberFormatException e){}

							//only continue if the fractal id was valid
							if(fractalId != -1){
								String name = resultSet.getObject(3).toString();
								String type = resultSet.getObject(4).toString();
								String[] params = new String[MainPageServlet.NUM_PARAMS];
								for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++){
									params[i] = resultSet.getObject(5 + i).toString();
								}

								//determine which fractal should be loaded in
								fractal = Fractal.getDefaultFractal(type);

								if(fractal != null){
									controller = fractal.createApproprateController();
									fractal.setName(name);
									fractal.setId(fractalId);
									//if the parameters couldn't be added, return null
									if(!controller.acceptParameters(params)) return null;


									//set up gradient for fractal
									try{
										loadFractalGradient(fractal, resultSet);
									}catch(IllegalArgumentException e){
										return null;
									}


									result.add(fractal);
								}
							}

						}
						return result;
					}
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}

	public ArrayList<Fractal> getAllFractalsByUsername(final String username)
	{
		return executeTransaction(new Transaction<ArrayList<Fractal>>()
		{
			@Override
			public ArrayList<Fractal> execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try
				{
					stmt = conn.prepareStatement("SELECT * FROM fractal, users WHERE fractal.user_id = users.user_id AND users.username = ?");
					stmt.setString(1, username);
					ArrayList<Fractal> result = new ArrayList<Fractal>();

					resultSet = stmt.executeQuery();

					while(resultSet.next())
					{
						Fractal fractal = null;
						FractalController controller = null;
						int fractalId = -1;
						try
						{
							fractalId = Integer.parseInt(resultSet.getObject(1).toString());
						}
						catch(NumberFormatException e){}

						//only continue if the fractal id was valid
						if(fractalId != -1)
						{
							String name = resultSet.getObject(3).toString();
							String type = resultSet.getObject(4).toString();
							String[] params = new String[MainPageServlet.NUM_PARAMS];
							for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++)
							{
								params[i] = resultSet.getObject(5 + i).toString();
							}

							//determine which fractal should be loaded in
							fractal = Fractal.getDefaultFractal(type);

							if(fractal != null)
							{
								controller = fractal.createApproprateController();
								fractal.setName(name);
								fractal.setId(fractalId);
								//if the parameters couldn't be added, return null
								if(!controller.acceptParameters(params))
								{
									return null;
								}

								//set up gradient for fractal
								try{
									loadFractalGradient(fractal, resultSet);
								}catch(IllegalArgumentException e){
									return null;
								}

								result.add(fractal);
							}
						}
					}

					return result;
				}
				finally
				{
					DBUtil.closeQuietly(resultSet);;
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Fractal getFractalById(int id) {
		return executeTransaction(new Transaction<Fractal>(){
			@Override
			public Fractal execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try{
					//create statement to get fractal
					stmt = conn.prepareStatement(
							"SELECT fractal.* FROM fractal " +
							"WHERE fractal.fractal_id = ?");
					stmt.setInt(1, id);

					Fractal result = null;
					//execute the query
					resultSet = stmt.executeQuery();

					//load the fractal if one is found
					if(resultSet.next()){
						//get info from fractal
						String name = resultSet.getObject(3).toString();
						String type = resultSet.getObject(4).toString();
						String[] params = new String[MainPageServlet.NUM_PARAMS];
						for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++){
							params[i] = resultSet.getObject(5 + i).toString();
						}

						//load the fractal
						result = Fractal.getDefaultFractal(type);

						if(result != null){
							//set fractal info
							result.setId(id);
							result.setName(name);
							FractalController controller = result.createApproprateController();
							controller.acceptParameters(params);

							//set up gradient for fractal
							try{
								loadFractalGradient(result, resultSet);
							}catch(IllegalArgumentException e){
								return null;
							}

						}
					}

					//return fractal, null if one was not found
					return result;
				}
				finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	@Override
	public Fractal getFractalByName(String name) {
		return executeTransaction(new Transaction<Fractal>(){
			@Override
			public Fractal execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try{
					//create statement to get fractal
					stmt = conn.prepareStatement(
							"SELECT fractal.* FROM fractal " +
							"WHERE fractal.name = ?");
					stmt.setString(1, name);

					Fractal result = null;
					//execute the query
					resultSet = stmt.executeQuery();

					//load the fractal if one is found
					if(resultSet.next()){
						//get info from fractal
						int id = -1;
						try {
							id = Integer.parseInt(resultSet.getObject(1).toString());
						}catch(NumberFormatException e){}
						String type = resultSet.getObject(4).toString();
						String[] params = new String[MainPageServlet.NUM_PARAMS];
						for(int i = 0; i < MainPageServlet.NUM_PARAMS; i++){
							params[i] = resultSet.getObject(5 + i).toString();
						}

						//load the fractal
						result = Fractal.getDefaultFractal(type);

						if(result != null && id != -1){
							//set fractal info
							result.setId(id);
							result.setName(name);
							FractalController controller = result.createApproprateController();
							controller.acceptParameters(params);

							//set up gradient for fractal
							try{
								loadFractalGradient(result, resultSet);
							}catch(IllegalArgumentException e){
								return null;
							}

						}
					}

					//return fractal, null if one was not found
					return result;
				}
				finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	@Override
	public String getUsernameByFractalId(int id) {
		return executeTransaction(new Transaction<String>(){
			@Override
			public String execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try{
					//create statement to get fractal
					stmt = conn.prepareStatement(
							" SELECT users.username FROM users, fractal " +
							" WHERE fractal.user_id = users.user_id " +
							" AND fractal.fractal_id = ?");
					stmt.setInt(1, id);

					String result = null;
					//execute the query
					resultSet = stmt.executeQuery();

					//load the fractal if one is found
					if(resultSet.next()){
						//get info from fractal
						result = resultSet.getObject(1).toString();
					}
					//return fractal, null if one was not found
					return result;
				}
				finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn)
	{
		try
		{
			return doExecuteTransaction(txn);
		}
		catch(SQLException e)
		{
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException
	{
		Connection conn = connect();

		try
		{
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while(!success && numAttempts < MAX_ATTEMPTS)
			{
				try
				{
					result = txn.execute(conn);
					conn.commit();
					success = true;
				}
				catch(SQLException e)
				{
					if(e.getSQLState() != null && e.getSQLState().equals("41000"))
					{
						numAttempts++;
					}
					else
					{
						throw e;
					}
				}
			}

			if(! success)
			{
				throw new SQLException("Transaction failed (too many retries)");
			}

			return result;
		}
		finally
		{
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		conn.setAutoCommit(false);;
		return conn;
	}

	private User loadUser(ResultSet resultSet) throws SQLException{
		User user;
		if(resultSet.getString(9).equals("Admin")) user = new Admin();
		else user = new StandardUser();
		
		user.setUsername(resultSet.getString(2));
		user.setFirstname(resultSet.getString(3));
		user.setLastname(resultSet.getString(4));
		user.setEmail(resultSet.getString(5));
		user.setPassword(resultSet.getString(6));
		user.setIsVerified(resultSet.getBoolean(8));
		
		return user;
	}

	/**
	 * Load in a fractal gradient based on the given result set, the result set must be from a select fractal.* call
	 * @param resultSet
	 * @throws SQLException, IllegalArgumentException
	 */
	private void loadFractalGradient(Fractal f, ResultSet resultSet) throws SQLException, IllegalArgumentException{
		//load in gradient
		String gradientType = resultSet.getObject(15).toString();
		Color baseColor = new Color(0);
		Color endColor = new Color(0);

		//load the colors in
		baseColor = new Color(
				Integer.parseInt(resultSet.getObject(16).toString()),
				Integer.parseInt(resultSet.getObject(17).toString()),
				Integer.parseInt(resultSet.getObject(18).toString())
		);
		endColor = new Color(
				Integer.parseInt(resultSet.getObject(19).toString()),
				Integer.parseInt(resultSet.getObject(20).toString()),
				Integer.parseInt(resultSet.getObject(21).toString())
		);

		//create the gradient
		Gradient gradient = new Gradient();
		gradient.setBaseColor(baseColor);
		gradient.setSecondaryColor(endColor);
		f.setGradient(gradient);
		f.setGradientType(gradientType);
	}

	public void createTables()
	{
		executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt = null;
				try
				{
					// Password varchar must be significantly long in order to contain the password hash.
					stmt = conn.prepareStatement("create table users ("
							+ " user_id integer primary key "
							+ " generated always as identity (start with 1, increment by 1), "
							+ " username varchar(40), "
							+ " firstname varchar(40), "
							+ " lastname varchar(40), "
							+ " email varchar(40), "
							+ " password varchar(2000), "
							+ " verify varchar(20), "
							+ " isVerified boolean"
							+ " type varchar(40) "
							+ ")"
							);
					int result = stmt.executeUpdate();
					boolean success = result > 0;

					DBUtil.closeQuietly(stmt);

					stmt = conn.prepareStatement("create table fractal ("
							+ " fractal_id integer primary key "
							+ " generated always as identity (start with 1, increment by 1), "
							+ " user_id integer constraint user_id references users, "
							+ " name varchar(40), "
							+ " type varchar(40), "
							+ " param0 varchar(40), "
							+ " param1 varchar(40), "
							+ " param2 varchar(40), "
							+ " param3 varchar(40), "
							+ " param4 varchar(40), "
							+ " param5 varchar(40), "
							+ " param6 varchar(40), "
							+ " param7 varchar(40), "
							+ " param8 varchar(40), "
							+ " param9 varchar(40), "
							+ " gradientType varchar(40), "
							+ " colorBaseR integer, "
							+ " colorBaseG integer, "
							+ " colorBaseB integer, "
							+ " colorEndR integer, "
							+ " colorEndG integer, "
							+ " colorEndB integer "
							+ ")"
							);

					result = stmt.executeUpdate();
					success = result > 0;

					return success;
				}
				finally
				{
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public void loadInitialData()
	{
		executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				List<User> userList;

				try
				{
					userList = InitialData.getUsers();
				}
				catch(IOException e)
				{
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;

				try
				{
					insertUser = conn.prepareStatement("INSERT INTO users (username, firstname, lastname, email, password) values (?, ?, ?, ?, ?)");
					for(User user : userList)
					{
						insertUser.setString(1,  user.getUsername());
						insertUser.setString(2, user.getFirstname());
						insertUser.setString(3, user.getLastname());
						insertUser.setString(4, user.getEmail());
						insertUser.setString(5, user.getPassword());
					}
					insertUser.executeBatch();
					return true;
				}
				finally
				{
					DBUtil.closeQuietly(insertUser);
				}
			}
		});
	}

	public static void main(String[] args) throws IOException
	{
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();

		System.out.println("Loading initial data...");
		db.loadInitialData();

		System.out.println("Success!");
	}

}
