package org.example.platzi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Esta clase maneja la logica de conexion a la BD
*/

public class DatabaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/project";
    private static String user = "root";
    private static String pass = "Admin1234";
    private static Connection myConn;

    public static Connection getInstance() throws SQLException {
        //Si la conexion es nula, hacemo conexion
        if(myConn == null){
            myConn = DriverManager.getConnection(url,user,pass);
        }
        return myConn;
    }
}
