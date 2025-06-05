package org.example.platzi.repository;

import org.example.platzi.model.Employee;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Implementamos de Repository con sus metodos
public class EmployeeRepository implements Repository<Employee>{

    //Metodo para la conexion
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    //Metodos de la implementacion
    @Override
    public List<Employee> findAll() throws SQLException {
        //Creamos la lista employees
        List<Employee> employees = new ArrayList<>();
        //Hacemos la conexion a la BD
        //Ejecutamos la consulta a la BD y almacenamos el resultado en un objeto ResultSet
        //Con un while, creamos los objetos employee con los datos obtenidos de la BD
        try(Statement myStamt = getConnection().createStatement();
            ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees")){
            while(myRes.next()){
                Employee e = createEmployee(myRes);
                employees.add(e); //Agregamos el objeto employee a la lista employees
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(PreparedStatement myStamt = getConnection().prepareStatement(
                "SELECT * FROM employees WHERE id=?")) {
            myStamt.setInt(1,id);
            try(ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()){
                    employee = createEmployee(myRes);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }


    //Metodo para crear un objeto employee con los datos de la BD
    private Employee createEmployee(ResultSet myRes) throws SQLException {
        //Creamos un nuevo objeto employee usando su constructor
        Employee e = new Employee();
        //Agregamos valores a los atributos
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));
        return e;
    }
}
