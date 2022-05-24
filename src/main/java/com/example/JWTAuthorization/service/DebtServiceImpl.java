package com.example.JWTAuthorization.service;

import com.example.JWTAuthorization.models.Debt;
import com.example.JWTAuthorization.repository.DebtRepository;
import org.springframework.stereotype.Service;

@Service
public class DebtServiceImpl {
    private final DebtRepository debtRepository;

    public DebtServiceImpl(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    public Debt findById(Long id) {
        return id == null ? null : debtRepository.findById(id).orElse(null);
    }
}
