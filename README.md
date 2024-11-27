# Simon Game

A Java-based implementation of the classic Simon memory game, where the player must replicate a growing sequence of colored buttons. This version includes functionality to store high scores using a database.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Getting Started](#getting-started)
5. [How to Play](#how-to-play)

## Introduction

The Simon Game is a memory game where a sequence of colors is shown to the player. The player must repeat the sequence correctly by pressing the corresponding colored buttons. The game increases the sequence length after every successful round. The player’s score is recorded in a database, showing their best score.

## Features

- Four colored buttons: Red, Blue, Green, Yellow.
- A growing sequence that the player needs to memorize and repeat.
- High score storage using JDBC (Java Database Connectivity).
- Game over screen showing the current score and asking the player to try again.
- A new round starts when the player successfully replicates the sequence.

## Technologies Used

- **Java**: The primary language used for game logic and GUI.
- **Swing**: For creating the game interface.
- **JDBC**: For connecting to the database and storing high scores.
- **MySQL**: Used as the database to store player names and high scores.
- **Maven**: For dependency management (e.g., MySQL Connector/J).

## Getting Started

To get a local copy of this project up and running, follow these steps:

### 1. Clone the repository:
Open a terminal and run:


```bash
git clone https://github.com/your-username/SimonGame.git
```
 ### 2.Install dependencies:
 If you’re using Maven, you can easily install the necessary dependencies by running:
 
 ``` teminal
mvn install
```
This will automatically download the required libraries, including MySQL Connector/J.

### 3. Set up the MySQL Database:
Make sure you have MySQL installed. Create a new database SimonGameDB and run the following SQL command to create the HighScores table:

```sql
CREATE DATABASE SimonGameDB;

USE SimonGameDB;

CREATE TABLE HighScores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(50) NOT NULL,
    high_score INT NOT NULL
);
```

### 4. Update Database Connection:
In the project, update the DatabaseHandler.java file with your MySQL database credentials, such as username, password, and host.

```java
// Example connection string
String url = "jdbc:mysql://localhost:3306/SimonGameDB";
String username = "root"; // Your MySQL username
String password = "password"; // Your MySQL password
```
### 5. Run the Game:

Once the database is set up and dependencies are installed, you can run the Main.java file to start playing the game.

### How to Play
Start the game by running the SimonGame class.
A sequence of colors will be shown to you, and you must repeat the sequence by clicking the corresponding colored buttons in the same order.
The sequence gets longer with each round.
If you make an error, the game will end, and your score will be shown.
The highest score achieved is saved to the database.

