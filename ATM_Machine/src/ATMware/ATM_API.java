package ATMware;

class ATM_API
{
	private Account currentAccount;
	private String currentAccountNumber;
	private String currentPin;
	private double currentCashInATM;

	public ATM_API(double startingCash)
	{
		// Self explanatory.
		currentCashInATM = startingCash;
	}
	
	public Boolean atmOperational()
	{
		return currentCashInATM > 0;
	}
	
	public void enterAccountNumber(String accountNumber)
	{
		currentAccountNumber = accountNumber;
	}
	
	public void enterPin(String Pin)
	{
		currentPin = Pin;
	}
	
	public Boolean loginToAccount()
	{
		currentAccount = new Account(currentAccountNumber, currentPin);
		return currentAccount.verifyAccount();
	}

	public Boolean getMoney(double amount)
	{
		depositeMoneyToDrawer(amount);
		return currentAccount.withdrawaCash(amount);
	}
	
	public void depositMoney(double amount)
	{
		amount /= 100;
		currentAccount.depositCash(amount);
	}

	public void setAccountType(Account.AccountType type)
	{
		currentAccount.setAccountType(type);
	}
	
	public String getBalence()
	{
		return "$" + currentAccount.getAccountBalence();
	}
	
	private void depositeMoneyToDrawer(double amount)
	{
		openDrawer();
		System.out.println("Debug: Deposited " + amount + "to drawer.");
	}
	
	public void openDrawer()
	{
		System.out.println("Debug: Drawer Opened.");
	}
	
	public void closeDrawer()
	{
		System.out.println("Debug: Drawer closed.");
	}
}
