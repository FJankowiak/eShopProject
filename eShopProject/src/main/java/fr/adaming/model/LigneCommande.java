package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="lignesCommande")
public class LigneCommande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ligneCom")
	private Long id_ligne;
	private int quantite;
	private double prix;
	
	
	// Transformer l'association UML en java
	@ManyToOne
	@JoinColumn(name = "p_id", referencedColumnName = "id_p")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name = "co_id", referencedColumnName = "id_co")
	private Commande commande;
	
	@Transient
	private Panier panier;
	

	// Declaration des constructeurs
	public LigneCommande() {
		super();
		
	}
	
	public LigneCommande(int quantite) {
		super();
		this.quantite = quantite;
	}

	public LigneCommande(Long id_ligne, int quantite) {
		super();
		this.id_ligne = id_ligne;
		this.quantite = quantite;
	}
	
	public LigneCommande(int quantite, double prix, Produit produit) {
		
		// pour l'administrateur seulement
		super();
		this.quantite = quantite;
		this.prix = prix;
		this.produit = produit;
	}

	// Declaration des getters et setters
	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Long getId_ligne() {
		return id_ligne;
	}

	public void setId_ligne(Long id_ligne) {
		this.id_ligne = id_ligne;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

}
