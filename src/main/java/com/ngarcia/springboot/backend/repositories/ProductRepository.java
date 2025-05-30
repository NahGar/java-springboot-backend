package com.ngarcia.springboot.backend.repositories;

import com.ngarcia.springboot.backend.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
