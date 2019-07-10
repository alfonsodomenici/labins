/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.boundary.TipoApparecchiaturaLink;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class TipoApparecchiaturaLinkAdapter implements JsonbAdapter<TipoApparecchiatura, TipoApparecchiaturaLink> {

        @Override
    public TipoApparecchiaturaLink adaptToJson(TipoApparecchiatura obj) throws Exception {
        return new TipoApparecchiaturaLink(obj);
    }

    @Override
    public TipoApparecchiatura adaptFromJson(TipoApparecchiaturaLink obj) throws Exception {
        return new TipoApparecchiatura(obj.id);
    }
  
    
}
