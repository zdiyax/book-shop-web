# book-shop-web

Java Web Project | EPAM Systems Qaraghandy Java Web Training

## Description

book-shop-web - is a project designed as a final examination on EPAM Systems Java Web Training. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Series of software you need:
* IDE
* postgresql
* JDK (1.8)
* Maven 3.*

### Install and run the project

A step by step series of examples to tell you have to get a development env running

1. Download/clone the project
2. Prepare the database ([schema](http://dbdesigner.net/designer/schema/61369) ):
    * Add a PostgreSQL Data Source 
    * Use the settings from the following property file `db.properties` or change them as you prefer
    * `jdbc:postgresql:postgres` url is used (see the property file [`db.properties`](https://github.com/zdiyax/book-shop-web/blob/master/src/main/resources/db.properties))
    * Use the following access settings: `login = postgres, password = root`
3. Execute SQL code in Data Source from `src.main.resources.sql`:
    * `create.sql` - initializing schema, tables, relations
    * `dump.sql` - dumping tables with data
    * `drop.sql` - dropping tables and schema

## Running the tests

Project contains some jUnit-tests to check the following features:
* Use `ConnectionPoolTest` to test project's connection pooling
* Use `DBConnectionTest` to test your database connection

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Zhannur Diyas** - *Initial work* - [zdiyax](https://github.com/zdiyax)
