package org.example.platzi.main;
/* Creamos un nuevo proyecto con Java y Maven
Agregamos la dependencia de mysql connector en el pom.xml, en lugar de agregar el
archivo jar. hay dos formas de hacerlo:
1 - Vamos al repositorio de Maven y tecleamos "MySQL Connector/J" para buscarlo y
elegimos la version deseada. Buscamos el codigo de Maven para copiarlo y pegarlo en
del pom, dentro de <dependencies> </dependencies>. Actualizamos el proyecto en el
icono de la m para cargar la dependencia.
2 - Tecleamos Alt + Insert, Add dependency... y buscamos "MySQL Connector/J", damos
clic en Add y listo
*/

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project";
        String user = "root";
        String pass = "Admin1234";

        //Try con recursos, a partir de una implementacion en Java 7.
        //Los recursos declarados dentro del bloque try se cierran automaticamente al
        //finalizar el bloque, ya sea que se haya producido una excepcion o no
        try (Connection myConn = DriverManager.getConnection(url, user, pass);
             Statement myStamt = myConn.createStatement();
             ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees");
        ) {

            while (myRes.next()){
                System.out.println(myRes.getString("first_name"));
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Algo salio mal :(");
        }

    }
}