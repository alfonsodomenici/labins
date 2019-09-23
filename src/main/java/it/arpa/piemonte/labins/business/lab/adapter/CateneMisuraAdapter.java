/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.CatenaMisuraStore;
import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class CateneMisuraAdapter implements JsonbAdapter<Set<CatenaMisura>, JsonArray> {

    @Inject
    CatenaMisuraStore store;
    
    @Override
    public JsonArray adaptToJson(Set<CatenaMisura> list) throws Exception {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        list.forEach(e-> builder.add(e.getId()));
        return builder.build();
    }

    @Override
    public Set<CatenaMisura> adaptFromJson(JsonArray json) throws Exception {
        TreeSet<CatenaMisura> result = new TreeSet<>();
        json.forEach(e->{
            result.add(store.find(new Long(e.toString())));
        });
        return result;
    }




    
}
