package com.app.fullstackbackend.repository;

import com.app.fullstackbackend.model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserClass, Long>{
}
