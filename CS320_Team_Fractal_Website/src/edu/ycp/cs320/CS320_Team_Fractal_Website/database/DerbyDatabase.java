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
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;

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
	
	// TODO: Create functions in this space that are implemented by IDatabase
	
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

	@Override
	public ArrayList<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
