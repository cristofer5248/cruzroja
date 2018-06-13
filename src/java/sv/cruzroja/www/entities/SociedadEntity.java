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
import javax.persistence.Id;
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
@Table(name = "sociedad")
@NamedQueries({
    @NamedQuery(name = "SociedadEntity.findAll", query = "SELECT s FROM SociedadEntity s"),
    @NamedQuery(name = "SociedadEntity.findById", query = "SELECT s FROM SociedadEntity s WHERE s.id = :id"),
    @NamedQuery(name = "SociedadEntity.findByNombre", query = "SELECT s FROM SociedadEntity s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SociedadEntity.findByIniciales", query = "SELECT s FROM SociedadEntity s WHERE s.iniciales = :iniciales")})
public class SociedadEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "iniciales", nullable = false, length = 5)
    private String iniciales;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sociedadN")
    private List<ProyectosEntity> proyectosEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sociedadN")
    private List<FinanciamientoEntity> financiamientoEntityList;

    public SociedadEntity() {
    }

    public SociedadEntity(Integer id) {
        this.id = id;
    }

    public SociedadEntity(Integer id, String nombre, String iniciales) {
        this.id = id;
        this.nombre = nombre;
        this.iniciales = iniciales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    @XmlTransient
    public List<ProyectosEntity> getProyectosEntityList() {
        return proyectosEntityList;
    }

    public void setProyectosEntityList(List<ProyectosEntity> proyectosEntityList) {
        this.proyectosEntityList = proyectosEntityList;
    }

    @XmlTransient
    public List<FinanciamientoEntity> getFinanciamientoEntityList() {
        return financiamientoEntityList;
    }

    public void setFinanciamientoEntityList(List<FinanciamientoEntity> financiamientoEntityList) {
        this.financiamientoEntityList = financiamientoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SociedadEntity)) {
            return false;
        }
        SociedadEntity other = (SociedadEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.SociedadEntity[ id=" + id + " ]";
    }

    
}
