# Zimny Goniec

## O programie

Programy JFR Pary i Teamy (http://www.pzbs.pl/pary-teamy) zawierają program Goniec, który ma problem z wrzucaniem plików na niektóre serwery FTP (wiadomo, że są takie problemy z serwerami home.pl), ponieważ nie jest przystosowany do określonych wersji lub ustawień protokołu FTP. Z tego powodu powstał Zimny Goniec, który ma te same funkcje co Goniec, ale jest pozbawiony wspomnianego wyżej ograniczenia.

## Budowanie

Wymagania: 

* JDK 7 lub 8
* Apache Maven 3.3.x (lub prawdopodobnie dowolny nowszy)

Sama kompilacja:

```
mvn compile
```

Kompilacja i pakowanie do dystrybucji:

```
mvn package
```

Powstanie ZIP gotowy do dystrybucji: target/zimny-goniec-${version}.zip

## Użycie

Rozpakować ZIP z dystrybucją, wprowadzić konfigurację do goniec.xml i uruchomić:

```
java -jar zimny-goniec-${version}.jar
```

