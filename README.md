# HBV202G: last assignment

The last assignment, using the program TakeAway, an assignment from HBV201G. The project will have been refactored and
improved.

## Maven

This project supports the Maven goals compile, test, exec:java, and the goals package and site will be added.

## Design patterns

- The Observer pattern is used, by implementing listeners.
    - A listener in GreidslaController, observing changes to the contents of Karfa.
    - in Karfa: a listener observing the contents of Karfa, to calculate the total price of the order.
    - in PontunController: a listener on the last selected item from a listView of the menu, to add that item to Karfa.

## Documentation

[Documentation](src/site/markdown/documentation.md)

## To do

- Git
    - nota bara git, báðar að committa
- Maven
    - nota Maven project layout (kafli/verkefni 4)
    - support goals: compile, test, exec:java, package, site
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

