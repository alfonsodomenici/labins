/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class TipoApparecchiaturaStore extends Store<TipoApparecchiatura> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public TipoApparecchiatura save(TipoApparecchiatura e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        em.remove(find(id));
    }

    public TipoApparecchiatura find(Long id) {
        return em.find(TipoApparecchiatura.class, id);
    }

    public List<TipoApparecchiatura> all(Long idLab){
        return em.createQuery("select e from TipoApparecchiatura e where e.laboratorio.id= :idLab order by e.descrizione", TipoApparecchiatura.class)
                .setParameter("idLab", idLab)
                .getResultList();
    }
    
    public JsonArray allAsCustomJson(Long idLab){
        return this.all(idLab)
                .stream()
                .map(TipoApparecchiatura::convertMinimal)
                .collect(jsonCollector)
                .build();
    }
}
