package com.optimum.favourite.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Song {

	@Id
	private String songId;
	
	private String songName;
	
	private String composer;
	
	private String singer;
	
	public Song() {
		// TODO Auto-generated constructor stub
	}

	public Song(String songId, String songName, String composer, String singer) {
		super();
		this.songId = songId;
		this.songName = songName;
		this.composer = composer;
		this.singer = singer;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Song [songId=" + songId + ", songName=" + songName + ", composer=" + composer + ", singer=" + singer
				+ "]";
	}
	
}
