# JWTAuthorization
JWT authorisation on spring

База данных локальная. Использовался PostgreSQL.
Для отправки запросов использовался Postman.

# Запросы:
## Signup
`POST localhost:8080/api/auth/signup`
```
{
    "username": "admin",
    "email": "admin@gmail.com",
    "password": "12345678",
    "role": ["admin, moderator"]
}
```

## Get JWT Token
`POST localhost:8080/api/auth/signin`
```
{
    "username": "admin",
    "password": "1234"
}
```
