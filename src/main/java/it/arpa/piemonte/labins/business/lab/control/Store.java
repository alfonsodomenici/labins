/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 *
 * @author alfonso
 */

public abstract class Store<K> {
    
    protected <E> Path<E> getPathExp(Root<K> rq, String field, Class<E> clazz) {
        if (field.indexOf('.') == -1) {
            return rq.<E>get(field);
        }

        String[] fields = field.split("\\.");
        Path<E> p = rq.<E>get(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            p = p.get(fields[i]);
        }
        return p;
    }
    
}
