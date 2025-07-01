package sergiomaselli.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sergiomaselli.entities.Evento;
import sergiomaselli.exceptions.NotFoundException;

public class EventoDAO {

    private EntityManager entityManager;

    public EventoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Evento newEvent) {

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(newEvent);

        transaction.commit();

        System.out.println("L'evento:" + newEvent.getTitolo() + "del giorno" + newEvent.getDataEvento() + "è stato aggiunto con successo");

    }

    public Evento findById(long eventId) {
        Evento found = entityManager.find(Evento.class, eventId);
        if (found == null) throw new NotFoundException(eventId);
        return found;
    }

    public void findIdAndDelete(long eventId) {

        Evento found = this.findById(eventId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("L'evento:" + found.getTitolo() + "del giorno" + found.getDataEvento() + "è stato rimosso con successo");


    }
}
