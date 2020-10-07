# Example OAS

An example application built from OAS 3.0 (Swagger 3.0) specification is demonstrated with this project.


## Structure

The project uses a multi-module Maven project to demonstrate the creation of a separate Library JAR
for the API classes generated from OAS specification.
Separating the library helps with reduced build time,
as well as improved maintainability of applications using the specification
as they can more easily control the version of the API in-use by the application.
Versioning of the API is more obvious when working with the JAR file as it is
dedicated to the API only.


**Modules**

* example-oas-jaxrs - API Library generated from the OAS specification.
* example-oas-cxf-app - Application serving the endpoints from the OAS Specification.  Uses CXF.


## TO BUILD

    $ mvn clean install


## TO RUN

    $ PROJECT_VERSION="$(mvn -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive -q org.codehaus.mojo:exec-maven-plugin:1.6.0:exec)"
    $ java -jar "example-oas-cxf-app/target/example-oas-cxf-app-${PROJECT_VERSION}.jar"


## TO TEST

    $ curl http://localhost:9000/hi
    $ curl -v http://localhost:9000/hi
