# Chess Game Application


Chess Application created with Java and the JavaFX framework.

The purpose of the project was an implementation the OOP concepts in Java, as well as an initiation to JavaFX.


## Table of Contents

<!-- [comment]: <> (Features: This section could include a list of the key features of your chess application, such as the ability to play against a computer or another player, different difficulty levels, and the option to save and load games.) -->
<!-- [comment]: <> (User Guide: This section could provide instructions on how to use your chess application, including how to start a new game, how to move the pieces, and how to access different options and settings.) -->

<!-- [comment]: <> (Future Developments: This section could include a list of planned updates or features that you intend to add to your chess application in the future.)
* [Features](#features) -->
* [Technologies](#technologies)
* [Environment](#environment)
* [Installation](#installation)
* [User Guide](#user-guide)
* [Images](#images)
* [Code](#code)
* [Additional Resources](#additional-resources)
* [Future Development](#future-development)

***
## Technologies 



Let's write down the technologies: languages used, libraries and its versions:

* Java 19
* JavaFX 
* UML Modelling

The chess application was developed using Java and JavaFX. Java is a popular programming language that is known for its cross-platform compatibility, and JavaFX is a Java library for creating graphical user interfaces.

In addition to Java and JavaFX, we also used Unified Modeling Language (UML) to design the architecture of the application. UML is a standard language for modeling object-oriented systems, and we used it to create class diagrams, sequence diagrams, and state diagrams to visualize the different components of the application and their interactions.


***

## Environment


The choosen environment to develop the application was IntelliJ. **IntelliJ IDEA** is a powerful integrated development environment **(IDE)** that is specifically designed for Java development. Additionally, it also has built-in support for JavaFX and Gradle, which helped to set up and manage the dependencies for the project. We found that **IntelliJ IDEA** was better suited for our needs compared to other IDEs such as VS Code, Eclipse, and NetBeans.

It is important to note that different developers have different preferences, and the choice of the **IDE** depends on the personal taste, the features that the developer needs, and the project requirements.


***

## Installation

The first step in running the chess application is to make sure that you have the Java Development Kit (JDK) installed on your computer. The JDK is a software development environment for building Java applications. It includes the Java Runtime Environment (JRE) as well as development tools such as the Java compiler.

You can download the JDK from the official Oracle website:
> <https://www.oracle.com/java/technologies/javase-downloads.html>

Or you can use a package manager like `apt-get` or `yum` to install the JDK on Linux.

You can check if the JDK is installed on your computer by running the following command in your terminal:

```console
java -version
```

This should display the version of the JDK that is currently installed on your computer. If the command is not recognized, then the JDK is not installed.



Installing JavaFX:
JavaFX is a Java library that is used for creating graphical user interfaces. It is included in the JDK, but if it is not included in your JDK you can download it separately from the official Oracle website:

> <https://gluonhq.com/products/javafx/>

JavaFX can be added to your project by adding the library jar files to the classpath and you can also use it with the gradle,maven or other build tools.

You can check if JavaFX is installed on your computer by running the following command in your terminal:

```console
javap -v -cp jfxrt.jar javafx.application.Application
```

This command will display information about the JavaFX Application class, if the command is not recognized, then JavaFX is not installed.

***

## User Guide

The developer of this chess application has created a comprehensive user guide section in the README.md file to provide users with clear instructions on how to navigate and use the application.

1. ### *Landing Page*

  The landing page has been designed with usability in mind, and features options to choose from different available themes and select the desired duration of the game.

2. ### *Chessboard Page*

 The chessboard page is the core of the application, and it includes an interactive chessboard with all the standard chess pieces and moves, allowing for a seamless and enjoyable game experience. 
 
3. ### *Result Page*

 The result page, which will be displayed once the game is over, provides an overview of the outcome of the game (win, lose or draw) and the time taken to complete the game. Overall, this user guide section has been crafted to ensure that users can easily understand and utilize all the features of the application.

