/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;

/**
 *
 * @author alfonso
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @Column(name = "data_creazione")
    protected Instant creatoIl;

    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @Column(name = "data_modifca")
    protected Instant modificatoIl;

    @Column(name = "utente_creazione")
    protected String creatoDa;

    @Column(name = "utente_modifica")
    protected String modificatoDa;

    protected boolean nascosto;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isNascosto() {
        return nascosto;
    }

    public void setNascosto(boolean nascosto) {
        this.nascosto = nascosto;
    }

    public Instant getCreatoIl() {
        return creatoIl;
    }

    public void setCreatoIl(Instant creatoIl) {
        this.creatoIl = creatoIl;
    }

    public Instant getModificatoIl() {
        return modificatoIl;
    }

    public void setModificatoIl(Instant modificatoIl) {
        this.modificatoIl = modificatoIl;
    }

    public String getCreatoDa() {
        return creatoDa;
    }

    public void setCreatoDa(String creatoDa) {
        this.creatoDa = creatoDa;
    }

    public String getModificatoDa() {
        return modificatoDa;
    }

    public void setModificatoDa(String modificatoDa) {
        this.modificatoDa = modificatoDa;
    }

}
