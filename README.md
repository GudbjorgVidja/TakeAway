# HBV202G: last assignment

The last assignment, using the program TakeAway, an assignment from HBV201G. The project will have been refactored and
improved.

In this program a user can pick menu items from a list and order them.

## Maven

This project supports the Maven goals compile, test, exec:java, package and site.

- `mvn compile` compiles all implementation classes.
- `mvn exec:java` executes the main method of the implementation.
- `mvn test` runs all test cases (i.e. all classes with a name that either starts with `Test` or ends with `Test`
  , `Tests`, or `TestCase`).
- `mvn package` makes a jar and a fat jar
- `mvn site` generates a site for the project

## Design patterns

The Observer pattern is used, by implementing listeners and bindings.

- In `GreidslaController.java`:
    - A listener, the observable is veitingarList in Karfa, and the observer is a method to make a
      new text
- in `Karfa.java`:
    - a listener, the observer is the total price of the order, and the observable is the contents of Karfa.
- in `PontunController.java`:
    - a listener, the observer is the Karfa, and the observable is the last selected item from a listView of the menu.
    - a binding, the observer is the listView in Karfa and the observable is the observableList<Veitingar> from the
      backend.
    - a binding,the observer is the label, and the observable is the Heildarverd.
    - two identical bidirectional bindings, one between a the text property of the name textfield and the name value in
      Vidskiptavinur and the other binding is the same except for the home address. Since the bindings are
      bidirectional, each property is both an observer and observable.
- in `VidskiptavinurDialog.java`:
    - a binding, the observer is the disableProperty for the ilagi button, the observable are the contents of the
      textfields nafn and heimilisfang.

## Documentation

[Documentation](src/site/markdown/documentation.md) in the form of UML diagram

## Licenses

The license used was an [MIT License](LICENSE).

## End-user documentation

Instructions to run the program:

For Mac and Linux (held ég):

- run the file run.cmd through the terminal

For Windows with bash or something:

- use maven commands clean and package
- Open the terminal
- type "source runjar.cmd"
- press enter

## To do

- Git
    - nota bara git, báðar að committa
- Maven (staðfesta að þetta sé komið)
    - nota Maven project layout (kafli/verkefni 4)
    - support goals: compile, test, exec:java, package, site
    - Komið:
        - compile
        - test
        - package
        - site
        - exec:java
- Junit test fyrir bakendann
    - we did not cover how to test user interfaces, so no need to test the UI, but you can test classes/the business
      logic behind the user interface.
- UML design
    - Nota comment fyrir fxml skrár og enum (í View.java)
    - create a class diagram (note that UMLet cannot read in enum types, create them on your own using the «enumeration»
      stereotype)
- Design patterns
- Refactor kóða
    - extract method: setja hluta kóðans í sér aðferðir, t.d. færa prentsetningar eða annað sem er sérstaklega commentað
      á í aðferð
    - passa að breyta ekki eða bæta við virkni á sama tíma og verið er að refactora
- Packaged jar file and command
- Documentation
    - README, license, nákvæmari documentation (markdown) með t.d. UML og leiðbeiningum fyrir notanda.
- Hreinsa upp pom skrána, taka út óþarfa kóða

allar tilviksbreytur fyrir viðmótshluti byrja á fx, t.d. væri innskráning hnappur fxInnskraning.

## Nánari leiðbeiningar

Frekari hint og leiðbeiningar frá Helmut, t.d. af ed

### GUI-related classes testing and modelling

As a reminder: no need to create JUnit tests for GUI-related classes (you did not learn how to. e.g. make JUnit click a
button in a GUI). Only create test for the business logic (e.g. in the takeaway project from the UI programming course:
test that the sum of items in the shopping cart is correctly calculated).

Sometimes, it a GUI-related class (e.g. some controller) that keeps business objects together. Take care to show in your
UML class diagram then also GUI classes (otherwise, it becomes not clear how the business objects relate to each other.

### You get Observer design pattern for free if you use a GUI based on the model-view-controller pattern

Note that when you use a GUI, it is very likely that you use the Observer pattern. In a GUI, you typically have event
handlers or change listeners that react to changes based on the Observer pattern, i.e. the event handler or change
listener is observing something and gets informed that something happened.

It is OK to claim that this is the design pattern that you used: just take care to describe this in your documentation,
i.e. what is the Observable in your case, what is the Observer.

