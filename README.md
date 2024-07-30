## General Info

A small project with 2 simple functions:
- Turn original URLs to short, hash-based URLs. Uses Base62 encoding (original URL --> SHA256 --> Base62).
- Redirect a user to original URL via hash-based URL.

If the URL was once shortened, get it from DB instead of generating again. Popular URLs to shorten get cached in Redis.


## Technologies used

### Programming languages
- Java **17**

### Frameworks
- Spring Boot  (version **3.3.2**)
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Data Redis
  - Spring Boot Starter Test
  - Spring Boot DevTools
 
### Additional libraries
- PostgreSQL Driver
- Lombok

