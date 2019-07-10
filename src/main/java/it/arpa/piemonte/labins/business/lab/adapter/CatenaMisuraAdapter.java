/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class CatenaMisuraAdapter implements JsonbAdapter<CatenaMisura, JsonObject> {

    @Override
    public JsonObject adaptToJson(CatenaMisura e) throws Exception {
        return Json.createObjectBuilder().add("id", e.getId()).build();
    }

    @Override
    public CatenaMisura adaptFromJson(JsonObject e) throws Exception {
        return e == null || e.isNull("id") ? null :  new CatenaMisura(new Long(e.getInt("id")));
    }


    
}
