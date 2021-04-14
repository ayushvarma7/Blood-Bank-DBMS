import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class donations_centers_info extends JFrame {

	private JPanel contentPane;
	private String bankName;
	private String cityName;
	private String phoneNo;
	private String address;
	private int capacity;
	private int user_id;
	private int status_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					donations_centers_info frame = new donations_centers_info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. 
	 */
	public donations_centers_info() {
		Initialize();
	}

	public donations_centers_info(String bankName, String cityName,String address, String phoneNo,int capacity, int user_id, int status_id) {
		this.bankName = bankName;
		this.cityName = cityName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.capacity = capacity;
		this.status_id = status_id;
		this.user_id = user_id;
		Initialize();
	}

	private void Initialize() {

		setTitle("Donation center details");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 983, 688);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228,30,37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		contentPane.add(btnNewButton);

		JButton btnBackButton = new JButton("Back"); //BACK
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Donation_centers myCenterPage = null;
				try {
					myCenterPage = new Donation_centers();
					myCenterPage.setVisible(true);
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

		
		JLabel bloodBankFixed = new JLabel("Blood bank : ");
		bloodBankFixed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bloodBankFixed.setHorizontalAlignment(SwingConstants.CENTER);
		bloodBankFixed.setForeground(new Color(220, 20, 60));
		bloodBankFixed.setBounds(277, 167, 160, 41);
		contentPane.add(bloodBankFixed);

		JLabel BankName_label = new JLabel(bankName);
		BankName_label.setHorizontalAlignment(SwingConstants.CENTER);
		BankName_label.setForeground(new Color(220, 20, 60));
		BankName_label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		BankName_label.setBounds(400, 165, 297, 48);
		contentPane.add(BankName_label);


		JLabel CapacityFixed = new JLabel("Blood capacity : ");
		CapacityFixed.setHorizontalAlignment(SwingConstants.CENTER);
		CapacityFixed.setForeground(new Color(220, 20, 60));
		CapacityFixed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CapacityFixed.setBounds(285, 266, 160, 41);
		contentPane.add(CapacityFixed);

		JLabel Capacity_label = new JLabel(String.valueOf(capacity));
		Capacity_label.setHorizontalAlignment(SwingConstants.CENTER);
		Capacity_label.setForeground(new Color(220, 20, 60));
		Capacity_label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Capacity_label.setBounds(400, 265, 297, 48);
		contentPane.add(Capacity_label);

		
		JLabel AmountFixed = new JLabel("Contact No. : ");
		AmountFixed.setHorizontalAlignment(SwingConstants.CENTER);
		AmountFixed.setForeground(new Color(220, 20, 60));
		AmountFixed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AmountFixed.setBounds(260, 360, 185, 41);
		contentPane.add(AmountFixed);

		JLabel Phone_label = new JLabel(phoneNo);
		Phone_label.setHorizontalAlignment(SwingConstants.CENTER);
		Phone_label.setForeground(new Color(220, 20, 60));
		Phone_label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Phone_label.setBounds(400, 360, 297, 48);
		contentPane.add(Phone_label);

		JLabel AddressFixed = new JLabel("Address : ");
		AddressFixed.setHorizontalAlignment(SwingConstants.CENTER);
		AddressFixed.setForeground(new Color(220, 20, 60));
		AddressFixed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddressFixed.setBounds(200, 459, 280, 48);
		contentPane.add(AddressFixed);

		JLabel Address_label = new JLabel(address);
		Address_label.setHorizontalAlignment(SwingConstants.CENTER);
		Address_label.setForeground(new Color(220, 20, 60));
		Address_label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Address_label.setBounds(400, 459, 297, 48);
		contentPane.add(Address_label);

		JLabel template_background2 = new JLabel("");
		template_background2.setIcon(new ImageIcon(donations_centers_info.class.getResource("/photos/Categories1.png")));
		template_background2.setBounds(161, 111, 812, 457);
		contentPane.add(template_background2);
		
		JLabel template_background = new JLabel("");
		template_background.setIcon(new ImageIcon(donations_centers_info.class.getResource("/photos/BloodDonationCentersInfo_template.png")));
		template_background.setBounds(0, 0, 1300, 700);
		contentPane.add(template_background);
	}
}
