package com.golden.dev.controller;

import com.golden.dev.model.AuthLog;
import com.golden.dev.repository.AuthLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class AuthLogController {

    @Autowired
    private AuthLogRepository authLogRepository;

    /**
     * Récupère tous les logs d'authentification.
     * Accessible uniquement aux rôles admin et éleveur.
     *
     * @return Liste des logs.
     */
    @GetMapping
    public ResponseEntity<List<AuthLog>> getAllLogs() {
        List<AuthLog> logs = authLogRepository.findAll();
        return ResponseEntity.ok(logs);
    }
}
