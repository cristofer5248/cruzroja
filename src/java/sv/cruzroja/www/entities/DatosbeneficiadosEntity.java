/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author crist
 */
@Entity
@Table(name = "datosbeneficiados")
@NamedQueries({
    @NamedQuery(name = "DatosbeneficiadosEntity.findAll", query = "SELECT d FROM DatosbeneficiadosEntity d"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findXapellidosfiltro", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.apellidos LIKE :apellido"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findX2mes", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.fecha >= CAST(:fecha AS DATE)"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findByIdusuario", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.idusuario = :idusuario"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findByNombres", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.nombres = :nombres"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findByApellidos", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.apellidos = :apellidos"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findByCorreo", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.correo = :correo"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findByEdad", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.edad = :edad"),
    @NamedQuery(name = "DatosbeneficiadosEntity.findByTelefono", query = "SELECT d FROM DatosbeneficiadosEntity d WHERE d.telefono = :telefono")})
public class DatosbeneficiadosEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idusuario", nullable = false, length = 6)
    private String idusuario;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    @Column(name = "apellidos", length = 20)
    private String apellidos;
    @Column(name = "correo", length = 40)
    private String correo;
    @Basic(optional = false)
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "telefono")
    private Integer telefono;
    @JoinColumn(name = "genero", referencedColumnName = "idg", nullable = false)
    @ManyToOne(optional = false)
    private GeneroEntity genero;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbeneficiado")
    private List<BeneficiadosEntity> beneficiadosEntityList;

    public DatosbeneficiadosEntity() {
    }

    public DatosbeneficiadosEntity(String idusuario) {
        this.idusuario = idusuario;
    }

    public DatosbeneficiadosEntity(String idusuario, String nombres, int edad) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.edad = edad;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<BeneficiadosEntity> getBeneficiadosEntityList() {
        return beneficiadosEntityList;
    }

    public void setBeneficiadosEntityList(List<BeneficiadosEntity> beneficiadosEntityList) {
        this.beneficiadosEntityList = beneficiadosEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosbeneficiadosEntity)) {
            return false;
        }
        DatosbeneficiadosEntity other = (DatosbeneficiadosEntity) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.DatosbeneficiadosEntity[ idusuario=" + idusuario + " ]";
    }

}
