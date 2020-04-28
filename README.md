# Product Search API
* An simple API is being developed catering the given requirement to search products.
* A H2 in memory database has been used, and provided data set is being loaded at the initialization of the application with the help of pre processed sql files.
* All the specified query parameters are being supported.

# Building Steps
* mvn clean install
* docker build -t adnilas/simple-search .
* docker run -p 8080:8080 adnilas/simple-search


