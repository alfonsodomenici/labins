/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.Deroga;
import it.arpa.piemonte.labins.business.lab.entity.Grandezza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class DerogaStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Deroga save(Deroga e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        em.remove(find(id));
    }

    public Deroga find(Long id) {
        return em.find(Deroga.class, id);
    }

    public List<Deroga> findByApparecchiatura(Long idApparecchiatura){
        return em.createQuery("select e from Deroga e where e.apparecchiatura.id= :idApparecchiatura order by e.derogaScadenza", Deroga.class)
                .setParameter("idApparecchiatura", idApparecchiatura)
                .getResultList();
    }
    
}
