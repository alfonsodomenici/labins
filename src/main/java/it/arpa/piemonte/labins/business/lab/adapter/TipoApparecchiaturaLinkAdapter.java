/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.TipoApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class TipoApparecchiaturaLinkAdapter implements JsonbAdapter<TipoApparecchiatura, JsonObject> {

    @Inject
    TipoApparecchiaturaStore store;

    @Override
    public JsonObject adaptToJson(TipoApparecchiatura obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("descrizione", obj.getDescrizione())
                .build();
    }

    @Override
    public TipoApparecchiatura adaptFromJson(JsonObject obj) throws Exception {
        System.out.println("json object -> " + obj);
        System.out.println(store == null);
        return store.find(new Long(obj.getInt("id")));
    }

}
