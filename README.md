# pos-app

# About

This project was developed to attend the following requirement:

```
Nossa empresa está precisando cuidar do delivery dos POS (Máquinas de cartão) aos nossos clientes, e seu trabalho é produzir um sistema (API) que controlará o envio desses materiais, ligando à tabela de funcionários.

Requisitos funcionais:
* O sistema deverá realizar o CRUD de dados de funcionários ( nome, admissão, periodo de trabalho ). Somente usuários com perfil Administrador
* Crud de entregas ( num_pedido, nome do cliente, endereco do cliente )
* Agendamento da entrega (qual funcionário deverá entregar qual pedido em qual data)


Requisitos não funcionais:
* Uso de spring-boot
* Autenticação: OAUTH2. Pode ser usado o keycloack como servidor.
* Banco de dados: SQL

OBS: Compartilhar o código em um repositório ou link público onde possamos ter acesso.
```

## DER

## How to run

```
mvn clean install
java -jar target\pos-app.jar
```

## Endpoints

All endpoints require fully authentication. It can be done through endpoint bellow:

OBS: Postman request can be accessed at project root, inside docs folder 

```
localhost:8080/oauth/token
```


