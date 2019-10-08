/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import it.arpa.piemonte.labins.business.CrossCheck;
import it.arpa.piemonte.labins.business.ValidEntity;
import it.arpa.piemonte.labins.business.lab.adapter.ApparecchiaturaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.AziendaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.EsitoFuoriServizioLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.FuoriServizioLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.MotivoFuoriServizioLinkAdapter;
import java.time.LocalDate;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */

@NamedQueries({
    @NamedQuery(name = FuoriServizio.FIND_BY_PARENT,
            query = "select e from FuoriServizio e where e.apparecchiatura.id= :idApparecchiatura and e.parent.id= :idParent ")
})
@Entity
@Table(name = "fuori_servizio")
@CrossCheck
public class FuoriServizio extends AbstractEntity implements ValidEntity {

    public static final String FIND_BY_PARENT = "FuoriServizio.findByParent";
    
    @Override
    public boolean isValid() {
        switch (motivo) {
            case MANUTENZIONE:
                return true;
            case TARATURA:
                return true;
            default:
                return true;
        }
    }

    public static enum Motivo {
        MANUTENZIONE, TARATURA, VERIFICA_INTERMEDIA, FS_STRAORDINARIO
    }

    public static enum Esito {
        POSITIVO, NEGATIVO
    }

    @JsonbTypeAdapter(FuoriServizioLinkAdapter.class)
    @ManyToOne
    private FuoriServizio parent;

    @JsonbTypeAdapter(MotivoFuoriServizioLinkAdapter.class)
    @Enumerated(EnumType.STRING)
    private Motivo motivo;

    @JsonbTypeAdapter(ApparecchiaturaLinkAdapter.class)
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Apparecchiatura apparecchiatura;

    @Column(name = "data_inizio")
    private LocalDate inizio;

    @Column(name = "data_fine")
    private LocalDate fine;

    @Column(name = "utente_inizio")
    private String utenteInizio;

    @Column(name = "utente_fine")
    private String utenteFine;

    @JsonbTypeAdapter(EsitoFuoriServizioLinkAdapter.class)
    @Enumerated(EnumType.STRING)
    private Esito esito;

    @Size(message = "Il campo descrizione può avere al max 255 caratteri")
    @Column(length = 1024)
    private String descrizione;

    @Column(name = "data_certificato")
    private LocalDate certificatoIl;
    private boolean accreditato;

    @JsonbTypeAdapter(AziendaLinkAdapter.class)
    @ManyToOne()
    private Azienda azienda;

    @JsonbTypeAdapter(ApparecchiaturaLinkAdapter.class)
    @ManyToOne()
    private Apparecchiatura riferimento;

    @Column(name = "necessaria_verifica")
    private boolean necessariaVerifica;
    @Column(name = "giorni_verifica")
    private int giorniVerifica;

    public FuoriServizio getParent() {
        return parent;
    }

    public void setParent(FuoriServizio parent) {
        this.parent = parent;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public Apparecchiatura getApparecchiatura() {
        return apparecchiatura;
    }

    public void setApparecchiatura(Apparecchiatura apparecchiatura) {
        this.apparecchiatura = apparecchiatura;
    }

    public LocalDate getInizio() {
        return inizio;
    }

    public void setInizio(LocalDate inizio) {
        this.inizio = inizio;
    }

    public LocalDate getFine() {
        return fine;
    }

    public void setFine(LocalDate fine) {
        this.fine = fine;
    }

    public String getUtenteInizio() {
        return utenteInizio;
    }

    public void setUtenteInizio(String utenteInizio) {
        this.utenteInizio = utenteInizio;
    }

    public String getUtenteFine() {
        return utenteFine;
    }

    public void setUtenteFine(String utenteFine) {
        this.utenteFine = utenteFine;
    }

    public Esito getEsito() {
        return esito;
    }

    public void setEsito(Esito esito) {
        this.esito = esito;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    @Override
    public String toString() {
        return descrizione;
    }

}
