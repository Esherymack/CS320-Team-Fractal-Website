import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;

public interface IDatabase{
	
	/**
	 * Get a list of all of the accounts in the database
	 * @return the list of accounts
	 */
	public ArrayList<Account> getAccounts();
	
	/**
	 * Add a new user account to the database
	 * @param username the name of the new account
	 * @param password the password of the new account
	 * @return true if the account was added successfully, false otherwise
	 */
	public boolean addUser(Account account);
}
