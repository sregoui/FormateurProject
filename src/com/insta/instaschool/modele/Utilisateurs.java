package com.insta.instaschool.modele;

import java.io.Serializable;

public class Utilisateurs implements Serializable {
	
	private String email;
	private String password;
	private String nom;
	private String prenom;
	private String login;
	private int id;
	private int id_role;
	
	public Utilisateurs() {
		super();
	}

	public Utilisateurs(String email, String password, String nom,
			String prenom, String login, int id, int id_role) {
		super();
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.id = id;
		this.id_role = id_role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	@Override
	public String toString() {
		return "Utilisateurs [email=" + email + ", password=" + password
				+ ", nom=" + nom + ", prenom=" + prenom + ", login=" + login
				+ ", id=" + id + ", id_role=" + id_role + "]";
	}
	
	

}
