/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.boundary.DominioLink;
import it.arpa.piemonte.labins.business.lab.entity.Dominio;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class DominioLinkAdapter implements JsonbAdapter<Dominio, DominioLink> {

    @Override
    public DominioLink adaptToJson(Dominio obj) throws Exception {
        return new DominioLink(obj);
    }

    @Override
    public Dominio adaptFromJson(DominioLink obj) throws Exception {
        return new Dominio(obj.id);
    }
    
}
