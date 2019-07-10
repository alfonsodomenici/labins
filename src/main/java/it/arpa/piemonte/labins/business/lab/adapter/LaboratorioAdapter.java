/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class LaboratorioAdapter implements JsonbAdapter<Laboratorio, JsonObject> {

    @Override
    public JsonObject adaptToJson(Laboratorio e) throws Exception {
        return Json.createObjectBuilder().add("id", e.getId()).build();
    }

    @Override
    public Laboratorio adaptFromJson(JsonObject obj) throws Exception {
        return new Laboratorio(new Long(obj.getInt("id")));
    }    
}
