package com.insta.instaschool.modele;

import java.io.Serializable;

public class Promo implements Serializable {
	
	private int id;
	private String promo;
	private String annee_debut;
	private String annee_fin;
	
	
	
	public Promo() {
		super();
	}



	public Promo(int id, String promo, String annee_debut, String annee_fin) {
		super();
		this.id = id;
		this.promo = promo;
		this.annee_debut = annee_debut;
		this.annee_fin = annee_fin;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPromo() {
		return promo;
	}



	public void setPromo(String promo) {
		this.promo = promo;
	}



	public String getAnnee_debut() {
		return annee_debut;
	}



	public void setAnnee_debut(String annee_debut) {
		this.annee_debut = annee_debut;
	}



	public String getAnnee_fin() {
		return annee_fin;
	}



	public void setAnnee_fin(String annee_fin) {
		this.annee_fin = annee_fin;
	}



	@Override
	public String toString() {
		return "Promo [id=" + id + ", promo=" + promo + ", annee_debut="
				+ annee_debut + ", annee_fin=" + annee_fin + "]";
	}
	
	

}
