package com.insta.instaschool.modele;

import java.io.Serializable;

public class Formateurs implements Serializable {
	
	
	private String id_formateur;
	private String nom;
	private String prenom;
	private String date_naissance;
	private String specialite;
	private String photo;

	public Formateurs() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Formateurs(String id_formateur, String nom, String prenom,
			String date_naissance, String specialite, String photo) {
		super();
		this.id_formateur = id_formateur;
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.specialite = specialite;
		this.photo = photo;
	}

	public String getId_formateur() {
		return id_formateur;
	}

	public void setId_formateur(String id_formateur) {
		this.id_formateur = id_formateur;
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

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Formateurs [id_formateur=" + id_formateur + ", nom=" + nom
				+ ", prenom=" + prenom + ", date_naissance=" + date_naissance
				+ ", specialite=" + specialite + ", photo=" + photo + "]";
	}
	
	

}
