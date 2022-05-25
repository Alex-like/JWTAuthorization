package com.example.JWTAuthorization.repository;

import com.example.JWTAuthorization.models.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebtRepository extends JpaRepository<Debt, Long> {
}
