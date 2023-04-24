# Spring Boot CRUDs + REST API

A sample project built with Spring Boot 3.0.5, Java 17 and Hibernate 6 to perform CRUD operations on relational models.

It also features a dedicated `@RestController` to send custom JSON responses.

## Database

MariaDB (MySQL) provided by [XAMPP](https://www.apachefriends.org/index.html) has been configured as the default
persistence provider.

**Cars** ("Auto" table) are connected to **Engines** ("Motore" table) via a `@ManyToOne` kind of relationship.

![Database diagram](https://i.ibb.co/rbc4Qbf/automobili.png "Database diagram")

Some **triggers** perform automatic backups after cars have been updated or deleted. Both deleted and updated cars can
be found in the `auto_bak` table along with a timestamp of the operation.

## Instructions

### Create and seed the database

Assuming you can access your MySQL server via phpMyAdmin, just **import** the provided `automobili.sql` file from the
main page. Make sure you're not selecting any database, as this file will take care of creating a database named '
automobili' and populate it with tables and rows.

### Start the server

Open the project from your favourite IDE, let it update Maven dependencies and run the application. You should now be
able to navigate to `http://localhost:8082`.

> If you are unable to start the server, make sure XAMPP's Apache and MySQL services are up and running.

> If needed, you can change credentials and ports in `src/main/resources/application.properties`.

## CRUDs

From the homepage, you can start making some simple CRUDs and perform some queries.

Templates are very simple as they're meant to just serve as a GUI for the backend.

Data bindings and cycles are handled with **Thymeleaf**.

## REST API

Assuming the application is running on `http://localhost:8082`, here is a list of available paths:

<table align="center">
<tr style="text-align: center">
    <th>URI</th>
    <th>METHOD</th>
</tr>
<tr>
    <td>http://localhost:8082/auto/{id}</td>
    <td>GET</td>
</tr>
<tr>
    <td>http://localhost:8082/auto/{id}</td>
    <td>PUT</td>
</tr>
<tr>
    <td>http://localhost:8082/auto/{id}</td>
    <td>DELETE</td>
</tr>
<tr>
    <td>http://localhost:8082/auto/insert</td>
    <td>POST</td>
</tr>
<tr>
    <td>http://localhost:8082/auto/update-price/{id}</td>
    <td>PUT</td>
</tr>
<tr>
    <td>http://localhost:8082/auto/all</td>
    <td>GET</td>
</tr>
</table>

### Sending a payload

POST and PUT methods expect a JSON body shaped like this:

```json
{
  "marca": "BMW",
  "modello": "X3",
  "prezzo": 16850,
  "colore": "Nero",
  "alimentazione": "Diesel",
  "motore": {
    "id": 20
  }
}
```

### Custom Response Handler

A custom `ResponseEntity` maps the retrieved object(s) to the "auto" property and also adds a "message" and a "status"
code.

Here is an example of a GET response:

```json
{
  "auto": {
    "id": 1,
    "marca": "Fiat",
    "modello": "Punto Evo",
    "prezzo": 13000,
    "colore": "Nera",
    "alimentazione": "Benzina",
    "motore": {
      "id": 3,
      "descrizione": "1200/15",
      "cilindrata": 1200,
      "cavalli": 15
    }
  },
  "link": {
    "rel": "self",
    "href": "http://localhost:8082/auto/1"
  },
  "message": "Auto with id:1 successfully retrieved",
  "status": 200
}
```
