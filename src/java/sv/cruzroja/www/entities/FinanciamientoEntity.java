/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author crist
 */
@Entity
@Table(name = "financiamiento")
@NamedQueries({
    @NamedQuery(name = "FinanciamientoEntity.findAll", query = "SELECT f FROM FinanciamientoEntity f"),
    @NamedQuery(name = "FinanciamientoEntity.findAllMesActual", query = "SELECT f FROM FinanciamientoEntity f where FUNC('MONTH', f.fechaIngreso) =:mes AND FUNC('YEAR', f.fechaIngreso) =:anio"),
    @NamedQuery(name = "FinanciamientoEntity.findAllFiltro", query = "SELECT f FROM FinanciamientoEntity f where CAST(f.fechaIngreso as Date)>=:fecha AND CAST(f.fechaIngreso as Date)<= :fecha2"),
    @NamedQuery(name = "FinanciamientoEntity.findTotal", query = "SELECT sum(f.financiamiento)as total FROM FinanciamientoEntity f"),
    @NamedQuery(name = "FinanciamientoEntity.findTotalFiltro", query = "SELECT sum(f.financiamiento)as total FROM FinanciamientoEntity f where CAST(f.fechaIngreso as Date)>=:fecha AND CAST(f.fechaIngreso as Date)<= :fecha2"),
    @NamedQuery(name = "FinanciamientoEntity.findTotalporfiltro", query = "SELECT sum(f.financiamiento)as total FROM FinanciamientoEntity f where FUNC('MONTH', f.fechaIngreso) =:mes AND FUNC('YEAR', f.fechaIngreso) =:anio"),
    @NamedQuery(name = "FinanciamientoEntity.findByIdp", query = "SELECT f FROM FinanciamientoEntity f WHERE f.idp = :idp"),
    @NamedQuery(name = "FinanciamientoEntity.findByFinanciamiento", query = "SELECT f FROM FinanciamientoEntity f WHERE f.financiamiento = :financiamiento")})
public class FinanciamientoEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idp", nullable = false, length = 5)
    private String idp;
    @Basic(optional = false)
    @Column(name = "financiamiento", nullable = false)
    private double financiamiento;
    @Basic(optional = false)
    @Column(name = "fechaIngreso", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @Column(name = "Detalles", nullable = false, length = 200)
    private String detalles;
    @JoinColumn(name = "sociedadN", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private SociedadEntity sociedadN;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto", nullable = false)
    @ManyToOne(optional = false)
    private ProyectosEntity proyecto;

    public FinanciamientoEntity() {
    }

    public FinanciamientoEntity(String idp) {
        this.idp = idp;
    }

    public FinanciamientoEntity(String idp, double financiamiento, Date fechaIngreso, String detalles) {
        this.idp = idp;
        this.financiamiento = financiamiento;
        this.fechaIngreso = fechaIngreso;
        this.detalles = detalles;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public double getFinanciamiento() {
        return financiamiento;
    }

    public void setFinanciamiento(double financiamiento) {
        this.financiamiento = financiamiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public SociedadEntity getSociedadN() {
        return sociedadN;
    }

    public void setSociedadN(SociedadEntity sociedadN) {
        this.sociedadN = sociedadN;
    }

    public ProyectosEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectosEntity proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idp != null ? idp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinanciamientoEntity)) {
            return false;
        }
        FinanciamientoEntity other = (FinanciamientoEntity) object;
        if ((this.idp == null && other.idp != null) || (this.idp != null && !this.idp.equals(other.idp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.FinanciamientoEntity[ idp=" + idp + " ]";
    }

    
}
