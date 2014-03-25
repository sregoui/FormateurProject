package com.insta.instaschool.modele;

import java.io.Serializable;

public class Eleves implements Serializable {
	
	private int idEleve;
	private String nom;
	private String prenom;
	private String date_naissance;
	private String photo;
	private int id_promo;
	

	
	
	public Eleves() {
		super();
	}


	public Eleves(int idEleve, String nom, String prenom, String date_naissance,
			String photo, int id_promo) {
		super();
		this.idEleve = idEleve;
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.photo = photo;
		this.id_promo = id_promo;
	}

	public int getIdEleve() {
		return idEleve;
	}


	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getDate_naissance() {
		return date_naissance;
	}


	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public int getId_promo() {
		return id_promo;
	}


	public void setId_promo(int id_promo) {
		this.id_promo = id_promo;
	}


	@Override
	public String toString() {
		return "Eleves [idEleve=" + idEleve + ", nom=" + nom + ", prenom="
				+ prenom + ", date_naissance=" + date_naissance + ", photo="
				+ photo + ", id_promo=" + id_promo + "]";
	}
	

}
