import java.awt.*;

import net.proteanit.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.html.ImageView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Admin_page extends JFrame {

	private JPanel contentPane;
	private Connection Database_Connection;
	private ResultSet myResultset;
	private MySQLAccess myDataBase = new MySQLAccess();
	private Statement myStatement;
	private int user_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_page frame = new Admin_page();
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
	public Admin_page() throws Exception {
		Initialize();
	}
	
	public Admin_page(int user_id) throws Exception {
		this.user_id = user_id;
		Initialize();
	}

	class ImagePanel extends JPanel {

		private Image img;
		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}
		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
		}
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}
	
	void Initialize() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 983, 728); //SCREEN SIZE PERFECT
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel side_panels = new JPanel();
		side_panels.setBackground(Color.black);
		side_panels.setBounds(0, 0, 225, 650);
		contentPane.add(side_panels);
		side_panels.setLayout(null);

		JPanel main_content = new JPanel();
		main_content.setBackground(new Color(203,26,23));
		main_content.setBounds(225,120,760,650);
		contentPane.add(main_content);

		//NAHI CHAL RAHA IDK WHY
/*
		Image image22 = new Image("C://xampp//htdocs//Blood-Bank-DBMS-master//Blood-Bank-DBMS-master//bin//photos//admin_back.jpeg");
		ImageView imageView = new ImageView(image22);
		imageView.setPreserveRatio(true);
		ImageIcon icon = new ImageIcon("C:/xampp/htdocs/Blood-Bank-DBMS-master/Blood-Bank-DBMS-master/bin/photos/admin_back");
		JLabel admin_back = new JLabel(icon);
		JLabel admin_back = new JLabel();
		admin_back.setBackground(Color.blue);
		admin_back.setBounds(0,0,225,50);
		contentPane.add(admin_back);

		ImagePanel panel = new ImagePanel(new ImageIcon(getClass()
				.getResource("/Resources/background.png"))
				.getImage());
		contentPane.add(panel);*/

		JButton btnDashboard = new JButton("Dashboard");
		JButton btnProfile = new JButton("Profile");
		JButton btnUsers = new JButton("Users");
		JButton btnDonors = new JButton("Donors");
		JButton btnGroups = new JButton("Blood Groups");



		btnDashboard.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDashboard.setBackground(new Color(245, 245, 245));
		btnDashboard.setBounds(68, 150, 100, 50);
		side_panels.add(btnDashboard);

		btnProfile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnProfile.setBackground(new Color(245, 245, 245));
		btnProfile.setBounds(68, 250, 100, 50);
		side_panels.add(btnProfile);

		btnUsers.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUsers.setBackground(new Color(245, 245, 245));
		btnUsers.setBounds(68, 350, 100, 50);
		side_panels.add(btnUsers);

		btnDonors.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDonors.setBackground(new Color(245, 245, 245));
		btnDonors.setBounds(68, 450, 100, 50);
		side_panels.add(btnDonors);

		btnGroups.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnGroups.setBackground(new Color(245, 245, 245));
		btnGroups.setBounds(52, 550, 130, 50);
		side_panels.add(btnGroups);



		JLabel profile_icon = new JLabel("");
		profile_icon.setIcon(new ImageIcon(Admin_page.class.getResource("/photos/profile_icon.png")));
		profile_icon.setBounds(83, 42, 59, 67);
		side_panels.add(profile_icon);
		
