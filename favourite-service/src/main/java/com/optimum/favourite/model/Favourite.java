package com.optimum.favourite.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Favourite {

	@Id
	private String favouriteId;
	
	private List<Song> songs;
	
	public Favourite() {
		// TODO Auto-generated constructor stub
	}

	public Favourite(String favouriteId, List<Song> songs) {
		super();
		this.favouriteId = favouriteId;
		this.songs = songs;
	}

	public String getFavouriteId() {
		return favouriteId;
	}

	public void setFavouriteId(String favouriteId) {
		this.favouriteId = favouriteId;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Favourite [favouriteId=" + favouriteId + ", songs=" + songs + "]";
	}
	
}
