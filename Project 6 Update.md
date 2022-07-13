# CSCI 5448: Object-Oriented Analysis & Design
## Prof. Bruce Montgomery
### Project 6: July 13, 2022

### Status summary<br/>
#### Team members<br/>
Tyler Walker, Ivane Gamkrelidze<br/>

#### Title<br/>
Pong With Me<br/>

#### Work done<br/>
In the first sprint, we
* set up the class structure (Ivane)
* developed the game interface (Tyler)
* added a movable paddle (Tyler)
* added a moving ball (Tyler)
* added a game timer (Tyler)
* added the main menu with the option to start the game, view the leaderboard, and quit (Ivane)
* added a top 5 leaderboard (Ivane)

#### Changes or issues encountered<br/>
So far, we have not deviated from our project plan. However, we are re-considering some of the patterns proposed in Project 5. We are having trouble trying to figure out how to handle the paddle–ball collision but this will likely be resolved before Project 7. We decided to implement an MVC pattern for the Game, Menu, and Leaderboard since that is a common pattern for modern JavaFX development, particularly when using FXML files. 

#### Pattern use<br/>
The following patterns are present: singleton for the game timer and the MVC pattern for the Game and Menu. The MVC pattern was the natural
go-to pattern for this application. The singleton pattern was helpful in enforcing a single instance of the game timer.

### Class diagram<br/>
#### Legend<br/>
&nbsp;&nbsp;&nbsp; + &nbsp;&nbsp;&nbsp;&nbsp; public access modifier<br/>
&nbsp;&nbsp;&nbsp; # &nbsp;&nbsp;&nbsp;&nbsp; protected access modifier<br/>
&nbsp;&nbsp;&nbsp; ~ &nbsp;&nbsp;&nbsp;&nbsp; package-private access modifier<br/>
&nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp;&nbsp; private access modifier<br/>

![Class diagram](CSCI5448_Proj6_ClassDiagram.drawio.svg)

### Plan for next iteration<br/>
For the final deliverable, we plan to have the collision detection between the paddle and the ball implemented and real game scores reflected in the leaderboard. The game should be able to store each score in a newline-delimited text file, and the leaderboard will read those scores from that text file. We also want to allow for an increasing game difficulty so the game becomes more challenging as time goes on. After achieving these items, our game should be complete per our planning from Project 5. From there, we can work on styling the application and any stretch goals that we have.

So far we have a couple of stretch goals: the first stretch goal is that we would like to make the game work despite the window size; the second stretch goal would be to include the functionality for a custom player name.
