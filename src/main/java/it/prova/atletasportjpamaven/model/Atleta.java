package it.prova.atletasportjpamaven.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "atleta")
public class Atleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "datadinascita")
	private LocalDate dataDiNascita;
	@Column(name = "codice")
	private String codice;
	@Column(name = "numeromedaglievinte")
	private int numeroMedaglieVinte;
	@ManyToMany
	@JoinTable(name = "atleta_sport", joinColumns = @JoinColumn(name = "atleta_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "ID"))
	private Set<Sport> sports = new HashSet<>(0);

	//costruttori
	public Atleta() {
		super();
	}

	public Atleta(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}

	public Atleta(String nome, String cognome, LocalDate dataDiNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}

	public Atleta(String nome, String cognome, LocalDate dataDiNascita, String codice) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codice = codice;
	}

	public Atleta(String nome, String cognome, LocalDate dataDiNascita, String codice, int numeroMedaglieVinte) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codice = codice;
		this.numeroMedaglieVinte = numeroMedaglieVinte;
	}

	
	//get e set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public int getNumeroMedaglieVinte() {
		return numeroMedaglieVinte;
	}

	public void setNumeroMedaglieVinte(int numeroMedaglieVinte) {
		this.numeroMedaglieVinte = numeroMedaglieVinte;
	}

	public Set<Sport> getSports() {
		return sports;
	}

	public void setSports(Set<Sport> sports) {
		this.sports = sports;
	}

	@Override
	public String toString() {
		String dataDiNascitaString = dataDiNascita != null ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataDiNascita)
				: " N.D.";
		
		return "Atleta [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascitaString
				+ ", codice=" + codice + ", numeroMedaglieVinte=" + numeroMedaglieVinte + "]";
	}

	
}
