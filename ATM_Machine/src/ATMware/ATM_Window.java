package ATMware;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class ATM_Window extends JFrame 
{
	private enum ATMWindowState
	{
		WelcomeEnterAccount,
		EnterPin,
		InvalidInfo,
		ChooseAccount,
		MainMenu,
		ShowBalence,
		WithdrawalMoney,
		DepositeMoney,
		InsuficiantFunds,
		DepositeTimeout,
		ThankYou,
		AtmMantaince
	}
	
	// Variables.
	private static ATMWindowState currentWindowState = ATMWindowState.WelcomeEnterAccount;
	private static ATM_API atmAPI = new ATM_API(10000);
	private static Boolean cancelTimeout = true;
	private static JPanel contentPane;
	private static JPanel WelcomeAccountPanel;
	private static JPanel EnterPinPanel;
	private static JPanel PinFailurePanel;
	private static JPanel ChooseAccountPanel;
	private static JPanel MainMenuPanel;
	private static JPanel CheckBalencePanel;
	private static JPanel WithdrawalMoneyPanel;
	private static JPanel DepositeMoneyPanel;
	private static JPanel DepositeTimeoutPanel;
	private static JPanel InsufficiantFundsPanel;
	private static JPanel ThankYouPanel;
	private static JLabel EnterAccountLabel;
	private static JPanel PleaseTakeMoneyPanel;
	private static JPanel AtmMantaince;
	private static JLayeredPane layeredPane;
	private static JPasswordField enterAccountNumber;
	private static JPasswordField enterPin;
	private static JTextField depositeMoneyTxt;
	private static JLabel EnterPinFeild;
	private static JLabel WelcomeLabel;
	private static JLabel PinFailedLabel;
	private static JLabel PleaseTakeMoneyLabel;
	private static JLabel Option1Label;
	private static JLabel Option2Label;
	private static JLabel Option3Label;
	private static JLabel Option4Label;
	private static JLabel Option5Label;
	private static JLabel SavingsLabel;
	private static JLabel CheckingsLabel;
	private static JLabel ExitLabel;
	private static JLabel ChooseAccountLabel;
	private static JLabel dbgOKlabel;
	private static JLabel dbgOKLabel2;
	private static JLabel YourBalanceLabel;
	private static JLabel TwentyLabel;
	private static JLabel FortyLabel;
	private static JLabel SixtyLabel;
	private static JLabel OneHundredLabel;
	private static JLabel TwoHundredLabel;
	private static JLabel CancelTransaction;
	private static JLabel lblNewLabel;
	private static JLabel label;
	private static JLabel DisplayBalenceLabel;
	private static JLabel TimeoutLabel;
	private static JLabel InsufficiantFundsLabel;
	private static JLabel ThankYouLabel;
	private static JLabel MaintenanceLabel;
	private static GroupLayout gl_WelcomeAccountPanel;
	private static GroupLayout gl_EnterPinPanel;
	private static GroupLayout gl_PinFailurePanel;
	private static GroupLayout gl_ChooseAccountPanel;
	private static GroupLayout gl_MainMenuPanel;
	private static GroupLayout gl_CheckBalencePanel;
	private static GroupLayout gl_WithdrawalMoneyPanel;
	private static GroupLayout gl_DepositeMoneyPanel;
	private static GroupLayout gl_DepositeTimeoutPanel;
	private static GroupLayout gl_PleaseTakeMoneyPanel;
	private static GroupLayout gl_ThankYouPanel;
	private static GroupLayout gl_InsufficiantFundsPanel;
	private static GroupLayout gl_AtmMantaince;

	
	/**
	 * Create the frame.
	 */
	private ATM_Window() 
	{
		initialize();
		createEvents();
	}
	
	/**
	 * Launch the application.
	 */
	public static void start() 
	{
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} 
		
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					ATM_Window frame = new ATM_Window();
					frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initializes components.
	 */
	private void initialize() 
	{
		// Main Jpanel window
		setTitle("ATMware Alpha 0.001");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_447950875915000");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		WelcomeAccountPanel = new JPanel();
		layeredPane.add(WelcomeAccountPanel, "name_448111353294900");
		
		WelcomeLabel = new JLabel("Welcome!");
		WelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		
		enterAccountNumber = new JPasswordField();
		enterAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		EnterAccountLabel = new JLabel("Enter Account Number:");
		EnterAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		
		dbgOKlabel = new JLabel("(Pressing enter simulates the ATM 'OK' button)");
		dbgOKlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dbgOKlabel.setForeground(Color.RED);
		gl_WelcomeAccountPanel = new GroupLayout(WelcomeAccountPanel);
		gl_WelcomeAccountPanel.setHorizontalGroup(
			gl_WelcomeAccountPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_WelcomeAccountPanel.createSequentialGroup()
					.addGap(283)
					.addComponent(WelcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(265))
				.addGroup(gl_WelcomeAccountPanel.createSequentialGroup()
					.addGap(106)
					.addComponent(EnterAccountLabel, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addGap(66))
				.addGroup(gl_WelcomeAccountPanel.createSequentialGroup()
					.addGap(201)
					.addComponent(enterAccountNumber, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
					.addGap(183))
				.addGroup(gl_WelcomeAccountPanel.createSequentialGroup()
					.addGap(274)
					.addComponent(dbgOKlabel, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addGap(261))
		);
		gl_WelcomeAccountPanel.setVerticalGroup(
			gl_WelcomeAccountPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_WelcomeAccountPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(WelcomeLabel)
					.addGap(18)
					.addComponent(EnterAccountLabel)
					.addGap(18)
					.addComponent(enterAccountNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(dbgOKlabel)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		WelcomeAccountPanel.setLayout(gl_WelcomeAccountPanel);
		
		EnterPinPanel = new JPanel();
		layeredPane.add(EnterPinPanel, "name_448169332301500");
		
		EnterPinFeild = new JLabel("Enter Pin:");
		EnterPinFeild.setFont(new Font("Tahoma", Font.PLAIN, 60));
		
		enterPin = new JPasswordField();
		enterPin.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		dbgOKLabel2 = new JLabel("(Pressing enter simulates the ATM 'OK' button)");
		dbgOKLabel2.setForeground(Color.RED);
		dbgOKLabel2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gl_EnterPinPanel = new GroupLayout(EnterPinPanel);
		gl_EnterPinPanel.setHorizontalGroup(
			gl_EnterPinPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_EnterPinPanel.createSequentialGroup()
					.addGap(265)
					.addGroup(gl_EnterPinPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_EnterPinPanel.createSequentialGroup()
							.addComponent(dbgOKLabel2, GroupLayout.PREFERRED_SIZE, 17, Short.MAX_VALUE)
							.addGap(269))
						.addGroup(gl_EnterPinPanel.createSequentialGroup()
							.addGroup(gl_EnterPinPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(enterPin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
								.addComponent(EnterPinFeild, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
							.addGap(269))))
		);
		gl_EnterPinPanel.setVerticalGroup(
			gl_EnterPinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EnterPinPanel.createSequentialGroup()
					.addGap(71)
					.addComponent(EnterPinFeild)
					.addGap(18)
					.addComponent(enterPin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(dbgOKLabel2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		EnterPinPanel.setLayout(gl_EnterPinPanel);
		
		PinFailurePanel = new JPanel();
		layeredPane.add(PinFailurePanel, "name_448174248064700");
		
		PinFailedLabel = new JLabel("Pin Failed!");
		PinFailedLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		gl_PinFailurePanel = new GroupLayout(PinFailurePanel);
		gl_PinFailurePanel.setHorizontalGroup(
			gl_PinFailurePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_PinFailurePanel.createSequentialGroup()
					.addGap(241)
					.addComponent(PinFailedLabel, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
					.addGap(237))
		);
		gl_PinFailurePanel.setVerticalGroup(
			gl_PinFailurePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PinFailurePanel.createSequentialGroup()
					.addGap(88)
					.addComponent(PinFailedLabel)
					.addContainerGap(175, Short.MAX_VALUE))
		);
		PinFailurePanel.setLayout(gl_PinFailurePanel);
		
		ChooseAccountPanel = new JPanel();
		layeredPane.add(ChooseAccountPanel, "name_448275599700700");
		
		SavingsLabel = new JLabel("Savings (1)");
		SavingsLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		CheckingsLabel = new JLabel("Checkings (2)");
		CheckingsLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		ExitLabel = new JLabel("Exit (3)");
		ExitLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		ChooseAccountLabel = new JLabel("Choose Account:");
		ChooseAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		gl_ChooseAccountPanel = new GroupLayout(ChooseAccountPanel);
		gl_ChooseAccountPanel.setHorizontalGroup(
			gl_ChooseAccountPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_ChooseAccountPanel.createSequentialGroup()
					.addGap(191)
					.addComponent(ChooseAccountLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(174))
				.addGroup(Alignment.TRAILING, gl_ChooseAccountPanel.createSequentialGroup()
					.addGap(264)
					.addGroup(gl_ChooseAccountPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(SavingsLabel, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_ChooseAccountPanel.createSequentialGroup()
							.addComponent(CheckingsLabel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE))
						.addGroup(gl_ChooseAccountPanel.createSequentialGroup()
							.addComponent(ExitLabel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)))
					.addGap(217))
		);
		gl_ChooseAccountPanel.setVerticalGroup(
			gl_ChooseAccountPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChooseAccountPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(ChooseAccountLabel)
					.addGap(41)
					.addComponent(SavingsLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(CheckingsLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(ExitLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		ChooseAccountPanel.setLayout(gl_ChooseAccountPanel);
		
		MainMenuPanel = new JPanel();
		layeredPane.add(MainMenuPanel, "name_448297316229600");
		
		Option1Label = new JLabel("Balence Inqury (1)");
		Option1Label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		Option2Label = new JLabel("Withdrawal (2)");
		Option2Label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		Option3Label = new JLabel("Deposit (3)");
		Option3Label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		Option4Label = new JLabel("Change Account (4)");
		Option4Label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		Option5Label = new JLabel("Exit (5)");
		Option5Label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		gl_MainMenuPanel = new GroupLayout(MainMenuPanel);
		gl_MainMenuPanel.setHorizontalGroup(
			gl_MainMenuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_MainMenuPanel.createSequentialGroup()
					.addGap(237)
					.addGroup(gl_MainMenuPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MainMenuPanel.createSequentialGroup()
							.addComponent(Option5Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(220))
						.addComponent(Option4Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_MainMenuPanel.createSequentialGroup()
							.addComponent(Option1Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(20))
						.addGroup(gl_MainMenuPanel.createSequentialGroup()
							.addComponent(Option2Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(87))
						.addGroup(gl_MainMenuPanel.createSequentialGroup()
							.addComponent(Option3Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(152)))
					.addGap(224))
		);
		gl_MainMenuPanel.setVerticalGroup(
			gl_MainMenuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainMenuPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(Option1Label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Option2Label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Option3Label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Option4Label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Option5Label)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		MainMenuPanel.setLayout(gl_MainMenuPanel);
		
		CheckBalencePanel = new JPanel();
		layeredPane.add(CheckBalencePanel, "name_448313915928800");
		
		YourBalanceLabel = new JLabel("Your balence is:");
		YourBalanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		
		DisplayBalenceLabel = new JLabel("");
		DisplayBalenceLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gl_CheckBalencePanel = new GroupLayout(CheckBalencePanel);
		gl_CheckBalencePanel.setHorizontalGroup(
			gl_CheckBalencePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CheckBalencePanel.createSequentialGroup()
					.addGap(198)
					.addComponent(YourBalanceLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(185))
				.addGroup(gl_CheckBalencePanel.createSequentialGroup()
					.addContainerGap(422, Short.MAX_VALUE)
					.addComponent(DisplayBalenceLabel)
					.addGap(342))
		);
		gl_CheckBalencePanel.setVerticalGroup(
			gl_CheckBalencePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CheckBalencePanel.createSequentialGroup()
					.addGap(48)
					.addComponent(YourBalanceLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(DisplayBalenceLabel)
					.addContainerGap(179, Short.MAX_VALUE))
		);
		CheckBalencePanel.setLayout(gl_CheckBalencePanel);
		
		WithdrawalMoneyPanel = new JPanel();
		layeredPane.add(WithdrawalMoneyPanel, "name_448433791803200");
		
		TwoHundredLabel = new JLabel("$200 (5)");
		TwoHundredLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		OneHundredLabel = new JLabel("$100 (4)");
		OneHundredLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		TwentyLabel = new JLabel("$20 (1)");
		TwentyLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		FortyLabel = new JLabel("$40 (2)");
		FortyLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		SixtyLabel = new JLabel("$60 (3)");
		SixtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		CancelTransaction = new JLabel("Cancel (6)");
		CancelTransaction.setFont(new Font("Tahoma", Font.PLAIN, 40));
		gl_WithdrawalMoneyPanel = new GroupLayout(WithdrawalMoneyPanel);
		gl_WithdrawalMoneyPanel.setHorizontalGroup(
			gl_WithdrawalMoneyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_WithdrawalMoneyPanel.createSequentialGroup()
					.addContainerGap(301, Short.MAX_VALUE)
					.addGroup(gl_WithdrawalMoneyPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(CancelTransaction, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
						.addComponent(TwentyLabel, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addComponent(FortyLabel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
						.addComponent(SixtyLabel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(OneHundredLabel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addComponent(TwoHundredLabel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
					.addGap(160))
		);
		gl_WithdrawalMoneyPanel.setVerticalGroup(
			gl_WithdrawalMoneyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_WithdrawalMoneyPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(TwentyLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(FortyLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(SixtyLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(OneHundredLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TwoHundredLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(CancelTransaction, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		WithdrawalMoneyPanel.setLayout(gl_WithdrawalMoneyPanel);
		
		DepositeMoneyPanel = new JPanel();
		layeredPane.add(DepositeMoneyPanel, "name_448490432129400");
		
		depositeMoneyTxt = new JTextField();
		depositeMoneyTxt.setFont(new Font("Tahoma", Font.PLAIN, 50));
		depositeMoneyTxt.setColumns(10);
		
		lblNewLabel = new JLabel("Enter Deposite Amount (In cents)");
		
		label = new JLabel("(Pressing enter simulates the ATM 'OK' button)");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gl_DepositeMoneyPanel = new GroupLayout(DepositeMoneyPanel);
		gl_DepositeMoneyPanel.setHorizontalGroup(
			gl_DepositeMoneyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DepositeMoneyPanel.createSequentialGroup()
					.addGap(270)
					.addGroup(gl_DepositeMoneyPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
						.addComponent(depositeMoneyTxt, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(264, Short.MAX_VALUE))
		);
		gl_DepositeMoneyPanel.setVerticalGroup(
			gl_DepositeMoneyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DepositeMoneyPanel.createSequentialGroup()
					.addGap(90)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(depositeMoneyTxt, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(145, Short.MAX_VALUE))
		);
		DepositeMoneyPanel.setLayout(gl_DepositeMoneyPanel);
		
		DepositeTimeoutPanel = new JPanel();
		layeredPane.add(DepositeTimeoutPanel, "name_448571185760500");
		
		TimeoutLabel = new JLabel("Timeout: You took longer than two minutes to deposite your money");
		TimeoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		gl_DepositeTimeoutPanel = new GroupLayout(DepositeTimeoutPanel);
		gl_DepositeTimeoutPanel.setHorizontalGroup(
			gl_DepositeTimeoutPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_DepositeTimeoutPanel.createSequentialGroup()
					.addGap(32)
					.addComponent(TimeoutLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(23))
		);
		gl_DepositeTimeoutPanel.setVerticalGroup(
			gl_DepositeTimeoutPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DepositeTimeoutPanel.createSequentialGroup()
					.addGap(146)
					.addComponent(TimeoutLabel)
					.addContainerGap(171, Short.MAX_VALUE))
		);
		DepositeTimeoutPanel.setLayout(gl_DepositeTimeoutPanel);
		
		InsufficiantFundsPanel = new JPanel();
		layeredPane.add(InsufficiantFundsPanel, "name_448586930020700");
		
		InsufficiantFundsLabel = new JLabel("Insufficiant Funds");
		InsufficiantFundsLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		gl_InsufficiantFundsPanel = new GroupLayout(InsufficiantFundsPanel);
		gl_InsufficiantFundsPanel.setHorizontalGroup(
			gl_InsufficiantFundsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_InsufficiantFundsPanel.createSequentialGroup()
					.addContainerGap(255, Short.MAX_VALUE)
					.addComponent(InsufficiantFundsLabel)
					.addGap(242))
		);
		gl_InsufficiantFundsPanel.setVerticalGroup(
			gl_InsufficiantFundsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_InsufficiantFundsPanel.createSequentialGroup()
					.addGap(131)
					.addComponent(InsufficiantFundsLabel)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		InsufficiantFundsPanel.setLayout(gl_InsufficiantFundsPanel);
		
		ThankYouPanel = new JPanel();
		layeredPane.add(ThankYouPanel, "name_448617390347600");
		
		ThankYouLabel = new JLabel("Thank you for choosing YOUR_BANK");
		ThankYouLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		gl_ThankYouPanel = new GroupLayout(ThankYouPanel);
		gl_ThankYouPanel.setHorizontalGroup(
			gl_ThankYouPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ThankYouPanel.createSequentialGroup()
					.addGap(79)
					.addComponent(ThankYouLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(80))
		);
		gl_ThankYouPanel.setVerticalGroup(
			gl_ThankYouPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ThankYouPanel.createSequentialGroup()
					.addGap(136)
					.addComponent(ThankYouLabel)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		ThankYouPanel.setLayout(gl_ThankYouPanel);
		
		PleaseTakeMoneyPanel = new JPanel();
		layeredPane.add(PleaseTakeMoneyPanel, "name_464535923857700");
		
		PleaseTakeMoneyLabel = new JLabel("Please Take Your Money");
		PleaseTakeMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		gl_PleaseTakeMoneyPanel = new GroupLayout(PleaseTakeMoneyPanel);
		gl_PleaseTakeMoneyPanel.setHorizontalGroup(
			gl_PleaseTakeMoneyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_PleaseTakeMoneyPanel.createSequentialGroup()
					.addGap(79)
					.addComponent(PleaseTakeMoneyLabel, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
					.addGap(36))
		);
		gl_PleaseTakeMoneyPanel.setVerticalGroup(
			gl_PleaseTakeMoneyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PleaseTakeMoneyPanel.createSequentialGroup()
					.addGap(124)
					.addComponent(PleaseTakeMoneyLabel)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		PleaseTakeMoneyPanel.setLayout(gl_PleaseTakeMoneyPanel);
		
		AtmMantaince = new JPanel();
		AtmMantaince.setFont(new Font("Tahoma", Font.PLAIN, 50));
		layeredPane.add(AtmMantaince, "name_124309303005300");
		
		MaintenanceLabel = new JLabel("ATM requires maintenance");
		MaintenanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		gl_AtmMantaince = new GroupLayout(AtmMantaince);
		gl_AtmMantaince.setHorizontalGroup(
			gl_AtmMantaince.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_AtmMantaince.createSequentialGroup()
					.addGap(127)
					.addComponent(MaintenanceLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(95))
		);
		gl_AtmMantaince.setVerticalGroup(
			gl_AtmMantaince.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AtmMantaince.createSequentialGroup()
					.addGap(134)
					.addComponent(MaintenanceLabel)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		AtmMantaince.setLayout(gl_AtmMantaince);
	}
	
	/**
	 * Initializes and creates events.
	 */
	private void createEvents() 
	{
		/**
		 * General Key monitoring.
		 */
		contentPane.setFocusable(true);
		contentPane.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				// JOptionPane.showMessageDialog(null, e.getKeyChar(), "Debug Key Press.", JOptionPane.INFORMATION_MESSAGE);
				windowStateLogic(e);
			}
		});
		
		/**
		 * Change focus to the contentPane, this allows you to return to the General Key monitoring. 
		 */
		contentPane.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				contentPane.requestFocusInWindow();
			}
		});
		
		/**
		 * Key monitoring on accountNumber.
		 */
		enterAccountNumber.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				char[] pass = enterAccountNumber.getPassword();
				String accountNum = new String(pass);
				enterAccountNumber.setText(filterChars(accountNum));
				windowStateLogic(e);
			}
		});
		
		/**
		 * Key monitoring on pin.
		 */
		enterPin.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				char[] pass = enterPin.getPassword();
				String pin = new String(pass);
				enterPin.setText(filterChars(pin));
				windowStateLogic(e);
			}
		});	
		
		/**
		 * Key monitoring on deposit.
		 */
		depositeMoneyTxt.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String deposite = depositeMoneyTxt.getText();
				depositeMoneyTxt.setText(filterChars(deposite));
				windowStateLogic(e);
			}
		});
	}
	
	/**
	 * Handles the core logic for the current window state, and where it should navigate to based off of the ATM_API logic.
	 * @param e
	 */
	private static void windowStateLogic(KeyEvent e)
	{
		int keyPressed = e.getKeyCode();
		
		if(atmAPI.atmOperational())
		{
			switch(currentWindowState)
			{
				case WelcomeEnterAccount:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						char[] pass = enterAccountNumber.getPassword();
						String accountNum = new String(pass);
						
						if(accountNum != null && !accountNum.isEmpty())
						{
							atmAPI.enterAccountNumber(accountNum);
							switchPanel(ATMWindowState.EnterPin);
						}
					}
					
					break;
					
				case EnterPin:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						char[] pass = enterPin.getPassword();
						String pin = new String(pass);
						
						if(pin != null && !pin.isEmpty())
						{
							atmAPI.enterPin(pin);
							
							enterAccountNumber.setText("");
							enterPin.setText("");
							
							// Attempt to login to account.
							if(atmAPI.loginToAccount())
							{
								switchPanel(ATMWindowState.ChooseAccount);
							}
							
							else
							{
								switchPanel(ATMWindowState.InvalidInfo);
							}
						}
					}
					
					break;
					
				case InvalidInfo:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						switchPanel(ATMWindowState.WelcomeEnterAccount);
					}
					
					break;
					
				case ChooseAccount:
					
					// Savings
					if(keyPressed == KeyEvent.VK_NUMPAD1)
					{
						atmAPI.setAccountType(Account.AccountType.savings);
						switchPanel(ATMWindowState.MainMenu);
					}
					
					// Checking
					if(keyPressed == KeyEvent.VK_NUMPAD2)
					{
						atmAPI.setAccountType(Account.AccountType.checkings);
						switchPanel(ATMWindowState.MainMenu);
					}
					
					// Exit
					if(keyPressed == KeyEvent.VK_NUMPAD3)
					{
						switchPanel(ATMWindowState.WelcomeEnterAccount);
					}
					
					break;
					
				case MainMenu:
					// Show Balance.
					if(keyPressed == KeyEvent.VK_NUMPAD1)
					{
						DisplayBalenceLabel.setText(atmAPI.getBalence());
						switchPanel(ATMWindowState.ShowBalence);
					}
					
					// Withdrawal Money.
					if(keyPressed == KeyEvent.VK_NUMPAD2)
					{
						switchPanel(ATMWindowState.WithdrawalMoney);
					}
					
					// Deposit Money.
					if(keyPressed == KeyEvent.VK_NUMPAD3)
					{
						// Two minute timeout.
						new Thread
						(
							() -> 
							{
								try 
								{
									System.out.println("Debug: Two minute timeout started.");
									cancelTimeout = true;
									Thread.sleep(1000 * 120);
									if(cancelTimeout)
									{
										atmAPI.closeDrawer();
										switchPanel(ATMWindowState.DepositeTimeout);
									}
								} 
								
								catch (InterruptedException e1) 
								{
									e1.printStackTrace();
								}
							}
						).start();
						atmAPI.openDrawer();
						switchPanel(ATMWindowState.DepositeMoney);
					}
					
					// Choose Account.
					if(keyPressed == KeyEvent.VK_NUMPAD4)
					{
						switchPanel(ATMWindowState.ChooseAccount);
					}
					
					// Exit.
					if(keyPressed == KeyEvent.VK_NUMPAD5)
					{
						switchPanel(ATMWindowState.WelcomeEnterAccount);
					}
					
					break;
					
				case ShowBalence:
					// Continue.
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						switchPanel(ATMWindowState.MainMenu);
					}
					
					break;
					
				case WithdrawalMoney:
					
					// 20
					if(keyPressed == KeyEvent.VK_NUMPAD1)
					{
						if(atmAPI.getMoney(20))
						{
							switchPanel(ATMWindowState.ThankYou);
						}
						
						else
						{
							switchPanel(ATMWindowState.InsuficiantFunds);
						}
					}
					
					// 40
					if(keyPressed == KeyEvent.VK_NUMPAD2)
					{
						if(atmAPI.getMoney(40))
						{
							switchPanel(ATMWindowState.ThankYou);
						}
						
						else
						{
							switchPanel(ATMWindowState.InsuficiantFunds);
						}
					}
					
					// 60
					if(keyPressed == KeyEvent.VK_NUMPAD3)
					{
						if(atmAPI.getMoney(60))
						{
							switchPanel(ATMWindowState.ThankYou);
						}
						
						else
						{
							switchPanel(ATMWindowState.InsuficiantFunds);
						}
					}
					
					// 100
					if(keyPressed == KeyEvent.VK_NUMPAD4)
					{
						if(atmAPI.getMoney(100))
						{
							switchPanel(ATMWindowState.ThankYou);
						}
						
						else
						{
							switchPanel(ATMWindowState.InsuficiantFunds);
						}
					}
					
					// 200
					if(keyPressed == KeyEvent.VK_NUMPAD5)
					{
						if(atmAPI.getMoney(200))
						{
							switchPanel(ATMWindowState.ThankYou);
						}
						
						else
						{
							switchPanel(ATMWindowState.InsuficiantFunds);
						}
					}
					
					// Cancel.
					if(keyPressed == KeyEvent.VK_NUMPAD6)
					{
						switchPanel(ATMWindowState.MainMenu);
					}
					
					
					break;
					
				case DepositeMoney:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						cancelTimeout = false;
						atmAPI.closeDrawer();
						atmAPI.depositMoney(tryParse(depositeMoneyTxt.getText()));
						switchPanel(ATMWindowState.ThankYou);
					}
					
					break;
					
				case InsuficiantFunds:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						switchPanel(ATMWindowState.MainMenu);
					}
					
					break;
					
				case DepositeTimeout:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						switchPanel(ATMWindowState.MainMenu);
					}
					
					break;
					
				case ThankYou:
					if(keyPressed == KeyEvent.VK_ENTER)
					{
						switchPanel(ATMWindowState.MainMenu);
					}
					
					break;
				}
			}
			
			else
			{
				switchPanel(ATMWindowState.AtmMantaince);
			}
	}
	
	
	/**
	 * Changes the state of the ATM panels for the UI.
	 * @param state
	 */
	private static void switchPanel(ATMWindowState state)
	{
		currentWindowState = state;
		layeredPane.removeAll();
		layeredPane.add(stateToPanel(state));
		layeredPane.repaint();
		layeredPane.revalidate();
		contentPane.requestFocusInWindow();
	}
	
	/**
	 * Handles state panel definitions.
	 * @param state
	 * @return
	 */
	private static JPanel stateToPanel(ATMWindowState state)
	{
		switch(state)
		{
			case WelcomeEnterAccount:
				return WelcomeAccountPanel;
			case EnterPin:
				return EnterPinPanel;
			case InvalidInfo:
				return PinFailurePanel;
			case ChooseAccount:
				return ChooseAccountPanel;
			case MainMenu:
				return MainMenuPanel;
			case ShowBalence:
				return CheckBalencePanel;
			case WithdrawalMoney:
				return WithdrawalMoneyPanel;
			case DepositeMoney:
				return DepositeMoneyPanel;
			case InsuficiantFunds:
				return InsufficiantFundsPanel;
			case DepositeTimeout:
				return DepositeTimeoutPanel;
			case ThankYou:
				return ThankYouPanel;
			case AtmMantaince:
				return AtmMantaince;
		}
		
		return WelcomeAccountPanel;
	}

	private static String filterChars(String s)
	{
		/*
		char[] whiteList = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Boolean flag = true;
		
		for(int i = 0; i < s.length(); i++)
		{
			for(int j = 0; j < whiteList.length; j++)
			{
				if(s.valueOf(i) == Character.toString(whiteList[j]))
				{
					flag = false;
				}
			}
			
			if(flag)
			{
				s.rep
			}
		}
		*/
		
		Pattern p = Pattern.compile("[^0-9]");
	    Matcher m = p.matcher(s);
	    s = m.replaceAll("");
		return s;
	}
	
	// Try parse input.
	private static Double tryParse(String value) 
	{
	    return tryParse(value, 0);
	}
	
	private static Double tryParse(String value, double defaultVal) 
	{
	    try 
	    {
	        return Double.parseDouble(value);
	    } 
	    
	    catch (NumberFormatException e) 
	    {
	        return defaultVal;
	    }
	}
}
