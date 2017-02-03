# book-shop-web

Java Web Project | EPAM Systems Karagandy Java Web Training

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Series of software you need:
* IDE (IntelliJ IDEA preferred)
* PostgreSQL (9.4 is used)
* JDK (1.8)
* Maven 3.*

### Install and run the project

A step by step series of examples that tell you have to get a development env running

1. Download/clone the project
2. Prepare the database:
    * Add a PostgreSQL Data Source 
    * Use the settings from the following property file `db.properties` or change them as you prefer
    * `jdbc:postgresql:postgres` url is used (see the property file [`db.properties`](https://github.com/zdiyax/book-shop-web/blob/master/src/main/resources/db.properties))
    * Use the following access settings: `login = postgres, password = root`
3. Execute SQL code in Data Source from `src.main.resources.sql`:
    * `create.sql` - initializing schema, tables, relations
    * `dump.sql` - dumping tables with data
    * `drop.sql` - dropping tables and schema

4. Database [schema](http://dbdesigner.net/designer/schema/61369) 
## Running the tests

Project contains some jUnit-tests to check the following features:
* Use `ConnectionPoolTest` to test project's connection pooling
* Use `DBConnectionTest` to test your database connection


## Deployment

Apache Tomcat ver. 8.5.8 was used to deploy the project. Use whatever you prefer most.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Zhannur Diyas** - *Initial work* - [zdiyax](https://github.com/zdiyax)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

