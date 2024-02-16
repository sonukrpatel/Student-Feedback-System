import MyPack.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;

class MyFrame extends JFrame implements ActionListener {
    JLabel L_logintitel;
    JLabel L_user_name;
    JLabel L_password;
    JTextField tf_user_name;
    JPasswordField tf_password;

    JButton b_login;
    JButton b_sinup;
    JButton b_fpassword;
    JCheckBox showpass;

    MyFrame() {
        super("Login");

        L_logintitel = new JLabel("Log In");
        L_logintitel.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
        L_logintitel.setForeground(Color.magenta);

        L_user_name = new JLabel("User Email");
        L_user_name.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        L_password = new JLabel("Password ");
        L_password.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        tf_user_name = new JTextField(15);
        tf_user_name.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        tf_password = new JPasswordField(15);
        tf_password.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        tf_password.setEchoChar('*');

        showpass = new JCheckBox("Show Password");

        b_login = new JButton("Log In");
        b_sinup = new JButton("Sin Up");
        b_fpassword = new JButton("Forgot Password");

        L_logintitel.setBounds(60, -5, 200, 200);
        L_user_name.setBounds(80, 132, 100, 15);
        tf_user_name.setBounds(180, 130, 250, 25);

        L_password.setBounds(80, 162, 100, 15);
        tf_password.setBounds(180, 160, 250, 25);

        showpass.setBounds(180, 185, 130, 20);

        b_sinup.setBounds(130, 230, 70, 30);
        b_login.setBounds(270, 230, 70, 30);
        b_fpassword.setBounds(270, 270, 140, 20);

        setLayout(null);

        tf_password.setActionCommand("login");
        tf_user_name.setActionCommand("nextbox");
        b_login.setActionCommand("login");
        b_sinup.setActionCommand("sinup");
        b_fpassword.setActionCommand("forgotpassword");

        b_login.addActionListener(this);
        b_sinup.addActionListener(this);
        b_fpassword.addActionListener(this);
        tf_password.addActionListener(this);
        tf_user_name.addActionListener(this);
        showpass.addActionListener(this);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });

        add(L_logintitel);
        add(L_user_name);
        add(tf_user_name);
        add(L_password);
        add(tf_password);
        add(b_sinup);
        add(b_login);
        add(b_fpassword);
        add(showpass);
        getContentPane().setBackground(Color.CYAN); 
        setResizable(false);
        setSize(580, 420);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    boolean validateField() {
        String username = tf_user_name.getText();
        String ps1 = new String(tf_password.getPassword());
        if (ps1.isEmpty() || username.isEmpty()) {

            if (ps1.isEmpty() && username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter user name and password first", "Show Password",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (ps1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password cannot be blank", "Show Password",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "User Name is empty", "Show Password",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "sinup") {
            new sinup();
        }
        if (e.getActionCommand() == "login") {
            if (validateField()) {
                // login
                String u_email = tf_user_name.getText();
                String ps1 = new String(tf_password.getPassword());
                String arrstr[] = resisterData.validateUserDetails(u_email, ps1);
                String feedbackData=resisterData.getFeedBack(arrstr[0]);
                if (arrstr[9] == "false") {
                   
                    if (u_email.equals(arrstr[3])) {
                        JOptionPane.showMessageDialog(this, "Wrong password", "Login Alert",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (ps1.equals(arrstr[2])) {
                        JOptionPane.showMessageDialog(this, "Invalid email id", "Login Alert",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (u_email != arrstr[3] && ps1 != arrstr[2]) {
                        JOptionPane.showMessageDialog(this, "invalid credentials", "Login Alert",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } else {
                    setVisible(false);
                    new afterLogin(arrstr,feedbackData);
                
                }

            }
        }
        if (e.getActionCommand() == "forgotpassword") {

            forgot_password fp = new forgot_password();
            fp.getusername(tf_user_name.getText());

        }
        if (e.getActionCommand() == "nextbox") {
            tf_password.requestFocus();
        }
        if (showpass.isSelected()) {

            tf_password.setEchoChar((char) 0);
        } else {
            tf_password.setEchoChar('*');
        }

    }

}

public class login {
    public static void main(String[] args) {
        new MyFrame();
        Connection newcon = cp.createCon();
        new resisterData(newcon);
    }
}