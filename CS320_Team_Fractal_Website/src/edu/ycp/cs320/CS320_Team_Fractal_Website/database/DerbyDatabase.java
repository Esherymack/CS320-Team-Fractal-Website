package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

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
	public ArrayList<User> getAccounts() 
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
						
						while(resultSet.next())
						{
							String username = resultSet.getObject(1).toString();
							String firstname = resultSet.getObject(2).toString();
							String lastname = resultSet.getObject(3).toString();
							String email = resultSet.getObject(4).toString();
							String password = resultSet.getObject(5).toString();
							User user = new StandardUser(username, firstname, lastname, email, password);
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
						loadUser(result, resultSet, 1);
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
							loadUser(userAdded, resultSet, 1);
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
	
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException
	{
		user.setUsername(resultSet.getString(index++));
		user.setFirstname(resultSet.getString(index++));
		user.setLastname(resultSet.getString(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
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
					if(result > 0)
					{
						return true;
					}
					else
					{
						return false;
					}
					
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
