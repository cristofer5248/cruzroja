/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@Table(name = "actividadesdetalles")
@NamedQueries({
    @NamedQuery(name = "ActividadesdetallesEntity.findAll", query = "SELECT a FROM ActividadesdetallesEntity a"),
    @NamedQuery(name = "ActividadesdetallesEntity.findById", query = "SELECT a FROM ActividadesdetallesEntity a where a.idactividadesdetalles= :codigo"),
    @NamedQuery(name = "ActividadesdetallesEntity.findAllxidactividad", query = "SELECT a FROM ActividadesdetallesEntity a where a.idactividadesdetalles = :codigo")})
public class ActividadesdetallesEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idactividadesdetalles", nullable = false, length = 6)
    private String idactividadesdetalles;
    @Column(name = "titulo", length = 40)
    private String titulo;
    @Column(name = "detalle", length = 300)
    private String detalle;
    @OneToMany(mappedBy = "detalleactividad" )
    private List<Actividades> actividadesList;

    public ActividadesdetallesEntity() {
    }

    public ActividadesdetallesEntity(String idactividadesdetalles) {
        this.idactividadesdetalles = idactividadesdetalles;
    }

    public String getIdactividadesdetalles() {
        return idactividadesdetalles;
    }

    public void setIdactividadesdetalles(String idactividadesdetalles) {
        this.idactividadesdetalles = idactividadesdetalles;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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
        hash += (idactividadesdetalles != null ? idactividadesdetalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadesdetallesEntity)) {
            return false;
        }
        ActividadesdetallesEntity other = (ActividadesdetallesEntity) object;
        if ((this.idactividadesdetalles == null && other.idactividadesdetalles != null) || (this.idactividadesdetalles != null && !this.idactividadesdetalles.equals(other.idactividadesdetalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.ActividadesdetallesEntity[ idactividadesdetalles=" + idactividadesdetalles + " ]";
    }
    
}
