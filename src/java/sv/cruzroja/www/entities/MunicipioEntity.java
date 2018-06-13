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
@Table(name = "municipio")
@NamedQueries({
    @NamedQuery(name = "MunicipioEntity.findAll", query = "SELECT m FROM MunicipioEntity m"),
    @NamedQuery(name = "MunicipioEntity.findByIdmunicipio", query = "SELECT m FROM MunicipioEntity m WHERE m.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "MunicipioEntity.findByIddepartamento", query = "SELECT m FROM MunicipioEntity m WHERE m.departamento = :iddepa"),
    @NamedQuery(name = "MunicipioEntity.findByNombre", query = "SELECT m FROM MunicipioEntity m WHERE m.nombre = :nombre")})
public class MunicipioEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idmunicipio", nullable = false)
    private Integer idmunicipio;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @JoinColumn(name = "departamento", referencedColumnName = "iddepartamento", nullable = false)
    @ManyToOne(optional = false)
    private DepartamentoEntity departamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio")
    private List<LugarEntity> lugarEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccionales")
    private List<ProyectosEntity> proyectosEntityList;

    public MunicipioEntity() {
    }

    public MunicipioEntity(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public MunicipioEntity(Integer idmunicipio, String nombre) {
        this.idmunicipio = idmunicipio;
        this.nombre = nombre;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    @XmlTransient
    public List<LugarEntity> getLugarEntityList() {
        return lugarEntityList;
    }

    public void setLugarEntityList(List<LugarEntity> lugarEntityList) {
        this.lugarEntityList = lugarEntityList;
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
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipioEntity)) {
            return false;
        }
        MunicipioEntity other = (MunicipioEntity) object;
        if ((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.MunicipioEntity[ idmunicipio=" + idmunicipio + " ]";
    }

    
}
    