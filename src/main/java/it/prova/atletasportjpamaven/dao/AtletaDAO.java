package it.prova.atletasportjpamaven.dao;

import it.prova.atletasportjpamaven.model.Atleta;

public interface AtletaDAO extends IBaseDAO<Atleta>{
	public Atleta findByIdFetchingSports (Long id);
	
	public Long sumNumeroMedaglieVinteInSportChiusi();

}
