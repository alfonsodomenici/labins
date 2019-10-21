/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.CatenaMisuraLink;
import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.json.stream.JsonCollectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class CatenaMisuraStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public CatenaMisura save(CatenaMisura e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        CatenaMisura e = this.find(id);
        e.setNascosto(true);
        save(e);
    }

    public CatenaMisura find(Long id) {
        return em.find(CatenaMisura.class, id);
    }

    public List<CatenaMisura> all(Long idDom) {
        return em.createQuery("select e from CatenaMisura e where e.nascosto=false and e.dominio.id = :idDom order by e.denominazione", CatenaMisura.class)
                .setParameter("idDom", idDom)
                .getResultList();
    }

    public List<CatenaMisuraLink> allLink(Long idDom) {
        return all(idDom).stream().map(CatenaMisuraLink::new).collect(Collectors.toList());
    }

}
