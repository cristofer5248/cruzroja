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
@Table(name = "departamento")
@NamedQueries({
    @NamedQuery(name = "DepartamentoEntity.findAll", query = "SELECT d FROM DepartamentoEntity d"),
    @NamedQuery(name = "DepartamentoEntity.findByIddepartamento", query = "SELECT d FROM DepartamentoEntity d WHERE d.iddepartamento = :iddepartamento"),
    @NamedQuery(name = "DepartamentoEntity.findByNombre", query = "SELECT d FROM DepartamentoEntity d WHERE d.nombre = :nombre")})
public class DepartamentoEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "iddepartamento", nullable = false)
    private Integer iddepartamento;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<MunicipioEntity> municipioEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<LugarEntity> lugarEntityList;

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public DepartamentoEntity(Integer iddepartamento, String nombre) {
        this.iddepartamento = iddepartamento;
        this.nombre = nombre;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<MunicipioEntity> getMunicipioEntityList() {
        return municipioEntityList;
    }

    public void setMunicipioEntityList(List<MunicipioEntity> municipioEntityList) {
        this.municipioEntityList = municipioEntityList;
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
        hash += (iddepartamento != null ? iddepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentoEntity)) {
            return false;
        }
        DepartamentoEntity other = (DepartamentoEntity) object;
        if ((this.iddepartamento == null && other.iddepartamento != null) || (this.iddepartamento != null && !this.iddepartamento.equals(other.iddepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.DepartamentoEntity[ iddepartamento=" + iddepartamento + " ]";
    }
    
}
