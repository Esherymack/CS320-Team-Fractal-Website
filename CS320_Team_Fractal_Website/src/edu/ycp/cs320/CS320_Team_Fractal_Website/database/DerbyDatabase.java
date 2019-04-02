package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.User;

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
						stmt = conn.prepareStatement("SELECT * FROM users");
						ArrayList<User> result = new ArrayList<User>();
						
						resultSet = stmt.executeQuery();
						
						while(resultSet.next())
						{
							// create new User object
							User user = new User();
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
	public User getAccountByUsernamePassword(final String username, final String password) 
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
					stmt = conn.prepareStatement("SELECT users.* FROM users WHERE users.username = ? AND users.password = ?");
					stmt.setString(1, username);
					stmt.setString(2, password);
					
					User result = new User();
					
					resultSet = stmt.executeQuery();
					
					Boolean found = false;
					while(resultSet.next())
					{
						found = true;
						loadUser(result, resultSet, 1);
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
	public boolean addUser(User user) 
	{
		// TODO Auto-generated method stub
		return false;
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
		user.setUserId(resultSet.getInt(index++));
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
					stmt = conn.prepareStatement("CREATE TABLE users (user_id integer primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), username VARCHAR(40), firstname VARCHAR(40), lastname VARCHAR(40), email VARCHAR(40), password VARCHAR(40))");
					stmt.executeUpdate();
					
					return true;
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
