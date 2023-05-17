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
		Partecipazione partecipazione2 = new Partecipazione(null, null, StatoPartecipazione.DA_CONFERMARE);

		Persona aldo = new Persona("Aldo", "Baglio", "Aldo.baglio@gmail.com", LocalDate.of(1980, 5, 13),
				SessoPersona.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)));

		Persona giovanni = new Persona("Giovanni", "Storti", "Giovannino.storti@gmail.com", LocalDate.of(1965, 10, 13),
				SessoPersona.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)));

		Evento festivalBar = new Evento("Festival Bar", LocalDate.of(2023, 8, 16), "bellissimoo", TipoEvento.PUBBLICO,
				1000, new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)), location);

//		ld.save(location);
//		partecipazioneD.save(partecipazione);
//		partecipazioneD.save(partecipazione2);
//		ed.save(festivalBar);
//		personaD.save(aldo);
//		personaD.save(giovanni);
//		partecipazione.setPersona(aldo);
//		partecipazione2.setPersona(giovanni);
//		partecipazione.setEvento(festivalBar);
//		partecipazione2.setEvento(festivalBar);
//		partecipazioneD.update(partecipazione);
//		partecipazioneD.update(partecipazione2);

		Evento trovato = ed.getById(UUID.fromString("e482c896-c52f-4ea3-9b73-aba04e64bb28"));

		if (trovato != null) {

			log.info(trovato.getListaPartecipazioni().toString());
		} else {
			log.info("Evento non trovato!");
		}

		em.close();
		emf.close();
	}

}