/*		JLabel saveLifeLabel = new JLabel("Save a life!");
		saveLifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveLifeLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		saveLifeLabel.setForeground(new Color(255, 255, 255));
		saveLifeLabel.setBounds(12, 600, 199, 67);
		side_panels.add(saveLifeLabel);
		*/

		JLabel welcom_label = new JLabel("Welcome Admin");
		welcom_label.setForeground(new Color(203,26,23));
		welcom_label.setFont(new Font("Helvetica", Font.BOLD, 44));
		welcom_label.setHorizontalAlignment(SwingConstants.CENTER);
		welcom_label.setBounds(350, 25, 399, 90);
		contentPane.add(welcom_label);


		
		JScrollPane infoPane = new JScrollPane();
		infoPane.setBounds(20, 100, 679, 345);
		//contentPane.add(infoPane);

		JTable table = new JTable();
		
		Database_Connection = myDataBase.Connect_to_DataBase();
		myStatement = (Objects.requireNonNull(Database_Connection.createStatement()));

		myResultset = myStatement.executeQuery("select User_Id , concat(First_Name , \" \" , Last_Name) as Name , Status , Blood_Code as Blood_Type , City ,  District ,  Neighborhood, Phone_No \r\n" + 
				"from User , Status , Address , Donor , Blood_Type\r\n" + 
				"where Donor.Donor_Id = User.User_Id and User.Status_Id = Status.Status_Id and User.Address_Id = Address.Address_Id and Donor.Blood_Id = Blood_Type.Blood_Id\r\n" + 
				"Union\r\n" + 
				"select User_Id , concat(First_Name , \" \" , Last_Name) as Name , Status , Blood_Code as Blood_Type , City , District , Neighborhood, Phone_No \r\n" + 
				"from User , Status , Address , Recipient , Blood_Type\r\n" + 
				"where Recipient.Recipient_Id = User.User_Id and User.Status_Id = Status.Status_Id and User.Address_Id = Address.Address_Id and Recipient.Blood_Id = Blood_Type.Blood_Id\r\n" + 
				"order by User_Id");
		
		infoPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(myResultset));

		// BLOOD GROUP //

		JButton grp_Ap = new JButton("0    A +");
		JButton grp_Bp = new JButton("0    B +");
		JButton grp_ABp = new JButton("0    AB +");
		JButton grp_Op = new JButton("0    0 +");
		JButton grp_An = new JButton("0    A -");
		JButton grp_Bn = new JButton("0    B -");
		JButton grp_ABn = new JButton("0    AB -");
		JButton grp_On = new JButton("0    O -");

		grp_Ap.setFont(new Font("Times New Roman", Font.BOLD, 14));
		grp_Ap.setBackground(Color.white);
		grp_Ap.setBounds(0, 200, 130, 50);

		grp_Bp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		grp_Bp.setBackground(Color.white);
		grp_Bp.setBounds(0, 500, 130, 50);



		// BUTTON ACTIONs sidepane //

		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent grpaction) {
				try {

					main_content.removeAll();
					JLabel dash_label = new JLabel("Dashboard");
					dash_label.setForeground(Color.WHITE);
					dash_label.setFont(new Font("Tahoma", Font.BOLD, 33));
					dash_label.setHorizontalAlignment(SwingConstants.CENTER);
					dash_label.setBounds(200, 0, 231, 72);
					main_content.add(dash_label);
					main_content.repaint();


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile_Admin myProfileAdmin;
				try {
					myProfileAdmin = new Profile_Admin(user_id);
					myProfileAdmin.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent useraction) {
				try {
					main_content.removeAll();
					JLabel users_label = new JLabel("Users");
					users_label.setForeground(Color.WHITE);
					users_label.setFont(new Font("Tahoma", Font.BOLD, 33));
					users_label.setHorizontalAlignment(SwingConstants.CENTER);
					users_label.setBounds(200, 0, 231, 72);
					main_content.add(users_label);
					main_content.add(infoPane);
					main_content.repaint();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent useraction) {
				try {
						// display no of users etc
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnDonors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent grpaction) {
				try {

					main_content.removeAll();
					JLabel don_label = new JLabel("Donors");
					don_label.setForeground(Color.WHITE);
					don_label.setFont(new Font("Tahoma", Font.BOLD, 33));
					don_label.setHorizontalAlignment(SwingConstants.CENTER);
					don_label.setBounds(200, 0, 231, 72);
					main_content.add(don_label);
					main_content.repaint();


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent grpaction) {
				try {
					System.out.println("111");
					main_content.removeAll();
					JLabel bgrp_label = new JLabel("Blood Groups");
					bgrp_label.setForeground(Color.WHITE);
					bgrp_label.setFont(new Font("Tahoma", Font.BOLD, 33));
					bgrp_label.setHorizontalAlignment(SwingConstants.CENTER);
					bgrp_label.setBounds(200, 0, 231, 72);
					main_content.add(bgrp_label);
					main_content.add(grp_Ap);
					main_content.add(grp_Bp);
					main_content.repaint();
					main_content.validate();


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start_Page myStartPage = new Start_Page();
				myStartPage.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setIcon(new ImageIcon(Admin_page.class.getResource("/photos/exit.png")));
		btnNewButton.setBounds(882, 26, 75, 67);
		contentPane.add(btnNewButton);
		
		Database_Connection.close();
		myStatement.close();
		myResultset.close();
		
	}
}
