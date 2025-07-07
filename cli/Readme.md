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

## Contributing

Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.



