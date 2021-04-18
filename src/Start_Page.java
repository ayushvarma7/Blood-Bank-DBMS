import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Start_Page extends JFrame {
	
	
	private JFrame frmStartPage;
	private JPanel contentPane;
	private JTextField user_idField;
	private JPasswordField passwordField;
	private Connection Database_Connection;
	private ResultSet myResultset;
	private MySQLAccess myDataBase = new MySQLAccess();
	private Statement myStatement;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int status_id;
	private PreparedStatement myPreparedStatement;
	private int status_id_to_log_in;
	private int user_id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					Start_Page window = new Start_Page();
					window.frmStartPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void setVisible(boolean B) {
		frmStartPage.setVisible(B);
	}
	
	

	/**
	 * Create the frame.
	 */
	public Start_Page() {
		Initialize();
	}
	
	void Initialize() {
		frmStartPage = new JFrame();
		frmStartPage.setTitle("Start page");
		frmStartPage.setBounds(100, 0, 983, 728);
		frmStartPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStartPage.getContentPane().setLayout(null);
		
		JLabel StartPagePhoto1 = new JLabel("");
		StartPagePhoto1.setIcon(new ImageIcon("resources/photos/StartPagePhoto.jpg"));
		StartPagePhoto1.setHorizontalAlignment(SwingConstants.CENTER);
		StartPagePhoto1.setBounds(10, 170, 450, 400);
		frmStartPage.getContentPane().add(StartPagePhoto1);
		/*
		JLabel startPagePhoto2 = new JLabel("");
		startPagePhoto2.setIcon(new ImageIcon("resources/photos/startPagePhoto2.jpg"));
		startPagePhoto2.setBounds(358, 0, 351, 230);
		frmStartPage.getContentPane().add(startPagePhoto2);*/
		
		JLabel welcomeLabel = new JLabel("Welcome to Blood Bank Management System ! ");
		welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 28));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(50, 60, 869, 47);
		frmStartPage.getContentPane().add(welcomeLabel);
		
		JLabel selectinfoLabel = new JLabel("Please select one of the following!");
		selectinfoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		selectinfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectinfoLabel.setBounds(500, 430, 451, 32);
		frmStartPage.getContentPane().add(selectinfoLabel);
		
		JCheckBox checkboxbloodBank = new JCheckBox("Blood Bank Admin");
		checkboxbloodBank.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		checkboxbloodBank.setBackground(new Color(245, 245, 245));
		checkboxbloodBank.setBounds(760, 485, 172, 25);
		frmStartPage.getContentPane().add(checkboxbloodBank);
		
		JCheckBox checkboxRecipient = new JCheckBox("Recipient");
		checkboxRecipient.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		checkboxRecipient.setHorizontalAlignment(SwingConstants.CENTER);
		checkboxRecipient.setBackground(new Color(245, 245, 245));
		checkboxRecipient.setBounds(490, 485, 113, 25);
		frmStartPage.getContentPane().add(checkboxRecipient);
		
		JCheckBox checkboxDonor = new JCheckBox("Donor");
		checkboxDonor.setHorizontalAlignment(SwingConstants.CENTER);
		checkboxDonor.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		checkboxDonor.setForeground(new Color(0, 0, 0));
		checkboxDonor.setBackground(new Color(245, 245, 245));
		checkboxDonor.setBounds(620, 485, 113, 25);
		frmStartPage.getContentPane().add(checkboxDonor);
		
		buttonGroup.add(checkboxDonor);
		buttonGroup.add(checkboxRecipient);
		buttonGroup.add(checkboxbloodBank);
