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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inizio")
    private LocalDate inizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fine")
    private LocalDate fine;

    @Column(name = "utente_inizio")
    private String utenteInizio;

    @Column(name = "utente_fine")
    private String utenteFine;

    @Temporal(TemporalType.DATE)
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

}
