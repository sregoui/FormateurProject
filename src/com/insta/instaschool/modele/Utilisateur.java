package com.insta.instaschool.modele;

import java.io.Serializable;

public class Utilisateur implements Serializable
{
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private int idrole;
	
	public Utilisateur()
	{
		super();
		this.nom = "";
		this.prenom = "";
		this.email = "";
		this.login = "";
		this.password = "";
		this.idrole = 0;
	}
	
	public Utilisateur(String n, String p, String e, String l, String pass, int r)
	{
		super();
		this.nom = n;
		this.prenom = p;
		this.email = e;
		this.login = l;
		this.password = pass;
		this.idrole = r;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdrole() {
		return idrole;
	}
	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}
}
