/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author crist
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "UsuariosEntity.findAll", query = "SELECT u FROM UsuariosEntity u"),
    @NamedQuery(name = "UsuariosEntity.findByIdusuario", query = "SELECT u FROM UsuariosEntity u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "UsuariosEntity.findByuserpass", query = "SELECT u FROM UsuariosEntity u WHERE u.idusuario = :idusuario AND u.pass= :pass"),
    @NamedQuery(name = "UsuariosEntity.findByencargados", query = "SELECT u FROM UsuariosEntity u WHERE u.tipousuario.idtipou = 2"),
    @NamedQuery(name = "UsuariosEntity.findByuserpassnivel", query = "SELECT u FROM UsuariosEntity u WHERE u.idusuario = :idusuario AND u.tipousuario.idtipou =:nivel"),
    @NamedQuery(name = "UsuariosEntity.findByNombres", query = "SELECT u FROM UsuariosEntity u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "UsuariosEntity.findByPass", query = "SELECT u FROM UsuariosEntity u WHERE u.pass = :pass")})
public class UsuariosEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idusuario", nullable = false, length = 6)
    private String idusuario;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 35)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "pass", nullable = false, length = 30)
    private String pass;
    @Column(name = "activo")
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encargadoproyecto")
    private List<LugarproyectoEntity> lugarproyectoEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encargado")
    private List<ProyectosEntity> proyectosEntityList;
    @JoinColumn(name = "tipousuario", referencedColumnName = "idtipou", nullable = false)
    @ManyToOne(optional = false)
    private TipouEntity tipousuario;
    @JoinColumn(name = "genero", referencedColumnName = "idg", nullable = false)
    @ManyToOne(optional = false)
    private GeneroEntity genero;

    public UsuariosEntity() {
    }

    public UsuariosEntity(String idusuario) {
        this.idusuario = idusuario;
    }

    public UsuariosEntity(String idusuario, String nombres, String pass) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.pass = pass;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<LugarproyectoEntity> getLugarproyectoEntityList() {
        return lugarproyectoEntityList;
    }

    public void setLugarproyectoEntityList(List<LugarproyectoEntity> lugarproyectoEntityList) {
        this.lugarproyectoEntityList = lugarproyectoEntityList;
    }

    @XmlTransient
    public List<ProyectosEntity> getProyectosEntityList() {
        return proyectosEntityList;
    }

    public void setProyectosEntityList(List<ProyectosEntity> proyectosEntityList) {
        this.proyectosEntityList = proyectosEntityList;
    }

    public TipouEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipouEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosEntity)) {
            return false;
        }
        UsuariosEntity other = (UsuariosEntity) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.UsuariosEntity[ idusuario=" + idusuario + " ]";
    }

    
}
