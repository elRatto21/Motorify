# Motorify Backend

This is the backend of my "Motorify" school project

### Used Technologies

- Spring Boot 3
- PostgreSQL
- KeyCloak
- Swagger UI


### How to use

#### PostgreSQL
1. Create user with username ``postgres`` and password ``RohrKabel06``
2. Create ``motorify-db`` database

#### KeyCloak
1. Create ``motorify`` realm
2. Create ``motorify`` client-id
3. Create ``ROLES_admin`` and ``ROLES_user`` roles

#### Spring
1. Start project with ``mvn spring-boot:run``