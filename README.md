# Deadline Cheat Sheet

## Add JUnit 5 dependencies to Maven

Add the following lines to your ```pom.xml```:
```xml
<dependency>
     <groupId>org.junit.jupiter</groupId>
     <artifactId>junit-jupiter-engine</artifactId>
     <version>5.1.1</version>
     <scope>test</scope>
</dependency>
<dependency>
     <groupId>org.junit.platform</groupId>
     <artifactId>junit-platform-runner</artifactId>
     <version> 1.1.1</version>
     <scope>test</scope>
</dependency>
```
