package org.acme.schedulers;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class LetScheduler {

    @Inject
    EntityManager em;

    @Scheduled(every = "30s")
    public void provjeriLetove() {
        Object broj = em.createQuery("SELECT count(l) FROM Let l").getSingleResult();

        System.out.println("Trenutni broj aktivnih letova u sistemu: " + broj);
    }
}
