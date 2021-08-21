package ATMware;

import java.util.ArrayList;
import java.util.List;

import ATMware.Account.AccountType;

class Simulate_Account_DB 
{
	private List<Simulate_Account_Row> Accounts = new ArrayList<Simulate_Account_Row>();
	
	/**
	 * This is a simulation of a populated DB object that has a API to deal with SQL querys.
	 */
	public Simulate_Account_DB()
	{
		// Steve.
		Accounts.add(new Simulate_Account_Row("12345", "77777", 1000.10, 69420.28));
		
		// Dave.
		Accounts.add(new Simulate_Account_Row("54321", "69420", 20.47, 0));
	}

	// Simulates a select statement to verify an account exists.
	public Boolean selectFromAccountVerify(String AccountNumber, String PIN)
	{
		for(int i = 0; i < Accounts.size(); i++)
		{
			if(Accounts.get(i).ACCOUNT_NUMBER.equals(AccountNumber) && Accounts.get(i).PIN.equals(PIN))
			{
				return true;
			}
		}
		
		return false;
	}
	
	// Simulates a select from statement that will get an accounts balance.
	public double selectFromBalence(String AccountNumber, String PIN, Account.AccountType Type)
	{
		for(int i = 0; i < Accounts.size(); i++)
		{
			if(Accounts.get(i).ACCOUNT_NUMBER.equals(AccountNumber) && Accounts.get(i).PIN.equals(PIN))
			{
				if(Type == Account.AccountType.checkings)
				{
					return Accounts.get(i).CHECKINGS_BALENCE;
				}
				
				else if(Type == Account.AccountType.savings)
				{
					return Accounts.get(i).SAVINGS_BALENCE;
				}
			}
		}
		
		return -1;
	}
	
	// Simulates a update values where statement for money deposits.
	public void updateValuesWhereDeposite(String AccountNumber, String PIN, double Amount, Account.AccountType Type)
	{
		for(int i = 0; i < Accounts.size(); i++)
		{
			if(Accounts.get(i).ACCOUNT_NUMBER.equals(AccountNumber) && Accounts.get(i).PIN.equals(PIN))
			{
				if(Type == Account.AccountType.checkings)
				{
					Accounts.get(i).CHECKINGS_BALENCE += Amount;
				}
				
				else if(Type == Account.AccountType.savings)
				{
					Accounts.get(i).SAVINGS_BALENCE += Amount;
				}
			}
		}
	}
	
	// Simulates update values where for money retrieval.
	public Boolean updateValuesWhereRetrieval(String AccountNumber, String PIN, double Amount, Account.AccountType Type)
	{
		for(int i = 0; i < Accounts.size(); i++)
		{
			if(Accounts.get(i).ACCOUNT_NUMBER.equals(AccountNumber) && Accounts.get(i).PIN.equals(PIN))
			{
				if(Type == Account.AccountType.checkings)
				{
					if(Accounts.get(i).CHECKINGS_BALENCE >= Amount)
					{
						Accounts.get(i).CHECKINGS_BALENCE -= Amount;
						return true;
					}
					
				}
				
				else if(Type == Account.AccountType.savings)
				{
					if(Accounts.get(i).SAVINGS_BALENCE >= Amount)
					{
						Accounts.get(i).SAVINGS_BALENCE -= Amount;
						return true;
					}
					
				}
			}
		}
		
		return false;
	}
}
