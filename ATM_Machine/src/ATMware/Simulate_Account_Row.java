package ATMware;

class Simulate_Account_Row 
{
	public String ACCOUNT_NUMBER;
	public String PIN;
	public double CHECKINGS_BALENCE;
	public double SAVINGS_BALENCE;
	
	public Simulate_Account_Row(String account_number, String pin, double checkings_balence, double savings_balence) 
	{
		ACCOUNT_NUMBER = account_number;
		PIN = pin;
		CHECKINGS_BALENCE = checkings_balence;
		SAVINGS_BALENCE = savings_balence;
	}
}
