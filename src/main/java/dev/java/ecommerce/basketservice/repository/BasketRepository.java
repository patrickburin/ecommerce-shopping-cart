package dev.java.ecommerce.basketservice.repository;

import dev.java.ecommerce.basketservice.entity.Status;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.java.ecommerce.basketservice.entity.Basket;

public interface BasketRepository extends MongoRepository<Basket, String> {

  Optional<Basket> findByClientAndStatus(Long client, Status status);

}
