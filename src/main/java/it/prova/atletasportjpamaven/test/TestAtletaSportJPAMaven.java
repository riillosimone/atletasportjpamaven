package it.prova.atletasportjpamaven.test;

import java.time.LocalDate;
import java.util.List;

import it.prova.atletasportjpamaven.dao.EntityManagerUtil;
import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;
import it.prova.atletasportjpamaven.service.AtletaService;
import it.prova.atletasportjpamaven.service.MyServiceFactory;
import it.prova.atletasportjpamaven.service.SportService;

public class TestAtletaSportJPAMaven {

	public static void main(String[] args) {
		AtletaService atletaServiceInstance = MyServiceFactory.getAtletaServiceInstance();
		SportService sportServiceInstance = MyServiceFactory.getSportServiceInstance();
		try {

			// inizializzo sport su db
//			initSport(sportServiceInstance);

//			System.out.println("Gli elementi nella tabella sport sono: "+sportServiceInstance.listAll().size());

//			System.out.println("Gli elementi nella tabella atleta sono: "+atletaServiceInstance.listAll().size());

			// TEST InserisciNuovoAtleta
//			testInserisciNuovoAtleta(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: "+atletaServiceInstance.listAll().size());

			// TEST collegaSportEAtletaEsistenti
//			testCollegaAtletaASportEsistente(atletaServiceInstance, sportServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: "+atletaServiceInstance.listAll().size());
//			System.out.println("Gli elementi nella tabella sport sono: "+sportServiceInstance.listAll().size());

			// TEST deleteAtleta
//			testDeleteAtleta(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());

			// TEST updateAtleta
//			testUpdateAtleta(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());

			// TEST deleteSport
//			testDeleteSport(sportServiceInstance);
//			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());

			// TEST updateSport
//			testUpdateSport(sportServiceInstance);
//			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());

			// TEST trovaErrori
//			testTrovaErrori(sportServiceInstance);
//			System.out.println("Gli elementi nella tabella sport sono: " + sportServiceInstance.listAll().size());
			
			// TEST sommaMedaglieInSportChiusi
//			testSommaMedaglieInSportChiusi(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: " + atletaServiceInstance.listAll().size());
			
			// TEST scollegaSportEAtletaEsistentiERimuoviAtleta
			testScollegaAtletaASportEsistenteERimuoviAtleta(atletaServiceInstance, sportServiceInstance);
			System.out.println("Gli elementi nella tabella atleta sono: "+atletaServiceInstance.listAll().size());
			System.out.println("Gli elementi nella tabella sport sono: "+sportServiceInstance.listAll().size());
			

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// chiudo tutte le connessioni e rilascioil main
			EntityManagerUtil.shutdown();
		}
	}

