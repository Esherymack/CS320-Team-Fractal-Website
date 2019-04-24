package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
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
							User user = new StandardUser();
							loadUser(user, resultSet);
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
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try
				{
					//create statement to get user
					stmt = conn.prepareStatement(
							"SELECT users.* FROM users " +
							"WHERE users.username = ? AND users.password = ?");
					stmt.setString(1, username);
					stmt.setString(2, password);
					
					User result = new StandardUser();
					
					resultSet = stmt.executeQuery();
					Boolean found = false;
					while(resultSet.next())
					{
						found = true;
						loadUser(result, resultSet);
					}
					
					if(!found)
					{	
						System.out.println("User not found.");
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
						loadUser(result, resultSet);
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
	public boolean addUser(User user) 
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
					
					//if the user with the requested username does not already exist, continue
					found = resultSet.next();
					if(!found){
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet);
						
						// now add the user if the username does not already exist
						stmt = conn.prepareStatement("INSERT INTO users (firstname, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)");
						stmt.setString(1, user.getFirstname());
						stmt.setString(2, user.getLastname());
						stmt.setString(3, user.getUsername());
						stmt.setString(4, user.getPassword());
						stmt.setString(5, user.getEmail());
						
						//now ensure that the user was added correctly
						int result = stmt.executeUpdate();
						
						if(result == 1)
						{
							getUserInfo = conn.prepareStatement("SELECT users.* FROM users WHERE users.username = ? AND users.password = ?");
							getUserInfo.setString(1, user.getUsername());
							getUserInfo.setString(2, user.getPassword());
							
							resultSet = getUserInfo.executeQuery();
						}
						User userAdded = new StandardUser();
						
						while(resultSet.next() && !found) 
						{
							//if the user was found, quit out of the loop and set found to true
							found = true;
							loadUser(userAdded, resultSet);
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
								+ " (name, type, user_id, param0, param1, param2, param3, param4, param5, param6, param7, param8, param9) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						//set attributes of statement
						stmt.setString(1, name);
						stmt.setString(2, fractal.getType());
						stmt.setInt(3, userId);
						for(int i = 0; i < 10; i++) stmt.setString(4 + i, params[i]);
						
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
						stmt = conn.prepareStatement("select fractal.* from fractal " +
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
	public ArrayList<Fractal> getAllFractalsByName(String name){
		return executeTransaction(new Transaction<ArrayList<Fractal>>(){
				@Override
				public ArrayList<Fractal> execute(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try{
						//retrieve all fractals and populate into the list
						stmt = conn.prepareStatement("select fractal.* from fractal " +
													 "where fractal.name = ?");
						
						stmt.setString(1, name);
						
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
	public ArrayList<Fractal> getAllFractalsWithCharSeq(String charSeq){
		return executeTransaction(new Transaction<ArrayList<Fractal>>(){
				@Override
				public ArrayList<Fractal> execute(Connection conn) throws SQLException{
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try{
						//retrieve all fractals and populate into the list
						stmt = conn.prepareStatement("select fractal.* from fractal " +
													 "where fractal.name = ?");
						//where fractal.name = ?
						//where fractal.name like '%?%'
						
						stmt.setString(1, charSeq);
						
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
	
	private void loadUser(User user, ResultSet resultSet) throws SQLException
	{
		user.setUsername(resultSet.getString(2));
		user.setFirstname(resultSet.getString(3));
		user.setLastname(resultSet.getString(4));
		user.setEmail(resultSet.getString(5));
		user.setPassword(resultSet.getString(6));
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
					stmt = conn.prepareStatement("create table users ("
							+ " user_id integer primary key "
							+ " generated always as identity (start with 1, increment by 1), "
							+ " username varchar(40), "
							+ " firstname varchar(40), "
							+ " lastname varchar(40), "
							+ " email varchar(40), "
							+ " password varchar(40)"
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
							+ " param9 varchar(40)"
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
