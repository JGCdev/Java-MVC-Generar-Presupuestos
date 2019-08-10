package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase utilizada para conectar con la base de datos
 *  
 */
public class Db {
	public static Connection connection;
    public static Connection connect(){
    	
    String url = "jdbc:mysql://localhost:3306/programagestion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "";
    System.out.println("Conectando...");
    try{
         connection = DriverManager.getConnection(url, user,pass);  
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return connection;
    
    
    }
}
