package it.montblanc0.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NamedQueries({
//		@NamedQuery(name = "Auto.findByMarcaLike", query = "SELECT a from Auto a WHERE a.marca LIKE :marca"),
		@NamedQuery(name = "Auto.findByCilindrataBetween", query = "SELECT a FROM Auto a WHERE a.pMotore.cilindrata BETWEEN :start AND :end ORDER BY a.marca, a.pMotore.cilindrata DESC"),
//		@NamedQuery(name = "", query = ""),

})
@Table(name = "auto", indexes = {
		@Index(name = "p_Motore", columnList = "p_Motore")
})
public class Auto implements Serializable {

	@Serial
	private static final long serialVersionUID = 4806808458393248343L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AUTO", columnDefinition = "INT UNSIGNED not null")
	private Long id;

	@Column(name = "Marca", length = 45)
	private String marca;

	@Column(name = "Modello", length = 45)
	private String modello;

	@Column(name = "Prezzo")
	private Integer prezzo;

	@Column(name = "Colore", length = 45)
	private String colore;

	@Column(name = "Alimentazione")
	@Enumerated(EnumType.STRING)
	private Alimentazione alimentazione;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "p_Motore", nullable = false)
	private Motore pMotore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public Alimentazione getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(Alimentazione alimentazione) {
		this.alimentazione = alimentazione;
	}

	@JsonProperty(value = "motore")
	public Motore getPMotore() {
		return pMotore;
	}

	public void setPMotore(Motore pMotore) {
		this.pMotore = pMotore;
	}

}