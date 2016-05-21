# TheCarSalonMaven

### Program leírása

*Ez egy autó összerakó, nézegető, rendeléseket leadó program.*
*Készítette: Szanics Levente*
*Készült: 2015/16 2. félév*
*Beadásra kerül: Programozási környezetek és Programozási technológiák tárgyból.*

### Rendszerkövetelmények

A program lefordításához `Maven 3.x` és `Java 1.8` szükséges.

### Futtatás

A `pom.xml` mappájába a következő parancsok kiadása szükséges:

```
mvn clean package
java -jar target/TheCarSalonMaven-1.0-jar-with-dependencies.jar
```

A site legenerálásához:

```
mvn site
```