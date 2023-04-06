package it.prova.atletasportjpamaven.dao;

import java.util.List;

import it.prova.atletasportjpamaven.model.Sport;

public interface SportDAO  extends IBaseDAO<Sport>{
	
	public Sport findByDescrizione(String descrizione);
	
	public List<Sport> findMistakes();

}
