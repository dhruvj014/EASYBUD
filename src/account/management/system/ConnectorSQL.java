package account.management.system;
import java.sql.*;
public class ConnectorSQL{
    Connection c;
    Statement s;
    public ConnectorSQL(){
        try{
            c=DriverManager.getConnection("jdbc:mysql:///EASYBUD","root","test1234");
            s = c.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
