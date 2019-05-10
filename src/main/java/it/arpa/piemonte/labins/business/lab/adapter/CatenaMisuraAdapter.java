/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.CatenaMisuraStore;
import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import javax.inject.Inject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class CatenaMisuraAdapter implements JsonbAdapter<CatenaMisura, Long> {

    @Inject
    CatenaMisuraStore store;
    
    @Override
    public Long adaptToJson(CatenaMisura e) throws Exception {
        return e == null ? null :  e.getId();
    }

    @Override
    public CatenaMisura adaptFromJson(Long id) throws Exception {
        return id == null ? null : store.find(id);
    }
    
}
