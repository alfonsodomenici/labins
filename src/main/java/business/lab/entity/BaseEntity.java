/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Past;

/**
 *
 * @author utente
 */
@MappedSuperclass
public class BaseEntity extends AbstractEntity {

    @Past
    @Column(name = "data_creazione")
    protected LocalDate creatoIl;

    @Past
    @Column(name = "data_modifca")
    protected LocalDate modificatoIl;

    @Column(name = "utente_creazione")
    protected String creatoDa;

    @Column(name = "utente_modifica")
    protected String modificatoDa;

    protected boolean nascosto;

    public LocalDate getCreatoIl() {
        return creatoIl;
    }

    public void setCreatoIl(LocalDate creatoIl) {
        this.creatoIl = creatoIl;
    }

    public LocalDate getModificatoIl() {
        return modificatoIl;
    }

    public void setModificatoIl(LocalDate modificatoIl) {
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

    public boolean isNascosto() {
        return nascosto;
    }

    public void setNascosto(boolean nascosto) {
        this.nascosto = nascosto;
    }

}
