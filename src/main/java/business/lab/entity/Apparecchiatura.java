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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "apparecchiatura")
public class Apparecchiatura extends BaseEntity {

    @NotEmpty(message = "Il campo codice è obbligatorio")
    @Column(nullable = false)
    private String codice;

    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    @Column(nullable = false)
    private String descrizione;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Laboratorio laboratorio;

    @ManyToOne
    private Dominio dominio;

    @ManyToOne
    @JoinColumn(name = "catena_misura_id")
    private CatenaMisura catenaMisura;

    @ManyToOne
    private TipoApparecchiatura tipologia;

    @ManyToOne
    private Azienda costruttore;

    @ManyToOne
    private Azienda distributore;

    @ManyToOne
    private Azienda taratore;

    @ManyToOne
    private Azienda manutentore; //deve esserci ???

    @NotEmpty(message = "Il campo matricola è obbligatorio")
    @Column(nullable = false)
    private String matricola;

    private boolean riferimento;

    @ManyToOne
    private Grandezza grandezza;

    @ManyToOne
    private UnitaMisura um;

    @Column(name = "campo_min")
    private double campoMin;

    @Column(name = "campo_max")
    private double campoMax;

    private String incertezza;

    @Column(name = "campo_operativo")
    private String campoOperativo;

    @Column(name = "criterio_accettazione")
    private String criterioAccettazione;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fabbricazione")
    private LocalDate fabbricatoIl;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_acquisto")
    private LocalDate acquistatoIl;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrata_funzione")
    private LocalDate inFunzioneDal;

    @ManyToOne
    @JoinColumn(name = "gestione_taratura_id")
    private GestioneApparecchiatura gestioneTaratura;

    @ManyToOne
    @JoinColumn(name = "gestione_manutenzione_id")
    private GestioneApparecchiatura gestioneManutenzione;

    @ManyToMany()
    @JoinTable(name = "apparecchiatura_documento",
            joinColumns = @JoinColumn(name = "apparecchiatura_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "documento_id", referencedColumnName = "id")
    )
    private Set<Documento> documenti;
       
}
