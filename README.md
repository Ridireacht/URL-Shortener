[![Maintainability](https://api.codeclimate.com/v1/badges/1c00d0d13e8a5dec5031/maintainability)](https://codeclimate.com/github/Ridireacht/URL-Shortener/maintainability)

## General Info

A small project with simple functions. URLs get turned into short hashes with Base62 encoding (original URL --> SHA256 --> Base62). These hashes then form short URLs this app can handle to redirect. Short URLs are shown in both text and QR-code forms.

## Installation & usage

Clone this repository, enter a directory and execute ```docker compose up``` console command. This will build and setup all Docker containers necessary for this application to run. After that you can start using it: go to the ```localhost:80``` link in your browser, where the main page is located.

In case any changes were applied to the project, execute ```docker compose build``` to rebuild it and then ```docker compose up``` to run it again.

If there is no need for this app anymore, execute ```docker compose down -v``` to remove its containers and volumes.

## Technologies used

### Programming languages
- Java **17**

### Frameworks
- Spring Boot (version **3.3.2**)
  - Spring Boot Starter Web
  - Spring Boot Starter Test
  - Spring Boot Starter Cache
  - Spring Boot Starter Data JPA
- Bootstrap **5.3**
 
### Additional libraries
- PostgreSQL Driver (main DB)
- H2 Driver (test DB)
- Lombok
- Caffeine
- [QR Code generator](https://github.com/nayuki/QR-Code-generator)

### Misc
- Nginx (frontend's web server)
- Docker Compose (version **3.9**)
