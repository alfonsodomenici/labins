/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author utente
 */
public class Apparecchiatura extends AbstractEntity {

    @NotEmpty(message = "Il campo codice è obbligatorio")
    @Column(nullable = false)
    private String codice;

    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    @Column(nullable = false)
    private String descrizione;

    @ManyToOne
    private Laboratorio laboratorio;

    @ManyToOne
    private Dominio dominio;

    @ManyToOne
    private CatenaMisura catenaMisura;

    @ManyToOne
    private TipoApparecchiatura tipo;

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
    
    private double campoMin;
    
    private double campoMax;
    
    private String incertezza;
    
    private String campoOperativo;
    
    private String criterioAccettazione;
    
    @Temporal(TemporalType.DATE)
    private LocalDate fabbricatoIl;
    
    @Temporal(TemporalType.DATE)
    private LocalDate acquistatoIl;
    
    @Temporal(TemporalType.DATE)
    private LocalDate inFunzioneDal;
    
    
    
}
