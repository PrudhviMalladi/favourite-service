package com.optimum.favourite.service;

import java.util.List;

import com.optimum.favourite.model.Favourite;
import com.optimum.favourite.model.Song;
import com.optimum.favourite.util.exception.DuplicateFavouriteIdException;
import com.optimum.favourite.util.exception.FavouriteNotFoundException;

public interface FavouriteService {

	public Favourite addFavourite(Favourite favourite) throws DuplicateFavouriteIdException;
	
	public List<Favourite> getAllFavourites();
	
	public Favourite getFavouriteById(String favouriteId) throws FavouriteNotFoundException;
	
	public Favourite updateFavourite(Favourite favourite) throws FavouriteNotFoundException;
	
	public boolean deleteFavouriteById(String favouriteId) throws FavouriteNotFoundException;
	
	public Favourite addSongToFavourite(String favouriteId, Song song) throws FavouriteNotFoundException;
	
	public Favourite deleteSongFromFavourite(String favouriteId, String songId) throws FavouriteNotFoundException;
}
