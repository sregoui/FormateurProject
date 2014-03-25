package com.insta.instaschool.modele;

import java.io.Serializable;

public class News implements Serializable {
	
	
	private String titre;
	private String sous_titre;
	private String news;
	private String date;
	private int idNews;
	
	
	
	public News() {
		super();
	}



	public News(String titre, String sous_titre, String news, String date, int idNews) {
		super();
		this.titre = titre;
		this.sous_titre = sous_titre;
		this.news = news;
		this.date = date;
		this.idNews = idNews;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getSous_titre() {
		return sous_titre;
	}



	public void setSous_titre(String sous_titre) {
		this.sous_titre = sous_titre;
	}



	public String getNews() {
		return news;
	}



	public void setNews(String news) {
		this.news = news;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public int getIdNews() {
		return idNews;
	}



	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}



	@Override
	public String toString() {
		return "News [titre=" + titre + ", sous_titre=" + sous_titre
				+ ", news=" + news + ", date=" + date + ", idNews=" + idNews
				+ "]";
	}	
	

}
