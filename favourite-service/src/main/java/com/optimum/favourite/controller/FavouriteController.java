package com.optimum.favourite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimum.favourite.model.Favourite;
import com.optimum.favourite.model.Song;
import com.optimum.favourite.service.FavouriteService;
import com.optimum.favourite.util.exception.DuplicateFavouriteIdException;
import com.optimum.favourite.util.exception.FavouriteNotFoundException;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
	
	@Autowired
	FavouriteService favouriteService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Favourite>> getAllFavourites() {
		return new ResponseEntity<List<Favourite>>(favouriteService.getAllFavourites(), HttpStatus.OK);
	}
	
	@GetMapping("/{favouriteId}")
	public ResponseEntity<Object> getFavouriteById(@PathVariable("favouriteId") String favouriteId) {
		Favourite favourite = null;
		try {
			favourite = favouriteService.getFavouriteById(favouriteId);
		} catch (FavouriteNotFoundException e) {
		}
		return new ResponseEntity<Object>(favourite, ObjectUtils.isEmpty(favourite) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> addFavourite(@RequestBody Favourite favourite) {
		Favourite savedFavourite = null;
		try {
			savedFavourite = favouriteService.addFavourite(favourite);
		} catch (DuplicateFavouriteIdException e) {
		}
		return new ResponseEntity<Object>(savedFavourite, ObjectUtils.isEmpty(savedFavourite) ? HttpStatus.CONFLICT : HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Object> updateFavourite(@RequestBody Favourite favourite) {
		Favourite updatedFavourite = null;
		try {
			updatedFavourite = favouriteService.updateFavourite(favourite);
		} catch (FavouriteNotFoundException e) {
		}
		return new ResponseEntity<Object>(updatedFavourite, ObjectUtils.isEmpty(updatedFavourite) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@DeleteMapping("/{favouriteId}")
	public ResponseEntity<Object> deleteFavouriteById(@PathVariable("favouriteId") String favouriteId) {
		boolean isFavouriteRemoved = false;
		try {
			isFavouriteRemoved = favouriteService.deleteFavouriteById(favouriteId);
		} catch (FavouriteNotFoundException e) {
		}
		return new ResponseEntity<Object>(!isFavouriteRemoved ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PutMapping("/{favouriteId}/addSong")
	public ResponseEntity<Object> addSongToFavourite(@PathVariable("favouriteId") String favouriteId, @RequestBody Song song) {
		Favourite updatedFavourite = null;
		try {
			updatedFavourite = favouriteService.addSongToFavourite(favouriteId, song);
		} catch (FavouriteNotFoundException e) {
		}
		return new ResponseEntity<Object>(updatedFavourite, ObjectUtils.isEmpty(updatedFavourite) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@DeleteMapping("/{favouriteId}/deleteSong/{songId}")
	public ResponseEntity<Object> deleteSongById(@PathVariable("favouriteId") String favouriteId, @PathVariable("songId") String songId ) {
		Favourite updatedFavourite = null;
		try {
			updatedFavourite = favouriteService.deleteSongFromFavourite(favouriteId, songId);
		} catch (FavouriteNotFoundException e) {
		}
		return new ResponseEntity<Object>(updatedFavourite, ObjectUtils.isEmpty(updatedFavourite) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
}
