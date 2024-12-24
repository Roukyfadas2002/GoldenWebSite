-- Création de la table des chiens (dogs)
CREATE TABLE dogs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    name VARCHAR(100) NOT NULL, -- Nom du chien
    breed VARCHAR(100) NOT NULL, -- Race
    birth_date DATE NOT NULL, -- Date de naissance
    birth_place VARCHAR(255), -- Lieu de naissance
    description TEXT, -- Description (optionnel)
    owner_id BIGINT, -- Référence à l'utilisateur propriétaire (optionnel)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date de création
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Dernière mise à jour
);

-- Création de la table des utilisateurs (users)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    first_name VARCHAR(100) NOT NULL, -- Prénom
    last_name VARCHAR(100) NOT NULL, -- Nom
    email VARCHAR(255) NOT NULL UNIQUE, -- Adresse email (unique)
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL, -- Rôle (proprietaire, eleveur, admin)
    postal_address VARCHAR(255), -- Adresse postale (optionnel)
    birth_date DATE, -- Date de naissance (optionnel)
    birth_place VARCHAR(255), -- Lieu de naissance (optionnel)
    adopted_dog_id BIGINT, -- Référence à un chien adopté (optionnel)
    comments TEXT, -- Commentaires (optionnel)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date de création
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Dernière mise à jour
    FOREIGN KEY (adopted_dog_id) REFERENCES dogs (id) ON DELETE SET NULL -- Lien avec un chien adopté
);

-- Création de la table des logs
CREATE TABLE logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    user_id BIGINT NOT NULL, -- Référence à l'utilisateur associé
    action VARCHAR(255) NOT NULL, -- Description de l'action (ex: "Connexion", "Ajout d'un chien")
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date de création
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE -- Lien avec un utilisateur
);

-- Création de la table des ventes (sales)
CREATE TABLE sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique
    user_id BIGINT NOT NULL, -- Référence à l'utilisateur (acheteur)
    dog_id BIGINT NOT NULL, -- Référence au chien vendu
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date de vente
    price DECIMAL(10, 2) NOT NULL, -- Prix de la vente
    notes TEXT, -- Notes supplémentaires (optionnel)
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, -- Lien avec l'utilisateur
    FOREIGN KEY (dog_id) REFERENCES dogs (id) ON DELETE CASCADE -- Lien avec le chien vendu
);

CREATE TABLE auth_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT
);

-- Insertion des profils par défaut dans la table users
INSERT INTO users (first_name, last_name, email, password, role, postal_address, birth_date, birth_place, comments)
VALUES
    ('Admin', 'User', 'admin@example.com', 'admin', 'admin', '123 Admin Street', '1980-01-01', 'Paris', 'Super administrateur.'),
    ('Eleveur', 'User', 'eleveur@example.com', 'eleveur', 'eleveur', '456 Farm Lane', '1990-02-02', 'Lyon', 'Éleveur de chiens.'),
    ('Proprietaire', 'User', 'proprietaire@example.com', 'proprietaire', 'proprietaire', '789 Home Road', '2000-03-03', 'Marseille', 'Propriétaire dun Golden Retriever.');
