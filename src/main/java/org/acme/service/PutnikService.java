package org.acme.service;

import org.acme.model.Putnik;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PutnikService {

    @Inject
    EntityManager em;

    public List<Putnik> getAllPutnici() {
        return em.createQuery("SELECT p FROM Putnik p", Putnik.class).getResultList();
    }

    @Transactional
    public void createPutnik(Putnik putnik) {
        em.persist(putnik);
    }
}