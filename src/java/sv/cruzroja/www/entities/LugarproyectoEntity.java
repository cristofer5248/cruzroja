/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author crist
 */

@Entity
@Table(name = "lugarproyecto")
@NamedQueries({
    @NamedQuery(name = "LugarproyectoEntity.findAll", query = "SELECT l FROM LugarproyectoEntity l"),
    @NamedQuery(name = "LugarproyectoEntity.findDatorepetido", query = "SELECT l FROM LugarproyectoEntity l where l.idlp.idproyecto= :codigo1 AND l.idlugar.idlugar= :codigo2"),
    @NamedQuery(name = "LugarproyectoEntity.findAllporFecha", query = "SELECT l FROM LugarproyectoEntity l where CAST(l.fechainicio as Date)>=:f1 AND CAST(l.fechafinal as Date) <=:f2"),
    @NamedQuery(name = "LugarproyectoEntity.findByIdl", query = "SELECT l FROM LugarproyectoEntity l WHERE l.idl = :idl"),
    @NamedQuery(name = "LugarproyectoEntity.findByIdlugar", query = "SELECT l FROM LugarproyectoEntity l WHERE l.idlugar.idlugar = :idlugar ORDER BY L.idlp.nombre ASC"),
    @NamedQuery(name = "LugarproyectoEntity.findByIdcategoria", query = "SELECT l FROM LugarproyectoEntity l WHERE l.idlp.categoria.idcategoria = :idcat"),
    @NamedQuery(name = "LugarproyectoEntity.findByIddepartamento", query = "SELECT l FROM LugarproyectoEntity l WHERE l.idlugar.departamento.iddepartamento = :iddepa"),
    @NamedQuery(name = "LugarproyectoEntity.findByIdlugaryEncargado", query = "SELECT l FROM LugarproyectoEntity l WHERE l.idlugar.idlugar = :idlugar AND l.encargadoproyecto.idusuario= :encargado"),
    @NamedQuery(name = "LugarproyectoEntity.findByFechainicio", query = "SELECT l FROM LugarproyectoEntity l WHERE l.fechainicio = :fechainicio"),
    @NamedQuery(name = "LugarproyectoEntity.findByFechafinal", query = "SELECT l FROM LugarproyectoEntity l WHERE l.fechafinal = :fechafinal")})
public class LugarproyectoEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idl", nullable = false, length = 6)
    private String idl;
    @Basic(optional = false)
    @Column(name = "fechainicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @Column(name = "fechafinal", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechafinal;
    @JoinColumn(name = "idlp", referencedColumnName = "idproyecto", nullable = false)
    @ManyToOne(optional = false)
    private ProyectosEntity idlp;
    @JoinColumn(name = "idlugar", referencedColumnName = "idlugar", nullable = false)
    @ManyToOne(optional = false)
    private LugarEntity idlugar;
    @JoinColumn(name = "encargadoproyecto", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private UsuariosEntity encargadoproyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarproyectoPadre")
    private List<Actividades> actividadesList;

    public LugarproyectoEntity() {
    }

    public LugarproyectoEntity(String idl) {
        this.idl = idl;
    }

    public LugarproyectoEntity(String idl, Date fechainicio, Date fechafinal) {
        this.idl = idl;
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
    }

    public String getIdl() {
        return idl;
    }

    public void setIdl(String idl) {
        this.idl = idl;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public ProyectosEntity getIdlp() {
        return idlp;
    }

    public void setIdlp(ProyectosEntity idlp) {
        this.idlp = idlp;
    }

    public LugarEntity getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(LugarEntity idlugar) {
        this.idlugar = idlugar;
    }

    public UsuariosEntity getEncargadoproyecto() {
        return encargadoproyecto;
    }

    public void setEncargadoproyecto(UsuariosEntity encargadoproyecto) {
        this.encargadoproyecto = encargadoproyecto;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idl != null ? idl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugarproyectoEntity)) {
            return false;
        }
        LugarproyectoEntity other = (LugarproyectoEntity) object;
        if ((this.idl == null && other.idl != null) || (this.idl != null && !this.idl.equals(other.idl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.LugarproyectoEntity[ idl=" + idl + " ]";
    }

    
}
