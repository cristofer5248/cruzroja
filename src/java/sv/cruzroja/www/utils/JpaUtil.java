/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author crist
 */
 public class JpaUtil { 
    private static final EntityManagerFactory emf;
        static{ 
            try{ emf=Persistence.createEntityManagerFactory("CruzRojaPU"); 
        }catch(Throwable t){ 
            System.out.println("Error a inicializar el Entity Manager Factory "+t);
            t.printStackTrace();
            throw new ExceptionInInitializerError(); 
        } 
    }
    public static EntityManagerFactory getEntityManagerFactory(){ 
        return emf; 
    }
}
