package com.optimum.favourite.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Favourite with specified detail is not found")
public class FavouriteNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
}
