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
@Table(name = "tipou")
@NamedQueries({
    @NamedQuery(name = "TipouEntity.findAll", query = "SELECT t FROM TipouEntity t"),
    @NamedQuery(name = "TipouEntity.findByIdtipou", query = "SELECT t FROM TipouEntity t WHERE t.idtipou = :idtipou"),
    @NamedQuery(name = "TipouEntity.findByNombre", query = "SELECT t FROM TipouEntity t WHERE t.nombre = :nombre")})
public class TipouEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idtipou", nullable = false)
    private Integer idtipou;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "idusuario", nullable = false, length = 30)
    private String idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipousuario")
    private List<UsuariosEntity> usuariosEntityList;

    public TipouEntity() {
    }

    public TipouEntity(Integer idtipou) {
        this.idtipou = idtipou;
    }

    public TipouEntity(Integer idtipou, String nombre, String idusuario) {
        this.idtipou = idtipou;
        this.nombre = nombre;
        this.idusuario = idusuario;
    }

    public Integer getIdtipou() {
        return idtipou;
    }

    public void setIdtipou(Integer idtipou) {
        this.idtipou = idtipou;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
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
        hash += (idtipou != null ? idtipou.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipouEntity)) {
            return false;
        }
        TipouEntity other = (TipouEntity) object;
        if ((this.idtipou == null && other.idtipou != null) || (this.idtipou != null && !this.idtipou.equals(other.idtipou))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.TipouEntity[ idtipou=" + idtipou + " ]";
    }

    
}
