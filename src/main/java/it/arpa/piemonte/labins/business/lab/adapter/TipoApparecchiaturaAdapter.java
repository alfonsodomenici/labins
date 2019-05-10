/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.TipoApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import javax.inject.Inject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class TipoApparecchiaturaAdapter implements JsonbAdapter<TipoApparecchiatura, Long> {

    @Inject
    TipoApparecchiaturaStore store;
    
    @Override
    public Long adaptToJson(TipoApparecchiatura e) throws Exception {
        return e == null ? null :  e.getId();
    }

    @Override
    public TipoApparecchiatura adaptFromJson(Long id) throws Exception {
        return id == null ? null : store.find(id);
    }
    
}
