/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "fuori_servizio")
public class FuoriServizio extends AbstractEntity {

    public static enum Motivo {
        MANUTENZIONE, TARATURA, FS_STRAORDINARIO
    }

    public static enum Esito {
        POSITIVO, NEGATIVO
    }

    @Enumerated(EnumType.STRING)
    private Motivo motivo;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Apparecchiatura apparecchiatura;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Azienda azienda;

    @ManyToOne(optional = false)
    @JoinColumn(name = "riferimento_id", nullable = false)
    private Apparecchiatura riferimento;

    @Column(name = "data_inizio")
    private LocalDate inizio;

    @Column(name = "data_fine")
    private LocalDate fine;

    @Column(name = "utente_inizio")
    private String utenteInizio;

    @Column(name = "utente_fine")
    private String utenteFine;

    @Column(name = "data_certificato")
    private LocalDate certificatoIl;

    @Enumerated(EnumType.STRING)
    private Esito esito;

    @Size(message = "Il campo descrizione può avere al max 255 caratteri")
    @Column(length = 1024)
    private String descrizione;

    private boolean accreditato;

    private boolean verifica;

    @ManyToMany()
    @JoinTable(name = "fuori_servizio_documento",
            joinColumns = @JoinColumn(name = "fuori_servizio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "documento_id", referencedColumnName = "id")
    )
    private Set<Documento> documenti;

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

    public LocalDate getCertificatoIl() {
        return certificatoIl;
    }

    public void setCertificatoIl(LocalDate certificatoIl) {
        this.certificatoIl = certificatoIl;
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

    public boolean isAccreditato() {
        return accreditato;
    }

    public void setAccreditato(boolean accreditato) {
        this.accreditato = accreditato;
    }

    public boolean isVerifica() {
        return verifica;
    }

    public void setVerifica(boolean verifica) {
        this.verifica = verifica;
    }

    public Set<Documento> getDocumenti() {
        return documenti;
    }

    public void setDocumenti(Set<Documento> documenti) {
        this.documenti = documenti;
    }
    
    @Override
    public String toString() {
        return descrizione;
    }

}
