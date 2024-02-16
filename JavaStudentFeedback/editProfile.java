import MyPack.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.*;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.regex.*;
import java.awt.Font;
import java.awt.event.*;

public class editProfile extends JFrame implements ActionListener, KeyListener {

    JLabel l_header;
    JLabel l_userName;
    JLabel l_mobileNo;
    JLabel l_email;
    JLabel l_address;
    JLabel l_gender;
    JLabel l_qualification;
    JLabel l_nationality;
    JLabel l_password;

    JTextField t_userName;
    JTextField t_mobileNo;
    JTextField t_email;
    JTextArea t_address;
    JRadioButton m_gender;
    JRadioButton f_gender;
    JRadioButton o_gender;
    JComboBox<String> qualification;
    String[] qual = { "Select", "10th", "12th", "BCA", "MCA", "Other" };

    JComboBox<String> nationality;
    String nation[] = { "Select", "Indian", "Other" };
    JPasswordField password;

    JCheckBox showpass;
    JCheckBox updateEmail;
    JButton b_cancel;
    JButton b_sumbit;
    JButton b_reset;

    String gen = "";
    String qua = "Select";
    String nat = "Select";
    boolean gflag = true;
    boolean fi = false;
    boolean fc = false;
    boolean updateWithEmail = false;

    resisterData obj = new resisterData();
    String unicid = "";
    String email = "";
    String pass = "";

