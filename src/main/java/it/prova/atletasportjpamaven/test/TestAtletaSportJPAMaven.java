package it.prova.atletasportjpamaven.test;

import java.time.LocalDate;

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

			// test InserisciNuovoAtleta
//			testInserisciNuovoAtleta(atletaServiceInstance);
//			System.out.println("Gli elementi nella tabella atleta sono: "+atletaServiceInstance.listAll().size());

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

}
