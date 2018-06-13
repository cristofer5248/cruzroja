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
@Table(name = "lugar")
@NamedQueries({
    @NamedQuery(name = "LugarEntity.findAll", query = "SELECT l FROM LugarEntity l ORDER BY L.nombre ASC"),
    @NamedQuery(name = "LugarEntity.findByIdlugar", query = "SELECT l FROM LugarEntity l WHERE l.idlugar = :idlugar"),
    @NamedQuery(name = "LugarEntity.findByNombre", query = "SELECT l FROM LugarEntity l WHERE l.nombre = :nombre")})
public class LugarEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idlugar", nullable = false, length = 5)
    private String idlugar;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlugar")
    private List<LugarproyectoEntity> lugarproyectoEntityList;
    @JoinColumn(name = "municipio", referencedColumnName = "idmunicipio", nullable = false)
    @ManyToOne(optional = false)
    private MunicipioEntity municipio;
    @JoinColumn(name = "departamento", referencedColumnName = "iddepartamento", nullable = false)
    @ManyToOne(optional = false)
    private DepartamentoEntity departamento;
    @JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LugarcategoriaEntity categoria;

    public LugarEntity() {
    }

    public LugarEntity(String idlugar) {
        this.idlugar = idlugar;
    }

    public LugarEntity(String idlugar, String nombre) {
        this.idlugar = idlugar;
        this.nombre = nombre;
    }

    public String getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(String idlugar) {
        this.idlugar = idlugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<LugarproyectoEntity> getLugarproyectoEntityList() {
        return lugarproyectoEntityList;
    }

    public void setLugarproyectoEntityList(List<LugarproyectoEntity> lugarproyectoEntityList) {
        this.lugarproyectoEntityList = lugarproyectoEntityList;
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public LugarcategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(LugarcategoriaEntity categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlugar != null ? idlugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugarEntity)) {
            return false;
        }
        LugarEntity other = (LugarEntity) object;
        if ((this.idlugar == null && other.idlugar != null) || (this.idlugar != null && !this.idlugar.equals(other.idlugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.LugarEntity[ idlugar=" + idlugar + " ]";
    }

 
    
}