    public editProfile(String[] dataofuser) {
        super("Edit Profile");

        email = dataofuser[3];
        pass = dataofuser[2];

        l_header = new JLabel("Update Your Account");
        l_header.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
        l_header.setForeground(Color.BLUE);

        l_userName = new JLabel("User Name");
        l_userName.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_mobileNo = new JLabel("Mobile Number");
        l_mobileNo.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_email = new JLabel("E-mail");
        l_email.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_address = new JLabel("Address");
        l_address.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_gender = new JLabel("Gender");
        l_gender.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_qualification = new JLabel("Qualification");
        l_qualification.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_nationality = new JLabel("Nationality");
        l_nationality.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        l_password = new JLabel("Change Password");
        l_password.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        /////////////////////////////////////////////
        t_userName = new JTextField();
        t_userName.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        t_mobileNo = new JTextField();
        t_mobileNo.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        t_email = new JTextField();
        t_email.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        t_email.setEditable(false);

        t_address = new JTextArea();
        t_address.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        t_address.setLineWrap(true);
        f_gender = new JRadioButton("Female");
        m_gender = new JRadioButton("Male");
        o_gender = new JRadioButton("Other");
        ButtonGroup bg = new ButtonGroup();
        bg.add(m_gender);
        bg.add(f_gender);
        bg.add(o_gender);

        qualification = new JComboBox<>(qual);
        nationality = new JComboBox<>(nation);
        password = new JPasswordField();
        password.setEchoChar('*');
        password.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        showpass = new JCheckBox("Show Password");

        updateEmail = new JCheckBox("Update Email");
        b_cancel = new JButton("Go Back");
        b_reset = new JButton("Clear Fields");
        b_sumbit = new JButton("Update Profile");

        setLayout(null);
        // Setting Bounds for all
        l_header.setBounds(20, 50, 280, 30);
        l_userName.setBounds(60, 100, 100, 15);
        l_mobileNo.setBounds(60, 130, 150, 15);
        l_email.setBounds(60, 160, 150, 15);
        l_address.setBounds(60, 215, 150, 15);
        l_gender.setBounds(60, 302, 150, 15);
        l_qualification.setBounds(60, 332, 150, 15);
        l_nationality.setBounds(60, 362, 150, 15);
        l_password.setBounds(60, 392, 150, 15);

        t_userName.setBounds(200, 97, 250, 25);
        t_mobileNo.setBounds(200, 127, 250, 25);
        t_email.setBounds(200, 157, 250, 25);
        updateEmail.setBounds(200, 182, 130, 20);

        t_address.setBounds(200, 214, 250, 80);
        m_gender.setBounds(200, 302, 60, 20);
        f_gender.setBounds(260, 302, 80, 20);
        o_gender.setBounds(340, 302, 70, 20);
        qualification.setBounds(200, 332, 100, 20);
        nationality.setBounds(200, 362, 100, 20);
        password.setBounds(200, 392, 250, 25);
        showpass.setBounds(200, 417, 130, 20);

        b_cancel.setBounds(100, 460, 105, 30);
        b_reset.setBounds(215, 460, 100, 30);
        b_sumbit.setBounds(330, 460, 150, 30);

        // Setting Action Command
        t_mobileNo.setActionCommand("txt2");
        // t_email.setActionCommand("txt3");
        b_cancel.setActionCommand("cancel");
        b_reset.setActionCommand("reset");
        b_sumbit.setActionCommand("submit");
        qualification.setActionCommand("qualification");
        nationality.setActionCommand("nationality");

        // Resistering Listener
        showpass.addActionListener(this);
        b_cancel.addActionListener(this);
        b_reset.addActionListener(this);
        b_sumbit.addActionListener(this);
        t_mobileNo.addKeyListener(this);
        m_gender.addActionListener(this);
        f_gender.addActionListener(this);
        o_gender.addActionListener(this);
        qualification.addActionListener(this);
        nationality.addActionListener(this);
        updateEmail.addActionListener(this);

        // Handling Windows Event Using Adapter Class
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeFunction();
            }
        });

        add(l_header);
        add(l_userName);
        add(l_mobileNo);
        add(l_email);
        add(l_address);
        add(l_gender);
        add(l_qualification);
        add(l_nationality);
        add(l_password);

        add(t_userName);
        add(t_mobileNo);
        add(t_email);
        add(t_address);
        add(m_gender);
        add(f_gender);
        add(o_gender);
        add(qualification);
        add(nationality);
        add(password);
        add(showpass);
        add(b_cancel);
        add(b_reset);
        add(b_sumbit);
        add(updateEmail);

        b_reset.setBackground(Color.red);

        setSize(550, 650);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUserdata(dataofuser);

    }

    public void setUserdata(String data[]) {
        int indexofquali = 0;
        int indexofnation = 0;
        unicid = data[0];
        t_userName.setText(data[1]);
        password.setText(data[2]);
        t_email.setText(data[3]);
        t_mobileNo.setText(data[4]);
        t_address.setText(data[5]);

        if (data[6].equals("Male")) {

            m_gender.setSelected(true);
            f_gender.setSelected(false);
            o_gender.setSelected(false);
            gen = "Male";
            gflag = false;
        }
        if (data[6].equals("Female")) {
            m_gender.setSelected(false);
            f_gender.setSelected(true);
            o_gender.setSelected(false);
            gen = "Female";
            gflag = false;

        }
        if (data[6].equals("Other")) {
            m_gender.setSelected(false);
            f_gender.setSelected(false);
            o_gender.setSelected(true);
            gen = "Other";
            gflag = false;

        }
        if (data[7].equals("10th")) {
            indexofquali = 0;
        }
        if (data[7].equals("12th")) {
            indexofquali = 1;
        }
        if (data[7].equals("BCA")) {
            indexofquali = 2;
        }
        if (data[7].equals("MCA")) {
            indexofquali = 3;
        }
        if (data[7].equals("Other")) {
            indexofquali = 4;
        }
        if (data[8].equals("Indian")) {
            indexofnation = 0;
        }
        if (data[8].equals("Other")) {
            indexofnation = 1;
        }
        qualification.setSelectedIndex(indexofquali);
        nationality.setSelectedIndex(indexofnation);

    }

    String removeExtraSpaces(String str) {
        return str.replaceAll("\\s+", " ").trim();
    }

    boolean gohead(String str) {
        String emailRegx = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pt = Pattern.compile(emailRegx);
        if (pt.matcher(str).matches()) {
            return false;
        } else {
            return true;
        }
    }

    void validateDataWithoutEmail() {
        String username = removeExtraSpaces(t_userName.getText().trim());
        String mobno = t_mobileNo.getText();

        String pass = new String(password.getPassword());
        String address = removeExtraSpaces(t_address.getText().trim());
        String gender = gen;
        String qual = qua;
        String nation = nat;

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User Name is Empty", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (mobno.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mobile Number is Empty", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (mobno.length() != 10) {
                JOptionPane.showMessageDialog(this, "Mobile Number Must be 10 digit ", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // For Address

        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address is Empty", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // For Gender
        if (gflag) {
            JOptionPane.showMessageDialog(this, "Please Choose your gender", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // For Password
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose your password", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (pass.length() < 6) {

                JOptionPane.showMessageDialog(this, "Password must be at least 6 character", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        fi = obj.dataForupdate(unicid, username, mobno, address, pass, gender, qual, nation);
        if (fi == true) {
            JOptionPane.showMessageDialog(this,
                    "Hello " + username + "\nProfile Update successfully",
                    "Update Status",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this,
                    "\nUpdate Unsuccessfull\nContact developer",
                    "Update Status",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    void validateData() {
        String username = removeExtraSpaces(t_userName.getText().trim());
        String mobno = t_mobileNo.getText();
        String email = removeExtraSpaces(t_email.getText().trim());
        String pass = new String(password.getPassword());
        String address = removeExtraSpaces(t_address.getText().trim());
        String gender = gen;
        String qual = qua;
        String nation = nat;
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User Name is Empty", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (mobno.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mobile Number is Empty", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (mobno.length() != 10) {
                JOptionPane.showMessageDialog(this, "Mobile Number Must be 10 digit ", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter e-mail address", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (gohead(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid e-mail", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // For Address
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address is Empty", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // For Gender
        if (gflag) {
            JOptionPane.showMessageDialog(this, "Please Choose your gender", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // For Password
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose your password", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (pass.length() < 6) {

                JOptionPane.showMessageDialog(this, "Password must be at least 6 character", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        fi = obj.dataForupdateWithEmail(unicid, username, mobno, email, address, pass, gender, qual, nation);
        fc = obj.uniqemail();
        if (fi != true && fc != true) {

            JOptionPane.showMessageDialog(this,
                    "\nUser already register with this email\n" + email,
                    "Update Status",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (fi) {
            JOptionPane.showMessageDialog(this,
                    "Hello " + username + "\nProfile update successfully",
                    "Update Status",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this,
                    "\nUpdate Unsuccessfull\nContact developer",
                    "Update Status",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

    }

    void clearFields() {
        t_userName.setText("");
        t_mobileNo.setText("");
        t_address.setText("");
        qualification.setSelectedIndex(0);
        nationality.setSelectedIndex(0);
    }

    void closeFunction() {
        dispose();
        email = t_email.getText();
        pass = new String(password.getPassword());
        String arrstr[] = resisterData.validateUserDetails(email, pass);
        String feedbackData = resisterData.getFeedBack(arrstr[0]);
        new afterLogin(arrstr, feedbackData);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "submit") {
            if (updateWithEmail) {
                validateData();
            } else {
                validateDataWithoutEmail();
            }
            // if (fi == true && fc == true) {
            // clearFields();
            // }
        }
        if (e.getActionCommand() == "cancel") {
            closeFunction();
        }
        if (e.getActionCommand() == "reset") {
            clearFields();
        }

        if (showpass.isSelected()) {

            password.setEchoChar((char) 0);

        } else {
            password.setEchoChar('*');
        }
        // Update check box
        if (updateEmail.isSelected()) {
            updateWithEmail = true;
            t_email.setEditable(true);
        } else {
            updateWithEmail = false;
            t_email.setEditable(false);
        }
        // Gender Data
        if (e.getActionCommand() == "Male") {
            gen = "Male";
            gflag = false;
        }
        if (e.getActionCommand() == "Female") {
            gen = "Female";
            gflag = false;

        }
        if (e.getActionCommand() == "Other") {
            gen = "Other";
            gflag = false;

        }

        // qualification
        if (e.getActionCommand() == "qualification") {
            qua = (String) qualification.getSelectedItem();
        }
        if (e.getActionCommand() == "nationality") {
            nat = (String) nationality.getSelectedItem();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String pn = t_mobileNo.getText();
        int len = pn.length();

        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            if (len < 10) {
                t_mobileNo.setEditable(true);
            } else {
                t_mobileNo.setEditable(false);
            }
        } else {
            if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                t_mobileNo.setEditable(true);
            } else {
                t_mobileNo.setEditable(false);

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}