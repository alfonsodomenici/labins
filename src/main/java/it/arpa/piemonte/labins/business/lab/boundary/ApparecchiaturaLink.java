/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */
public class ApparecchiaturaLink {

    public Long id;

    public Link link;

    public String codice;
    public String descrizione;
    public TipoApparecchiaturaLink tipologia;
    public String matricola;
    public AziendaLink costruttore;
    public LaboratorioLink laboratorio;
    public DominioLink dominio;

    public ApparecchiaturaLink(Apparecchiatura e) {
        this.id = e.getId();
        this.codice = e.getCodice();
        this.descrizione = e.getDescrizione();
        this.tipologia = e.getTipologia() == null ? null : new TipoApparecchiaturaLink(e.getTipologia());
        this.matricola = e.getMatricola();
        this.costruttore = e.getCostruttore() == null ? null : new AziendaLink(e.getCostruttore());
        this.laboratorio = e.getLaboratorio() == null ? null : new LaboratorioLink(e.getLaboratorio());
        this.dominio = e.getDominio() == null ? null : new DominioLink(e.getDominio());
    }

}
