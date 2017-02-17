/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queryexample;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author julio
 */
public class QueryExample {
    
    private static SessionFactory factory;
    private static ServiceRegistry registry;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            //Crear configuración
            Configuration conf = new Configuration().configure();

            //Crear registro
            registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

            //Construir la sesión
            factory = conf.buildSessionFactory(registry);
        }catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory object. " + ex);
            throw new ExceptionInInitializerError();
        }
        
        /*** Ejemplo de Hibernate Query Language or HQL ***/
        
        //Iniciar una sesión
        Session session = factory.openSession();
        
        //Crear una transacción
        Transaction tx = null;
        

        try {
            //Iniciar transacción
            tx = session.beginTransaction();
            //Crear una query
            //Query query = session.createQuery("FROM Employee AS E");
            //Query query = session.createQuery("SELECT e.firstName FROM Employee as e");
            //Query query = session.createQuery("FROM Employee AS e WHERE e.firstName "
            //        + "like 'c%' and salary > 1000 ");
            
            /*** Agregar parametros en HQL ***/
            /*
            String hql = "FROM Employee WHERE salary > :salary";
            Query query = session.createQuery(hql);
            query.setInteger("salary", 1000);
            List employees = query.list();
        
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                Employee ee = (Employee)iterator.next();
                //String fname = (String)iterator.next();
                System.out.println("First name: " + ee.getFirstName());
                System.out.println("Last name: " + ee.getLastName());
                System.out.println("Salary: " + ee.getSalary());
                
            }
            */
            
            /*** Agregar metodos en HQL ***/
            /*
            String hql = "SELECT max(e.salary) FROM Employee AS e";
            Query query = session.createQuery(hql);
            int maxSalary = (int)query.uniqueResult();
            System.out.println("Max salary is: " + maxSalary);
            */
            
            /*** Crear restricciones con Criteria Query API (Más información en hibernate.org) ***/
            //Crear una instacia de Criteria
            Criteria criteria = session.createCriteria(Employee.class);
            //Agregar restricciones
            //salary > 1000
            criteria.add(Restrictions.gt("salary", 1000));
            //Ordenar los resultados de forma ascendente
            criteria.addOrder(Order.asc("firstName"));
            
            List employees = criteria.list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                Employee ee = (Employee)iterator.next();
                //String fname = (String)iterator.next();
                System.out.println("First name: " + ee.getFirstName());
                System.out.println("Last name: " + ee.getLastName());
                System.out.println("Salary: " + ee.getSalary());
                
            }
            
            //Commit
            tx.commit();
        } catch (HibernateException e) {
            
            if(tx != null){
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally {
            session.close();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        
    }
    
}
