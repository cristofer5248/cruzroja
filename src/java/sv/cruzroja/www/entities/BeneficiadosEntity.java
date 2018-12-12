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
 *
 */
@Entity
@Table(name = "beneficiados")
@NamedQueries({
    @NamedQuery(name = "BeneficiadosEntity.findAll", query = "SELECT b FROM BeneficiadosEntity b"),
    @NamedQuery(name = "BeneficiadosEntity.findByBeneficiado", query = "SELECT b FROM BeneficiadosEntity b where b.idbeneficiado.idusuario = :idbene ORDER BY b.idproyecto.lugarproyectoPadre.idlp.categoria.nombre"),
    @NamedQuery(name = "BeneficiadosEntity.findByIdlp", query = "SELECT b FROM BeneficiadosEntity b where b.idproyecto.lugarproyectoPadre.idl = :codproyecto"),
    @NamedQuery(name = "BeneficiadosEntity.findByIdlpGROUP", query = "SELECT b FROM BeneficiadosEntity b where b.idproyecto.lugarproyectoPadre.idl = :codproyecto GROUP BY b.idbeneficiado.idusuario"),
    @NamedQuery(name = "BeneficiadosEntity.findByIdlpOrder", query = "SELECT b FROM BeneficiadosEntity b where b.idproyecto.lugarproyectoPadre.idl = :codproyecto ORDER BY b.idbeneficiado.idusuario"),
    @NamedQuery(name = "BeneficiadosEntity.findByIdbeneYactividad", query = "SELECT b FROM BeneficiadosEntity b where b.idbeneficiado.idusuario = :idusu AND b.idproyecto.idactividad=:idactividad "),
    @NamedQuery(name = "BeneficiadosEntity.findByTotalXarea", query = "SELECT b FROM BeneficiadosEntity b where b.idproyecto.lugarproyectoPadre.idlp.categoria.idcategoria = :idarea"),
    @NamedQuery(name = "BeneficiadosEntity.findByEdad", query = "SELECT b FROM BeneficiadosEntity b where b.idbeneficiado.edad >= :edad1 AND b.idbeneficiado.edad <=:edad2 and b.idproyecto.lugarproyectoPadre.idl = :idpro"),
    @NamedQuery(name = "BeneficiadosEntity.findByEdadGroup", query = "SELECT b FROM BeneficiadosEntity b where b.idbeneficiado.edad >= :edad1 AND b.idbeneficiado.edad <=:edad2 and b.idproyecto.lugarproyectoPadre.idl = :idpro GROUP BY b.idbeneficiado.idusuario ORDER BY b.idbeneficiado.edad desc"),
    @NamedQuery(name = "BeneficiadosEntity.findByLugar", query = "SELECT b FROM BeneficiadosEntity b where b.idproyecto.lugarproyectoPadre.idl = :idlugar AND b.idbeneficiado.edad< :limite"),
    @NamedQuery(name = "BeneficiadosEntity.findByIdb", query = "SELECT b FROM BeneficiadosEntity b WHERE b.idb = :idb")})
public class BeneficiadosEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idb", nullable = false, length = 8)
    private String idb;
    @JoinColumn(name = "idbeneficiado", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private DatosbeneficiadosEntity idbeneficiado;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idactividad", nullable = false)
    @ManyToOne(optional = false)
    private Actividades idproyecto;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public BeneficiadosEntity() {
    }

    public BeneficiadosEntity(String idb) {
        this.idb = idb;
    }

    public String getIdb() {
        return idb;
    }

    public void setIdb(String idb) {
        this.idb = idb;
    }

    public DatosbeneficiadosEntity getIdbeneficiado() {
        return idbeneficiado;
    }

    public void setIdbeneficiado(DatosbeneficiadosEntity idbeneficiado) {
        this.idbeneficiado = idbeneficiado;
    }

    public Actividades getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Actividades idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idb != null ? idb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeneficiadosEntity)) {
            return false;
        }
        BeneficiadosEntity other = (BeneficiadosEntity) object;
        if ((this.idb == null && other.idb != null) || (this.idb != null && !this.idb.equals(other.idb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.cruzroja.www.entities.BeneficiadosEntity[ idb=" + idb + " ]";
    }

}
