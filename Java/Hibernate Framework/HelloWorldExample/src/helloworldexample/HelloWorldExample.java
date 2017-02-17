/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldexample;

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
public class HelloWorldExample {
    //Crear una sesion
    private static SessionFactory factory;
    //Crear un registro del servicio 
    private static ServiceRegistry registry;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Leer datos en consola
        Scanner in = new Scanner(System.in);
        //Guardar el dato de la consola 
        String saveValueConsole = "";
        //Mostrar texto en la consola 
        System.out.println("Enter a message: ");
        //Guardar el valor en la variable 'saveValueConsole'
        saveValueConsole = in.nextLine();
        
        /*** Código Hinernate ***/
        
        /*** Establecer una sesión de conexión ***/
        
        try{
            //Establecer una configuración 
            Configuration conf = new Configuration().configure();
            //Establecer un registro para la conexión Factory
            //Agregar la configuración hecha previamente
            registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            
            //Establecer un Factory
            //Agregar el registro hecho previamente
            factory = conf.buildSessionFactory(registry);
            
            
        }catch(Throwable ex){
            System.err.println("Failed to create session factory object " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        /*** Abrir la sesión de conexión ***/
        Session session = factory.openSession();
        
        //Crear una transaccion
        Transaction tx = null;
        
        //Crear una variable para el campo primary key de la base de datos
        Short msgID = null;
        
        try{
            //Comenzar la sesión de la conexión
            tx = session.beginTransaction();
            
            //Crear un objeto de la clase Message
            Message msg = new Message(saveValueConsole);
            
            //Guardar el objeto msg en la base de datos
            //Regresara el ID del primary key como resultado 
            msgID = (Short) session.save(msg);
            
            /*** Imprimir en pantalla los datos que existen en la base de datos ***/
            //Crear un lista para guardar los resultados
            List messages = session.createQuery("FROM Message").list();
            
            //Recorrer los resultados
            for(Iterator iterator = messages.iterator(); iterator.hasNext();){
                //Crear un objeto Message
                Message message = (Message)iterator.next();
                //Imprimir en pantalla
                System.out.println("Mensaje: " + message.getMessage());
            }
            
            //Realizar una transacción para ejecutar las sentencias SQL
            tx.commit();
            
        }catch(HibernateException ex){
            //Verificar si la transacción tiene valor,
            //pero como ha ocurrido un error, es recomendable
            //realizar un rollback a la base de datos
            
            if(tx != null){
                tx.rollback();
            }
            ex.printStackTrace();
            
        }
        finally{
            //Cerrar la sesión de la conexión
            session.close();
        }
        //Cerrar el registro
        StandardServiceRegistryBuilder.destroy(registry);
        
        
        
        
    }
    
}
