/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.DominioStore;
import it.arpa.piemonte.labins.business.lab.entity.Dominio;
import it.arpa.piemonte.labins.business.lab.entity.Dominio;
import javax.inject.Inject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class DominioAdapter implements JsonbAdapter<Dominio, Long> {

    @Inject
    DominioStore store;
    
    @Override
    public Long adaptToJson(Dominio e) throws Exception {
        return e == null ? null :  e.getId();
    }

    @Override
    public Dominio adaptFromJson(Long id) throws Exception {
        return id == null ? null : store.find(id);
    }
    
}