	private static void initSport(SportService sportServiceInstance) throws Exception {
		if (sportServiceInstance.cercaPerDescrizione("Calcio") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Calcio"));
		if (sportServiceInstance.cercaPerDescrizione("Basket") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Basket"));
		if (sportServiceInstance.cercaPerDescrizione("Nuoto") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Nuoto"));
		if (sportServiceInstance.cercaPerDescrizione("Pallavolo") == null)
			sportServiceInstance.inserisciNuovo(new Sport("Pallavolo"));
	}

	private static void testInserisciNuovoAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testInserisciNuovoAtleta INIZIO-----");
		Atleta nuovoAtleta = new Atleta("Simone", "Riillo", LocalDate.of(1995, 5, 19), "001", 5);
		atletaServiceInstance.inserisciNuovo(nuovoAtleta);
		if (nuovoAtleta.getId() == null) {
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: atleta non inserito.");
		}
		System.out.println("-----TEST testInserisciNuovoAtleta FINE-----");
	}

	private static void testCollegaAtletaASportEsistente(AtletaService atletaServiceInstance,
			SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testCollegaAtletaASportEsistente INIZIO-----");

		Sport sportEsistenteSuDB = sportServiceInstance.cercaPerDescrizione("Calcio");
		if (sportEsistenteSuDB == null) {
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: sport inesistente");
		}

		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		if (listaAtleti.size() < 1) {
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: Non ci sono atleti nel DB");
		}
		Atleta atletaEsistente = listaAtleti.get(0);
		atletaServiceInstance.aggiungiSport(atletaEsistente, sportEsistenteSuDB);
		Atleta altetaReloaded = atletaServiceInstance.caricaSingoloAtleta(atletaEsistente.getId());
		if (altetaReloaded.getSports().size() != 1)
			throw new RuntimeException("testInserisciNuovoAtleta FALLITO: sport non collegato");
		System.out.println("-----TEST testCollegaAtletaASportEsistente FINE-----");

	}

	private static void testDeleteAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testDeleteAtleta INIZIO-----");
		List<Atleta> listaAtletiPrimaDellaRimozione = atletaServiceInstance.listAll();
		if (listaAtletiPrimaDellaRimozione.size() < 1)
			throw new RuntimeException("testDeleteAtleta FALLITO: non sono presenti atleti in lista.");

		// inserisco un atleta per poi eliminarlo
		Atleta nuovoAtleta = new Atleta("Simone", "Riillo", LocalDate.of(1995, 5, 19), "001", 5);
		atletaServiceInstance.inserisciNuovo(nuovoAtleta);
		if (nuovoAtleta.getId() == null) {
			throw new RuntimeException("testDeleteAtleta FALLITO: atleta non inserito.");
		}
		atletaServiceInstance.rimuovi(nuovoAtleta.getId());
		List<Atleta> listaAtletiDopoDellaRimozione = atletaServiceInstance.listAll();
		if (listaAtletiPrimaDellaRimozione.size() != listaAtletiDopoDellaRimozione.size())
			throw new RuntimeException("testDeleteAtleta FALLITO: atleta non rimosso.");
		System.out.println("-----TEST testDeleteAtleta FINE-----");

	}

	private static void testUpdateAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testUpdateAtleta INIZIO-----");
		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		if (listaAtleti.size() < 1)
			throw new RuntimeException("testDeleteAtleta FALLITO: non sono presenti atleti in lista.");
		Atleta atletaDaAggiornare = listaAtleti.get(0);
		String nuovoNome = "Luca";
		atletaDaAggiornare.setNome(nuovoNome);
		atletaServiceInstance.aggiorna(atletaDaAggiornare);
		System.out.println(atletaDaAggiornare);
		System.out.println("-----TEST testUpdateAtleta FINE-----");
	}

	private static void testDeleteSport(SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testDeleteSport INIZIO-----");
		List<Sport> listaSportPrimaDellaRimozione = sportServiceInstance.listAll();
		if (listaSportPrimaDellaRimozione.size() < 1)
			throw new RuntimeException("testDeleteSport FALLITO: non sono presenti sport in lista.");

		// inserisco un atleta per poi eliminarlo
		Sport nuovoSport = new Sport("Corsa");
		sportServiceInstance.inserisciNuovo(nuovoSport);
		if (nuovoSport.getId() == null) {
			throw new RuntimeException("testDeleteSport FALLITO: atleta non inserito.");
		}
		sportServiceInstance.rimuovi(nuovoSport.getId());
		List<Sport> listaSportDopoDellaRimozione = sportServiceInstance.listAll();
		if (listaSportPrimaDellaRimozione.size() != listaSportDopoDellaRimozione.size())
			throw new RuntimeException("testDeleteSport FALLITO: sport non rimosso.");
		System.out.println("-----TEST testDeleteSport FINE-----");

	}

	private static void testUpdateSport(SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testUpdateAtleta INIZIO-----");
		List<Sport> listaSport = sportServiceInstance.listAll();
		if (listaSport.size() < 1)
			throw new RuntimeException("testUpdateSport FALLITO: non sono presenti sport in lista.");
		Sport sportDaAggiornare = listaSport.get(3);
		String nuovaDescrizione = "Corsa";
		sportDaAggiornare.setDescrizione(nuovaDescrizione);
		sportServiceInstance.aggiorna(sportDaAggiornare);
		System.out.println(sportDaAggiornare);
		System.out.println("-----TEST testUpdateAtleta FINE-----");
	}

