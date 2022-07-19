# CSCI 5448: Object-Oriented Analysis & Design
## Prof. Bruce Montgomery
### Project 7: July 20, 2022

### Final project report<br/>

#### 1. Name of project and names of all team members<br/>
Pong With Me by Tyler Walker and Ivane Gamkrelidze<br/>

#### 2. Final state of system statement<br/>
_A paragraph on the final state of your system: what features were implemented, what features were not and why, what changed from Project 5 and 6._

Every feature outlined in Project 5 was implemented. Every stretch goal outlined in Project 6 was met. We deviated from the patterns proposed in Project 5 as we found more appropriate patterns.

Primary features implemented were:
* Main menu
* Top5 leaderboard
* Gameplay
  * Movable paddle
  * Moving ball
  * Game timer
  * Collisiion detection
  * Increasing game difficulty

Stretch goals implemented were:
* Make the game work despite window size
* Custom player name

#### 3. Final class diagram and comparison statement<br/>

##### Final class diagram
###### Legend<br/>
&nbsp;&nbsp;&nbsp; + &nbsp;&nbsp;&nbsp;&nbsp; public access modifier<br/>
&nbsp;&nbsp;&nbsp; # &nbsp;&nbsp;&nbsp;&nbsp; protected access modifier<br/>
&nbsp;&nbsp;&nbsp; ~ &nbsp;&nbsp;&nbsp;&nbsp; package-private access modifier<br/>
&nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp;&nbsp; private access modifier<br/>

![Final class diagram](CSCI5448_Proj7_ClassDiagram.drawio.svg)

##### Original class diagram<br/>
![Original class diagram](CSCI5448_Proj5_UML_class_diag_pattern_use.drawio.svg)

##### Key changes in system since original design<br/>
_Support the diagrams with a written paragraph identifying key changes in your system since your design/work was submitted in Projects 5 and 6_

Since the original design and analysis work, we found the observer and decorator patterns unnecessary and thus did not implement them. We also found a different need for the simple factory pattern than originally planned.

#### 4. Third-party code _vs_ original code statement
A clear statement of what code in the project is original vs. what code you used from other sources – whether tools, frameworks, tutorials, 
or examples – this section must be present even if you used NO third-party code - include the sources (URLs) for your third-party elements

#### 5. Statement on the OOAD process for your overall semester project
_List three key design process elements or issues (positive or negative) that your team experienced in your analysis and design of the OO semester project_

1. Implementing patterns pre-determined during the design stage proved challenging. While some were obvious (MVC), in other cases we had to learn as we go which patterns were appropriate as we developed the program.
1. We found the UML class diagram to be a helpful starting point. Briefly, we started the project by setting up the structures identified in the UML class diagram. However, we found this was not the way forward for this project (too many depenedencies for GUI to figure out). So, instead we began with a simple JavaFX `HelloWorld`-type project and built up from that. We still found the majority of the structures identified in the UML class diagram instructive.
1. JavaFX timelines?
