/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.boundary.AziendaLink;
import it.arpa.piemonte.labins.business.lab.entity.Azienda;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class AziendaLinkAdapter implements JsonbAdapter<Azienda, AziendaLink> {

    @Override
    public AziendaLink adaptToJson(Azienda obj) throws Exception {
        return new AziendaLink(obj);
    }

    @Override
    public Azienda adaptFromJson(AziendaLink obj) throws Exception {
        return new Azienda(obj.id);
    }
    
}
