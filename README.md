# COGIP CLI

COGIP CLI is a command-line interface application for managing companies, contacts, and invoices. It is built with Java and provides an interactive way to perform CRUD operations on your business data.

## Features

- List, add, update, and delete companies
- List, add, update, and delete contacts
- List, add, update, and delete invoices
- Interactive command-line menus
- Integration with a backend REST API

## Requirements

- Java 21 or higher
- Maven 3.6 or higher

## Setup

1. Clone the repository:
   ```sh
   git clone <your-repository-url>
   cd cli
   ```

2. Build the project:
   ```sh
   mvn clean install
   ```

3. Configure the application:
   - Edit `src/main/resources/application.properties` to set your backend API URL and other settings.

## Usage

Run the CLI application with:

```sh
mvn spring-boot:run
```

You will be presented with a menu to manage companies, contacts, and invoices.

## Commands

- `company` - Manage companies (list, add, update, delete)
- `contact` - Manage contacts (list, add, update, delete)
- `invoice` - Manage invoices (list, add, update, delete)

Follow the on-screen instructions to navigate through the menus and perform actions.


# COGIP API

## Description

This project is a REST API developed in Java for managing a fictitious company, COGIP. It allows you to manage companies, contacts, and invoices. The API exposes several routes to perform CRUD (Create, Read, Update, Delete) operations on these entities.

## Project Structure

```
api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cogip/
│   │   │           ├── controllers/   # REST controllers (CompanyController, ContactController, InvoiceController)
│   │   │           ├── models/        # JPA entities (Company, Contact, Invoice)
│   │   │           ├── repositories/  # Data access interfaces (CompanyRepository, etc.)
│   │   │           ├── services/      # Business logic (CompanyService, etc.)
│   │   │           └── CogipApiApplication.java # Spring Boot entry point
│   │   └── resources/
│   │       ├── application.properties # Application configuration (DB, port, etc.)
│   │       └── ...                    # Other resources (data.sql, etc.)
│   └── test/                          # Unit and integration tests
│
├── README.md                          # This file
├── pom.xml                            # Maven dependencies and project configuration
└── ...
```

