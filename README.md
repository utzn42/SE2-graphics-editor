# Quick and dirty REST Tutorial

## Basic Info

REST ist eine API, mit deren Hilfe man Java Code über den Browser/HTTP aufrufen kann. Was man dazu braucht, sind ein paar Imports, ein paar Annotations und eben einen Browser.

## Setup

Gehen wir das ganze anhand des Codes in diesem Repository durch. Die Dependencies sollte Maven bereits automatisch heruntergeladen haben. Was wir jetzt in unserer **Main.java** benötigen, sind folgende Imports:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

Zusätzlich benötigen wir vor unserer **Main.class** ein `@SpringBootApplication`, welches dem Compiler bescheid gibt, dass dies die Hauptklasse unserer REST-Implementierung darstellen wird. Der Methodenaufruf `SpringApplication.run(Main.class, args);` startet dann die Applikation. Das Ziel von nun an ist es, für jede funktionelle URL in unserem Zielbereich eine Klasse zu erstellen, die die Funktionalität für diese URL abdeckt. In jede dieser Klassen importieren wir daher: 

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
```

`@RestController` markiert unsere jeweiligen Komponenten (Klassen), die für das HTTP-Handling zuständig sind. Für die jewiligen URLs die wir mappen wollen, erstellen wir jeweils eine Funktion, über der wir `@RequestMapping("/path/")` schreiben, um die dazugehörige URL zu markieren. Um auch noch die HTTP-Methode zu spezifizieren (Standard ist GET), müssen wir stattdessen `@RequestMapping(value = "/path/", method = RequestMethod.METHOD)` schreiben.

## Aufruf

Um die URL-Methoden, die wir ausprogrammiert haben aufzurufen, müssen wir dies nun mittels zB Postman oder einem Browser aufrufen. Dazu starten wir das Programm in IntelliJ und rufen **localhost:8080/path** auf.

## Fehlerquellen

+ **Auf die Package-Struktur achten.**

Es ist wichtig, dass die `@SpringBootApplication` die Wurzel des gesamten REST-Programms bildet (wie im Beispiel), da dies sonst zu Fehlern führen kann.