Explaination how the code works:
1.In the battlefield class I declared two 3D arrays one array is to hold the ship and the other is for the advanced part to show where the
 player has hit and it can also be used as a reference for the player where to place the next missile. Then I declared a random variable
 that can be used to place the ship on the grid as different positions and orientation.
 
2.Then I declared a constructor that initialises both the 3D arrays to 0 which indicates that the field is empty.

3.Then there is a print method which prints out the field to the teminal according to their coordinates.

4.Then there is a attackPrintField which prints out the other grid which shows the history of the attack's made by the player.

-----------Deploying the Ships--------------

5.deathStar is a method which takes 8 integers as arguments and returns a boolean telling us whether these places are empty or not,
 these 8 points are the 8 compartments of a cube.
 
6.Then there is a method called randomDeathStar which takes an integer as an argument that integer which is given as an argument is for
 the representation of the ship on the grid, then there are a series of integers declared the first three are the co-Ordinates, the next
 represent the compartments of the cube. We initialise the coordinates randomly and then chose the 8 cubes next to each other and then sent
 their values into the variables for their checking if they are empty or not if they are empty then we replace all these cubes with the
 integer that was given as an argument. If we these places are not empty then this method is called again until the ship is successfully
 placed in the grid.
 
7.Then I declared a method called ship which takes six arguments of int type one of the argument is the length of the ship and the next are
 the positions occupied by the ship, there are a few if else statements in this method. the first one, if the length is zero then it is 
 enough that the first cube is empty, is the length is one then the first two positions should be empty, if the length two then the first 
 three positions should be empty, if the length is three then the first four positions should be empty if the length is four the the first 
 five positions should be empty. If the conditions satisfy then it returns true else it returns false. this method is for another method 
 which places the ships randomly.
 
8.Then I declared another method of the same name called ship but takes different arguments but has the same function as the last one but 
this method is used for placing the ship manually. This also returns true if the ship can be placed at the given position or false if the 
position is not possible.

9.Then there is a method called placeShip which takes direction, length of the ship, position on the coordinate and a integer which is used 
to represent it on the grid.

10.Then there is a method called randomShip which takes the ship length and a integer which is used to represent the ship on the grid, in 
this method there are a series of integers for the coordinates and the positions of the ship on the grid and a integer to decide the 
direction in which the ship is placed, the position of the coordinates is declared randomly and the direction is also decided randomly here 
0 means x axis, 1 means y axis and 2 means z axis. The length is initiallised with the argument from the method then there are a series of 
switch statements which places the ship on the grid this method calls itself again if it cannot place the ship at a specific place.
11.Then there is a method called loadGame which has all the ships required by each player in the game.

-----------Attacking Ships------------

12.Then I declared a method called inRange which takes the co-Ordinates as arrguments and checks if there is a ship at that position or not, 
if there is a ship there then it returns true or else it returns false.

13.Then there is a method called checkField which checks if there is any ship left in the grid or not, if the grid is empty then it returns 
true or else it returns false.

13.Then there is a method called placeMissile which takes the co-Ordinates as the arrgument and places a missile at that specific position, 
that is it replaces the point in the field to zero as well as it places a 1 in the other 3D array declared in the starting showing that a 
missile was hit at that point.

----------A better Strategy-------------

14.I have tried to make the computer more intelligent but it needs more improvement.
