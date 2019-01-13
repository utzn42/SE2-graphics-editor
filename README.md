# Deadline Cheat Sheet

## Deadline report document
[Click me (Google docs)](https://docs.google.com/document/d/1giVYf8zheo_p9spFaeQ0Py_6l9_OwKBESeWITqjDuj4/edit)

## Add JUnit 5 dependencies to Maven

Add the following lines to your ```pom.xml```:
```xml
<dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.2.0</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-params</artifactId>
      <version>5.2.0</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.platform</groupId>
      <artifactId>junit-platform-runner</artifactId>
      <version>1.1.1</version>
      <scope>test</scope>
    </dependency>
```
In case of problems, replace 5.2.0 with 5.1.1.

