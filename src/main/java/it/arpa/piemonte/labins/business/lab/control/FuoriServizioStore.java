/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.FuoriServizioLink;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
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
public class FuoriServizioStore extends Store<FuoriServizio> {

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
            Long idApparecchiatura,
            Boolean storico,
            Boolean fs,
            Boolean vi) {

        Predicate cond = cb.conjunction();
        cond = cb.and(cond, cb.equal(getPathExp(root, "apparecchiatura.id", Object.class), idApparecchiatura));
        if (storico != null && storico) {
            cond = cb.and(cond, cb.isNotNull(root.get("inizio")), cb.isNotNull(root.get("fine")));
        }
        if (fs != null && fs) {
            cond = cb.and(cond, cb.isNotNull(root.get("inizio")), cb.isNull(root.get("fine")));
        }
        if (vi != null && vi) {
            cond = cb.and(cond, cb.isNotNull(root.get("inizio")), cb.isNotNull(root.get("fine")), cb.equal(root.get("necessariaVerifica"), true));
        }
        return cond;
    }

    public List<FuoriServizio> search(
            Long idApparecchiatura,
            Boolean storico,
            Boolean fs,
            Boolean vi,
            Boolean last,
            Integer start,
            Integer pageSize) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FuoriServizio> query = cb.createQuery(FuoriServizio.class);
        Root<FuoriServizio> root = query.from(FuoriServizio.class);

        query.select(root)
                .where(searchPredicate(cb, root, idApparecchiatura, storico, fs, vi))
                .orderBy(cb.asc(root.get("inizio")));

        TypedQuery<FuoriServizio> q = em.createQuery(query);
        if (start != null) {
            q.setFirstResult(start);
        }
        if (pageSize != null) {
            q.setMaxResults(pageSize);
        }

        List<FuoriServizio> result = q.getResultList();

        if (last != null && last) {
            return result.isEmpty() ? result : result.stream().skip(result.size() - 1).collect(Collectors.toList());
        }

        return result;
    }

    public List<FuoriServizioLink> searchLink(
            Long idApparecchiatura,
            Boolean storico,
            Boolean fs,
            Boolean vi,
            Boolean last,
            Integer start,
            Integer pageSize) {
        return search(idApparecchiatura, storico, fs, vi, last, start, pageSize).stream().map(FuoriServizioLink::new).collect(Collectors.toList());
    }

    public int searchCount(
            Long idApparecchiatura,
            Boolean storico,
            Boolean fs,
            Boolean vi,
            Boolean last) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<FuoriServizio> root = query.from(FuoriServizio.class);

        query.select(cb.count(root))
                .where(searchPredicate(cb, root, idApparecchiatura, storico, fs, vi));

        int result = ((Long) em.createQuery(query)
                .getSingleResult()).intValue();

        if (last != null && last) {
            return result == 0 ? 0 : 1;
        }

        return result;
    }

    public JsonObject status(Long idApparecchiatura) {
        Boolean isFuoriServizio = !this.search(idApparecchiatura, null, Boolean.TRUE, null, null, null, null).isEmpty();
        Boolean isViRequired = this.searchCount(idApparecchiatura, Boolean.TRUE, null, Boolean.TRUE, Boolean.TRUE) == 1;
        Boolean isViMissing = this.isViMissing(idApparecchiatura);
        Boolean isStoricoEmpty = this.searchCount(idApparecchiatura, Boolean.TRUE, null, null, null) == 0;
        return Json.createObjectBuilder()
                .add("isFuoriServizio", isFuoriServizio)
                .add("isViRequired", isViRequired)
                .add("isViMissing", isViMissing)
                .add("isStoricoEmpty", isStoricoEmpty)
                .build();
    }

    public boolean isViMissing(Long idApparecchiatura) {
        List<FuoriServizio> lastViRequired = this.search(idApparecchiatura, Boolean.TRUE, null, Boolean.TRUE, Boolean.TRUE, null, null);
        System.out.println(lastViRequired);
        if (lastViRequired.isEmpty()) {
            return false;
        }

        List<FuoriServizio> resultList = em.createNamedQuery(FuoriServizio.FIND_BY_PARENT, FuoriServizio.class)
                .setParameter("idParent", lastViRequired.get(0).getId())
                .setParameter("idApparecchiatura", idApparecchiatura)
                .getResultList();

        return resultList.isEmpty();
    }
}
