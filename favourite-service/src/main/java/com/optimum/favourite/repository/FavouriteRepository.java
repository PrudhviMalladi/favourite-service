package com.optimum.favourite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.optimum.favourite.model.Favourite;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite, String>{

}
