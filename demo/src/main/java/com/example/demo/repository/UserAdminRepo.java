package com.example.demo.repository;

import com.example.demo.entity.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface UserAdminRepo extends JpaRepository<UserAdmin, Integer> {

    Optional<UserAdmin> findByEmail(String email);

}
