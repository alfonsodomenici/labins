/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.Abilitazione;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alfonso
 */
@Stateless
public class AbilitazioneStore extends Store<Abilitazione> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Optional<Abilitazione> find(Long id) {
        Abilitazione found = em.find(Abilitazione.class, id);
        return found != null ? Optional.of(found) : Optional.empty();
    }

    public Abilitazione save(Abilitazione abilitazione) {
        return em.merge(abilitazione);
    }

    public void remove(Long id) {
        find(id).ifPresent(v -> em.remove(v));
    }

    public List<Abilitazione> findByUsr(String usr) {
        return em.createNamedQuery(Abilitazione.FIND_BY_USR, Abilitazione.class)
                .setParameter("utente", usr)
                .getResultList();
    }

    public List<Abilitazione> findByUsrAndLivello(String usr, Abilitazione.Livello livello) {
        return findByUsr(usr).stream().filter(v -> v.getLivello() == livello).collect(Collectors.toList());
    }

    public Optional<Abilitazione> findByUsrAndLaboratorio(String usr, Long labId) {
        return findByUsr(usr).stream().filter(v -> v.getLaboratorio().getId().equals(labId)).findFirst();
    }

    public List<Long> findLaboratoriByUsrAndLivello(String usr, Abilitazione.Livello livello) {
        return findByUsrAndLivello(usr, livello).stream().map(v -> v.getLaboratorio().getId()).collect(Collectors.toList());
    }

}
