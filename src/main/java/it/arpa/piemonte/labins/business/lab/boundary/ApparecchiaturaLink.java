/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import java.time.LocalDate;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */

public class ApparecchiaturaLink {

    public Long id;

    public Link link;
    public String responsabile;
    public String modello;
    public String codice;
    public String descrizione;
    public TipoApparecchiaturaLink tipologia;
    public String matricola;
    public String firmware;
    public AziendaLink costruttore;
    public LaboratorioLink laboratorio;
    public DominioLink dominio;
    public LocalDate dataPianificata;
    
    public ApparecchiaturaLink(Apparecchiatura e) {
        this.id = e.getId();
        this.responsabile = e.getResponsabile();
        this.modello = e.getModello();
        this.codice = e.getCodice();
        this.descrizione = e.getDescrizione();
        this.firmware = e.getFirmware();
        this.tipologia = e.getTipologia() == null ? null : new TipoApparecchiaturaLink(e.getTipologia());
        this.matricola = e.getMatricola();
        this.costruttore = e.getCostruttore() == null ? null : new AziendaLink(e.getCostruttore());
        this.laboratorio = e.getLaboratorio() == null ? null : new LaboratorioLink(e.getLaboratorio());
        this.dominio = e.getDominio() == null ? null : new DominioLink(e.getDominio());
        this.dataPianificata = e.getDataPianificata();
    }

}
