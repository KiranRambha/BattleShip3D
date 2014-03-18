import java.util.*;

public class Battleship3D {
	public static void main(String[] args) {
		int orientation, length, x = 0, y = 0, z = 0, l, p = 0, q = 0, r = 0, direction = 0;
		Scanner scanner = new Scanner(System.in); //the scanner object
		Random random = new Random(); //the random object
		boolean HuntMode = true;
		Character character = null;
		Battlefield player = new Battlefield(); //declaring the player object
		Battlefield computer = new Battlefield(); //declating the computer object
		computer.loadGame(); //loading the computers ships on to its grid randomly
		System.out.println("Soldier get ready for the BattleShip");
		System.out.print("Do you want to place you ships Randomly? (y/n)"); //asking the user if he wants to place the ship randomly or manually

		// initialising
		while(true){ //taking the input from the user
			character = scanner.next().charAt(0);
			if(character == 'y' || character == 'n'){
				break;
			}
			if(character != 'y' || character != 'n')
			{
				System.out.println("wrong input enter again");
			}
		}
		if (character == 'y') { //if the user wants to place the ship randomly
			player.loadGame(); //it calls the loadGame method which loads the ships into the grid
			player.printfield(); //it prints out the users grid with the ships on it
		} else if (character == 'n') { //if the user wants to add the ships manually
			int i = 1;
			while (i <= 9) { //it goes through the loop asking the user to enter the ships values
				try {
					if (i == 1) {
						System.out.println("Death star placed randomly");
						player.randomDeathStar(1); //DeathStar is placed randomly
					}
					if (i == 2) {
						System.out.println("enter the orientation, length, x-position, y-position, z-position");
						System.out.println("enter a cruiser");
						orientation = scanner.nextInt();
						length = scanner.nextInt();
						x = scanner.nextInt();
						y = scanner.nextInt();
						z = scanner.nextInt();
						l = 2;
						player.placeShip(orientation, length, x, y, z, l);
					}
					if (i == 3) {
						System.out.println("enter the orientation, length, x-position, y-position, z-position");
						System.out.println("enter a frigate");
						orientation = scanner.nextInt();
						length = scanner.nextInt();
						x = scanner.nextInt();
						y = scanner.nextInt();
						z = scanner.nextInt();
						l = 3;
						player.placeShip(orientation, length, x, y, z, l);
					}
					if (i == 4 || i == 5) {
						System.out.println("enter the orientation, length, x-position, y-position, z-position");
						System.out.println("enter a bomber" + i);
						orientation = scanner.nextInt();
						length = scanner.nextInt();
						x = scanner.nextInt();
						y = scanner.nextInt();
						z = scanner.nextInt();
						l = 4;
						player.placeShip(orientation, length, x, y, z, l);
					}
					if (i == 6 || i == 7 || i == 8 || i == 9) {
						System.out.println("enter the orientation, length, x-position, y-position, z-position");
						System.out.println("enter a fighter");
						orientation = scanner.nextInt();
						length = scanner.nextInt();
						x = scanner.nextInt();
						y = scanner.nextInt();
						z = scanner.nextInt();
						l = 5;
						player.placeShip(orientation, length, x, y, z, l);
					}
					i++;
				} catch (InputMismatchException e) { //catching the wrong input
					System.out.println("Wrong Input");
					scanner.next();
				} catch (Exception e) { //cathing the wrong position
					System.out.println("Wrong Input");
				}
			}
			player.printfield(); //printing the field of the user with the ships on it
		} else {
			System.out.println("Wrong Input"); 
		}

		// Attacking in a better Stratagy

		System.out.println("Let the Battle begin");
		while (!player.checkField() || !computer.checkField()) { //while the player grid and the computer grid is not empty it will execute the following
			System.out.println("What is your move?"); //asking the user for his move
			try {
				System.out.print("Enter the x coOrdinate : ");
				x = scanner.nextInt(); //taking the x coordinate
				System.out.print("Enter the y coOrdinate : ");
				y = scanner.nextInt(); //taking the y coordinate
				System.out.print("Enter the z coOrdinate : ");
				z = scanner.nextInt(); //taking the z coordinate
				if (!computer.inRange(x, y, z)) { //checking if there is a ship at this position in the computers grid
					computer.placeMissile(x, y, z); 
					System.out.println("Miss :("); //if no then say the missile did'nt hit a ship
				} else if (computer.inRange(x, y, z)) {
					computer.placeMissile(x, y, z); 
					System.out.println("Hit :)"); //if yes then say a ship was hit by the missile
				}
				// Advanced
				System.out.println("your launch history"); 
				computer.attackPrintField(); //printing the launch history of the user
			} catch (InputMismatchException e) {
				System.out.println("wrong input");
				scanner.next();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("wrong input");
			}
			if(!HuntMode){ //this mode is to make the computer perform better rather than working randomly, this method is called when the computer hits a ship
				int tempP = p; //storing the value of the previous attack of the ship
				int tempQ = q; //storing the value of the previous attack of the ship
				int tempR = r; //storing the value of the previous attack of the ship
				if(direction == 5){ //along z axis backwards
					if(r-1 >= 0){ //to handle exception
						if (!player.inRange(p, q, r-1)) { //checking if there is a ship at that point in the players field
							player.placeMissile(p, q, r-1); 
							direction = 6;
							p = tempP;
							q = tempQ;
							r = tempR;
						} else if (player.inRange(p, q, r-1)) {
							player.placeMissile(p, q, r-1);
							System.out.println("The computer plays " + p + " " + q + " " + (r-1));
							System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
							direction = 5; //it will move in the same direction
							r--; 
						}
					}else{
						direction = 6;
					}
				}
				if(direction == 4){ //along z axis forwards
					if(r+1 <= 4){ //to handle exception
						System.out.println("The computer plays " + p + " " + q + " " + (r+1));
						if (!player.inRange(p, q, r+1)) { //checking if there is a ship at that point in the players field
							player.placeMissile(p, q, r+1); 
							System.out.println("Miss!"); //if no then it says that computer could not hit a ship
							direction = 5;
							p = tempP;
							q = tempQ;
							r = tempR;
						} else if (player.inRange(p, q, r+1)) {
							player.placeMissile(p, q, r+1);
							System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
							direction = 4; //it will move in the same direction
							r++;
						}
					}else{
						direction = 5;
					}
				}
				if(direction == 3){ //along y axis backwards
					if(q-1 >= 0){ //to handle exception
						System.out.println("The computer plays " + p + " " + (q-1) + " " + r);
						if (!player.inRange(p, q-1, r)) { //checking if there is a ship at that point in the players field
							player.placeMissile(p, q-1, r); 
							System.out.println("Miss!"); //if no then it says that computer could not hit a ship
							direction = 4;
							p = tempP;
							q = tempQ;
							r = tempR;
						} else if (player.inRange(p, q-1, r)) {
							player.placeMissile(p, q-1, r);
							System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
							direction = 3; //it will move in the same direction
							q--;
						}
					}else{
						direction = 4;
					}
				}
				if(direction == 2){ //along y axis forwards
					if(q+1 <= 4){ //to handle exception
						System.out.println("The computer plays " + p + " " + (q+1) + " " + r);
						if (!player.inRange(p, q+1, r)) { //checking if there is a ship at that point in the players field
							player.placeMissile(p, q+1, r); 
							System.out.println("Miss!"); //if no then it says that computer could not hit a ship
							direction = 3;
							p = tempP;
							q = tempQ;
							r = tempR;
						} else if (player.inRange(p, q+1, r)) {
							player.placeMissile(p, q+1, r);
							System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
							direction = 2;
							q++;
						}
					}else{
						direction = 3;
					}
				}
				if(direction == 1){ //along x axis backwards
					if(p-1 >= 0){ //to handle exception
						System.out.println("The computer plays " + (p-1) + " " + q + " " + r);
						if (!player.inRange(p-1, q, r)) { //checking if there is a ship at that point in the players field
							player.placeMissile(p-1, q, r); 
							System.out.println("Miss!"); //if no then it says that computer could not hit a ship
							direction = 2;
							p = tempP;
							q = tempQ;
							r = tempR;
						} else if (player.inRange(p-1, q, r)) {
							player.placeMissile(p-1, q, r);
							System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
							direction = 1; //it will move in the same direction
							p--;
						}
					}else{
						direction = 2;
					}
				}
				if(direction == 0){ //along x axis forwards
					if(p+1 <= 4){ //to handle exception
						System.out.println("The computer plays " + (p+1) + " " + q + " " + r);
						if (!player.inRange(p+1, q, r)) { //checking if there is a ship at that point in the players field
							player.placeMissile(p+1, q, r); 
							System.out.println("Miss!"); //if no then it says that computer could not hit a ship
							direction = 1;
							p = tempP;
							q = tempQ;
							r = tempR;
						} else if (player.inRange(p+1, q, r)) {
							player.placeMissile(p+1, q, r);
							System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
							direction = 0; //it will move in the same direction
							p++;
						}
					}else{
						direction = 1;
					}
				}
				if(direction == 6){ //switching back to the hunt mode 
					HuntMode = true;
					direction = 0;
				}
			}
			if(HuntMode){
				p = random.nextInt(5); //x coordinate for the computers attack
				q = random.nextInt(5); //y coordinate for the computers attack
				r = random.nextInt(5); //z coordinate for the computers attack
				System.out.println("The computer plays " + p + " " + q + " " + r);
				if (!player.inRange(p, q, r)) { //checking if there is a ship at that point in the players field
					player.placeMissile(p, q, r); 
					System.out.println("Miss!"); //if no then it says that computer could not hit a ship
				} else if (player.inRange(p, q, r)) {
					player.placeMissile(p, q, r);
					System.out.println("Hit!"); //if yes then it says that computer was successful in hitting a ship
					HuntMode = false;
				}
			}
			
			// Advanced
			System.out.println("Your ship's left are shown below");
			player.printfield(); //this prints out the remaining ships of the user
			if (player.checkField() || computer.checkField()) { //if the grid is empty then it breaks from the loop
				break;
			}
		}
		if (player.checkField()) { //if the players grid is empty then says computer won
			System.out.println("You have no more ships ..... The computer wins");
		} else if (computer.checkField()) { //if the computers grid is empty then it says the player won
			System.out.println("You won!! :D");
		}
		scanner.close();
	}
}
