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
@Table(name = "genero")
@NamedQueries({
    @NamedQuery(name = "GeneroEntity.findAll", query = "SELECT g FROM GeneroEntity g"),
    @NamedQuery(name = "GeneroEntity.findByIdg", query = "SELECT g FROM GeneroEntity g WHERE g.idg = :idg"),
    @NamedQuery(name = "GeneroEntity.findByNombre", query = "SELECT g FROM GeneroEntity g WHERE g.nombre = :nombre")})
public class GeneroEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idg", nullable = false)
    private Integer idg;
    @Column(name = "nombre", length = 10)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genero")
    private List<DatosbeneficiadosEntity> datosbeneficiadosEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genero")
    private List<UsuariosEntity> usuariosEntityList;

    public GeneroEntity() {
    }

    public GeneroEntity(Integer idg) {
        this.idg = idg;
    }

    public Integer getIdg() {
        return idg;
    }

    public void setIdg(Integer idg) {
        this.idg = idg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<DatosbeneficiadosEntity> getDatosbeneficiadosEntityList() {
        return datosbeneficiadosEntityList;
    }

    public void setDatosbeneficiadosEntityList(List<DatosbeneficiadosEntity> datosbeneficiadosEntityList) {
        this.datosbeneficiadosEntityList = datosbeneficiadosEntityList;
    }

    @XmlTransient
    public List<UsuariosEntity> getUsuariosEntityList() {
        return usuariosEntityList;
    }

    public void setUsuariosEntityList(List<UsuariosEntity> usuariosEntityList) {
        this.usuariosEntityList = usuariosEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idg != null ? idg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneroEntity)) {
            return false;
        }
        GeneroEntity other = (GeneroEntity) object;
        if ((this.idg == null && other.idg != null) || (this.idg != null && !this.idg.equals(other.idg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.GeneroEntity[ idg=" + idg + " ]";
    }


    
}
