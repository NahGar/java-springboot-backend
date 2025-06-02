package com.ngarcia.springboot.backend.controllers;

import com.ngarcia.springboot.backend.entities.Product;
import com.ngarcia.springboot.backend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

   final private ProductService service;

   public ProductController(ProductService service) {
      this.service = service;
   }

   @GetMapping
   public ResponseEntity<List<Product>> list() {
      return  ResponseEntity.ok(service.findAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Product> details(@PathVariable Long id) {
      Optional<Product> opt = service.findById(id);
      if(opt.isPresent()) {
         return ResponseEntity.ok(opt.orElseThrow());
      }
      return ResponseEntity.notFound().build();
   }

   @PostMapping
   public ResponseEntity<Product> create(@RequestBody Product product) {
      return ResponseEntity.status(HttpStatus.CREATED).
              body(service.save(product));
   }

   @PutMapping("/{id}")
   public ResponseEntity<Product> update(@RequestBody Product product,
                                         @PathVariable Long id) {
      Optional<Product> opt = service.findById(id);
      if(opt.isPresent()) {
         //Product productDB = opt.orElseThrow();
         //productDB.setName(product.getName());
         //productDB.setDescription(product.getDescription());
         //productDB.setPrice(product.getPrice());

         //return ResponseEntity.status(HttpStatus.CREATED).
         //        body(service.save(productDB));
         product.setId(id);
         return ResponseEntity.status(HttpStatus.CREATED).
                 body(service.save(product));
      }
      return ResponseEntity.notFound().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Product> delete(@PathVariable Long id) {
      Optional<Product> opt = service.deleteById(id);
      if(opt.isPresent()) {
         Product optDelete =  opt.orElseThrow();
         return ResponseEntity.ok().body(optDelete);
      }
      return ResponseEntity.notFound().build();
   }

}
