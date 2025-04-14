package com.example.KomponentIntegrationTestExempel.repository;

import com.example.KomponentIntegrationTestExempel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
