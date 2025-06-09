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

/* Las transacciones se utilizan para garantizar la integridad y consistencia de los
datos en una BD. Las transacciones se toman como una unidad atomica, se tienen diferentes
operaciones que todas se deben cumplir. Se usa un commit sitodo sale bien, o
un rollaback para regresar todosi algo falla.
Principios ACID:
Atomicity (Atomicidad)
Consistency (Consistencia)
Isolation (Aislamiento)
Durability (Durabilidad)
 */

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        try(Connection myConn = DatabaseConnection.getInstance()){

            //Creamos un autocommit en falso porque por defecto tenemos un commit
            //verdadero, es decir siempre que se hacen cambios, pases o no pasen,
            if (myConn.getAutoCommit()){
                myConn.setAutoCommit(false);
            }

            try{
                Repository<Employee> repository = new EmployeeRepository(myConn);

                System.out.println("-----Insertando un nuevo cliente-----");
                Employee employee = new Employee();
//                employee.setFirst_name("America");
//                employee.setPa_surname("Lopez");
//                employee.setMa_surname("Villa");
//                employee.setEmail("america@mail.com");
//                employee.setSalary(3000F);
//                employee.setCurp("AMER12369842568719");
//                repository.save(employee);
//                myConn.commit(); //Ponemos el commit ya que lo habiamos quitado

                //Intentamos guardar otro empleado con el mismo CURP para generar una
                //excepcion, mandarnos al catch y dar un rollback
                employee.setFirst_name("David");
                employee.setPa_surname("Gutierrez");
                employee.setMa_surname("Olvera");
                employee.setEmail("david@mail.com");
                employee.setSalary(3200F);
                employee.setCurp("AMER12369842568719");
                repository.save(employee);
                myConn.commit();

            } catch (SQLException e) {
                myConn.rollback(); //Regresa al ultimo estado de la BD
                throw new RuntimeException(e);
            }
        }


        //Hacemos funcionar la app con una UI de Java Swing
        /*SwingApp app = new SwingApp();
        app.setVisible(true);*/

    /* A partir de aqui hacia abajo, todolo comentado con una tabulacion, hace funcionar el programa en modo consola
        //Try con recursos, a partir de una implementacion en Java 7.
        //Los recursos declarados dentro del bloque try se cierran automaticamente al
        //finalizar el bloque, ya sea que se haya producido una excepcion o no
        try (Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            //Obtenemos los empleados
            System.out.println("----Lista de empleados-----");
            repository.findAll().forEach(System.out::println);

            //Obtenemos empleado por id
            System.out.println("Busqueda de empleado con id " +repository.getById(3));
    */
            /*//Guardamos un nuevo empleado
            System.out.println("----Insertando un empleado----");
            Employee employee = new Employee();
            employee.setFirst_name("Diego");
            employee.setPa_surname("Pimentel");
            employee.setMa_surname("Gutierrez");
            employee.setEmail("diego@mail.com");
            employee.setSalary(19000);
            repository.save(employee);
            //Imprimimos la lista de empleados
            repository.findAll().forEach(System.out::println);*/

            /*//Actualizamos un empleado
            System.out.println("----Actulizando un empleado----");
            Employee employee = new Employee();
            employee.setId(8);
            employee.setFirst_name("Diego");
            employee.setPa_surname("Pimentel");
            employee.setMa_surname("Villanueva");
            employee.setEmail("diego@mail.com");
            employee.setSalary(30000);
            repository.save(employee);
            //Imprimimos la lista de empleados
            repository.findAll().forEach(System.out::println);*/
    /*
            //Eliminar un empleado
            System.out.println("----Eliminamos un empleado----");
            repository.delete(8);
            //Imprimimos la lista de empleados
            repository.findAll().forEach(System.out::println);
        }
     */


    }
}