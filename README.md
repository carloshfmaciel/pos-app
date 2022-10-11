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
OBS: All Postman requests can be accessed at project root, inside docs folder


### Getting a valid token

It can used "admin" or "client" user. Both has the password "123456". Additionaly to that is necessary execute Basic Authentication informing username(client) and password(123) as follow.

Obs: To register employee is mandatory to be logged as admin because the endpoint POST and PUT requires role "ROLE_ADMIN". Only "admin" user has it.

```
localhost:8080/oauth/token
```

![image](https://github.com/carloshfmaciel/pos-app/blob/main/screenshots/001.JPG)

### Registering an Employee

![image](https://github.com/carloshfmaciel/pos-app/blob/main/screenshots/002.JPG)

JSON Body Request
```
{
    "id": 3,
    "name": "Carlos Maciel",
    "entityType": "E",
    "address": {
        "address": "Rua Xpto",
        "number": 333,
        "zipCode": "04824100",
        "cityId": 1
    },
    "jobRole": "Delivery",
    "admissionDate": "01/01/2022",
    "startPeriodTime": "09:00",
    "endPeriodTime": "18:00"
}
```

### Creating a customer

![image](https://github.com/carloshfmaciel/pos-app/blob/main/screenshots/003.JPG)

Json Body Request
```
{
    "name": "Maria Almeida",
    "entityType": "C",
    "address": {
        "address": "Rua Axz",
        "cityId": 1,
        "number": 333,
        "zipCode": "04824100"
    }
}
```

### Creating a order

![image](https://github.com/carloshfmaciel/pos-app/blob/main/screenshots/004.JPG)

Json Body Request
```
{
  "customerId": 4
}
```

### Inserting a schedule delivery associating a customer and an employee

![image](https://github.com/carloshfmaciel/pos-app/blob/main/screenshots/005.JPG)

```
{
    "employeeId": 4,
    "orderId": 1,
    "deliveryDate": "11/10/2022"
}
```

# Attention Points

- It was not implemented any business rules
- This project has just the purpose to show rest api implementation protected by oauth2 mechanism as well persisting data in a transaction database



