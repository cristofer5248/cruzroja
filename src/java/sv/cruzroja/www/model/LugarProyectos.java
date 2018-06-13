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
import sv.cruzroja.www.entities.BeneficiadosEntity;
import sv.cruzroja.www.entities.LugarproyectoEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class LugarProyectos {

    public List<LugarproyectoEntity> listarLugarproyectos() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<LugarproyectoEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<LugarproyectoEntity> listarLugarproyectosporfecha(Date fecha1, Date fecha2) {
//Obtengo una instancia de EntityManager

        Date fechaparametro1 = fecha1;
        Date fechaparametro2 = fecha2;

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findAllporFecha").setParameter("f1", fecha1).setParameter("f2", fecha2);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<LugarproyectoEntity> lista = consulta.getResultList();
            if (lista.size() == 0) {
                System.out.println("no hay nada we en listarlugarproductosporfecha");
            } else {
                System.out.println("entonces si hay datos");
            }
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Map<String, ?>> listarLugarproyectosporfechapdf(Date fecha1, Date fecha2) {
        //Obtengo una instancia de EntityManager
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        System.out.println("La fechaaaa" + fecha1);
        String fechaparametro1 = d.format(fecha1);
        String fechaparametro2 = d.format(fecha2);
        String fechaString;
        String fechaString2;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findAllporFecha").setParameter("f1", fecha1).setParameter("f2", fecha2);
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
            List<LugarproyectoEntity> lista = consulta.getResultList();
            for (LugarproyectoEntity b : lista) {

                Map<String, Object> m = new HashMap<String, Object>();
                m.put("proyectos_nombre", b.getIdlp().getNombre());
                m.put("municipio_nombre", b.getIdlugar().getMunicipio().getNombre());
                m.put("lugar_nombre", b.getIdlugar().getNombre());
                m.put("categoria_nombre", b.getIdlp().getCategoria().getNombre());
                m.put("usuarios_nombres", b.getEncargadoproyecto().getNombres());
                fechaString = d.format(b.getFechainicio());
                fechaString2 = d.format(b.getFechafinal());

                m.put("fecha1", fechaString);
                m.put("fecha2", fechaString2);
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
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Map<String, ?>> listarLugarproyectosporgeneropdf(String codproyecto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        System.out.print("El codigooooo es este we agarralo sin pena, hasta que se rompa el suelo...." + codproyecto);
        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByIdlpGROUP").setParameter("codproyecto", codproyecto);
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
                System.out.println("Prueba de datos: " + b.getIdbeneficiado().getNombres());
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

    public int insertarLugarproyecto(LugarproyectoEntity estudiante) {
        System.out.println(estudiante.getIdlugar());
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

    public int modificarLugarproyecto(LugarproyectoEntity estudiante) {
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

    public int eliminarLugarproyecto(String id) {
        //System.out.println(id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            LugarproyectoEntity est = em.find(LugarproyectoEntity.class, id);

            System.out.println(est);
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

    public LugarproyectoEntity obtenerLugar(String carnet) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            LugarproyectoEntity estudiante = em.find(LugarproyectoEntity.class, carnet);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String obtenerLugarString(String carnet) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findByIdl").setParameter("idl", carnet);
            List<LugarproyectoEntity> estudiante = consulta.getResultList();

            em.close();
            return "UBICACION: " + estudiante.get(0).getIdlugar().getNombre() + System.getProperty("line.separator") + " Departamento: " + estudiante.get(0).getIdlugar().getDepartamento().getNombre() + " PROYECTO: " + estudiante.get(0).getIdlp().getNombre() + "\nDescripcion:" + estudiante.get(0).getIdlp().getDes();
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public boolean buscarDatoRepetido(String codigo1, String codigo2) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findDatorepetido").setParameter("codigo1", codigo1).setParameter("codigo2", codigo2);
            List<LugarproyectoEntity> lista = consulta.getResultList();
            if (lista.size() != 0) {
                return true;
            }
            em.close();
            return false;
        } catch (Exception e) {
            em.close();
            return false;
        }
    }

    public List<LugarproyectoEntity> listarLugarproyectosporlugar(String idlugar) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findByIdlugar").setParameter("idlugar", idlugar);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<LugarproyectoEntity> lista = consulta.getResultList();
            System.out.printf("CODIGO de listaLugarproyectoporLugar (" + idlugar + ")");
            if (lista.size() == 0) {
                System.out.print("No hay datos funcion listarLugarproyectosporlugar el codigo es " + idlugar);
            }
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<LugarproyectoEntity> listarLugarproyectosporArea(int idcat) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findByIdcategoria").setParameter("idcat", idcat);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<LugarproyectoEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<LugarproyectoEntity> listarLugarproyectosporDepartamento(int iddepa) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findByIddepartamento").setParameter("iddepa", iddepa);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<LugarproyectoEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<LugarproyectoEntity> listarLugarproyectosporlugarSegunEncargado(String idlugar, String encargado) {
        System.out.printf("mostrando al encargado en el modelobhhh " + encargado);
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("LugarproyectoEntity.findByIdlugaryEncargado").setParameter("idlugar", idlugar).setParameter("encargado", encargado);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<LugarproyectoEntity> lista = consulta.getResultList();
            if (lista.size() == 0) {
                System.out.print("No hay datos");
            }
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }



}
