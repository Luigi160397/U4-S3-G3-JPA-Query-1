package app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;
import entities.SessoPersona;
import entities.StatoPartecipazione;
import entities.TipoEvento;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j

public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EventoDAO ed = new EventoDAO(em);
		PersonaDAO personaD = new PersonaDAO(em);
		LocationDAO ld = new LocationDAO(em);
		PartecipazioneDAO partecipazioneD = new PartecipazioneDAO(em);

		Location location = new Location("Arena di Verona", "Verona");

		Partecipazione partecipazione = new Partecipazione(null, null, StatoPartecipazione.CONFERMATA);

		Persona aldo = new Persona("Aldo", "Baglio", "Aldo.baglio@gmail.com", LocalDate.of(1980, 5, 13),
				SessoPersona.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione)));

		Evento festivalBar = new Evento("Festival Bar", LocalDate.of(2023, 8, 16), "bellissimoo", TipoEvento.PUBBLICO,
				1000, new HashSet<Partecipazione>(Arrays.asList(partecipazione)), location);

//		ld.save(location);
//		partecipazioneD.save(partecipazione);
//		ed.save(festivalBar);
//		personaD.save(aldo);
//		partecipazione.setPersona(aldo);
//		partecipazione.setEvento(festivalBar);
//		partecipazioneD.update(partecipazione);

		Evento trovato = ed.getById(UUID.fromString("33b853a1-3654-4035-a1c5-c3bccabee918"));

		if (trovato != null) {

			log.info(trovato.getListaPartecipazioni().toString());
		} else {
			log.info("Evento non trovato!");
		}

		em.close();
		emf.close();
	}

}
