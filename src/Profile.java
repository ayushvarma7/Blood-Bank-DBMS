import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Profile extends JFrame {
    private JPanel contentPane;
    private int user_id;
    private ComboBox_Objects myObject;
    private String bloodcode;
    private Connection Database_Connection;
    private ResultSet myResultset;
    private PreparedStatement myPreparedStatement;
    private MySQLAccess myDataBase = new MySQLAccess();
    private Statement myStatement;
    private int status_id;
    private JTextField surnameField;
    private JTextField phonenumberField;
    private JComboBox bloodtype_comboBox;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel phonenumberLabel;
    private JLabel lblBloodType;
    private JLabel cityLabel;
    private JLabel districtLabel;
    private JLabel neighborhoodLabel;
    private JPasswordField passwordField;
    private JPasswordField confirmpasswordField;

    JLabel namedash ;
    JLabel surnamedash;
    JLabel phonedash;
    JLabel blooddash;
    JLabel citydash;
    JLabel districtdash;
    JLabel neighbourdash;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Profile frame = new Profile();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Profile() throws Exception {
        Initialize();
    }

    public Profile(int user_id, int status_id) throws Exception {
        this.user_id = user_id;
        this.status_id = status_id;
        Initialize();
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
        main_content.setBackground(new Color(203, 26, 23));
        main_content.setBounds(225, 120, 860, 650);
        main_content.setLayout(null);
        contentPane.add(main_content);

        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnDashboard.setBackground(new Color(245, 245, 245));
        btnDashboard.setBounds(38, 200, 160, 50);
        side_panels.add(btnDashboard);


        JButton btncenter = new JButton("Donation Centers");
        btncenter.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btncenter.setBackground(new Color(245, 245, 245));
        btncenter.setBounds(38, 300, 160, 50);
        side_panels.add(btncenter);

        JButton btnEdit = new JButton("Edit Profile ");
        btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnEdit.setBackground(new Color(245, 245, 245));
        btnEdit.setBounds(38, 400, 160, 50);
        side_panels.add(btnEdit);

        JButton btnDelete = new JButton("Delete Profile");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnDelete.setBackground(new Color(245, 245, 245));
        btnDelete.setBounds(38, 500, 160, 50);
        side_panels.add(btnDelete);





        //CHange icon
        JLabel profile_icon = new JLabel("");
        profile_icon.setIcon(new ImageIcon(Admin_page.class.getResource("/photos/profile_icon.png")));
        profile_icon.setBounds(83, 42, 59, 67);
        side_panels.add(profile_icon);

        String user = null;
        try {
            Database_Connection = myDataBase.Connect_to_DataBase();
            myStatement = Database_Connection.createStatement();

            myResultset = myStatement.executeQuery("SELECT `First_Name`, Recipient_ID FROM `Recipient`JOIN User where User.User_Id=Recipient.Recipient_Id");
            while (myResultset.next()) {
                if (user_id == myResultset.getInt(2)) {
                    user = myResultset.getString(1);
                }
            }
            if(user==null)
            {
                myResultset = myStatement.executeQuery("SELECT `First_Name`, Donor_ID FROM `Donor`JOIN User where User.User_Id=Donor.donor_Id");
                while (myResultset.next()) {
                    if (user_id == myResultset.getInt(2)) {
                        user = myResultset.getString(1);
                    }
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        JLabel welcom_label = new JLabel("Welcome " + user);
        welcom_label.setForeground(new Color(203, 26, 23));
        welcom_label.setFont(new Font("Helvetica", Font.BOLD, 44));
        welcom_label.setHorizontalAlignment(SwingConstants.CENTER);
        welcom_label.setBounds(350, 25, 399, 90);
        contentPane.add(welcom_label);

        Database_Connection = myDataBase.Connect_to_DataBase();
        myStatement = (Objects.requireNonNull(Database_Connection.createStatement()));
        myResultset = myStatement.executeQuery("select User_Id , First_Name ,Last_Name , Blood_Code as Blood_Type , City ,  District ,  Neighborhood, Phone_No \r\n" +
                "from User, Address , Donor , Blood_Type\r\n" +
                "where Donor.Donor_Id = User.User_Id and User.Address_Id = Address.Address_Id and Donor.Blood_Id = Blood_Type.Blood_Id\r\n" +
                "Union\r\n" +
                "select User_Id ,First_Name ,Last_Name  , Blood_Code as Blood_Type , City , District , Neighborhood, Phone_No \r\n" +
                "from User , Status , Address , Recipient , Blood_Type\r\n" +
                "where Recipient.Recipient_Id = User.User_Id and User.Address_Id = Address.Address_Id and Recipient.Blood_Id = Blood_Type.Blood_Id\r\n" +
                "order by User_Id");
        while(myResultset.next())
        {
            if(user_id==myResultset.getInt(1))
            {
                namedash = new JLabel(myResultset.getString(2));
                surnamedash = new JLabel(myResultset.getString(3));
                phonedash = new JLabel(myResultset.getString(8));
                blooddash = new JLabel(myResultset.getString(4));
                citydash = new JLabel(myResultset.getString(5));
                districtdash = new JLabel(myResultset.getString(6));
                neighbourdash = new JLabel(myResultset.getString(7));

            }
        }

        Database_Connection.close();
        myStatement.close();
        myResultset.close();


        btnDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    main_content.removeAll();
                    JLabel dash_label = new JLabel(" Dashboard");
                    dash_label.setForeground(Color.WHITE);
                    dash_label.setFont(new Font("Tahoma", Font.BOLD, 33));
                    dash_label.setHorizontalAlignment(SwingConstants.CENTER);
                    dash_label.setBounds(200, 0, 231, 72);
                    main_content.add(dash_label);

                    nameLabel = new JLabel("Name:");
                    nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                    nameLabel.setForeground(Color.white);
                    nameLabel.setBounds(21, 100, 69, 35);
                    main_content.add(nameLabel);

                    surnameLabel = new JLabel("Surname:");
                    surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                    surnameLabel.setForeground(Color.white);
                    surnameLabel.setBounds(400, 100, 100, 35);
                    main_content.add(surnameLabel);

                    phonenumberLabel = new JLabel("Phone number:");
                    phonenumberLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                    phonenumberLabel.setForeground(Color.WHITE);
                    phonenumberLabel.setBounds(21, 200, 200, 39);
                    main_content.add(phonenumberLabel);

                    lblBloodType = new JLabel("Blood type:");
                    lblBloodType.setFont(new Font("Tahoma", Font.BOLD, 18));
                    lblBloodType.setForeground(Color.white);
                    lblBloodType.setBounds(21, 300, 200, 35);
                    main_content.add(lblBloodType);

                    cityLabel = new JLabel("City:");
                    cityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                    cityLabel.setForeground(Color.white);
                    cityLabel.setBounds(21, 400, 100, 35);
                    main_content.add(cityLabel);

                    districtLabel = new JLabel("District:");
                    districtLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                    districtLabel.setForeground(Color.white);
                    districtLabel.setBounds(230, 400, 100, 35);
                    main_content.add(districtLabel);

                    neighborhoodLabel = new JLabel("Neighborhood:");
                    neighborhoodLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                    neighborhoodLabel.setForeground(Color.white);
                    neighborhoodLabel.setBounds(450, 400, 200, 35);
                    main_content.add(neighborhoodLabel);

                            namedash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            namedash.setForeground(Color.white);
                            namedash.setBounds(100, 100, 100, 35);
                            main_content.add(namedash);

                            surnamedash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            surnamedash.setForeground(Color.white);
                            surnamedash.setBounds(500, 100, 100, 35);
                            main_content.add(surnamedash);

                            phonedash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            phonedash.setForeground(Color.white);
                            phonedash.setBounds(180, 200, 200, 35);
                            main_content.add(phonedash);

                            blooddash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            blooddash.setForeground(Color.white);
                            blooddash.setBounds(180, 300, 200, 35);
                            main_content.add(blooddash);

                            citydash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            citydash.setForeground(Color.white);
                            citydash.setBounds(80, 400, 200, 35);
                            main_content.add(citydash);

                            districtdash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            districtdash.setForeground(Color.white);
                            districtdash.setBounds(320, 400, 200, 35);
                            main_content.add(districtdash);

                            neighbourdash.setFont(new Font("Tahoma", Font.BOLD, 20));
                            neighbourdash.setForeground(Color.white);
                            neighbourdash.setBounds(600, 400, 200, 35);
                            main_content.add(neighbourdash);


                    main_content.repaint();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btncenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Profile_Admin myProfileAdmin;
                Donation_centers d_center;
                try {
                    d_center = new Donation_centers(user_id, status_id);
                    d_center.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    main_content.removeAll();
                    JLabel edit_label = new JLabel("Edit Profile");
                    edit_label.setForeground(Color.WHITE);
                    edit_label.setFont(new Font("Tahoma", Font.BOLD, 33));
                    edit_label.setHorizontalAlignment(SwingConstants.CENTER);
                    edit_label.setBounds(200, 0, 231, 72);

                    Update_Donor_Recipient_Frame updateFrame = new Update_Donor_Recipient_Frame(user_id,status_id);
                    updateFrame.setVisible(true);

                    dispose();
                }

                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }});
        //end btnedit

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    main_content.removeAll();
                    JLabel del_label = new JLabel("Delete Profile");
                    del_label.setForeground(Color.WHITE);
                    del_label.setFont(new Font("Tahoma", Font.BOLD, 33));
                    del_label.setHorizontalAlignment(SwingConstants.CENTER);
                    del_label.setBounds(200, 0, 231, 72);

                    Database_Connection = myDataBase.Connect_to_DataBase();
                    myPreparedStatement = Database_Connection.prepareStatement("DELETE FROM USER WHERE user.USER_ID=? ");
                    myPreparedStatement.setInt(1,user_id);
                    myPreparedStatement.execute();
                    dispose();


                    Database_Connection.close();
                    myPreparedStatement.close();

                    main_content.add(del_label);
                    main_content.repaint();

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

