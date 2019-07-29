[![Build Status](https://travis-ci.org/augustoberwaldt/softdesign.svg?branch=master)](https://travis-ci.org/augustoberwaldt/softdesign)

##### Conciderações

Escolhi o spring boot para realizar teste , por ser facil de levantar servidor e ja ter bastante abstrações programação. 
Acabei não colocando as configurações no CONSUL, mas seria o ideal. Os Dados estão sendo perssitidos no H2, mas para mudar
seria so  subir um docker de outro banco e alterar application.yml.

##### Link da aplicação no Heroku

https://softdesignbrasil.herokuapp.com/swagger-ui.html


###### Interface para acessar Banco H2

localhost:8080/db

##### Tecnologias Utilizadas
- Linguagem de Programação Java
- Spring Boot
- Banco de dados H2
- swagger
- Junit