import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Update_Donor_Recipient_Frame extends JFrame{
	private JPanel contentPane;
	private JTextField surnameField;
	private JTextField phonenumberField;
	private JComboBox bloodtype_comboBox;
	private JComboBox comboBox_district;
	private JComboBox comboBox_neighbourhood;
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel passwordLabel;
	private JLabel phonenumberLabel;
	private JLabel lblBloodType;
	private JLabel neighborhoodLabel;
	private JLabel cityLabel;
	private JLabel districtLabel;
	private JPasswordField passwordField;
	private JButton signupButton;
	private JButton backButton;
	private JLabel confirmpasswordLabel;
	private int blood_id;
	private JPasswordField confirmpasswordField;
	private String city;
	private String [] district;
	private PreparedStatement myPreparedStatement;
	private ComboBox_Objects [] district_objects;
	private ComboBox_Objects [] neighbourhood_objects;
	private ComboBox_Objects myObject;
	private String district_name;
	private int capacity;
	private String neighborhood_name;
	private Random random = new Random();
	private int user_id;
	private Statement myStatement;
	private Connection Database_Connection;
	private ResultSet myResultset;
	private MySQLAccess myDataBase = new MySQLAccess();
	private String bloodcode;
	private int status_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Donor_Recipient_Frame frame = new Update_Donor_Recipient_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Update_Donor_Recipient_Frame() throws Exception {
		Initialize();
	}
	
	public Update_Donor_Recipient_Frame(int user_id, int status_id) throws Exception {
		
//		passing status id dynamically
		this.user_id = user_id;
		this.status_id = status_id;
		System.out.println("status "+status_id);
		Initialize();
	}
	
	void Initialize() throws Exception {
//		initialisation function, make UI changes here
		setTitle("Update Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 30, 37));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Profile myProfile = new Profile(user_id, status_id);
					myProfile.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		backButton.setForeground(Color.white);
		backButton.setBackground(Color.black);
		backButton.setBounds(12, 13, 80, 42);
		contentPane.add(backButton);


		JTextField nameField = new JTextField();
		nameField.setBounds(122, 89, 250, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);

		surnameField = new JTextField();
		surnameField.setColumns(10);
		surnameField.setBounds(610, 89, 250, 41);
		contentPane.add(surnameField);

		phonenumberField = new JTextField();
		phonenumberField.addKeyListener(new KeyAdapter() {
			//			Allowing only digits to be types in our field
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		phonenumberField.setColumns(10);
		phonenumberField.setBounds(610, 260, 250, 41);
		contentPane.add(phonenumberField);

		bloodtype_comboBox = new JComboBox();
		bloodtype_comboBox.setBackground(new Color(255, 255, 255));
		bloodtype_comboBox.setBounds(124, 260, 250, 41);
		contentPane.add(bloodtype_comboBox);
		
		for(int i =0; i < getBloodTypeFromDB().size(); i++) {
			
//			Getting bloodtype from database and adding it to our comboBox

			bloodtype_comboBox.addItem(new ComboBox_Objects(getBloodTypeFromDB().get(i)));
		}
		
//		getting default comboBox value
		myObject = (ComboBox_Objects) bloodtype_comboBox.getItemAt(bloodtype_comboBox.getSelectedIndex());
		bloodcode = myObject.toString();
		
		bloodtype_comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				finding combobox value after it has been changed 
				myObject = (ComboBox_Objects) bloodtype_comboBox.getItemAt(bloodtype_comboBox.getSelectedIndex());
				bloodcode = myObject.toString();
				
			}
		});

		nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		nameLabel.setBounds(21, 91, 69, 35);
		nameLabel.setForeground(Color.white);
		contentPane.add(nameLabel);

		surnameLabel = new JLabel("Surname");
		surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		surnameLabel.setBounds(450, 89, 100, 35);
		surnameLabel.setForeground(Color.white);
		contentPane.add(surnameLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBounds(12, 170, 100, 35);
		passwordLabel.setForeground(Color.white);
		contentPane.add(passwordLabel);

		phonenumberLabel = new JLabel("Phone number");
		phonenumberLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		phonenumberLabel.setBounds(440, 260, 134, 39);
		phonenumberLabel.setForeground(Color.white);
		contentPane.add(phonenumberLabel);

		cityLabel = new JLabel("City");
		cityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		cityLabel.setBounds(12, 350, 46, 35);
		cityLabel.setForeground(Color.white);
		contentPane.add(cityLabel);

		lblBloodType = new JLabel("Blood type");
		lblBloodType.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBloodType.setBounds(12, 260, 100, 35);
		lblBloodType.setForeground(Color.white);
		contentPane.add(lblBloodType);

		passwordField = new JPasswordField();
		passwordField.setBounds(122, 165, 250, 41);
		contentPane.add(passwordField);

		neighborhoodLabel = new JLabel("Neighborhood");
		neighborhoodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		neighborhoodLabel.setBounds(540, 350, 150, 35);
		neighborhoodLabel.setForeground(Color.white);
		contentPane.add(neighborhoodLabel);

		confirmpasswordLabel = new JLabel("Confirm Password");
		confirmpasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		confirmpasswordLabel.setBounds(420, 165, 200, 39);
		confirmpasswordLabel.setForeground(Color.white);
		contentPane.add(confirmpasswordLabel);

		districtLabel = new JLabel("District");
		districtLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		districtLabel.setBounds(260, 350, 85, 35);
		districtLabel.setForeground(Color.white);
		contentPane.add(districtLabel);

		
		signupButton = new JButton("Update");
		signupButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				Basic manual form filling error checks 
				
				if(passwordField.getText().isEmpty() || confirmpasswordField.getText().isEmpty() || nameField.getText().isEmpty() || surnameField.getText().isEmpty() || phonenumberField.getText().isEmpty()) {
					JOptionPane myOption = new JOptionPane();
			    	myOption.showMessageDialog(null, "A Field can not be empty");
				
				}else if (!Arrays.equals(passwordField.getPassword(), confirmpasswordField.getPassword())) {
					JOptionPane myOption = new JOptionPane();
			    	myOption.showMessageDialog(null, "Your passwords are not the same");
				
				}else if(phonenumberField.getText().length() > 10 || phonenumberField.getText().length() < 10) {
					JOptionPane myOption = new JOptionPane();
			    	myOption.showMessageDialog(null, "Enter a valid phone number");
				}else {
					
					try {

						int address_id = 0;
						Database_Connection = myDataBase.Connect_to_DataBase();
						myPreparedStatement = Database_Connection.prepareStatement("select Address_Id from Address where District = ? and Neighborhood = ?");
						myPreparedStatement.setString(1, district_name);
						myPreparedStatement.setString(2, neighborhood_name);
						myResultset = myPreparedStatement.executeQuery();

						while(myResultset.next()) {
							address_id = myResultset.getInt("Address_Id");
						}

						myPreparedStatement = Database_Connection.prepareStatement("select Status_Id from User where User_id=?");
						myPreparedStatement.setInt(1, user_id);
						myResultset = myPreparedStatement.executeQuery();

						while(myResultset.next()) {
							status_id = myResultset.getInt(1);
						}

						myPreparedStatement = Database_Connection.prepareStatement("select Blood_id from blood_type where Blood_code =?");
						myPreparedStatement.setString(1, bloodcode);
						myResultset = myPreparedStatement.executeQuery();

						while(myResultset.next()) {
							blood_id = myResultset.getInt(1);
						}

						myPreparedStatement = Database_Connection.prepareStatement("UPDATE user\r\n" +
								" Set Phone_No = ? ,\r\n" + 
								"     Password =  ?,\r\n" +
								" Address_ID= ? \r\n"+
								" WHERE User_Id = ?");
						//myPreparedStatement.setDouble(1,address_id);
						myPreparedStatement.setString(1, phonenumberField.getText());
						myPreparedStatement.setString(2, passwordField.getText());
						myPreparedStatement.setInt(3,address_id);
						myPreparedStatement.setInt(4, user_id);
						myPreparedStatement.executeUpdate();



						
						if(status_id == 2) {
							myPreparedStatement = Database_Connection.prepareStatement("UPDATE donor\r\n" + 
									" Set First_Name = ? \r\n" + 
									"    ,Last_Name =  ? \r\n" +
									" , Blood_ID = ?\r\n"+
									" WHERE Donor_Id = ?;");
							myPreparedStatement.setString(1, nameField.getText());
							myPreparedStatement.setString(2, surnameField.getText());
							myPreparedStatement.setInt(3,blood_id);
							myPreparedStatement.setInt(4, user_id);
							myPreparedStatement.executeUpdate();
						}else {
							myPreparedStatement = Database_Connection.prepareStatement("UPDATE recipient\r\n" + 
									" Set First_Name = ? \r\n" + 
									"    ,Last_Name =  ? \r\n" + 
									" WHERE Recipient_Id = ?");
							myPreparedStatement.setString(1, nameField.getText());
							myPreparedStatement.setString(2, surnameField.getText());
							myPreparedStatement.setInt(3, user_id);
							myPreparedStatement.executeUpdate();
						}
						
						
						JOptionPane myOption = new JOptionPane();
						myOption.showMessageDialog(null, "Data Updated Successfully for " + user_id);



						Database_Connection.close();
						myPreparedStatement.close();


						Profile myProfile = new Profile(user_id, status_id);
						myProfile.setVisible(true);
						dispose();

						

						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
					
				
			}
		});
		signupButton.setIcon(new ImageIcon("resources\\\\photos\\\\Next.png"));
		signupButton.setBounds(308, 399, 322, 41);
		contentPane.add(signupButton);


		JComboBox comboBox_city = new JComboBox();
		comboBox_city.setBackground(Color.WHITE);
		comboBox_city.setBounds(68, 350, 160, 41);
		contentPane.add(comboBox_city);

		city = getCityFromDB();
		comboBox_city.addItem(new ComboBox_Objects(city));
		contentPane.add(comboBox_city);



		comboBox_district = new JComboBox();
		comboBox_district.setBackground(Color.WHITE);
		comboBox_district.setBounds(345, 350, 160, 41);

		for(int i =0; i < getDistrictFromDB().size(); i++) {

//			adding items to district combobox

			comboBox_district.addItem(new ComboBox_Objects(getDistrictFromDB().get(i)));
		}
//		finding default combobox value

		myObject = (ComboBox_Objects) comboBox_district.getItemAt(comboBox_district.getSelectedIndex());
		district_name = myObject.toString();

		contentPane.add(comboBox_district);

		comboBox_neighbourhood = new JComboBox();
		comboBox_neighbourhood.setBackground(Color.WHITE);
		comboBox_neighbourhood.setBounds(690, 350, 160, 41);
		contentPane.add(comboBox_neighbourhood);


		for(int i =0; i < getNeighborhoodFromDB(district_name).size(); i++) {
//			adding items to neighbourhood combobox
			comboBox_neighbourhood.addItem(new ComboBox_Objects(getNeighborhoodFromDB(district_name).get(i)));
		}

//		finding default value
		myObject = (ComboBox_Objects) comboBox_neighbourhood.getItemAt(comboBox_neighbourhood.getSelectedIndex());
		neighborhood_name = myObject.toString();


		comboBox_neighbourhood.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					finding value when it changes
					myObject = (ComboBox_Objects) comboBox_neighbourhood.getItemAt(comboBox_neighbourhood.getSelectedIndex());
					neighborhood_name = myObject.toString();
				}catch (Exception e2) {

				}


			}
		});

		JLabel template_background = new JLabel("");
		template_background.setIcon(new ImageIcon(donations_centers_info.class.getResource("/photos/BloodDonationCentersInfo_template.png")));
		template_background.setBounds(0, 12, 500, 560);
		contentPane.add(template_background);


		comboBox_district.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
