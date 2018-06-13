/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.entities.BeneficiadosEntity;
import sv.cruzroja.www.entities.DatosbeneficiadosEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class BeneficiadoModel {

    public List<DatosbeneficiadosEntity> listarLugar() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("DatosbeneficiadosEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<DatosbeneficiadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<DatosbeneficiadosEntity> listarLugarX2meses(String fecha) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("DatosbeneficiadosEntity.findX2mes").setParameter("fecha", fecha);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<DatosbeneficiadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<DatosbeneficiadosEntity> buscarXapellidos(String apellido) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("DatosbeneficiadosEntity.findXapellidosfiltro").setParameter("apellido", apellido + "%");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<DatosbeneficiadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public DatosbeneficiadosEntity obtenerBeneficiado(String codigo) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            DatosbeneficiadosEntity estudiante = em.find(DatosbeneficiadosEntity.class, codigo);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarEstudiante(DatosbeneficiadosEntity estudiante) {
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
            e.getMessage();
            return 0;
        }
    }

    public int modificarBeneficiado(DatosbeneficiadosEntity estudiante) {
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

    public List<Map<String, ?>> listarLugarproyectosporedad(int edad1, int edad2, String idpro) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByEdadGroup").setParameter("edad1", edad1).setParameter("edad2", edad2).setParameter("idpro", idpro);
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
            List<BeneficiadosEntity> lista = consulta.getResultList();
            int totalcount = lista.size();
            for (BeneficiadosEntity b : lista) {

                Map<String, Object> m = new HashMap<String, Object>();
                m.put("datosbeneficiados_nombres", b.getIdbeneficiado().getNombres());
                m.put("datosbeneficiados_apellidos", b.getIdbeneficiado().getApellidos());
                m.put("datosbeneficiados_edad", b.getIdbeneficiado().getEdad());
                m.put("datosbeneficiados_correo", b.getIdbeneficiado().getCorreo());
                m.put("datosbeneficiados_telefono", b.getIdbeneficiado().getTelefono());
                m.put("proyectos_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getNombre());
                m.put("genero_nombre", b.getIdbeneficiado().getGenero().getNombre());
                m.put("totalcount", totalcount);
                System.out.println("Prueba de datos: " + b.getIdbeneficiado().getNombres() + " total:" + totalcount);
                result.add(m);

            }
            if (lista.size() == 0) {
                System.out.println("we lo siento :C");
            }
            em.close();// Cerrando el EntityManager
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Map<String, ?>> listapdfasistencia(String proyecto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByIdlpOrder").setParameter("codproyecto", proyecto);
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
            List<BeneficiadosEntity> lista = consulta.getResultList();
            int totalcount = lista.size();
            for (BeneficiadosEntity b : lista) {

                Map<String, Object> m = new HashMap<String, Object>();
                m.put("datosbeneficiados_nombres", b.getIdbeneficiado().getNombres());
                m.put("datosbeneficiados_apellidos", b.getIdbeneficiado().getApellidos());
                m.put("datosbeneficiados_edad", b.getIdbeneficiado().getEdad());
                m.put("actividadesdetalles_titulo", b.getIdproyecto().getDetalleactividad().getTitulo());                
                m.put("proyectos_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getNombre());
                m.put("genero_nombre", b.getIdbeneficiado().getGenero().getNombre());
                m.put("datosbeneficiados_idusuario", b.getIdbeneficiado().getIdusuario());
                m.put("datosbeneficiados_edad", b.getIdbeneficiado().getEdad());
                m.put("totalcount", totalcount);
                System.out.println("Prueba de datos: " + b.getIdbeneficiado().getNombres() + " total:" + totalcount);
                result.add(m);

            }
            if (lista.size() == 0) {
                System.out.println("we lo siento :C");
            }
            em.close();// Cerrando el EntityManager
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Map<String, ?>> listarcantidadeBeneficiadosXrea(int idarea) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String fechaString;
        String fechaString2;
        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByTotalXarea").setParameter("idarea", idarea);
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
            List<BeneficiadosEntity> lista = consulta.getResultList();
            int totalcount = lista.size();
            for (BeneficiadosEntity b : lista) {

                Map<String, Object> m = new HashMap<String, Object>();
                fechaString = d.format(b.getIdproyecto().getLugarproyectoPadre().getFechainicio());
                fechaString2 = d.format(b.getIdproyecto().getLugarproyectoPadre().getFechafinal());
                m.put("fechainicio", fechaString);
                m.put("fechafinal", fechaString2);
                m.put("proyectos_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getNombre());
                m.put("usuarios_nombres", b.getIdproyecto().getLugarproyectoPadre().getEncargadoproyecto().getNombres());
                m.put("proyectos_des", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getDes());
                m.put("departamento_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlugar().getDepartamento().getNombre());
                m.put("lugar_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlugar().getNombre());
                m.put("beneficiado", b.getIdbeneficiado().getNombres() + " " + b.getIdbeneficiado().getApellidos());
                m.put("totalcount", totalcount);
                m.put("areamisional", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getCategoria().getNombre());
                System.out.println("Prueba de datos: " + b.getIdbeneficiado().getNombres() + " total:" + totalcount);
                result.add(m);

            }
            if (lista.size() == 0) {
                System.out.println("we lo siento :C");
            }
            em.close();// Cerrando el EntityManager
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public int eliminarLugar(String id) {
        //System.out.println(id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            DatosbeneficiadosEntity est = em.find(DatosbeneficiadosEntity.class, id);
            if (est != null) {

                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();

            return filasBorradas;
        } catch (Exception e) {

            em.close();
            return 0;
        }
    }

}
