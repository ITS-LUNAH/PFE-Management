# PFE Manager 📚📝

> Application web full-stack de gestion des projets de fin d'études — suivi des stages, étudiants, entreprises, encadrants et génération automatique de rapports PDF.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-6DB33F?style=flat&logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-17-DD0031?style=flat&logo=angular&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=flat&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white)

---
## Auteur

| | |
|---|---|
| **Établissement** | Université Cadi Ayyad / Faculté des Sciences Semlalia |
| **Département** | Informatique / Ingénierie des Systèmes d'Information |
| **Filière** | Master Ingénierie des Systèmes d'Information S2 |
| **Module** | Programmation JEE & Spring Boot |
| **Réalisé par** | KHALIL Basma |
| **Encadré par** | Pr. Zahi JARIR |
| **Année universitaire** | 2025 – 2026 |


## Sommaire

- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Architecture](#architecture)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Lancement](#lancement)
- [Routes API](#routes-api)
- [Comptes de test](#comptes-de-test)

---

## Aperçu

**PFE Manager** centralise la gestion complète du cycle de vie d'un stage de fin d'études :

- Création et suivi des stages avec formulaire multi-étapes
- Gestion des étudiants, filières, entreprises et encadrants
- Tableau de bord analytique avec graphiques en temps réel
- Génération automatique de rapports de stage en PDF

---

## Fonctionnalités

| Module | Description |
|---|---|
| **Dashboard** | Statistiques globales, graphiques Chart.js (statuts, top entreprises, répartition filières) |
| **Stages** | CRUD complet, formulaire 3 étapes, assignation encadrants, export PDF |
| **Étudiants** | CRUD avec recherche en temps réel et filtre par filière |
| **Entreprises** | Répertoire des entreprises d'accueil |
| **Encadrants** | Gestion des encadrants académiques et professionnels |
| **Filières** | Référentiel des filières avec responsable associé |
| **Auth** | Connexion JWT, rôles ADMIN / ENSEIGNANT / ETUDIANT |

---

## Architecture

```
pfe-management/
├── pfe-backend/          # Spring Boot 3 (Java 17)
│   └── src/main/java/com/pfe/backend/
│       ├── config/       # SecurityConfig, JwtAuthFilter, CorsConfig
│       ├── controller/   # REST Controllers
│       ├── service/      # Business logic + PdfService
│       ├── repository/   # Spring Data JPA
│       ├── model/        # Entités JPA
│       └── dto/          # Request / Response DTOs
│
└── pfe-frontend/         # Angular 17 (standalone components)
    └── src/app/
        ├── components/   # dashboard, stages, etudiants, entreprises...
        ├── services/     # HTTP services
        ├── models/       # Interfaces TypeScript
        ├── guards/       # AuthGuard
        └── interceptors/ # AuthInterceptor (JWT)
```

---

## Prérequis

| Outil | Version minimale |
|---|---|
| Java | 17 |
| Maven | 3.8+ |
| Node.js | 18+ |
| Angular CLI | 17+ |
| MySQL | 8.0+ |

---

## Installation

### 1. Cloner le projet

```bash
git clone https://github.com/votre-username/pfe-management.git
cd pfe-management
```

### 2. Créer la base de données

```sql
CREATE DATABASE pfe_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Installer les dépendances backend

```bash
cd pfe-backend
mvn install
```

### 4. Installer les dépendances frontend

```bash
cd pfe-frontend
npm install
```

---

## Configuration

Modifier `pfe-backend/src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pfe_db
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Données de test au premier démarrage
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

---

## Lancement

### Backend

```bash
cd pfe-backend
mvn spring-boot:run
```

Le serveur démarre sur **http://localhost:8080**

### Frontend

```bash
cd pfe-frontend
ng serve
```

L'application est accessible sur **http://localhost:4200**

---

## Routes API

### Authentification
| Méthode | Route | Description |
|---|---|---|
| POST | `/api/auth/register` | Créer un compte |
| POST | `/api/auth/login` | Connexion → retourne le token JWT |

### Stages
| Méthode | Route | Description |
|---|---|---|
| GET | `/api/stages` | Liste tous les stages |
| GET | `/api/stages/{id}` | Détail d'un stage |
| GET | `/api/stages/filiere/{id}` | Stages par filière |
| GET | `/api/stages/annee/{year}` | Stages par année |
| POST | `/api/stages` | Créer un stage |
| PUT | `/api/stages/{id}` | Modifier un stage |
| DELETE | `/api/stages/{id}` | Supprimer un stage |
| POST | `/api/stages/{id}/assign-academic/{eid}` | Assigner encadrant académique |
| POST | `/api/stages/{id}/assign-professional/{eid}` | Assigner encadrant pro |
| GET | `/api/stages/{id}/report` | Télécharger le rapport PDF |

### Autres modules
| Méthode | Route | Description |
|---|---|---|
| GET/POST/PUT/DELETE | `/api/etudiants` | CRUD étudiants |
| GET/POST/PUT/DELETE | `/api/entreprises` | CRUD entreprises |
| GET/POST/PUT/DELETE | `/api/filieres` | CRUD filières |
| GET/POST/PUT/DELETE | `/api/encadrants-academiques` | CRUD encadrants académiques |
| GET/POST/PUT/DELETE | `/api/encadrants-pro` | CRUD encadrants professionnels |
| GET | `/api/dashboard` | Statistiques globales |

> Toutes les routes (sauf `/api/auth/**`) nécessitent le header :
> `Authorization: Bearer <token>`

---

## Comptes de test

| Identifiant | Mot de passe | Rôle |
|---|---|---|
| `admin` | `password123` | ADMIN |
| `enseignant1` | `password123` | ENSEIGNANT |
| `etudiant1` | `password123` | ETUDIANT |

---

## Pages de l'application

| Route Angular | Page |
|---|---|
| `/login` | Page de connexion |
| `/dashboard` | Tableau de bord |
| `/stages` | Liste des stages |
| `/stages/new` | Créer un stage |
| `/stages/:id` | Détail d'un stage |
| `/stages/:id/edit` | Modifier un stage |
| `/etudiants` | Gestion des étudiants |
| `/entreprises` | Gestion des entreprises |
| `/filieres` | Gestion des filières |
| `/encadrants` | Gestion des encadrants |
