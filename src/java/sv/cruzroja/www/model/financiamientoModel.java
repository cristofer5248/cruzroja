/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.entities.FinanciamientoEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class financiamientoModel {

    public List<FinanciamientoEntity> listarLugar() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<FinanciamientoEntity> lista = consulta.getResultList();

            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<FinanciamientoEntity> listarLugarMesactual(int mes, int anio) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findAllMesActual").setParameter("mes", mes).setParameter("anio", anio);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<FinanciamientoEntity> lista = consulta.getResultList();

            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<FinanciamientoEntity> listarLugarFiltro(Date fecha, Date fecha2) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findAllFiltro").setParameter("fecha", fecha).setParameter("fecha2", fecha2);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<FinanciamientoEntity> lista = consulta.getResultList();

            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<Map<String, ?>> listarLugarFiltroPDF(Date fecha, Date fecha2) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        String fechaparametro1 = d.format(fecha);
        String fechaparametro2 = d.format(fecha2);
        String fechaString;
        String fechaString2;
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findAllFiltro").setParameter("fecha", fecha).setParameter("fecha2", fecha2);
            Query total = em.createNamedQuery("FinanciamientoEntity.findTotalFiltro").setParameter("fecha", fecha).setParameter("fecha2", fecha2);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
            List<FinanciamientoEntity> lista = consulta.getResultList();
            List totallista = total.getResultList();
            System.out.print("total en el pdf es"+ totallista.get(0));
            
            for (FinanciamientoEntity b : lista) {

                Map<String, Object> m = new HashMap<String, Object>();
                m.put("proyectos_nombre", b.getProyecto().getNombre());
                m.put("sociedad_nombre", b.getSociedadN().getNombre());
                m.put("categoria_nombre", b.getProyecto().getCategoria().getNombre());
                m.put("municipio_nombre", b.getProyecto().getSeccionales().getNombre());
                m.put("financiamiento_financiamiento",b.getFinanciamiento());
                fechaString = d.format(b.getFechaIngreso());
                m.put("financiamiento_Detalles", (b.getDetalles()));
                System.out.print(fechaString+ "va aqui tienes tu fecha en String con formato bonito :p");
                m.put("financiamiento_fechaIngreso", fechaString);
                m.put("total", String.valueOf(totallista.get(0)));
                m.put("fechainicio", fechaparametro1);
                m.put("fechafinal", fechaparametro2);
                result.add(m);

            }
            if (lista.size() == 0) {
                System.out.println("we lo siento :C");
            }
            em.close();// Cerrando el EntityManager
            return result;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public Double total() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findTotal");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List lista = consulta.getResultList();
            Double total = (Double) lista.get(0);

            em.close();// Cerrando el EntityManager
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public Double totalfiltro(Date fecha, Date fecha2) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findTotalFiltro").setParameter("fecha", fecha).setParameter("fecha2", fecha2);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List lista = consulta.getResultList();
            Double total = (Double) lista.get(0);

            em.close();// Cerrando el EntityManager
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public Double totalporfiltro(int mes, int anio) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("FinanciamientoEntity.findTotalporfiltro").setParameter("mes", mes).setParameter("anio", anio);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List lista = consulta.getResultList();
            Double total = (Double) lista.get(0);

            em.close();// Cerrando el EntityManager
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public int insertarFinanciamiento(FinanciamientoEntity estudiante) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(estudiante); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return 0;
        }
    }

    public int modificarFinanciamiento(FinanciamientoEntity estudiante) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(estudiante); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
}
