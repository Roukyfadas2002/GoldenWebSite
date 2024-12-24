package com.golden.dev.controller;

import com.golden.dev.dto.LoginRequest;
import com.golden.dev.dto.RegisterRequest;
import com.golden.dev.model.AuthLog;
import com.golden.dev.model.User;
import com.golden.dev.repository.AuthLogRepository;
import com.golden.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthLogRepository authLogRepository;

    /**
     * Authentification d'un utilisateur.
     *
     * @param loginRequest les informations d'identification (email et mot de passe)
     * @return une réponse avec un message ou les détails de l'utilisateur en cas de succès
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Log de la requête
        System.out.println("=== Authentification Reçue ===");
        System.out.println("Email : " + email);
        System.out.println("Mot de passe reçu : " + password);

        // Recherche de l'utilisateur par email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("[ERREUR] Utilisateur non trouvé pour l'email : " + email);
            authLogRepository.save(new AuthLog(email, "USER_NOT_FOUND", "Utilisateur non trouvé"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Utilisateur non trouvé."));
        }

        // Vérification du mot de passe
        System.out.println("Mot de passe enregistré dans la base : " + user.getPassword());
        if (!password.equals(user.getPassword())) {
            System.out.println("[ERREUR] Mot de passe incorrect pour l'utilisateur : " + email);
            authLogRepository.save(new AuthLog(email, "FAILURE", "Mot de passe incorrect"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Mot de passe incorrect."));
        }

        // Connexion réussie
        System.out.println("[SUCCÈS] Connexion réussie pour l'utilisateur : " + email);
        System.out.println("Rôle de l'utilisateur : " + user.getRole());
        authLogRepository.save(new AuthLog(email, "SUCCESS", "Connexion réussie"));

        // Réponse
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Connexion réussie !");
        response.put("user", Map.of(
                "email", user.getEmail(),
                "role", user.getRole(),
                "firstName", user.getFirstName(),
                "lastName", user.getLastName()
        ));
        return ResponseEntity.ok(response);
    }

    /**
     * Inscription d'un nouvel utilisateur.
     *
     * @param registerRequest les détails de l'utilisateur à inscrire
     * @return une réponse avec un message de succès ou d'erreur
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();

        // Log de l'inscription
        System.out.println("=== Inscription Reçue ===");
        System.out.println("Email : " + email);
        System.out.println("Prénom : " + registerRequest.getFirstName());
        System.out.println("Nom : " + registerRequest.getLastName());
        System.out.println("Rôle : " + registerRequest.getRole());

        // Vérifie si l'email est déjà utilisé
        if (userRepository.findByEmail(email) != null) {
            System.out.println("[ERREUR] Email déjà utilisé : " + email);
            authLogRepository.save(new AuthLog(email, "REGISTER_FAILURE", "Cet email est déjà utilisé"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Cet email est déjà utilisé."));
        }

        // Création d'un nouvel utilisateur
        User newUser = new User();
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setEmail(email);
        newUser.setRole(registerRequest.getRole());
        newUser.setPassword(registerRequest.getPassword()); // Pas d'encodage

        userRepository.save(newUser);
        System.out.println("[SUCCÈS] Nouvel utilisateur enregistré avec succès : " + email);
        authLogRepository.save(new AuthLog(email, "REGISTER_SUCCESS", "Utilisateur enregistré avec succès"));

        return ResponseEntity.ok(Map.of("message", "Utilisateur enregistré avec succès !"));
    }

    @GetMapping("/logs")
    public ResponseEntity<?> getAllLogs() {
    return ResponseEntity.ok(authLogRepository.findAll());
}

}
