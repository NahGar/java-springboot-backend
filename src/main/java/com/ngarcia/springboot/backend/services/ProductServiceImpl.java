package com.ngarcia.springboot.backend.services;

import com.ngarcia.springboot.backend.entities.Product;
import com.ngarcia.springboot.backend.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

      private final ProductRepository repository;

      public ProductServiceImpl(ProductRepository repository) {
         this.repository = repository;
      }

      @Transactional(readOnly = true)
      @Override
      public List<Product> findAll() {
         return (List<Product>) repository.findAll();
      }

      @Override
      @Transactional(readOnly = true)
      public Optional<Product> findById(Long id) {
         return repository.findById(id);
      }

      @Override
      @Transactional
      public Product save(Product product) {
         return repository.save(product);
      }

      @Override
      @Transactional
      public Optional<Product> deleteById(Long id) {
         Optional<Product> opt = repository.findById(id);
         if(opt.isPresent()) {
            repository.deleteById(id);
            return opt;
         }
         return Optional.empty();
      }
}
