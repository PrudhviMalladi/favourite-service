package com.optimum.favourite.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimum.favourite.model.Favourite;
import com.optimum.favourite.model.Song;
import com.optimum.favourite.repository.FavouriteRepository;
import com.optimum.favourite.util.exception.DuplicateFavouriteIdException;
import com.optimum.favourite.util.exception.FavouriteNotFoundException;

@Service
public class FavouriteServiceImpl implements FavouriteService {
	
	@Autowired
	FavouriteRepository favouriteRepository;

	@Override
	public Favourite addFavourite(Favourite favourite) throws DuplicateFavouriteIdException {
		Optional<Favourite> favouriteOpt = favouriteRepository.findById(favourite.getFavouriteId());
		if(favouriteOpt.isEmpty()) {
			return favouriteRepository.save(favourite);
		}
		throw new DuplicateFavouriteIdException();
	}

	@Override
	public List<Favourite> getAllFavourites() {
		return favouriteRepository.findAll();
	}

	@Override
	public Favourite getFavouriteById(String favouriteId) throws FavouriteNotFoundException {
		Optional<Favourite> favouriteOpt = favouriteRepository.findById(favouriteId);
		if(favouriteOpt.isPresent()) {
			return favouriteOpt.get();
		}
		throw new FavouriteNotFoundException();
	}

	@Override
	public Favourite updateFavourite(Favourite favourite) throws FavouriteNotFoundException {
		Optional<Favourite> favouriteOpt = favouriteRepository.findById(favourite.getFavouriteId());
		if(favouriteOpt.isPresent()) {
			return favouriteRepository.save(favourite);
		}
		throw new FavouriteNotFoundException();
	}

	@Override
	public boolean deleteFavouriteById(String favouriteId) throws FavouriteNotFoundException {
		Optional<Favourite> favouriteOpt = favouriteRepository.findById(favouriteId);
		if(favouriteOpt.isPresent()) {
			favouriteRepository.delete(favouriteOpt.get());
			return true;
		}
		throw new FavouriteNotFoundException();
	}

	@Override
	public Favourite addSongToFavourite(String favouriteId, Song song) throws FavouriteNotFoundException {
		Optional<Favourite> favouriteOpt = favouriteRepository.findById(favouriteId);
		if(favouriteOpt.isPresent()) {
			Favourite favourite = favouriteOpt.get();
			favourite.getSongs().add(song);
			return favouriteRepository.save(favourite);
		}
		throw new FavouriteNotFoundException();
	}

	@Override
	public Favourite deleteSongFromFavourite(String favouriteId, String songId) throws FavouriteNotFoundException {
		Optional<Favourite> favouriteOpt = favouriteRepository.findById(favouriteId);
		if(favouriteOpt.isPresent()) {
			Favourite favourite = favouriteOpt.get();
			favourite.setSongs(favourite.getSongs().stream().filter(exsSong -> !exsSong.getSongId().equals(songId)).collect(Collectors.toList()));
			return favouriteRepository.save(favourite);
		}
		throw new FavouriteNotFoundException();
	}

}