- **controllers/**: Handles HTTP requests and API routes.
- **models/**: Defines entities persisted in the database.
- **repositories/**: Provides methods to interact with the database.
- **services/**: Contains business logic and specific processing.
- **resources/**: Configuration files and static resources.

## Main Features

- Company management
- Contact management
- Invoice management

## API Routes

### Companies

- `GET /companies`  
  Retrieves the list of all companies.

- `GET /companies/{id}`  
  Retrieves information about a company by its ID.

- `POST /companies`  
  Creates a new company.

- `PUT /companies/{id}`  
  Updates an existing company.

- `DELETE /companies/{id}`  
  Deletes a company.

### Contacts

- `GET /contacts`  
  Retrieves the list of all contacts.

- `GET /contacts/{id}`  
  Retrieves information about a contact by its ID.

- `POST /contacts`  
  Creates a new contact.

- `PUT /contacts/{id}`  
  Updates an existing contact.

- `DELETE /contacts/{id}`  
  Deletes a contact.

### Invoices

- `GET /invoices`  
  Retrieves the list of all invoices.

- `GET /invoices/{id}`  
  Retrieves information about an invoice by its ID.

- `POST /invoices`  
  Creates a new invoice.

- `PUT /invoices/{id}`  
  Updates an existing invoice.

- `DELETE /invoices/{id}`  
  Deletes an invoice.

---

## API Documentation

> **Note:** All `id` fields are UUIDs and are automatically generated by the system.

### Companies

#### GET /companies
- **Description:** Lists all companies.
- **Response:**
  ```json
  [
    {
      "id": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f",
      "name": "Company name",
      "address": "Address",
      "vat": "VAT number"
    },
    ...
  ]
  ```

#### GET /companies/{id}
- **Description:** Details of a company.
- **Parameters:**
  - `id` (path, UUID): company identifier
- **Response:**
  ```json
  {
    "id": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f",
    "name": "Company name",
    "address": "Address",
    "vat": "VAT number"
  }
  ```

#### POST /companies
- **Description:** Creates a company.
- **Request body:**
  ```json
  {
    "name": "Company name",
    "address": "Address",
    "vat": "VAT number"
  }
  ```
- **Response:** Created company (JSON object, with generated UUID)

#### PUT /companies/{id}
- **Description:** Updates a company.
- **Parameters:**
  - `id` (path, UUID)
- **Request body:** Same as POST
- **Response:** Updated company (JSON object)

#### DELETE /companies/{id}
- **Description:** Deletes a company.
- **Parameters:**
  - `id` (path, UUID)
- **Response:**
  ```json
  { "message": "Company deleted" }
  ```

---

### Contacts

#### GET /contacts
- **Description:** Lists all contacts.
- **Response:**
  ```json
  [
    {
      "id": "c1a2b3c4-d5e6-4f3a-9b2e-7b8a1c2e7b8a",
      "firstname": "First name",
      "lastname": "Last name",
      "email": "email@example.com",
      "companyId": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f"
    },
    ...
  ]
  ```

#### GET /contacts/{id}
- **Description:** Details of a contact.
- **Parameters:**
  - `id` (path, UUID)
- **Response:**
  ```json
  {
    "id": "c1a2b3c4-d5e6-4f3a-9b2e-7b8a1c2e7b8a",
    "firstname": "First name",
    "lastname": "Last name",
    "email": "email@example.com",
    "companyId": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f"
  }
  ```

#### POST /contacts
- **Description:** Creates a contact.
- **Request body:**
  ```json
  {
    "firstname": "First name",
    "lastname": "Last name",
    "email": "email@example.com",
    "companyId": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f"
  }
  ```
- **Response:** Created contact (JSON object, with generated UUID)

#### PUT /contacts/{id}
- **Description:** Updates a contact.
- **Parameters:**
  - `id` (path, UUID)
- **Request body:** Same as POST
- **Response:** Updated contact (JSON object)

#### DELETE /contacts/{id}
- **Description:** Deletes a contact.
- **Parameters:**
  - `id` (path, UUID)
- **Response:**
  ```json
  { "message": "Contact deleted" }
  ```

---

### Invoices

#### GET /invoices
- **Description:** Lists all invoices.
- **Response:**
  ```json
  [
    {
      "id": "f3a4c9e9-b2e1-a2b3-c4d5-e6f7b8a1c2e7",
      "number": "INV-001",
      "date": "2024-01-01",
      "companyId": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f",
      "amount": 1000.0
    },
    ...
  ]
  ```

#### GET /invoices/{id}
- **Description:** Details of an invoice.
- **Parameters:**
  - `id` (path, UUID)
- **Response:**
  ```json
  {
    "id": "f3a4c9e9-b2e1-a2b3-c4d5-e6f7b8a1c2e7",
    "number": "INV-001",
    "date": "2024-01-01",
    "companyId": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f",
    "amount": 1000.0
  }
  ```

#### POST /invoices
- **Description:** Creates an invoice.
- **Request body:**
  ```json
  {
    "number": "INV-001",
    "date": "2024-01-01",
    "companyId": "e7b8a1c2-4f3a-4c9e-9b2e-1a2b3c4d5e6f",
    "amount": 1000.0
  }
  ```
- **Response:** Created invoice (JSON object, with generated UUID)

#### PUT /invoices/{id}
- **Description:** Updates an invoice.
- **Parameters:**
  - `id` (path, UUID)
- **Request body:** Same as POST
- **Response:** Updated invoice (JSON object)

#### DELETE /invoices/{id}
- **Description:** Deletes an invoice.
- **Parameters:**
  - `id` (path, UUID)
- **Response:**
  ```json
  { "message": "Invoice deleted" }
  ```

---

## Quick Start

1. Clone the repository
2. Install dependencies with Maven:  
   `mvn install`
3. Start the application:  
   `mvn spring-boot:run`

## Author

Project by [Manu-cj].


## Contributing

Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.



