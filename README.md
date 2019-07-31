[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e52996a93abe4f0cac08457f34b9fe78)](https://app.codacy.com/app/augustoberwaldt/softdesign?utm_source=github.com&utm_medium=referral&utm_content=augustoberwaldt/softdesign&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/augustoberwaldt/softdesign.svg?branch=master)](https://travis-ci.org/augustoberwaldt/softdesign)

##### Conciderações

Escolhi o spring boot para realizar teste , por ser facil de levantar servidor e ja ter bastante abstrações programação. 
Acabei não colocando as configurações no CONSUL, mas seria o ideal. Os Dados estão sendo perssitidos no H2, mas para mudar
seria so  subir um docker de outro banco e alterar application.yml.

##### Link da aplicação no Heroku

https://softdesignbrasil.herokuapp.com/swagger-ui.html


###### Interface para acessar Banco H2

localhost:8080/db

###### Interface para acessar swagger

localhost:8080/swagger-ui.html

##### Tecnologias Utilizadas
- Linguagem de Programação Java
- Spring Boot
- Banco de dados H2
- swagger
- Junit
- Heroku Cloud