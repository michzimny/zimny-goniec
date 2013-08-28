# Zimny Goniec

## O programie

Programy JFR Pary i Teamy (http://www.pzbs.pl/pary-teamy) zawierają program Goniec, który ma problem z wrzucaniem plików na niektóre serwery FTP (wiadomo, że są takie problemy z serwerami home.pl), ponieważ nie jest przystosowany do określonych wersji lub ustawień protokołu FTP. Z tego powodu powstał Zimny Goniec, który ma te same funkcje co Goniec, ale jest pozbawiony wspomnianego wyżej ograniczenia.

## Kompilacja

Wymagania: 

* JDK 7 lub 8
* Apache Maven 3.3.x (lub prawdopodobnie dowolny nowszy)

Kompilacja:

```
mvn compile package
```

Powstanie wykonywalny JAR: target/zimny-goniec-${version}-jar-with-dependencies.jar

## Użycie

Umieścić w jednym katalogu wykonywalny JAR oraz goniec.xml z konfiguracją. Uruchomić:

```
java -jar ${jar_filename}
```
