package org.bellotech.springSecurity.repository;

import org.bellotech.springSecurity.model.OurUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUserModel, Integer>{
    @Query(value = "select * from ourusers where email = ?1 ", nativeQuery = true )
    Optional<OurUserModel> findByEmail(String email);
}
