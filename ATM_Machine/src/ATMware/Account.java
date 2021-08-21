package ATMware;

class Account 
{
	/**
	 * Variables
	 */
	private String accountNumber;
	private String pin;
	private AccountType currentAccount;
	private Simulate_Account_DB DB = new Simulate_Account_DB();
	
	public enum AccountType
	{
		checkings,
		savings
	}
	
	public Account(String AccountNumber, String PIN)
	{
		accountNumber = AccountNumber;
		pin = PIN;
	}
	
	/**
	 * Connects the the DB. If we fail; we let garbage collection do its job.
	 * @return
	 */
	public Boolean verifyAccount()
	{
		return DB.selectFromAccountVerify(accountNumber, pin);
	}
	
	public void depositCash(double amount)
	{
		DB.updateValuesWhereDeposite(accountNumber, pin, amount, currentAccount);
	}
	
	
	public Boolean withdrawaCash(double amount)
	{
		return DB.updateValuesWhereRetrieval(accountNumber, pin, amount, currentAccount);
	}
	
	
	public double getAccountBalence()
	{
		return DB.selectFromBalence(accountNumber, pin, currentAccount);
	}
	

	public void setAccountType(AccountType type)
	{
		currentAccount = type;
	}
	
}
