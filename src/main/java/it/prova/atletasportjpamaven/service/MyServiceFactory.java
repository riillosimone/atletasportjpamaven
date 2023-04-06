package it.prova.atletasportjpamaven.service;

import it.prova.atletasportjpamaven.dao.MyDAOFactory;

public class MyServiceFactory {

	// rendo questo factory SINGLETON
	private static AtletaService ATLETA_SERVICE_INSTANCE;
	private static SportService SPORT_SERVICE_INSTANCE;

	public static AtletaService getAtletaServiceInstance() {
		if (ATLETA_SERVICE_INSTANCE == null)
			ATLETA_SERVICE_INSTANCE = new AtletaServiceImpl();

		ATLETA_SERVICE_INSTANCE.setAtletaDAO(MyDAOFactory.getAtletaDAOInstance());
		ATLETA_SERVICE_INSTANCE.setSportDAO(MyDAOFactory.getSportDAOInstance());
		return ATLETA_SERVICE_INSTANCE;
	}

	public static SportService getSportServiceInstance() {
		if (SPORT_SERVICE_INSTANCE == null)
			SPORT_SERVICE_INSTANCE = new SportServiceImpl();

		SPORT_SERVICE_INSTANCE.setAtletaDAO(MyDAOFactory.getAtletaDAOInstance());
		SPORT_SERVICE_INSTANCE.setSportDAO(MyDAOFactory.getSportDAOInstance());
		return SPORT_SERVICE_INSTANCE;
	}
}
