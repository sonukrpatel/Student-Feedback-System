package MyPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

public class resisterData {
    static Connection newcon;

    boolean uniqe = true;
    int datafound = 1;

    public resisterData(Connection newco) {
        newcon = newco;
    }

    public resisterData() {

    }

    public boolean checkForUniEmail(String str) {
        String query = "select * from userData1";

        try {
            Statement st = newcon.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                if (str.equals(rs.getString(4))) {
                    datafound = rs.getInt(1);
                    uniqe = false;
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("From checkForUniEmail: " + e);
        }

        return true;
    }

    public boolean uniqemail() {
        return uniqe;
    }

    // Function for inserting data
    public boolean dataForSubmit(String username, String mobno, String email, String address, String pass,
            String gender,
            String qual, String nation) {

        boolean flagsubmit = false;
        if (checkForUniEmail(email)) {
            try {
                String que = "insert into userdata1 values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = newcon.prepareStatement(que);

                ps.setInt(1, 0);// auto increment
                ps.setString(2, username);
                ps.setString(3, pass);
                ps.setString(4, email);
                ps.setString(5, mobno);
                ps.setString(6, address);
                ps.setString(7, gender);
                ps.setString(8, qual);
                ps.setString(9, nation);
                ps.executeUpdate();
                flagsubmit = true;
            } catch (Exception e) {
                System.out.println("Wrong syntax" + e);
            }
        }
        return flagsubmit;
    }

    // Function for login
    public static String[] validateUserDetails(String username, String pass) {
        String query = "select * from userData1";
        String[] arrData = { "0", "", "", "", "", "", "", "", "", "false" };

        try {
            Statement st = newcon.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                arrData[3] = rs.getString(4);
                arrData[2] = rs.getString(3);
                if (username.equals(arrData[3]) && pass.equals(arrData[2])) {
                    arrData[0] = "" + rs.getInt(1);
                    arrData[1] = rs.getString(2);
                    arrData[4] = rs.getString(5);
                    arrData[5] = rs.getString(6);
                    arrData[6] = rs.getString(7);
                    arrData[7] = rs.getString(8);
                    arrData[8] = rs.getString(9);
                    arrData[9] = "true";
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Function validateUserDetails:-" + e);
        }

        if (arrData[9] == "true") {
            System.out.println("Login success...");
            return arrData;
        } else {
            System.out.println("Invalid login");
        }
        return arrData;
    }

    public boolean forgotPassword(String femail, String fpass) {
        checkForUniEmail(femail);
        if (uniqe == false) {
            try {
                String idd = "" + datafound;
                String query = "update userData1 set pass=? where id=?";
                PreparedStatement ps = newcon.prepareStatement(query);
                ps.setString(1, fpass);
                ps.setString(2, idd);
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                System.out.println("From forgotPassword method: " + e);
            }
        }
        return false;
    }

    // Function for Update
    public boolean dataForupdateWithEmail(String sid, String username, String mobno, String email, String address,
            String pass,
            String gender,
            String qual, String nation) {
        int id = Integer.parseInt(sid);
        boolean flagsubmit = false;
        if (checkForUniEmail(email)) {
            try {
                String que = "update userData1 set username=?, pass=?, email=?, mob=?, address=?, gender=?, qual=?, nation=? where id=?";


                PreparedStatement ps = newcon.prepareStatement(que);
                // ps.setInt(1, 0);// auto increment
                ps.setString(1, username);
                ps.setString(2, pass);
                ps.setString(3, email);
                ps.setString(4, mobno);
                ps.setString(5, address);
                ps.setString(6, gender);
                ps.setString(7, qual);
                ps.setString(8, nation);
                ps.setInt(9, id);

                ps.executeUpdate();
                flagsubmit = true;
            } catch (Exception e) {
                System.out.println("Wrong syntax" + e);
            }
        }
        return flagsubmit;
    }

    public boolean dataForupdate(String sid, String username, String mobno, String address, String pass,
            String gender,
            String qual, String nation) {
        int id = Integer.parseInt(sid);
        boolean flagsubmit = false;

        try {
            

String que = "update userData1 set username=?, pass=?, mob=?, address=?, gender=?, qual=?, nation=? where id=?";

            PreparedStatement ps = newcon.prepareStatement(que);
            // ps.setInt(1, 0);// auto increment
		ps.setString(1, username);
                ps.setString(2, pass);
                ps.setString(3, mobno);
                ps.setString(4, address);
                ps.setString(5, gender);
                ps.setString(6, qual);
                ps.setString(7, nation);
                ps.setInt(8, id);

            ps.executeUpdate();
            flagsubmit = true;
        } catch (Exception e) {
            System.out.println("Wrong syntax" + e);
        }

        return flagsubmit;
    }

    public boolean deleteMyAccount(String id) {
        int newid = Integer.parseInt(id);

        // line 86 me null pointer hoga esily zero pe koi interger value daal do like 0
        try {
            // First feedback row deleted
            String query2 = "delete from userFeedback where IDfk=?";
            PreparedStatement ps1 = newcon.prepareStatement(query2);
            ps1.setInt(1, newid);
            ps1.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception Occur in deleting Feedback Row");
        }

        try {
            // user row deleted
            String query1 = "delete from userData1 where id=?";
            PreparedStatement ps = newcon.prepareStatement(query1);
            ps.setInt(1, newid);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occur in Deleting UserData Row");
        }
        return false;
    }

    boolean isFeedBack(int id) {
        String query = "select * from userFeedback";
        try {
            Statement st = newcon.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                if (id == rs.getInt(2)) {
                    // System.out.println("Fdbk"+rs.getInt(1));
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Occour in isFeedBack ");
        }

        return true;
    }

    public boolean givefeedback(String str, String id) {
        int newId = Integer.parseInt(id);
        if (isFeedBack(newId)) {
            System.out.println("Passed true");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            try {
                String que = "insert into userFeedback values(?,?,?,?,?)";
                PreparedStatement ps = newcon.prepareStatement(que);

                ps.setInt(1, 0);// auto increment
                ps.setInt(2, newId);// FK
                ps.setString(3, str);
                ps.setString(4, "5");
                ps.setString(5, date);
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Wrong syntax" + e);
            }
        } else {
            System.out.println("Passed False");
            try {
                String que = "update userFeedback set f_message=? where IDfk=?";
                PreparedStatement ps = newcon.prepareStatement(que);
                ps.setString(1, str);
                ps.setInt(2, newId);
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                System.out.println("Wrong syntax" + e);
            }
        }
        return false;
    }

    public static String getFeedBack(String id) {
        int newId = Integer.parseInt(id);
        String str = "";
        try {
            String query = "select f_message from userFeedback where IDfk=?";
            PreparedStatement ps = newcon.prepareStatement(query);
            ps.setInt(1, newId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Print :" + rs.getString(1));
                str = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println("Occour in isFeedBack " + e);
        }
        return str;
    }
}
