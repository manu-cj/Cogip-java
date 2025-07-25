# Cogip-java

📦 **Dépôt 1 — API REST (Backend)**

## 🧩 Fonctionnalités par catégorie

### 🔐 Authentification & Rôles

- Authentification utilisateur (login par token ou session)
- Chargement du rôle (admin, user) pour la gestion des droits
- Middleware de vérification de rôle (admin a tous les droits)

### 🧑‍💼 Utilisateurs

- Ajouter un utilisateur
- Modifier un utilisateur
- Supprimer un utilisateur
- Lister tous les utilisateurs
- Obtenir un utilisateur spécifique

### 🏢 Entreprises

- Ajouter une entreprise
- Modifier une entreprise
- Supprimer une entreprise
- Lister toutes les entreprises (avec pagination)
- Filtrer entreprises par type (client ou fournisseur)
- Obtenir une entreprise spécifique (avec contacts + factures liées)

### 📇 Contacts

- Ajouter un contact
- Modifier un contact
- Supprimer un contact
- Lister tous les contacts (avec pagination)
- Obtenir un contact spécifique (avec l'entreprise + factures liées)

### 🧾 Factures

- Ajouter une facture
- Modifier une facture
- Supprimer une facture
- Lister toutes les factures (avec pagination)
- Obtenir une facture spécifique (avec société, type, contact lié)

---

## 🌐 Routes API (REST) — `/api`

### 🔐 Authentification

| Méthode | Route   | Paramètres         | Rôle requis |
|---------|---------|--------------------|-------------|
| POST    | /login  | email, password    | Aucun       |

### 🧑‍💼 Utilisateurs (`/users`)

| Méthode | Route         | Paramètres body/query/path | Rôle requis |
|---------|--------------|---------------------------|-------------|
| GET     | /users       | ?page, ?limit             | admin       |
| GET     | /users/:id   |                           | admin       |
| POST    | /users       | name, email, role, ...    | admin       |
| PUT     | /users/:id   | Champs modifiables        | admin       |
| DELETE  | /users/:id   |                           | admin       |

### 🏢 Entreprises (`/companies`)

| Méthode | Route           | Paramètres                | Rôle requis |
|---------|-----------------|--------------------------|-------------|
| GET     | /companies      | ?page, ?limit, ?type     | TOUS        |
| GET     | /companies/:id  |                          | TOUS        |
| POST    | /companies      | name, vat, type, etc.    | admin       |
| PUT     | /companies/:id  | Champs modifiables       | admin       |
| DELETE  | /companies/:id  |                          | admin       |

> **Note :** La réponse de `GET /companies` et `GET /companies/:id` doit contenir :
> - Nom
> - N° TVA
> - Liste des factures liées
> - Liste des contacts liés

### 📇 Contacts (`/contacts`)

| Méthode | Route           | Paramètres         | Rôle requis |
|---------|-----------------|--------------------|-------------|
| GET     | /contacts       | ?page, ?limit      | TOUS        |
| GET     | /contacts/:id   |                    | TOUS        |
| POST    | /contacts       | firstName, lastName, email, companyId | admin |
| PUT     | /contacts/:id   | Champs modifiables | admin       |
| DELETE  | /contacts/:id   |                    | admin       |

> **Note :** `GET /contacts/:id` doit retourner :
> - Prénom / Nom
> - Email
> - Entreprise
> - Liste des factures liées à ce contact

### 🧾 Factures (`/invoices`)

| Méthode | Route           | Paramètres                    | Rôle requis |
|---------|-----------------|-------------------------------|-------------|
| GET     | /invoices       | ?page, ?limit                 | TOUS        |
| GET     | /invoices/:id   |                               | TOUS        |
| POST    | /invoices       | number, date, companyId, contactId | admin  |
| PUT     | /invoices/:id   | Champs modifiables            | admin       |
| DELETE  | /invoices/:id   |                               | admin       |

> **Note :** `GET /invoices/:id` doit retourner :
> - Numéro, date
> - Société liée + type (client/fournisseur)
> - Contact lié

---

## 💻 Dépôt 2 — Interface CLI

### 🎯 Fonctionnalités CLI (non interactive)

- `login --user=<email> --password=<password>` : Simule un login et stocke un token ou rôle en local

#### Entreprises

- `companies list [--type=fournisseur|client] [--pretty]`
- `companies add --name= --vat= --type=`
- `companies update --id= --name= --vat=`
- `companies delete --id=`
- `companies show --id= [--pretty]`

#### Contacts

- `contacts list [--pretty]`
- `contacts add --firstname= --lastname= --email= --company=`
- `contacts update --id= ...`
- `contacts delete --id=`
- `contacts show --id= [--pretty]`

#### Factures

- `invoices list [--pretty]`
- `invoices add --number= --date= --company= --contact=`
- `invoices update --id= ...`
- `invoices delete --id=`
- `invoices show --id= [--pretty]`

#### Utilisateurs

- `users list`
- `users add --name= --email= --role=`
- `users update --id=`
- `users delete --id=`
- `users show --id=`
