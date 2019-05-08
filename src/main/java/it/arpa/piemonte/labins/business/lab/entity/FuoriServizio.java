/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import it.arpa.piemonte.labins.business.CrossCheck;
import it.arpa.piemonte.labins.business.ValidEntity;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "fuori_servizio")
@CrossCheck
public class FuoriServizio extends AbstractEntity implements ValidEntity {

    @Override
    public boolean isValid() {
        switch (motivo) {
            case MANUTENZIONE:
                return manutenzione != null && taratura == null;
            case TARATURA:
                return taratura != null && manutenzione == null;
            default:
                return manutenzione == null && taratura == null;
        }
    }

    public static enum Motivo {
        MANUTENZIONE, TARATURA, VERIFICA_INTERMEDIA, FS_STRAORDINARIO
    }

    public static enum Esito {
        POSITIVO, NEGATIVO
    }

    @Enumerated(EnumType.STRING)
    private Motivo motivo;

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

    @Enumerated(EnumType.STRING)
    private Esito esito;

    @Size(message = "Il campo descrizione può avere al max 255 caratteri")
    @Column(length = 1024)
    private String descrizione;

    @ManyToMany()
    @JoinTable(name = "fuori_servizio_documento",
            joinColumns = @JoinColumn(name = "fuori_servizio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "documento_id", referencedColumnName = "id")
    )
    private Set<Documento> documenti;

    @OneToOne
    private FsTaratura taratura;

    @OneToOne
    private FsManutenzione manutenzione;

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

    public Set<Documento> getDocumenti() {
        return documenti;
    }

    public void setDocumenti(Set<Documento> documenti) {
        this.documenti = documenti;
    }

    public FsTaratura getTaratura() {
        return taratura;
    }

    public void setTaratura(FsTaratura taratura) {
        this.taratura = taratura;
    }

    public FsManutenzione getManutenzione() {
        return manutenzione;
    }

    public void setManutenzione(FsManutenzione manutenzione) {
        this.manutenzione = manutenzione;
    }

    @Override
    public String toString() {
        return descrizione;
    }

}