//		added checkboxes to same group so only one can be checked at a time
		
		
		JButton signupButton = new JButton("Sign up");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkboxbloodBank.isSelected()) {
					status_id = 1;
					BloodBank_SignUp_Frame myBlood_Bank_SignUp_Frame;
					try {
						
						myBlood_Bank_SignUp_Frame = new BloodBank_SignUp_Frame(status_id);
						myBlood_Bank_SignUp_Frame.setVisible(true);
						frmStartPage.dispose();
						
						
//						close start page here
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
									
				
				}else if(checkboxDonor.isSelected()) {
					status_id =2;
					Sign_Up_Frame Signup_Frame;
					try {
						Signup_Frame = new Sign_Up_Frame(status_id);
						Signup_Frame.setVisible(true);
						frmStartPage.dispose();
//					close start page here
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if (checkboxRecipient.isSelected()) { 
					status_id =3;
					Sign_Up_Frame Signup_Frame;
					try {
						Signup_Frame = new Sign_Up_Frame(status_id);
						Signup_Frame.setVisible(true);
						frmStartPage.dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane myOption = new JOptionPane();
			    	myOption.showMessageDialog(null, "Select a User Type to Sign Up for ");
				}
			}
		});
		signupButton.setBackground(Color.white);
	//	signupButton.setIcon(new ImageIcon("resources/photos/Next.png"));
		signupButton.setForeground(new Color(221,69,66));
		signupButton.setFont(new Font("Times New Roman", Font. PLAIN, 18));
		signupButton.setBounds(550, 550, 334, 57);
		frmStartPage.getContentPane().add(signupButton);
		
		user_idField = new JTextField();
		user_idField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		user_idField.setBounds(660, 150, 234, 41);
		frmStartPage.getContentPane().add(user_idField);
		user_idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(660, 200, 234, 41);
		frmStartPage.getContentPane().add(passwordField);
		
		JLabel useridLabel = new JLabel("User ID");
		useridLabel.setHorizontalAlignment(SwingConstants.CENTER);
		useridLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		useridLabel.setBounds(500, 150, 121, 41);
		frmStartPage.getContentPane().add(useridLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		passwordLabel.setBounds(500, 200, 121, 41);
		frmStartPage.getContentPane().add(passwordLabel);
		
		JButton signinButton = new JButton("Sign In");
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean user_exists = false;
					Database_Connection = myDataBase.Connect_to_DataBase();
					myStatement = (Objects.requireNonNull(Database_Connection.createStatement()));
					myResultset = myStatement.executeQuery("select User_Id, Password from User"); 
					while(myResultset.next()) {
						if(myResultset.getInt("User_Id") == Integer.valueOf(user_idField.getText()) && myResultset.getString("Password").equals(passwordField.getText())) {
							user_exists = true;
						}
				
					}
					
					if(user_exists == true) {
						myPreparedStatement = Database_Connection.prepareStatement("select Status_Id from User where User_Id = ? and Password = ?");
						myPreparedStatement.setInt(1, Integer.valueOf(user_idField.getText()));
						myPreparedStatement.setString(2, passwordField.getText());
						myResultset = myPreparedStatement.executeQuery();
						
						while(myResultset.next()) {
							status_id_to_log_in = myResultset.getInt("Status_Id");
						}
						
						if(status_id_to_log_in == 1) {
							Admin_page myAdminpage = new Admin_page(Integer.valueOf(user_idField.getText()));
							myAdminpage.setVisible(true);
							myPreparedStatement.close();
							myResultset.close();
							Database_Connection.close();
							frmStartPage.dispose();
						}else {
							user_id = Integer.valueOf(user_idField.getText());
							Profile myProfile = new Profile(user_id, status_id);
							myProfile.setVisible(true);
							myPreparedStatement.close();
							myResultset.close();
							Database_Connection.close();
							
							frmStartPage.dispose();
						}
						
						
						
						
					}else {
						JOptionPane myOption = new JOptionPane();
				    	myOption.showMessageDialog(null, "Check your Info or Sign Up");
				    	
				    	
						myResultset.close();
						Database_Connection.close();
					}

					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				
			}
		});
	//	signinButton.setIcon(new ImageIcon("resources/photos/Next.png"));
		signinButton.setBackground(Color.WHITE);
		signinButton.setForeground(new Color(221,69,66));
		signinButton.setFont(new Font("Times New Roman", Font. PLAIN, 18));
		signinButton.setBounds(550, 280, 334, 57);
		frmStartPage.getContentPane().add(signinButton);
		
		JLabel signupLabel = new JLabel("No Account yet?");
		signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signupLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		signupLabel.setBounds(550, 380, 369, 40);
		frmStartPage.getContentPane().add(signupLabel);
	}
	
	

}