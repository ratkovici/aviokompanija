package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Putnik;
import org.acme.model.Karta;
import org.acme.exceptions.PutnikException;
import java.util.List;

@Dependent
public class PutnikService {

    @Inject
    EntityManager em;


    @Transactional
    public Putnik createPutnik(Putnik putnik) throws PutnikException {
        if (putnik == null) {
            throw new PutnikException("Putnik nije proslijedjen");
        }
        if (putnik.getIme() == null || putnik.getIme().isEmpty()) {
            throw new PutnikException("Ime je prazno");
        }
        if (putnik.getPrezime() == null || putnik.getPrezime().isEmpty()) {
            throw new PutnikException("Prezime je prazno");
        }

        if (putnik.getPasos() != null) {
            putnik.getPasos().setPutnik(putnik);
        }

        if (putnik.getDetalji() != null) {
            putnik.getDetalji().setPutnik(putnik);
        }

        if (putnik.getKarte() != null) {
            for (Karta k : putnik.getKarte()) {
                k.setPutnik(putnik);
            }
        }
        return em.merge(putnik);
    }

    @Transactional
    public List<Putnik> getAllPutnici() throws PutnikException {
        List<Putnik> putnici = em.createNamedQuery(Putnik.GET_ALL_PUTNICI, Putnik.class).getResultList();

        if (putnici.isEmpty()) {
            throw new PutnikException("Nema putnika.");
        }
        return putnici;
    }

    public List<Putnik> getPutnikByName(String name) {
        List<Putnik> putnici = em.createNamedQuery(Putnik.GET_PUTNIK_BY_NAME, Putnik.class)
                .setParameter("imeP", name)
                .getResultList();
        return putnici;
    }

    public List<Karta> getKarteByPutnikId(Long id) {
        List<Karta> karte = em.createNamedQuery(Karta.GET_ALL_KARTE_FOR_PUTNIK_ID, Karta.class)
                .setParameter("id", id)
                .getResultList();
        return karte;
    }

}