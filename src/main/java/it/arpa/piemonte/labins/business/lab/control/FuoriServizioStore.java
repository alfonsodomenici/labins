/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class FuoriServizioStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public FuoriServizio save(FuoriServizio e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        em.remove(find(id));
    }

    public FuoriServizio find(Long id) {
        return em.find(FuoriServizio.class, id);
    }

    public List<FuoriServizio> all(){
        return em.createQuery("select e from FuoriServizio e order by e.inizio DESC", FuoriServizio.class)
                .getResultList();
    }
    
}
