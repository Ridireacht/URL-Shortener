## General Info

A small project with simple functions: 
1. Turn original URLs to short, hash-based URLs. This is done via Base62 encoding (original URL --> SHA256 --> Base62).
2. Redirect user to original URL with hash-based URL.

If the URL was once shortened, the application retrieves it from DB instead of generating again. URL-hash pairs also get temporarily cached with Caffeine, so we check cache as well.

## Installation & usage

Clone this repository, enter a directory and execute ```docker compose up``` console command. This will build and setup all Docker containers necessary for this application to run. After that you can start using it: go to the ```localhost:8080``` link in your browser, where the main page is located.

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
  - Spring Boot Starter Thymeleaf
- Bootstrap **5.3**
 
### Additional libraries
- PostgreSQL Driver
- Lombok
- Caffeine

