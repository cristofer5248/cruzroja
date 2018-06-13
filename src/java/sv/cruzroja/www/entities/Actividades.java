/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "actividades")
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a"),
    @NamedQuery(name = "Actividades.findAllOrder", query = "SELECT a FROM Actividades a ORDER BY a.lugarproyectoPadre.idlp.idproyecto"),
    @NamedQuery(name = "Actividades.findAllXproyecto", query = "SELECT a FROM Actividades a where a.lugarproyectoPadre.idl = :codigo"),
    @NamedQuery(name = "Actividades.findAllXidActividadDetalle", query = "SELECT a FROM Actividades a where a.detalleactividad.idactividadesdetalles = :codigo ORDER BY a.lugarproyectoPadre.idlp.nombre DESC"),
    @NamedQuery(name = "Actividades.findAllXid", query = "SELECT a FROM Actividades a where a.idactividad = :codigo"),
    @NamedQuery(name = "Actividades.findAllXiddetalle", query = "SELECT a FROM Actividades a where a.detalleactividad.idactividadesdetalles = :codigo"),
    @NamedQuery(name = "Actividades.findAllIddetalleyPadre", query = "SELECT a FROM Actividades a where a.detalleactividad.idactividadesdetalles = :codigo AND a.lugarproyectoPadre.idl = :padre"),
    @NamedQuery(name = "Actividades.findAllporDepartamento", query = "SELECT a FROM Actividades a where a.lugarproyectoPadre.idlugar.departamento.iddepartamento = :depa")})
public class Actividades implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idactividad", nullable = false)
    private Integer idactividad;
    @JoinColumn(name = "detalleactividad", referencedColumnName = "idactividadesdetalles")
    @ManyToOne
    private ActividadesdetallesEntity detalleactividad;
    @JoinColumn(name = "lugarproyectoPadre", referencedColumnName = "idl")
    @ManyToOne
    private LugarproyectoEntity lugarproyectoPadre;

    public Actividades() {
    }

    public Actividades(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public ActividadesdetallesEntity getDetalleactividad() {
        return detalleactividad;
    }

    public void setDetalleactividad(ActividadesdetallesEntity detalleactividad) {
        this.detalleactividad = detalleactividad;
    }

    public LugarproyectoEntity getLugarproyectoPadre() {
        return lugarproyectoPadre;
    }

    public void setLugarproyectoPadre(LugarproyectoEntity lugarproyectoPadre) {
        this.lugarproyectoPadre = lugarproyectoPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.Actividades[ idactividad=" + idactividad + " ]";
    }

}
