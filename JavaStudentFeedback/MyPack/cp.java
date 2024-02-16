package MyPack;
import java.sql.*;

public class cp {
    public static Connection con;

    public static Connection createCon() {
        
        try{
            
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","admin");
            System.out.println("Connection Created");
        }catch(Exception e){
            System.out.println("Exception Occure: "+e);
        }
        return con;

    }    
    
}
