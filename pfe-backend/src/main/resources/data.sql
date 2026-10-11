--*************************************************************************
-- Script SQL pour initialiser la base de données avec des données de test
--*************************************************************************

-- 1. Filières
INSERT IGNORE INTO filiere (id, intitule) VALUES
(1, 'Ingénierie des Systèmes d''Information'),
(2, 'Intelligence Artificielle et Data Science'),
(3, 'Réseaux et Systèmes');

-- 2. Responsables de filière
INSERT IGNORE INTO responsable_filiere (id, nom, email, filiere_id) VALUES
(1, 'Mohammed Alami', 'm.alami@ecole.ma', 1),
(2, 'Fatima Benali', 'f.benali@ecole.ma', 2),
(3, 'Youssef Kadiri', 'y.kadiri@ecole.ma', 3);

-- 3. Encadrants académiques
INSERT IGNORE INTO encadrant_academique (id, nom, departement, etablissement) VALUES
(1, 'Prof. Hassan Tazi', 'Ingénierie des Systèmes d''Information', 'FSSM'),
(2, 'Prof. Sara Amrani', 'Intelligence Artificielle et Data Science', 'FSSM'),
(3, 'Prof. Karim Lahlou', 'Réseaux et Systèmes', 'FSSM');

-- 4. Entreprises
INSERT IGNORE INTO entreprise (id, nom, adresse, tel, email, ville, pays, responsable_email) VALUES
(1, 'OCP Group', 'Avenue Hassan II', '0522-001122', 'contact@ocp.ma', 'Casablanca', 'Maroc', 'rh@ocp.ma'),
(2, 'Maroc Telecom', 'Avenue Annakhil, Hay Riad', '0537-001122', 'contact@iam.ma', 'Rabat', 'Maroc', 'rh@iam.ma'),
(3, 'Capgemini Maroc', 'Technopark Casablanca', '0522-334455', 'maroc@capgemini.com', 'Casablanca', 'Maroc', 'rh@capgemini.ma');

-- 5. Encadrants professionnels
INSERT IGNORE INTO encadrant_pro (id, email, tel, entreprise_id) VALUES
(1, 'enc.ocp@ocp.ma', '0661-111111', 1),
(2, 'enc.iam@iam.ma', '0662-222222', 2),
(3, 'enc.cap@capgemini.ma', '0663-333333', 3);

-- 6. Étudiants
INSERT IGNORE INTO etudiant (id, nom, prenom, email, cne, tel, filiere_id) VALUES
(1, 'Bennani', 'Amine', 'amine.bennani@etud.ma', 'G123456789', '0611-000001', 1),
(2, 'Chaoui', 'Nadia', 'nadia.chaoui@etud.ma', 'G987654321', '0611-000002', 1),
(3, 'Idrissi', 'Hamza', 'hamza.idrissi@etud.ma', 'G111222333', '0611-000003', 2);

-- 7. Stages
INSERT IGNORE INTO stage (id, sujet, date_debut, date_fin, description, objectifs, solution, demarche, outils, etudiant_id, entreprise_id, encadrant_academique_id, encadrant_pro_id) VALUES
(1, 'Développement d''une application de gestion RH',
 '2024-02-01', '2024-07-31',
 'Création d''une application web complète pour la gestion des ressources humaines.',
 'Automatiser les processus RH, améliorer le suivi des employés.',
 'Application Spring Boot + Angular avec authentification JWT.',
 'Analyse des besoins, conception UML, développement itératif, tests.',
 'Spring Boot, Angular, MySQL, Docker',
 1, 1, 1, 1),
(2, 'Mise en place d''une solution de monitoring réseau',
 '2024-03-01', '2024-08-31',
 'Déploiement d''une plateforme de surveillance du réseau en temps réel.',
 'Détecter les pannes proactivement, réduire les temps d''arrêt.',
 'Solution basée sur Grafana + Prometheus + Alertmanager.',
 'Étude de l''existant, choix technologique, déploiement, documentation.',
 'Grafana, Prometheus, Linux, Docker',
 2, 2, 3, 2),
(3, 'Développement d''un chatbot intelligent pour le support client',
 '2024-04-01', '2024-09-30',
 'Création d''un chatbot basé sur l''IA pour automatiser le support client.',
 'Réduire la charge du support humain, améliorer l''expérience client.',
 'Chatbot avec NLP (Python + FastAPI) intégré dans un portail web.',
 'Recherche bibliographique, prototype, entraînement du modèle, intégration.',
 'Python, FastAPI, Angular, PostgreSQL, HuggingFace',
 3, 3, 2, 3);

-- 8. Utilisateurs
INSERT IGNORE INTO users (id, username, password, role) VALUES
(1, 'admin',      '$2a$10$ioHaDr1nb0SleMx9k0M4lu0dRcNvHIOeFLgILfuWInq1j94rqcWRK', 'ADMIN'),
(2, 'enseignant1','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'ENSEIGNANT'),
(3, 'etudiant1',  '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'ETUDIANT');
