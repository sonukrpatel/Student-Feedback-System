import MyPack.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class afterLogin extends JFrame implements ActionListener {
    static String userData[] = { "", "", "", "", "", "", "", "", "", "" };
    String name = userData[1];

    JLabel lu_id;
    JLabel lu_name;
    JLabel lu_cont;
    JLabel lu_email;
    JLabel lu_address;
    JLabel lu_gender;
    JLabel lu_qual;
    JLabel lu_nation;

    JTextArea tfForAddress;
	
	
	//FEEDBACK FORM:
	JTextArea tfForFeedback;
	JLabel lfForFeedback;
	JButton bfForFeedback;

    //View Feedback
    JTextArea tfForViewFeedback;
	JLabel lfForViewFeedback;

    JButton bEditProfile;
    JButton bLogOut;
    JButton bViewFB;
    JButton bGiveFB;
    JButton bChangePassword;

    JComboBox<String> bMyAccount;
    String[] MyAccountItem = { "My Profile", "Delete My Account", "Change Password", "Logout" };

    JPanel pforbutton;
    JPanel pforSideImage;
    JLabel limg;
    JPanel pforUserDetails;

    JPanel pforMainPannel;

    JPanel pForMyProfile;
    JPanel pForGiveFeedback;
    JPanel pForViewFeedback;
    JPanel pForDeleteMyAccount;

    // Components for Delete Account
    JLabel ldEmail;
    JTextField tdEmail;
    JButton bdMyAccount;

    public afterLogin(String arrData[],String str) {

        showData(arrData);
        
        // GUI Code For AfterLogin Form
        setTitle("Welcome " + userData[1]);
        lu_id = new JLabel("Id No: " + "BU2022-RN" + userData[0]);
        lu_id.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_name = new JLabel(userData[1]);
        lu_name.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_email = new JLabel("E-Mail: " + userData[3]);
        lu_email.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_cont = new JLabel("Mob: " + userData[4]);
        lu_cont.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_address = new JLabel("Address:");
        lu_address.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_gender = new JLabel("Gender: " + userData[6]);
        lu_gender.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_qual = new JLabel("Qualification: " + userData[7]);
        lu_qual.setFont(new Font("Bell MT", Font.PLAIN, 20));

        lu_nation = new JLabel("Nation: " + userData[8]);
        lu_nation.setFont(new Font("Bell MT", Font.PLAIN, 20));

        pForMyProfile = new JPanel();
        setLayout(null);

        bEditProfile = new JButton("Edit Profile");
        bViewFB = new JButton("View Feedback");
        // bViewFB.setBackground(Color.cyan);
        bGiveFB = new JButton("Give Feedback");
        bLogOut = new JButton("Logout");

        // Combo box
        bMyAccount = new JComboBox<>(MyAccountItem);

        bEditProfile.setBounds(5, 3, 100, 30);
        bViewFB.setBounds(105, 3, 120, 30);
        bGiveFB.setBounds(225, 3, 120, 30);
        bMyAccount.setBounds(650, 3, 130, 30);

        pforbutton = new JPanel();
        pforbutton.setLayout(null);
        pforbutton.setBackground(Color.CYAN);
        pforbutton.setBounds(0, 30, 785, 35);
        pforbutton.add(bViewFB);
        pforbutton.add(bEditProfile);
        pforbutton.add(bGiveFB);
        pforbutton.add(bMyAccount);

        pforSideImage = new JPanel();
        pforSideImage.setLayout(null);
        pforSideImage.setBackground(Color.CYAN);
        pforSideImage.setBounds(430, 30, 250, 250);

        limg = new JLabel("");
        limg.setIcon(new ImageIcon("logo.png"));
        limg.setBounds(0, 0, 250, 250);
        pforSideImage.add(limg);

        pforUserDetails = new JPanel();
        pforUserDetails.setLayout(null);
        // pforUserDetails.setBackground(Color.CYAN);
        pforUserDetails.setBounds(0, 0, 350, 320);
        pforUserDetails.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan, 4, true),
                "My Profile", TitledBorder.RIGHT, TitledBorder.CENTER));

        lu_name.setBounds(10, 10, 300, 20);
        lu_name.setForeground(Color.BLUE);
        lu_id.setBounds(10, 35, 300, 20);
        lu_cont.setBounds(10, 60, 300, 20);
        lu_email.setBounds(10, 85, 300, 20);
        lu_nation.setBounds(10, 110, 300, 20);
        lu_gender.setBounds(10, 135, 300, 20);
        lu_qual.setBounds(10, 160, 300, 20);
        lu_address.setBounds(10, 185, 300, 20);

        tfForAddress = new JTextArea(userData[5]);
        tfForAddress.setLineWrap(true);
        tfForAddress.setBounds(25, 210, 300, 100);
        tfForAddress.setFont(new Font("Bell MT", Font.PLAIN, 20));
        tfForAddress.setEditable(false);
        tfForAddress.setBackground(Color.LIGHT_GRAY);

        pforUserDetails.add(lu_name);
        pforUserDetails.add(lu_id);
        pforUserDetails.add(lu_cont);
        pforUserDetails.add(lu_email);
        pforUserDetails.add(lu_address);
        pforUserDetails.add(tfForAddress);
        pforUserDetails.add(lu_gender);
        pforUserDetails.add(lu_qual);
        pforUserDetails.add(lu_nation);

        // Adding user data and pic to main pannel
        pForMyProfile.setLayout(null);
        pForMyProfile.setBounds(0, 0, 700, 320);
        // pForMyProfile.setBackground(Color.red);

        pForMyProfile.add(pforUserDetails);
        pForMyProfile.add(pforSideImage);

        pforMainPannel = new JPanel();
        pforMainPannel.setLayout(null);// defining layout for main pannel as card Layout
        pforMainPannel.setBounds(30, 100, 700, 320);

		 //=============================================================

        // Components for Other Tabs
        // Now Creating Pannel for Givefb Viewfb DelAccount...
        // Give Feedback
        pForGiveFeedback = new JPanel();
        pForGiveFeedback.setLayout(null);
        pForGiveFeedback.setVisible(false);
        pForGiveFeedback.setBounds(0, 0, 700, 320);
        pForGiveFeedback.setBackground(Color.CYAN);
		tfForFeedback=new JTextArea();
        tfForFeedback.setLineWrap(true);
        tfForFeedback.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		tfForFeedback.setBounds(20,70,500,200);
		 pForGiveFeedback.add(tfForFeedback); 
		 lfForFeedback=new JLabel("Give Feedback Here");
		 lfForFeedback.setBounds(20,20,300,50);
		 pForGiveFeedback.add(lfForFeedback);
		 bfForFeedback=new JButton("SUBMIT");
		 bfForFeedback.setBounds(20,280,130,30);
		 pForGiveFeedback.add(bfForFeedback);
		 //=============================================================
        // View Feedback
        pForViewFeedback = new JPanel();
        pForViewFeedback.setLayout(null);
        
        pForViewFeedback.setVisible(false);
        pForViewFeedback.setBounds(0, 0, 700, 320);
        pForViewFeedback.setBackground(Color.CYAN);
        lfForViewFeedback=new JLabel("Your Feedback is");
        lfForViewFeedback.setBounds(20,20,300,50);
        pForViewFeedback.add(lfForViewFeedback);

        tfForViewFeedback=new JTextArea();
        tfForViewFeedback.setBounds(20,70,500,200);
        tfForViewFeedback.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        pForViewFeedback.add(tfForViewFeedback);
        setFeedback(str);

        // Delete My Account
        pForDeleteMyAccount = new JPanel();
        pForDeleteMyAccount.setLayout(null);
        ldEmail = new JLabel("Enter Email");
        ldEmail.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        ldEmail.setBounds(90, 20, 150, 30);
        tdEmail = new JTextField(15);
        tdEmail.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        tdEmail.setBounds(200, 20, 200, 30);
        tdEmail.setActionCommand("Delete My Account");
        tdEmail.addActionListener(this);
        bdMyAccount = new JButton("Delete My Account");
        bdMyAccount.addActionListener(this);
        bdMyAccount.setBounds(200, 55, 150, 30);
        pForDeleteMyAccount.add(ldEmail);
        pForDeleteMyAccount.add(tdEmail);
        pForDeleteMyAccount.add(bdMyAccount);
        pForDeleteMyAccount.setVisible(false);
        pForDeleteMyAccount.setBounds(0, 0, 700, 320);
        // pForDeleteMyAccount.setBackground(Color.green);

        // Adding component to main pannel
        pforMainPannel.add(pForDeleteMyAccount);
        pforMainPannel.add(pForViewFeedback);
        pforMainPannel.add(pForGiveFeedback);
        pforMainPannel.add(pForMyProfile);

        // Resitering Listener
        bMyAccount.addActionListener(this);
        bEditProfile.addActionListener(this);
        bViewFB.addActionListener(this);
        bGiveFB.addActionListener(this);
		bfForFeedback.addActionListener(this);
        // code for Give FeedBack Form

        add(pforbutton);
        add(pforMainPannel);

        setResizable(false);
        setSize(800, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showData(String arrData[]) {
        for (int i = 0; i < arrData.length; i++) {
            userData[i] = arrData[i];
        }

    }
    void setFeedback(String str)
    {
        tfForViewFeedback.setText(str);
        tfForViewFeedback.setEditable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action for Combo Box
        if (e.getActionCommand() == "comboBoxChanged") {
            String selectedItem = (String) bMyAccount.getSelectedItem();
            if (selectedItem == "My Profile") {
                System.out.println("Profile");
                pForDeleteMyAccount.setVisible(false);
                pForViewFeedback.setVisible(false);
                pForGiveFeedback.setVisible(false);
                pForMyProfile.setVisible(true);
            }
            if (selectedItem == "Delete My Account") {
                System.out.println("Delete My Account");
                pForMyProfile.setVisible(false);
                pForViewFeedback.setVisible(false);
                pForGiveFeedback.setVisible(false);
                pForDeleteMyAccount.setVisible(true);
            }
            if (selectedItem == "Change Password") {
                dispose();
                new editProfile(userData);
            }
            if (selectedItem == "Logout") {
                int result = JOptionPane.showConfirmDialog(this, "Are you sure want to logout ?", "Alert",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    dispose();
                    new MyFrame();
                    Connection newcon = cp.createCon();
                    new resisterData(newcon);
                }
            }

        }

        // Edit Profile
        if (e.getActionCommand() == "Edit Profile") {
            dispose();
            new editProfile(userData);

        }
        // View Feedback
        if (e.getActionCommand() == "View Feedback") {
            String feedbackData=resisterData.getFeedBack(userData[0]);
            setFeedback(feedbackData);
            pForMyProfile.setVisible(false);
            pForDeleteMyAccount.setVisible(false);
            pForGiveFeedback.setVisible(false);
            pForViewFeedback.setVisible(true);

        }
        // Give Feedback
        if (e.getActionCommand() == "Give Feedback") {
            pForMyProfile.setVisible(false);
            pForDeleteMyAccount.setVisible(false);
            pForViewFeedback.setVisible(false);
            pForGiveFeedback.setVisible(true);

        }
        if (e.getActionCommand() == "Delete My Account") {
            if (userData[3].equals(tdEmail.getText())) {
                int result = JOptionPane.showConfirmDialog(this, "Are you sure want to Delete Your Account ?",
                        "Delete Alert",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    resisterData obj=new resisterData();
                   if( obj.deleteMyAccount(userData[0]))
                   {
                    JOptionPane.showMessageDialog(this, "Account Deleted Successfully", "Delete Alert",
                    JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MyFrame();
                    Connection newcon = cp.createCon();
                    new resisterData(newcon);
                   }
                   else{
                    JOptionPane.showMessageDialog(this, "Account Not Deleted \nFunction return False Value", "Delete Alert",
                    JOptionPane.INFORMATION_MESSAGE);
                   }

                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter your correct email\n"+tdEmail.getText()+" is not match with your profile", "Delete Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
		if(e.getActionCommand() =="SUBMIT")
		{
			System.out.println(tfForFeedback.getText());
			resisterData obj=new resisterData();
			if(obj.givefeedback(tfForFeedback.getText(),userData[0]))
			{
				JOptionPane.showMessageDialog(this, "Feedback successfuly given", "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				System.out.println("contact developer");
			}
		}
    }
}
