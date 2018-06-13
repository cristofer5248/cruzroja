package sv.cruzroja.www.beans;

import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.bean.RequestScoped;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.entities.UsuariosEntity;
import sv.cruzroja.www.model.UsuariosModel;
import sv.cruzroja.www.entities.TipouEntity;
import sv.cruzroja.www.entities.GeneroEntity;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class UsuariosBean {

    private UsuariosEntity usuarios;
    UsuariosModel usuariosmodel = new UsuariosModel();
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;

    public UsuariosBean() {
        usuarios = new UsuariosEntity();
        usuarios.setTipousuario(new TipouEntity());
        usuarios.setGenero(new GeneroEntity());
    }

    public List<UsuariosEntity> getListaProyectos() {
        /* Notese que se llama al método listarEstudiantes
    para obtener la lista de objetos a partir de la bd */
        return usuariosmodel.listarUsuarios();
    }
    public List<UsuariosEntity> getSoloEncargados() {
        /* Notese que se llama al método listarEstudiantes
    para obtener la lista de objetos a partir de la bd */
        return usuariosmodel.listarEncargados();
    }    
    

    public void obtenerUsuario() {
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        this.usuarios = usuariosmodel.obtenerUsuario(carnet);
        JsfUtil.setFlashMessage("exito", "Listo, edita los datos el panel de color verde");

    }

    public String guardarUsuario() {

        if (usuariosmodel.insertarUsuarios(usuarios) != 1) {
            usuariosmodel.modificarUsuarios(usuarios);
            JsfUtil.setFlashMessage("exito", "Usuario actualizado");
            return "usuarios?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "Usuario registrado exitosamente");
//Forzando la redirección en el cliente
            return "usuarios?faces-redirect=true";
        }
    }

    public String eliminarUsuarios() {
// Leyendo el parametro enviado desde la vista
        String carnet = JsfUtil.getRequest().getParameter("codigo");

        //System.out.println(carnet);
        if (usuariosmodel.eliminarUsuarios(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Usuarios eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este Usuario");
        }
        return "usuarios?faces-redirect=true";
    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        idgenerado = usuarios.getNombres().substring(0, 3).toUpperCase().concat(dia);
        usuarios.setIdusuario(idgenerado);
    }

    /**
     * @return the usuarios
     */
    public UsuariosEntity getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(UsuariosEntity usuarios) {
        this.usuarios = usuarios;
    }
}
