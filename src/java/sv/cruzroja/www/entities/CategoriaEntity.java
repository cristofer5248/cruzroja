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
@Table(name = "categoria")
@NamedQueries({
    @NamedQuery(name = "CategoriaEntity.findAll", query = "SELECT c FROM CategoriaEntity c"),
    @NamedQuery(name = "CategoriaEntity.findByIdcategoria", query = "SELECT c FROM CategoriaEntity c WHERE c.idcategoria = :idcategoria"),
    @NamedQuery(name = "CategoriaEntity.findByNombre", query = "SELECT c FROM CategoriaEntity c WHERE c.nombre = :nombre")})
public class CategoriaEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idcategoria", nullable = false)
    private Integer idcategoria;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<ProyectosEntity> proyectosEntityList;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public CategoriaEntity(Integer idcategoria, String nombre) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ProyectosEntity> getProyectosEntityList() {
        return proyectosEntityList;
    }

    public void setProyectosEntityList(List<ProyectosEntity> proyectosEntityList) {
        this.proyectosEntityList = proyectosEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaEntity)) {
            return false;
        }
        CategoriaEntity other = (CategoriaEntity) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.CategoriaEntity[ idcategoria=" + idcategoria + " ]";
    }
    
}