//					Finding value when it chaanges

					myObject = (ComboBox_Objects) comboBox_district.getItemAt(comboBox_district.getSelectedIndex());
					district_name = myObject.toString();
					comboBox_neighbourhood.removeAllItems();
					for(int i =0; i < getNeighborhoodFromDB(district_name).size(); i++) {
//					displaying new items in the combo box based on district selected
						comboBox_neighbourhood.addItem(new ComboBox_Objects(getNeighborhoodFromDB(district_name).get(i)));
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}



			}
		});





		confirmpasswordField = new JPasswordField();
		confirmpasswordField.setBounds(595, 171, 255, 41);
		contentPane.add(confirmpasswordField);
	}String getCityFromDB() throws Exception {
//		Finding city from DB
		String city = null;
		try {
			Database_Connection = myDataBase.Connect_to_DataBase();
			myStatement = Database_Connection.createStatement();
			myResultset = myStatement.executeQuery("select distinct City from Address");

			while(myResultset.next()) {
				city = myResultset.getString("City");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		Database_Connection.close();
		myStatement.close();
		myResultset.close();

		return city;

	}

	ArrayList<String> getDistrictFromDB() throws SQLException {
//		Finding List of Districts from DB
		ArrayList<String> district = new ArrayList<String>();
		try {
			Database_Connection = myDataBase.Connect_to_DataBase();
			myStatement = Database_Connection.createStatement();
			myResultset = myStatement.executeQuery("select distinct District from Address");

			int counter = 0;

			while(myResultset.next()) {
				district.add(myResultset.getString("District"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		Database_Connection.close();
		myStatement.close();
		myResultset.close();

		return district;

	}

	ArrayList<String> getNeighborhoodFromDB(String district) throws SQLException {
//		Finding List of NeighbourHood based on district from DB
		ArrayList<String> Neighborhood = new ArrayList<String>();
		try {
			Database_Connection = myDataBase.Connect_to_DataBase();
			myStatement = Database_Connection.createStatement();
			myPreparedStatement = Database_Connection.prepareStatement("select distinct Neighborhood from Address where Address.district = ?");

			myPreparedStatement.setString(1, district);
			myResultset = myPreparedStatement.executeQuery();


			while(myResultset.next()) {
				Neighborhood.add(myResultset.getString("Neighborhood"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		Database_Connection.close();
		myStatement.close();
		myResultset.close();

		return Neighborhood;

	}

	ArrayList<String> getBloodTypeFromDB() throws SQLException {

//		Finding blood type from DB
		ArrayList<String> bloodtype = new ArrayList<String>();
		try {
			Database_Connection = myDataBase.Connect_to_DataBase();
			myStatement = Database_Connection.createStatement();
			myResultset = myStatement.executeQuery("select Blood_Code from Blood_Type");



			while(myResultset.next()) {
				bloodtype.add(myResultset.getString("Blood_Code"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		Database_Connection.close();
		myStatement.close();
		myResultset.close();

		return bloodtype;

	}
}
