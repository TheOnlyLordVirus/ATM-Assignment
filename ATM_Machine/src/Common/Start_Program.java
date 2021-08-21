package Common;

import ATMware.ATM_Window;

public class Start_Program 
{
	/**
	 * This is how the ATM program would be started with any program or software.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Debug Info: This is far from finished and key inputs need to be polished to correctly represent the ATM keys.");
		System.out.println("Debug Info: This just needs to 'function.'");
		System.out.println("Debug Info: The simulated database is reset everytime an account is logged in, this wouldn't represent real life becuase the row values wouldn't be reset in the DB constructor.");
		System.out.println("Account Numbers: 12345 Pin: 77777, 54321 Pin: 69420");
		ATM_Window.start();
	}
}
