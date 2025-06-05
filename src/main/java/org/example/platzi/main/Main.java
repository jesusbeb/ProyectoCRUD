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

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        //Try con recursos, a partir de una implementacion en Java 7.
        //Los recursos declarados dentro del bloque try se cierran automaticamente al
        //finalizar el bloque, ya sea que se haya producido una excepcion o no
        try (Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            //Obtenemos los empleados
            repository.findAll().forEach(System.out::println);
        }
    }
}