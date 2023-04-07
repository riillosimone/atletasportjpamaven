package it.prova.atletasportjpamaven.service;

import java.util.List;

import it.prova.atletasportjpamaven.dao.AtletaDAO;
import it.prova.atletasportjpamaven.dao.SportDAO;
import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;

public interface AtletaService {

	public List<Atleta> listAll() throws Exception;

	public Atleta caricaSingoloAtleta(Long id) throws Exception;

	public void aggiorna(Atleta atletaInstance) throws Exception;

	public void inserisciNuovo(Atleta atletaInstance) throws Exception;

	public void rimuovi(Long idAtleta) throws Exception;

	public void aggiungiSport(Atleta atletaEsistente, Sport sportInstance) throws Exception;
	
	public void rimuoviSport(Atleta atletaEsistente, Sport sportInstance) throws Exception;
	
	public Long sommaMedaglieVinteInSportChiusi () throws Exception;
	
	public List<Atleta> listaAtletiAppartenentiAUnoSport (String descrizione) throws Exception;

	// per injection
	public void setAtletaDAO(AtletaDAO atletaDAO);

	public void setSportDAO(SportDAO sportDAO);

}
