/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.AziendaLink;
import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import it.arpa.piemonte.labins.business.lab.entity.Azienda;
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
public class AziendaStore extends Store {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Azienda save(Azienda e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        Azienda e = this.find(id);
        e.setNascosto(true);
        save(e);
    }

    public Azienda find(Long id) {
        return em.find(Azienda.class, id);
    }

    private Predicate searchPredicate(
            CriteriaBuilder cb,
            Root<Azienda> root,
            String tipo,
            String denominazione) {

        Predicate cond = cb.conjunction();
        cond = cb.and(cond, cb.equal(root.get("nascosto"), false));
        if (tipo != null && !tipo.isEmpty()) {
            switch (tipo) {
                case "costruttore":
                    cond = cb.and(cond, cb.equal(root.get("costruttore"), true));
                    break;
                case "taratore":
                    cond = cb.and(cond, cb.equal(root.get("taratore"), true));
                    break;
                case "manutentore":
                    cond = cb.and(cond, cb.equal(root.get("manutentore"), true));
                    break;
                case "distributore":
                    cond = cb.and(cond, cb.equal(root.get("distributore"), true));
                    break;
                default:
                    cond = cb.and(cond, cb.equal(root.get("id"), -1));
            }
        }
        if(denominazione!=null && !denominazione.isEmpty()){
            cond = cb.and(cond, cb.like(root.get("denominazione"), denominazione + "%"));
        }
        return cond;
    }

    public List<Azienda> search(
            String tipo,
            String denominazione,
            Integer start,
            Integer pageSize) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Azienda> query = cb.createQuery(Azienda.class);
        Root<Azienda> root = query.from(Azienda.class);

        query.select(root)
                .where(searchPredicate(cb, root, tipo,denominazione))
                .orderBy(cb.asc(root.get("denominazione")));

        TypedQuery<Azienda> q = em.createQuery(query);
        if (start != null) {
            q.setFirstResult(start);
        }
        if (pageSize != null) {
            q.setMaxResults(pageSize);
        }
        return q.getResultList();
    }

    public List<AziendaLink> searchLink(
            String tipo,
            String denominazione,
            Integer start,
            Integer pageSize) {
        return search(tipo,denominazione, start, pageSize).stream().map(AziendaLink::new).collect(Collectors.toList());
    }

    public int searchCount(
            String tipo,
            String denominazione) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<Azienda> root = query.from(Azienda.class);

        query.select(cb.count(root))
                .where(searchPredicate(cb, root, tipo,denominazione));

        return ((Long) em.createQuery(query)
                .getSingleResult()).intValue();
    }
}
