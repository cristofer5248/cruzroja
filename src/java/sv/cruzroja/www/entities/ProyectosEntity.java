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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author crist
 */
@Entity
@Table(name = "proyectos")
@NamedQueries({
    @NamedQuery(name = "ProyectosEntity.findAll", query = "SELECT p FROM ProyectosEntity p"),
    @NamedQuery(name = "ProyectosEntity.findByIdproyecto", query = "SELECT p FROM ProyectosEntity p WHERE p.idproyecto = :idproyecto"),
    @NamedQuery(name = "ProyectosEntity.findByNombre", query = "SELECT p FROM ProyectosEntity p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ProyectosEntity.findByDes", query = "SELECT p FROM ProyectosEntity p WHERE p.des = :des")})
public class ProyectosEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idproyecto", nullable = false, length = 6)
    private String idproyecto;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @Column(name = "des", length = 120)
    private String des;
    @Column(name = "objetivos", length = 300)
    private String objetivos;
    @Column(name = "indicadores", length = 100)
    private String indicadores;
    @Column(name = "hipotesis", length = 150)
    private String hipotesis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlp")
    private List<LugarproyectoEntity> lugarproyectoEntityList;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria", nullable = false)
    @ManyToOne(optional = false)
    private CategoriaEntity categoria;
    @JoinColumn(name = "sociedadN", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private SociedadEntity sociedadN;
    @JoinColumn(name = "seccionales", referencedColumnName = "idmunicipio", nullable = false)
    @ManyToOne(optional = false)
    private MunicipioEntity seccionales;
    @JoinColumn(name = "encargado", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private UsuariosEntity encargado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private List<FinanciamientoEntity> financiamientoEntityList;

    public ProyectosEntity() {
    }

    public ProyectosEntity(String idproyecto) {
        this.idproyecto = idproyecto;
    }

    public ProyectosEntity(String idproyecto, String nombre) {
        this.idproyecto = idproyecto;
        this.nombre = nombre;
    }

    public String getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(String indicadores) {
        this.indicadores = indicadores;
    }

    public String getHipotesis() {
        return hipotesis;
    }

    public void setHipotesis(String hipotesis) {
        this.hipotesis = hipotesis;
    }

    @XmlTransient
    public List<LugarproyectoEntity> getLugarproyectoEntityList() {
        return lugarproyectoEntityList;
    }

    public void setLugarproyectoEntityList(List<LugarproyectoEntity> lugarproyectoEntityList) {
        this.lugarproyectoEntityList = lugarproyectoEntityList;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public SociedadEntity getSociedadN() {
        return sociedadN;
    }

    public void setSociedadN(SociedadEntity sociedadN) {
        this.sociedadN = sociedadN;
    }

    public MunicipioEntity getSeccionales() {
        return seccionales;
    }

    public void setSeccionales(MunicipioEntity seccionales) {
        this.seccionales = seccionales;
    }

    public UsuariosEntity getEncargado() {
        return encargado;
    }

    public void setEncargado(UsuariosEntity encargado) {
        this.encargado = encargado;
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
        hash += (idproyecto != null ? idproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectosEntity)) {
            return false;
        }
        ProyectosEntity other = (ProyectosEntity) object;
        if ((this.idproyecto == null && other.idproyecto != null) || (this.idproyecto != null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.ProyectosEntity[ idproyecto=" + idproyecto + " ]";
    }

    
}
