import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Donation_centers extends JFrame {

	private JPanel contentPane;
	private int user_id;
	private Connection Database_Connection;
	private ResultSet myResultset;
	private MySQLAccess myDataBase = new MySQLAccess();
	private Statement myStatement;
	private ArrayList<String> bankName = new ArrayList();
	private ArrayList<String> cityName = new ArrayList();
	private ArrayList<String> phone_No = new ArrayList();
	private ArrayList<String> addressDetails = new ArrayList();
	private int length_of_tuples;
	private int number_of_updates_done = 0;
	private int update_index = 1;
	private int status_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Donation_centers frame = new Donation_centers();
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
	public Donation_centers() throws Exception {
		Initialize();
	}

	public Donation_centers(int user_id, int status_id) throws Exception {
		this.user_id = user_id;
		this.status_id = status_id;
		Initialize();
	}

	void Initialize() throws Exception {
		setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 17));
		setTitle("Donation centers\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 0, 983, 688);
	//	setBounds(75, 0, 1160, 750);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Exit"); //EXIT
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start_Page myStartPage = new Start_Page();
				myStartPage.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(Color.white);
		btnNewButton.setForeground(Color.RED);
       btnNewButton.setBounds(900, 11, 61, 50);
//		btnNewButton.setBounds(1050, 11, 61, 67);
		contentPane.add(btnNewButton);


		JButton btnBackButton = new JButton("Back"); //BACK
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Admin_page myAdminPage = null;
				try {
					myAdminPage = new Admin_page(user_id);
					myAdminPage.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnBackButton.setBackground(Color.white);
		btnBackButton.setForeground(Color.RED);
		btnBackButton.setBounds(10, 11, 81, 50);
//		btnNewButton.setBounds(1050, 11, 61, 67);
		contentPane.add(btnBackButton);

		final BufferedImage image = ImageIO.read(new URL(
				"https://png.pngtree.com/thumb_back/fw800/back_our/20190621/ourmid/pngtree-blood-donation-art-free-simple-white-banner-image_180424.jpg"));

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(27f));
		g.setColor(Color.RED);
		g.drawString("GIVE A BIT, CHANGE A LOT", 320, 200);
		g.drawString("CREATE A BRIGHT FUTURE", 350, 250);
		g.dispose();
		ImageIO.write(image, "png", new File("test.png"));

		JLabel banner;
		ImageIcon banner1 = new ImageIcon("test.png");
		banner = new JLabel("",banner1,JLabel.CENTER);
		banner.setBounds(0,0,1000,220);
		contentPane.add(banner);



		JLabel nameRightLabel = new JLabel("Donation center Name");
		nameRightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameRightLabel.setForeground(Color.black);
		nameRightLabel.setFont(new Font("Comic Sans MS", Font.BOLD , 22));
		nameRightLabel.setBounds(650, 362, 324, 48);
		contentPane.add(nameRightLabel);

		JLabel cityRightLabel = new JLabel("Region");
		cityRightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityRightLabel.setForeground(Color.black);
		cityRightLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cityRightLabel.setBounds(680, 423, 235, 26);
		contentPane.add(cityRightLabel);


		JLabel nameLeftLabel = new JLabel("Donation center Name");
		nameLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLeftLabel.setForeground(Color.black);
		nameLeftLabel.setFont(new Font("Comic Sans MS", Font.BOLD , 22));
		nameLeftLabel.setBounds(70, 362, 215, 48);
		contentPane.add(nameLeftLabel);

		JLabel cityLeftLabel = new JLabel("Region");
		cityLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityLeftLabel.setForeground(Color.black);
		cityLeftLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cityLeftLabel.setBounds(30, 423, 286, 31);
		contentPane.add(cityLeftLabel);


		JLabel nameMidLabel = new JLabel("Donation center Name");
		nameMidLabel.setForeground(Color.black);
		nameMidLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		nameMidLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameMidLabel.setBounds(320, 362, 361, 48);
		contentPane.add(nameMidLabel);

		JLabel cityMidLabel = new JLabel("Region");
		cityMidLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cityMidLabel.setForeground(Color.black);
		cityMidLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityMidLabel.setBounds(370, 423, 251, 45);
		contentPane.add(cityMidLabel);


		JButton contactUs_btn_left = new JButton("Contact us!");
		contactUs_btn_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonationCenterInfo myDonationCenterInfo = new DonationCenterInfo(nameLeftLabel.getText(), cityLeftLabel.getText(), user_id, status_id);
				myDonationCenterInfo.setVisible(true);
				dispose();
			}
		});

		contactUs_btn_left.setForeground(new Color(255, 250, 240));
		contactUs_btn_left.setBackground(new Color(220, 20, 60));
		contactUs_btn_left.setBounds(100, 500, 132, 36);
		contentPane.add(contactUs_btn_left);

		JButton contactUs_btn_mid = new JButton("Contact us!"); //CONTACT US WHITE BEKAAR PAGE KHULTA HAI
		contactUs_btn_mid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonationCenterInfo myDonationCenterInfo = new DonationCenterInfo(nameMidLabel.getText(), cityMidLabel.getText(), user_id, status_id);
				myDonationCenterInfo.setVisible(true);
				dispose();
			}
		});

		contactUs_btn_mid.setForeground(Color.white);
		contactUs_btn_mid.setBackground(new Color(220, 20, 60));
		contactUs_btn_mid.setBounds(430, 500, 132, 36);
		contentPane.add(contactUs_btn_mid);

		JButton contactUs_btn_right = new JButton("Contact us!");
		contactUs_btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonationCenterInfo myDonationCenterInfo = new DonationCenterInfo(nameRightLabel.getText(), cityRightLabel.getText(), user_id, status_id);
				myDonationCenterInfo.setVisible(true);
				dispose();
			}
		});
		contactUs_btn_right.setForeground(Color.white);
		contactUs_btn_right.setBackground(new Color(220, 20, 60));
		contactUs_btn_right.setBounds(740, 500, 132, 36);
		contentPane.add(contactUs_btn_right);

		// PUT LEFT AND RIGHT ARROWS
		JButton donationCenterGroup_btn_1 = new JButton("Next");
		donationCenterGroup_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(number_of_updates_done == length_of_tuples) {
					JOptionPane myOption = new JOptionPane();
					myOption.showMessageDialog(null, "All available blood banks displayed");
				}else {
					if(update_index == 1) {
						nameLeftLabel.setText(bankName.get(number_of_updates_done));
						cityLeftLabel.setText(cityName.get(number_of_updates_done));

						number_of_updates_done++;
						update_index++;
					}

					if(number_of_updates_done != length_of_tuples) {
						if(update_index == 2) {
							nameMidLabel.setText(bankName.get(number_of_updates_done));
							cityMidLabel.setText(cityName.get(number_of_updates_done));

							number_of_updates_done++;
							update_index++;
						}
					}

					if(number_of_updates_done != length_of_tuples) {
						if(update_index == 3) {
							nameRightLabel.setText(bankName.get(number_of_updates_done));
							cityRightLabel.setText(cityName.get(number_of_updates_done));
							number_of_updates_done++;
							update_index = 1;
						}
					}


				}
			}
		});

		//NEXT

		donationCenterGroup_btn_1.setForeground(new Color(255, 255, 255));
		donationCenterGroup_btn_1.setBackground(new Color(220, 20, 60));
		donationCenterGroup_btn_1.setBounds(544, 230, 97, 25);
		contentPane.add(donationCenterGroup_btn_1);


		//Previous Button
		JButton donationCenterGroup_btn_2 = new JButton("Previous");
		donationCenterGroup_btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(number_of_updates_done ==0) {
					JOptionPane myOption = new JOptionPane();
					myOption.showMessageDialog(null, "Click on the next button to view the centers");
				}else if (number_of_updates_done>0){
					if(update_index == 1) {
						nameLeftLabel.setText(bankName.get(number_of_updates_done));
						cityLeftLabel.setText(cityName.get(number_of_updates_done));

						number_of_updates_done--;
						update_index++;
					}

					if(number_of_updates_done != length_of_tuples) {
						if(update_index == 2) {
							nameMidLabel.setText(bankName.get(number_of_updates_done));
							cityMidLabel.setText(cityName.get(number_of_updates_done));

							number_of_updates_done--;
							update_index++;
						}
					}

					if(number_of_updates_done != length_of_tuples) {
						if(update_index == 3) {
							nameRightLabel.setText(bankName.get(number_of_updates_done));
							cityRightLabel.setText(cityName.get(number_of_updates_done));
							number_of_updates_done--;
							update_index = 1;
						}
					}


				}
			}
		});

		donationCenterGroup_btn_2.setForeground(new Color(255, 255, 255));
		donationCenterGroup_btn_2.setBackground(new Color(220, 20, 60));
		donationCenterGroup_btn_2.setBounds(344, 230, 97, 25);
		contentPane.add(donationCenterGroup_btn_2);


		JLabel templateLabel = new JLabel("");
		templateLabel.setIcon(new ImageIcon(Donation_centers.class.getResource("/photos/Donation censters_template.png")));
		templateLabel.setBounds(0, 40, 1137, 724);
		contentPane.add(templateLabel);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(573, 381, 46, 14);
		contentPane.add(lblNewLabel);

		Database_Connection = myDataBase.Connect_to_DataBase();
		myStatement = Database_Connection.createStatement();
		myResultset = myStatement.executeQuery("SELECT Name, City, District, Neighborhood, Phone_no from Blood_Bank, Address, User, Status where Blood_Bank.Bank_Id = User.User_Id and User.Status_Id = Status.Status_Id and User.Address_Id = Address.Address_Id");


		while(myResultset.next()) {
			bankName.add(myResultset.getString("Name"));
			cityName.add(myResultset.getString("City"));
			phone_No.add(myResultset.getString("Phone_no"));
			addressDetails.add(myResultset.getString("Neighborhood") + ", " + myResultset.getString("District"));
			length_of_tuples++;
		}

		if(number_of_updates_done != length_of_tuples) {
			if(update_index == 1) {
				nameLeftLabel.setText(bankName.get(number_of_updates_done));
				cityLeftLabel.setText(cityName.get(number_of_updates_done));

				number_of_updates_done++;
				update_index++;
			}
		}

		if(number_of_updates_done != length_of_tuples) {
			if(update_index == 2) {
				nameMidLabel.setText(bankName.get(number_of_updates_done));
				cityMidLabel.setText(cityName.get(number_of_updates_done));

				number_of_updates_done++;
				update_index++;
			}
		}

		if(number_of_updates_done != length_of_tuples) {
			if(update_index == 3) {
				nameRightLabel.setText(bankName.get(number_of_updates_done));
				cityRightLabel.setText(cityName.get(number_of_updates_done));

				number_of_updates_done++;
				update_index = 1;
			}
		}







	}
}