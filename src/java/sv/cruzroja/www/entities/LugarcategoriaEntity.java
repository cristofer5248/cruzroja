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
@Table(name = "lugarcategoria")
@NamedQueries({
    @NamedQuery(name = "LugarcategoriaEntity.findAll", query = "SELECT l FROM LugarcategoriaEntity l"),
    @NamedQuery(name = "LugarcategoriaEntity.findById", query = "SELECT l FROM LugarcategoriaEntity l WHERE l.id = :id"),
    @NamedQuery(name = "LugarcategoriaEntity.findByNombre", query = "SELECT l FROM LugarcategoriaEntity l WHERE l.nombre = :nombre")})
public class LugarcategoriaEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nombre", length = 20)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<LugarEntity> lugarEntityList;

    public LugarcategoriaEntity() {
    }

    public LugarcategoriaEntity(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<LugarEntity> getLugarEntityList() {
        return lugarEntityList;
    }

    public void setLugarEntityList(List<LugarEntity> lugarEntityList) {
        this.lugarEntityList = lugarEntityList;
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
        if (!(object instanceof LugarcategoriaEntity)) {
            return false;
        }
        LugarcategoriaEntity other = (LugarcategoriaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.LugarcategoriaEntity[ id=" + id + " ]";
    }

  
}
