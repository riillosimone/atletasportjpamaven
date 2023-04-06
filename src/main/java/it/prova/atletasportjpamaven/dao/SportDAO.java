package it.prova.atletasportjpamaven.dao;

import it.prova.atletasportjpamaven.model.Sport;

public interface SportDAO  extends IBaseDAO<Sport>{
	
	public Sport findByDescrizione(String descrizione) throws Exception;

}
