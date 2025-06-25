# Projeto AssemblyVoting 

# Sobre o Projeto
Este projeto consiste no desenvolvimento de uma API backend em Java voltada para a realização de votações em assembleias de forma simples, segura e controlada.

No sistema, usuários podem cadastrar pautas, que representam os assuntos a serem discutidos e votados durante uma assembleia. Cada pauta pode ser aberta para votação, permitindo que os participantes registrem seu voto escolhendo entre as opções SIM ou NÃO.

A principal regra de negócio implementada é a restrição de voto único:
Cada usuário pode votar apenas uma vez por pauta, garantindo integridade e imparcialidade nos resultados.

Além disso, quando uma pauta é aberta para votação, ela possui um tempo de duração limitado. Após esse período, a sessão de votação é automaticamente encerrada, impedindo novos votos. Isso simula o funcionamento de assembleias reais, onde há um prazo definido para deliberação.

O sistema foi desenvolvido utilizando o framework Spring Boot, com foco em boas práticas de API REST, validações de dados, organização de entidades e clareza nas regras de domínio. Ele também pode ser facilmente integrado a sistemas frontend ou aplicativos mobile, possuindo como validação as suas regras de negócio os testes de unidade em sua camada de "Service".

Este backend serve como uma base sólida para sistemas de votação em cooperativas, associações, empresas ou qualquer outro cenário onde decisões coletivas precisam ser tomadas de forma digital e transparente.

# Modelo de Domínio :
![modelo conceitual](https://github.com/user-attachments/assets/8a587883-73c2-4d07-a66b-194ced4a0ada)


# Tecnologias Utilizadas 


# Back end 

●Java

● Spring Boot (utilizando Spring Tool Suite - STS)

● Spring Data MongoDB

● JUnit 5 / Mockito

● Maven

# Banco de dados 

● Mongo DB

● Mongo Compass

# Outros 

● Postman


# Como executar o projeto
# Back end

Pré-requisitos: Java 21

● Clonar repositório

git clone https://github.com/gabrielnilsonespindola/assemblyVoting-JavaProject.git

● Entrar na pasta do projeto back end

cd assemblyVoting

● Executar o projeto

./mvnw spring-boot:run


# Autor : 

Gabriel Nilson Espindola

🔗 https://www.linkedin.com/in/gabriel-nilson-espindola-065694297/
