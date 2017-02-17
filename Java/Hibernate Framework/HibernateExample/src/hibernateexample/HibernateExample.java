/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author julio
 */
public class HibernateExample {
    private static SessionFactory factory;
    private static ServiceRegistry registry;
    private static Scanner in = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Crear configuración
            Configuration conf = new Configuration().configure();
            //Crear el registro
            registry = new StandardServiceRegistryBuilder().applySettings(
            conf.getProperties()).build();
            //Construir la sesión
            factory = conf.buildSessionFactory(registry);
        } catch (Exception e) {
            System.err.println("Failed to create SessionFactory. " + e);
            throw new ExceptionInInitializerError(e);
        }
        
        HibernateExample he = new HibernateExample();
        String more = "yes";
        Integer empID = 0;
        
        //Agregar employees dentro de la base de datos
        
        while (more.charAt(0) == 'y' || more.charAt(0) == 'Y') {
            empID = he.addEmployee();
            System.out.println("More employess? (y/n)");
            more = in.nextLine();
        }
        
        
        //Actualizar employee dentro de la base de datos
        //he.updateEmployee(1,24000);
        
        //Eliminar un employee dentro de la base de datos
        //he.deleteEmployee(1);
        
        //Mostrar los employees
        he.listEmployees();
        
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private Integer addEmployee() {
        
        //Imprimir en consola y obtener los datos
        System.out.println("Enter first name: ");
        String fname = in.nextLine();
        System.out.println("Enter last name: ");
        String lname = in.nextLine();
        System.out.println("Enter cell: ");
        String cell = in.nextLine();
        System.out.println("Enter home phone: ");
        String hPhone = in.nextLine();
        System.out.println("Enter salary: ");
        int salary = in.nextInt();
        in.nextLine();
        //Crear arreglo de objetos Phone 
        HashSet hs = new HashSet();
        hs.add(new Phone(cell));
        hs.add(new Phone(hPhone));
        
        /*** Hibernate ***/
        //Abrir una sesión
        Session session = factory.openSession();
        //Crear una transacción
        Transaction tx = null;
        //Guardar el numero generado por Hibernate
        Integer employeeID = null;
        try {
            //Comenzar la transacción
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employee.setPhones(hs);
            employeeID = (Integer)session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return employeeID;
    }

    private void updateEmployee(Integer EmployeeID, int salary) {
        //Crear una sesión
        Session session = factory.openSession();
        //Crear un transacción
        Transaction tx = null;
        try {
            //Iniciar la transacción
            tx = session.beginTransaction();
            //Obtener Employee mediante el ID
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            //Asignar el nuevo valor a la propiedad 
            employee.setSalary(salary);
            //Actulizar en la base de datos
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                //Revertir cambios
                tx.rollback();
            }
            e.printStackTrace();
        }   
        finally {
            session.close();
        }
        
    }

    private void deleteEmployee(Integer EmployeeID) {
        //Crear una sesión
        Session session = factory.openSession();
        //Crear una transacción
        Transaction tx = null;
        try {
            //Iniciar la transacción
            tx = session.beginTransaction();
            //Obtener Employee mediante el ID
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            //Eliminar el employee
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null){
                tx.commit();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
    }

    private void listEmployees() {
        /*** Código Hibernate ***/
        
        //Abrir una sesión
        Session session = factory.openSession();
        //Crear una transacción
        Transaction tx = null;
        
        try{
            //Comenzar la transacción
            tx = session.beginTransaction();
            //Crear un lista para guardar los resultados
            List employees = session.createQuery("FROM Employee").list();

            //Recorrer los resultados
            for(Iterator iterator = employees.iterator(); iterator.hasNext();){
                //Crear un objeto Employee
                Employee employee = (Employee)iterator.next();
                //Imprimir en pantalla
                System.out.println("First name: " + employee.getFirstName() + "\nSalary: " + employee.getSalary());
            }
            
            tx.commit();
        }catch(HibernateException e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();   
        }
        finally {
            session.close();
        }
            
    }
    
}
