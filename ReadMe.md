# Synthetic core starter
## Quick start
- Clone Repo:
  ```bash
  git clone https://github.com/{username}/{repoName}.git
  ```
- Build project
  ```bash
  cd project-bishop
  mvn clean install
  ```
- Start application
  ```bash
  cd bishop-prototype
  mvn spring-boot:run
  ```
- If you want use kafka config:
- - Run docker-compose file:
  ```bash
  cd bishop-prototype
  mvn spring-boot:run
  ```
- - Run application with profile active: 
  ```bash
  mvn spring-boot:run -Dspring.profiles.active=kafka
  ```
  
## How to use

- All endpoints are available at the link:
  - API: http://localhost:8080/api/v1/android
  - Metrics: http://localhost:8080/actuator
  - Swagger UI: http://localhost:8080/swagger-ui.html
