/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "fs_taratura")
public class FsTaratura extends AbstractEntity {

    @Column(name = "data_certificato")
    private LocalDate certificatoIl;
    private boolean accreditato;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Azienda azienda;
    @ManyToOne(optional = false)
    @JoinColumn(name = "riferimento_id", nullable = false)
    private Apparecchiatura riferimento;
    @Column(name = "necessaria_verifica")
    private boolean necessariaVerifica;
    @Column(name = "giorni_verifica")
    private int giorniVerifica;

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public Apparecchiatura getRiferimento() {
        return riferimento;
    }

    public void setRiferimento(Apparecchiatura riferimento) {
        this.riferimento = riferimento;
    }

    public LocalDate getCertificatoIl() {
        return certificatoIl;
    }

    public void setCertificatoIl(LocalDate certificatoIl) {
        this.certificatoIl = certificatoIl;
    }

    public boolean isAccreditato() {
        return accreditato;
    }

    public void setAccreditato(boolean accreditato) {
        this.accreditato = accreditato;
    }

    public boolean isNecessariaVerifica() {
        return necessariaVerifica;
    }

    public void setNecessariaVerifica(boolean necessariaVerifica) {
        this.necessariaVerifica = necessariaVerifica;
    }

    public int getGiorniVerifica() {
        return giorniVerifica;
    }

    public void setGiorniVerifica(int giorniVerifica) {
        this.giorniVerifica = giorniVerifica;
    }

}
