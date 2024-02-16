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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forgot_password extends JFrame implements ActionListener {
    JLabel L_logintitel;
    JLabel L_user_name;
    JLabel L_password;
    JLabel L_password2;

    JTextField tf_user_email;
    JPasswordField tf_password;
    JPasswordField tf_password2;

    JLabel test;

    JButton b_changepassword;
    JButton b_cancel;
    JCheckBox showpass;

    String password = "";

    public forgot_password() {
        super("Forgot Password");

        L_logintitel = new JLabel("Forgot Password");
        L_logintitel.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
        L_logintitel.setForeground(Color.magenta);

        L_user_name = new JLabel("Enter User Email");
        L_user_name.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        L_password = new JLabel("New Password ");
        L_password.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        L_password2 = new JLabel("Confirm Password");
        L_password2.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        tf_user_email = new JTextField(15);
        tf_user_email.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));

        tf_password = new JPasswordField(15);
        tf_password.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        tf_password.setEchoChar('*');

        tf_password2 = new JPasswordField(15);
        tf_password2.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
        tf_password2.setEchoChar('*');

        showpass = new JCheckBox("Show Password");

        b_changepassword = new JButton("Change Password");
        b_cancel = new JButton("Close");

        setLayout(null);

        L_logintitel.setBounds(60, -5, 400, 200);
        L_user_name.setBounds(80, 132, 150, 15);
        L_password.setBounds(80, 162, 150, 15);
        L_password2.setBounds(80, 192, 150, 15);

        tf_user_email.setBounds(220, 130, 250, 25);
        tf_password.setBounds(220, 160, 250, 25);
        tf_password2.setBounds(220, 190, 250, 25);

        showpass.setBounds(220, 215, 130, 20);

        b_cancel.setBounds(150, 260, 80, 30);
        b_changepassword.setBounds(270, 260, 150, 30);

        tf_user_email.setActionCommand("gobox2");
        tf_password.setActionCommand("gobox3");
        tf_password2.setActionCommand("forgotpassword");
        b_changepassword.setActionCommand("forgotpassword");
        b_cancel.setActionCommand("cancel");

        b_changepassword.addActionListener(this);
        b_cancel.addActionListener(this);
        tf_user_email.addActionListener(this);
        tf_password.addActionListener(this);
        tf_password2.addActionListener(this);
        showpass.addActionListener(this);

        add(L_logintitel);
        add(L_user_name);
        add(tf_user_email);
        add(L_password);
        add(tf_password);
        add(L_password2);
        add(tf_password2);
        add(b_cancel);
        add(b_changepassword);
        add(showpass);

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    boolean isMatched(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }

    boolean validatePassword() {
        String ps1 = new String(tf_password.getPassword());
        String ps2 = new String(tf_password2.getPassword());
        String username = tf_user_email.getText();
        if (ps1.isEmpty() || ps2.isEmpty() || username.isEmpty()) {
            if (ps1.isEmpty() && ps2.isEmpty() && username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter user name and password first", "Show Password",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (ps1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password cannot be blank", "Show Password",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if (ps2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Confirm Password cannot be blank\nAgain Enter Confirm Password",
                        "Show Password", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "User Name is empty", "Show Password",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } else {

            if (isMatched(ps1, ps2)) {
                if (ps1.length() < 6) {

                    JOptionPane.showMessageDialog(this, "Password must be at least 6 character", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                } else {
                    return true;
                }

            } else {
                JOptionPane.showMessageDialog(this, "New password and confirm password are not same.",
                        "Show Password", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return false;
    }

    public void getusername(String userdata) {
        tf_user_email.setText(userdata);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "forgotpassword") {
            if (validatePassword()) {
                resisterData obj=new resisterData();
                String ps = new String(tf_password2.getPassword());
                boolean statusForgot=obj.forgotPassword(tf_user_email.getText(),ps);
                if(statusForgot)
                {
                    JOptionPane.showMessageDialog(this, "Password reset successfully",
                        "Password Change Status", JOptionPane.INFORMATION_MESSAGE);

                }
                else
                {
                    System.out.println("Something went wrong Need to check Developer");
                    JOptionPane.showMessageDialog(this, "Email Not matched with any account",
                        "Password Change Status", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (e.getActionCommand() == "cancel") {
            System.out.println("cancel");
            dispose();
        }

        if (e.getActionCommand() == "gobox2") {
            tf_password.requestFocus();
        }
        if (e.getActionCommand() == "gobox3") {
            tf_password2.requestFocus();
        }
        if (showpass.isSelected()) {

            tf_password.setEchoChar((char) 0);
            tf_password2.setEchoChar((char) 0);
        } else {
            tf_password.setEchoChar('*');
            tf_password2.setEchoChar('*');
        }

    }
}