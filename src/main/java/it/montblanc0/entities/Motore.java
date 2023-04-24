package it.montblanc0.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "motore", indexes = {
		@Index(name = "xd_Auto_Motore___Cilindrata_Cavalli", columnList = "Cilindrata, Cavalli", unique = true)
})
public class Motore implements Serializable {
	@Serial
	private static final long serialVersionUID = -1665110655078324610L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MOTORE", columnDefinition = "INT UNSIGNED not null")
	private Long id;

	@Column(name = "Descrizione", length = 10)
	private String descrizione;

	@Column(name = "Cilindrata")
	private Integer cilindrata;

	@Column(name = "Cavalli")
	private Integer cavalli;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(Integer cilindrata) {
		this.cilindrata = cilindrata;
	}

	public Integer getCavalli() {
		return cavalli;
	}

	public void setCavalli(Integer cavalli) {
		this.cavalli = cavalli;
	}

}