/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class TipoApparecchiaturaAdapter implements JsonbAdapter<TipoApparecchiatura, JsonObject> {

    @Override
    public JsonObject adaptToJson(TipoApparecchiatura e) throws Exception {
         return Json.createObjectBuilder().add("id", e.getId()).build();
    }

    @Override
    public TipoApparecchiatura adaptFromJson(JsonObject json) throws Exception {
        return json == null || json.isNull("id") ? null : new TipoApparecchiatura(new Long(json.getInt("id")));
    }

  
    
}
