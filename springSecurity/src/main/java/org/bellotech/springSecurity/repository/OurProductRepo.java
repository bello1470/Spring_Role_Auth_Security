package org.bellotech.springSecurity.repository;

import org.bellotech.springSecurity.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OurProductRepo extends JpaRepository<ProductModel, Integer> {

}
