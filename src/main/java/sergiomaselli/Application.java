package sergiomaselli;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sergiomaselli.dao.EventoDAO;
import sergiomaselli.entities.EventType;
import sergiomaselli.entities.Evento;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EventoDAO eventoDAO = new EventoDAO(em); // nome coerente

        try {
            // 1. Creazione evento
            Evento festa = new Evento(
                    "Beverdì",
                    LocalDate.of(2025, 7, 10),
                    "Bevi responsabilmente",
                    EventType.PUBBLICO,
                    50
            );

            Evento evento2 = new Evento(
                    "discoteca",
                    LocalDate.of(2025, 6, 20),
                    "Divertiti",
                    EventType.PRIVATO,
                    40
            );

            eventoDAO.save(festa);
            eventoDAO.save(evento2);


            // 2. Trova per ID
            Evento trovato = eventoDAO.findById(festa.getId());
            System.out.println("Evento trovato: " + trovato);

            // 3. Elimina evento
            eventoDAO.findIdAndDelete(festa.getId());

        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
