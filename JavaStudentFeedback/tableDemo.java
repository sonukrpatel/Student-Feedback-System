import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

class MyFrame extends JFrame implements ActionListener {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    JButton b1;
    JComboBox<String> cb, cb1;
    JTextArea area;

    MyFrame() {
        super("Displaying Query Result");
        b1 = new JButton("Execute");
        String[] queryNames = {"Authors", "ISBN", "Title", "Year"};
        String[] op = {"Select","Create", "Insert", "Update", "Delete"};
        
        area = new JTextArea();
        JScrollPane sc = new JScrollPane(area);  
        cb = new JComboBox<>(queryNames);
        cb1 = new JComboBox<>(op);
        cb.setBounds(330, 80, 80, 30);
        cb1.setBounds(330, 120, 80, 30);
        sc.setBounds(20, 20, 300, 50);
        b1.setBounds(330, 20, 90, 35);
        JScrollPane pg = new JScrollPane(jtbl);
        pg.setBounds(20, 80, 300, 100);
        cnt.add(pg);

        add(b1);
        add(cb);
        add(cb1);
        add(sc);
        cb.addActionListener(this);
        cb1.addActionListener(this);
        b1.addActionListener(this);
        cnt.setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        model.setRowCount(0);
        model.setColumnCount(0);
        if (e.getSource() == cb) {
            String selectedQuery = (String) cb.getSelectedItem();
            try {
                //Class.forName("org.sqlite.JDBC");//for sqlite
               // Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");//Establish Connection
        Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
                PreparedStatement p = null;
                if(selectedQuery.equals("Authors")) {
                    p = connection.prepareStatement("SELECT * FROM Authors");
                } else if (selectedQuery.equals("ISBN")) {
                    p = connection.prepareStatement("SELECT a.last_name, a.first_name, b.title, b.year, b.isbn\n" +
                            "FROM Authors a\n" +
                            "JOIN Books b ON a.author_id = b.author_id\n" +
                            "WHERE a.author_id = 101\n" +
                            "ORDER BY a.last_name, a.first_name");
                } else if (selectedQuery.equals("Title")) {
                    p = connection.prepareStatement("SELECT a.last_name, a.first_name\n" +
                            "FROM Authors a\n" +
                            "JOIN Books b ON a.author_id = b.author_id\n" +
                            "WHERE b.title = 'The Power of Now'\n" +
                            "ORDER BY a.last_name, a.first_name");
                } else if (selectedQuery.equals("Year")) {
                    p = connection.prepareStatement("SELECT * FROM Books WHERE year = 2010");
                }

                ResultSet rs = p.executeQuery();
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colname = new String[cols];
                for (int i = 0; i < cols; i++)
                    colname[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colname);
                while (rs.next()) {
                    Object[] rowData = new Object[cols];
                    for (int i = 0; i < cols; i++) {
                        rowData[i] = rs.getString(i + 1);
                    }
                    model.addRow(rowData);
                }
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == b1) {
            try {
                String query = area.getText();
                if (query.equals("")) {
                    JOptionPane.showMessageDialog(null, "TextField Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
   
                PreparedStatement p = connection.prepareStatement(query);
                ResultSet rs = p.executeQuery();
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colname = new String[cols];
                for (int i = 0; i < cols; i++)
                    colname[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colname);
                while (rs.next()) {
                    Object[] rowData = new Object[cols];
                    for (int i = 0; i < cols; i++) {
                        rowData[i] = rs.getString(i + 1);
                    }
                    model.addRow(rowData);
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Syntax", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == cb1)
        {
            String query = (String) cb1.getSelectedItem();
          if(query.equals("Create"))
          {
            try {
                String s1=area.getText(); 
                Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
   
             PreparedStatement statement = connection.prepareStatement(s1);
             statement.executeUpdate();
             JOptionPane.showMessageDialog(null, "Query OK, 0 rows affected (0.20 sec)");
            } catch (SQLException e2) {
            e2.printStackTrace();
             }
          }
          else if(query.equals("Insert"))
          {
              try{
               String insertQuery = area.getText(); // Get the query from the JTextArea
              Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
   
              PreparedStatement statement = connection.prepareStatement(insertQuery);
               statement.executeUpdate();

              JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
              }
              catch( SQLException e3)
              {
                  e3.printStackTrace();
              }
          }
          else if(query.equals("Update"))
          {
              try {
            String updateQuery = area.getText(); // Get the query from the JTextArea
            Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
   
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            int rowsAffected = statement.executeUpdate();

            JOptionPane.showMessageDialog(null, rowsAffected + " row(s) updated successfully");
           } catch (SQLException e2) {
           e2.printStackTrace();
            }
          }
          else if(query.equals("Delete"))
          {
              try {
            String updateQuery = area.getText(); // Get the query from the JTextArea
            Connection connection=DriverManager.getConnection("jdbc:sqlite:C://sqlite//univ.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            int rowsAffected = statement.executeUpdate();

            JOptionPane.showMessageDialog(null, rowsAffected + " row(s) updated successfully");
           } catch (SQLException e2) {
                 e2.printStackTrace();
            }
          }
            
        }
    }
}

class tableDemo {
    public static void main(String args[]) {
        MyFrame f = new MyFrame();
        f.setSize(500, 300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
   