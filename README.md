## General Info

A small project with simple functions:
1. Turn original URLs to short, hash-based URLs. Uses Base62 encoding (original URL --> SHA256 --> Base62).
2. Redirect user to original URL from hash-based URL.

If the URL was once shortened, get it from DB instead of generating again. URLs and hashes get cached with Caffeine.

## How to deploy

Clone this repository, enter a directory and execute ```docker compose up``` console command there. This will create and setup all Docker containers. Then locate to ```localhost:8080``` webpage, where lies a home page of the project.

In case changes were applied to the project, execute ```docker compose build``` to rebuild it and then ```docker compose up``` to run.

## Technologies used

### Programming languages
- Java **17**

### Frameworks
- Spring Boot (version **3.3.2**)
  - Spring Boot Starter Web
  - Spring Boot Starter Test
  - Spring Boot Starter Cache
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Thymeleaf
- Bootstrap **5**
 
### Additional libraries
- PostgreSQL Driver
- Lombok
- Caffeine