	private static void testTrovaErrori(SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testTrovaErrori INIZIO-----");
		List<Sport> listaSport = sportServiceInstance.listAll();
		if (listaSport.size() < 1)
			throw new RuntimeException("testTrovaErrori FALLITO: non sono presenti sport in lista.");
		Sport sportConErrore = new Sport("Palestra", LocalDate.now(), LocalDate.of(2000, 12, 1));
		sportServiceInstance.inserisciNuovo(sportConErrore);
		if (sportConErrore.getId() == null) {
			throw new RuntimeException("testTrovaErrori FALLITO: sportConErrore non inserito");
		}
		List<Sport> listaErrori = sportServiceInstance.cercaErrori();
		if (listaErrori.size() < 1) {
			throw new RuntimeException("testTrovaErrori FALLITO: non sono presenti errori");
		}
		System.out.println(listaErrori);
		sportServiceInstance.rimuovi(sportConErrore.getId());
		List<Sport> listaSportDopoRimozione = sportServiceInstance.listAll();
		if (listaSport.size() != listaSportDopoRimozione.size())
			throw new RuntimeException("testTrovaErrori FALLITO: sport non rimosso.");
		System.out.println("-----TEST testTrovaErrori FINE-----");

	}
	
	private static void testSommaMedaglieInSportChiusi (AtletaService atletaServiceInstance) throws Exception {
		System.out.println("-----TEST testSommaMedaglieInSportChiusi INIZIO-----");
		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		if (listaAtleti.size() < 1)
			throw new RuntimeException("testSommaMedaglieInSportChiusi FALLITO: non sono presenti atleti in lista.");
		Long sommaMedaglie = atletaServiceInstance.sommaMedaglieVinteInSportChiusi();
		System.out.println("La somma delle medaglie vinte in sport chiusi è: "+sommaMedaglie);
		System.out.println("-----TEST testSommaMedaglieInSportChiusi FINE-----");
	}
	
	private static void testScollegaAtletaASportEsistenteERimuoviAtleta(AtletaService atletaServiceInstance,
			SportService sportServiceInstance) throws Exception {
		System.out.println("-----TEST testScollegaAtletaASportEsistenteERimuoviAtleta INIZIO-----");

		Sport sportEsistenteSuDB = sportServiceInstance.cercaPerDescrizione("Calcio");
		if (sportEsistenteSuDB == null) {
			throw new RuntimeException("testScollegaAtletaASportEsistenteERimuoviAtleta FALLITO: sport inesistente");
		}
		String descrizioneSportDaScollegare = sportEsistenteSuDB.getDescrizione();
		List<Atleta> listaAtleti = atletaServiceInstance.listaAtletiAppartenentiAUnoSport(descrizioneSportDaScollegare);
		if (listaAtleti.size() < 1) {
			throw new RuntimeException("testScollegaAtletaASportEsistenteERimuoviAtleta FALLITO: Non ci sono atleti che fanno sport con questa descrizione");
		}
		Atleta atletaEsistente = listaAtleti.get(0);
		atletaServiceInstance.rimuoviSport(atletaEsistente, sportEsistenteSuDB);
		Atleta altetaReloaded = atletaServiceInstance.caricaSingoloAtleta(atletaEsistente.getId());
		if (altetaReloaded.getSports().size() != 0)
			throw new RuntimeException("testScollegaAtletaASportEsistenteERimuoviAtleta FALLITO: sport non scollegato");
		atletaServiceInstance.rimuovi(atletaEsistente.getId());
		System.out.println("-----TEST testScollegaAtletaASportEsistenteERimuoviAtleta FINE-----");

	}
}
