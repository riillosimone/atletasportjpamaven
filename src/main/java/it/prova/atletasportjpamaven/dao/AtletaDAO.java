package it.prova.atletasportjpamaven.dao;

import java.util.List;

import it.prova.atletasportjpamaven.model.Atleta;

public interface AtletaDAO extends IBaseDAO<Atleta>{
	public Atleta findByIdFetchingSports (Long id);
	
	public Long sumNumeroMedaglieVinteInSportChiusi();

	public List<Atleta> findByDescrizioneSport(String descrizione);
}
