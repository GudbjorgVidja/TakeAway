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

For Windows with bash or something:

- use maven commands clean and package
- Open the terminal
- type "source runjar.cmd"
- press enter

For Mac and Linux (mvn command):

- run the file run.cmd through the terminal

