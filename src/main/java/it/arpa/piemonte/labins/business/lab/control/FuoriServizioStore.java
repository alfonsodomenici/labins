/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.FuoriServizioLink;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author utente
 */
@Stateless
public class FuoriServizioStore extends Store<FuoriServizio>{

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

    private Predicate searchPredicate(
            CriteriaBuilder cb,
            Root<FuoriServizio> root,
            Long idApparecchiatura) {

        Predicate cond = cb.conjunction();
        cond = cb.and(cond, cb.equal(getPathExp(root, "apparecchiatura.id", Object.class), idApparecchiatura));
        return cond;
    }

    public List<FuoriServizio> search(
            Long idApparecchiatura,
            Integer start,
            Integer pageSize) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FuoriServizio> query = cb.createQuery(FuoriServizio.class);
        Root<FuoriServizio> root = query.from(FuoriServizio.class);

        query.select(root)
                .where(searchPredicate(cb, root, idApparecchiatura))
                .orderBy(cb.desc(root.get("inizio")));

        TypedQuery<FuoriServizio> q = em.createQuery(query);
        if (start != null) {
            q.setFirstResult(start);
        }
        if (pageSize != null) {
            q.setMaxResults(pageSize);
        }
        return q.getResultList();
    }

    public List<FuoriServizioLink> searchLink(
            Long idApparecchiatura,
            Integer start,
            Integer pageSize) {
        return search(idApparecchiatura, start, pageSize).stream().map(FuoriServizioLink::new).collect(Collectors.toList());
    }

    public int searchCount(
            Long idApparecchiatura) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<FuoriServizio> root = query.from(FuoriServizio.class);

        query.select(cb.count(root))
                .where(searchPredicate(cb, root, idApparecchiatura));

        return ((Long) em.createQuery(query)
                .getSingleResult()).intValue();
    }
    
}
