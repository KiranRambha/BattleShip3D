import java.util.*;

public class Battlefield {

	int[][][] field = new int[5][5][5]; //Battle Field
	int[][][] attack = new int[5][5][5]; //A second battle field for giving the user an  idea of his attack
	Random random = new Random();
	
	/**
	 * A constructor to initialise the fields 
	 */
	public Battlefield() {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				for (int z = 0; z < field[x][y].length; z++) {
					field[x][y][z] = 0; 
					attack[x][y][z] = 0; 
				}
			}
		}
	}
	
	 /**
	  * A method to print the battle area
	  */
	public void printfield() {
		System.out.println("*************************************************************************************************");
		System.out.print("  Z = 0             Z = 1             Z = 2             Z = 3             Z = 4" + '\n');
		System.out.print("X=0  1  2  3  4     0  1  2  3  4     0  1  2  3  4     0  1  2  3  4     0  1  2  3  4" + '\n');
		System.out.println();
		for(int y = 0; y < field.length; y++){
			for(int z = 0; z < field.length; z++){
				for(int x = 0; x < field.length; x++){
					System.out.print("  " + field[x][y][z]);
				}
				System.out.print("   ");
			}
			System.out.print(" Y = " + y);
			System.out.println();
		}
		System.out.println("*************************************************************************************************");
	}
	
	/**
	 * A method to prints the history of the attack the player has made
	 */
	public void attackPrintField() {
		System.out.println("*************************************************************************************************");
		System.out.print("  Z = 0             Z = 1             Z = 2             Z = 3             Z = 4" + '\n');
		System.out.print("X=0  1  2  3  4     0  1  2  3  4     0  1  2  3  4     0  1  2  3  4     0  1  2  3  4" + '\n');
		System.out.println();
		for(int y = 0; y < field.length; y++){
			for(int z = 0; z < field.length; z++){
				for(int x = 0; x < field.length; x++){
					System.out.print("  " + attack[x][y][z]);
				}
				System.out.print("   ");
			}
			System.out.print(" Y = " + y);
			System.out.println();
		}
		System.out.println("*************************************************************************************************");
	}

	/* Deploying the Ships */
	
	/**
	 * To check if the Death Star can be placed at the position
	 * @param bottom1 One of the bottom cube
	 * @param bottom2 One of the bottom cube
	 * @param bottom3 One of the bottom cube
	 * @param bottom4 One of the bottom cube
	 * @param top1 One of the top cube
	 * @param top2 One of the top cube
	 * @param top3 One of the top cube
	 * @param top4 One of the top cube
	 * @return returns true if the ship can be placed at that position else returns false
	 */
	public boolean deathStar(int bottom1, int bottom2, int bottom3, int bottom4, int top1, int top2, int top3, int top4) {
		if (bottom1 == 0 && bottom2 == 0 && bottom3 == 0 && bottom4 == 0 && top1 == 0 && top2 == 0 && top3 == 0 && top4 == 0) { //checks if all the positions required to place the ship are empty or not
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to place the death star randomly 
	 * @param i This is the integer with which the Death Star is represented in the field
	 */
	public void randomDeathStar(int i) {
		int x, y, z, bottom1, bottom2, bottom3, bottom4, top1, top2, top3, top4;
		//sending some random points to place the ship
		x = random.nextInt(5);
		y = random.nextInt(5);
		z = random.nextInt(5);
		if (x + 1 <= 4 && y + 1 <= 4 && z + 1 <= 4) { //this is done to handle exceptions
			//sending the values into the variable to get them verified if they can be placed at that position or not 
			bottom1 = field[x][y][z];
			bottom2 = field[x + 1][y][z];
			bottom3 = field[x + 1][y + 1][z];
			bottom4 = field[x][y + 1][z];
			top1 = field[x][y][z + 1];
			top2 = field[x + 1][y][z + 1];
			top3 = field[x + 1][y + 1][z + 1];
			top4 = field[x][y + 1][z + 1];
			if (deathStar(bottom1, bottom2, bottom3, bottom4, top1, top2, top3, top4)) { //checks if the positions are empty ot not
				//if yes then it is placed on the grid with some number for representation
				field[x][y][z] = i;
				field[x + 1][y][z] = i;
				field[x + 1][y + 1][z] = i;
				field[x][y + 1][z] = i;
				field[x][y][z + 1] = i;
				field[x + 1][y][z + 1] = i;
				field[x + 1][y + 1][z + 1] = i;
				field[x][y + 1][z + 1] = i;
			} else {
				randomDeathStar(i); //if the ship was not able to be placed then it calls the method again so a ship is placed at some position
			}
		} else {
			randomDeathStar(i);
		}
	}
	
	/**
	 * This method is for the ranodom method to place the ship it checks if the ship of some length sattisfies the conditions to place at that position
	 * @param l The length of the ship
	 * @param pos1 The number of positions each ship occupies
	 * @param pos2 The number of positions each ship occupies
	 * @param pos3 The number of positions each ship occupies
	 * @param pos4 The number of positions each ship occupies
	 * @param pos5 The number of positions each ship occupies
	 * @return returns true if the number of positions required to place the ship is empty
	 */
	public boolean ship(int l, int pos1, int pos2, int pos3, int pos4, int pos5) {
		if (l == 0 && pos1 == 0) { //if the length is 1 then it checks only the first position if that position is empty or not
			return true;
		} else if (l == 1 && pos1 == 0 && pos2 == 0) { //if the length is 2 then it checks the first and second position are empty or not
			return true;
		} else if (l == 2 && pos1 == 0 && pos2 == 0 && pos3 == 0) { //if the length is 3 then it checks the first three position are empty or not
			return true;
		} else if (l == 3 && pos1 == 0 && pos2 == 0 && pos3 == 0 && pos4 == 0) { //if the length is 4 then it checks if the first four positions are empty or not
			return true;
		} else if (l == 4 && pos1 == 0 && pos2 == 0 && pos3 == 0 && pos4 == 0 && pos5 == 0) { //if the length is 5 then it checks if the first five positions are empty or not 
			return true;
		} else { 
			return false;
		}
	}
	
	/**
	 * This method is used to check if the ship can be placed manually or not
	 * @param orientation direcction in which the ship is to be placed
	 * @param shiplength the length of the ship
	 * @param i position of the x co-Ordinate
	 * @param j position of the y co-Ordinate
	 * @param k position of the z co-Ordinate
	 * @return returns true if the position and orientation is possible
	 */
	public boolean ship(int orientation, int shiplength, int i, int j, int k) {
		int direction = orientation;
		int length = shiplength;
		int x = i;
		int y = j;
		int z = k;
		boolean flag = false;
		if (direction == 0) { //if the direction is 0 then it goes along the x axis for a given length of the ship from a given point checking if those many positions are empty or not
			for (int a = x; a < x + length; a++) {
				if (field[a][y][z] == 0) {
					flag = true;
				} else if (field[a][y][z] != 0) {
					flag = false;
					break;
				}
			}
		} else if (direction == 1) { //if the direction is 1 then it goes along the y axis for a given length of the ship from a given point checking if those many positions are empty or not
			for (int a = x; a < y + length; a++) {
				if (field[x][a][z] == 0) {
					flag = true;
				} else if (field[x][a][z] != 0) {
					flag = false;
					break;
				}
			}
		} else if (direction == 2) { //if the direction is 2 then it goes along the z axis for a givec length of the ship from a given point checking if those many positions are empty or not 
			for (int a = x; a < z + length; a++) {
				if (field[x][y][a] == 0) {
					flag = true;
				} else if (field[x][y][a] != 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * This method is used to place the ship manually
	 * @param orientation The direction in which the ship should be placed
	 * @param length_of_the_ship The length of the ship
	 * @param i position of the x co-Ordinate
	 * @param j position of the y co-Ordinate
	 * @param k position of the z co-Ordinate
	 * @param m The number with which the ship should be represented on the field
	 */
	public void placeShip(int orientation, int length_of_the_ship, int i, int j, int k, int m) {
		int direction = orientation;
		int length = length_of_the_ship;
		int x = i;
		int y = j;
		int z = k;
		int l = m;
		if (ship(orientation, length, x, y, z)) { //if the positions are empty then it places a ship at that place
			if (direction == 0) { //along x axis
				for (int a = 0; a < x + length; a++) {
					field[a][y][z] = l;
				}
			} else if (direction == 1) { //along y axis
				for (int a = 0; a < y + length; a++) {
					field[x][a][z] = l;
				}
			} else if (direction == 2) { //along z axis
				for (int a = 0; a < z + length; a++) {
					field[x][y][a] = l;
				}
			}
		}
		else{
			System.out.println("try different position");
		}
	}

	/**
	 * This method is used to place the ship randomly on the grid 
	 * @param shiplength The length of the ship
	 * @param i The number with which the ship is represented on the grid
	 */
	public void randomShip(int shiplength, int i) {
		Integer x, y, z, length, pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0, pos5 = 0, direction;
		x = random.nextInt(5); //random x coordinate
		y = random.nextInt(5); //random y coordinate
		z = random.nextInt(5); //random z coordinate
		length = shiplength;
		direction = random.nextInt(3); //random direction
		switch (direction) { //switch for the direction
		case 0: //along x axis
			if (length == 0) { //if the length is 1
				pos1 = field[x][y][z];
				if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
					field[x][y][z] = i;
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 1) { //if the length is 2
				if (x + 1 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x + 1][y][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x + 1][y][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 2) { //if the length is 3
				if (x + 2 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x + 1][y][z];
					pos3 = field[x + 2][y][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x + 1][y][z] = i;
						field[x + 2][y][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 3) { //if the length is 4
				if (x + 3 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x + 1][y][z];
					pos3 = field[x + 2][y][z];
					pos4 = field[x + 3][y][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x + 1][y][z] = i;
						field[x + 2][y][z] = i;
						field[x + 3][y][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 4) { //if the length is 5
				if (x + 4 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x + 1][y][z];
					pos3 = field[x + 2][y][z];
					pos4 = field[x + 3][y][z];
					pos5 = field[x + 4][y][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x + 1][y][z] = i;
						field[x + 2][y][z] = i;
						field[x + 3][y][z] = i;
						field[x + 4][y][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			}
			break;

		case 1: //along y axis
			if (length == 0) { //if the length is 1
				pos1 = field[x][y][z];
				if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
					field[x][y][z] = i;
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 1) { //if the length is 2 
				if (y + 1 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y + 1][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y + 1][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 2) { //if the length is 3
				if (y + 2 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y + 1][z];
					pos3 = field[x][y + 2][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y + 1][z] = i;
						field[x][y + 2][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 3) { //if the length is 4
				if (y + 3 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y + 1][z];
					pos3 = field[x][y + 2][z];
					pos4 = field[x][y + 3][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y + 1][z] = i;
						field[x][y + 2][z] = i;
						field[x][y + 3][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 4) { //if the length is 5
				if (y + 4 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y + 1][z];
					pos3 = field[x][y + 2][z];
					pos4 = field[x][y + 3][z];
					pos5 = field[x][y + 4][z];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y + 1][z] = i;
						field[x][y + 2][z] = i;
						field[x][y + 3][z] = i;
						field[x][y + 4][z] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			}
			break;

		case 2: //along the z axis
			if (length == 0) { //if the length is 1
				pos1 = field[x][y][z];
				if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
					field[x][y][z] = i;
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 1) { //if the length is 2
				if (z + 1 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y][z + 1];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y][z + 1] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 2) { //if the length is 3
				if (z + 2 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y][z + 1];
					pos3 = field[x][y][z + 2];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y][z + 1] = i;
						field[x][y][z + 2] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 3) { //if the length is 4
				if (z + 3 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y][z + 1];
					pos3 = field[x][y][z + 2];
					pos4 = field[x][y][z + 3];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y][z + 1] = i;
						field[x][y][z + 2] = i;
						field[x][y][z + 3] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			} else if (length == 4) { //if the length is 5
				if (z + 4 < field.length) {
					pos1 = field[x][y][z];
					pos2 = field[x][y][z + 1];
					pos3 = field[x][y][z + 2];
					pos4 = field[x][y][z + 3];
					pos5 = field[x][y][z + 4];
					if (ship(length, pos1, pos2, pos3, pos4, pos5)) {
						field[x][y][z] = i;
						field[x][y][z + 1] = i;
						field[x][y][z + 2] = i;
						field[x][y][z + 3] = i;
						field[x][y][z + 4] = i;
					} else {
						randomShip(shiplength, i);
					}
				} else {
					randomShip(shiplength, i);
				}
			}
			break;
		}
	}

	/**
	 * The type of ship the player has in the game 
	 */
	public void loadGame() {
		randomDeathStar(2); //this is a death star
		randomShip(3, 3); //this is a cruiser
		randomShip(2, 4); //this is a frigate
		randomShip(1, 5); //this is a bomber
		randomShip(1, 5); //this is a bomber
		randomShip(0, 6); //this is a fighter
		randomShip(0, 6); //this is a fighter
		randomShip(0, 6); //this is a fighter
		randomShip(0, 6); //this is a fighter
	}

	/* Attacking Ships */
	/**
	 * Checks if the given position has a ship or not
	 * @param x position of the x co-Ordinate
	 * @param y position of the y co-Ordinate
	 * @param z position of the z co-Ordinate
	 * @return returns true if there is a ship or else returns false
	 */
	public boolean inRange(int x, int y, int z) {
		if (field[x][y][z] == 0) return false; //checking if there is a ship at the specific position or not for placing a missile 
		else return true;
	}

	/**
	 * Checks if the field has any ships left or not
	 * @return returns true if all the ships are destroyed or else returns false
	 */
	public boolean checkField() {
		int sum = 0;
		for(int x = 0; x < field.length; x++){
			for(int y = 0; y < field[x].length; y++){
				for(int z = 0; z < field[x][y].length; z++){
					sum += field[x][y][z]; //calculating the sum of the whole grid so that we can know if the grid is empty or not
				}
			}
		}
		if(sum == 0) return true; //if the sum is zero then it means the grid is empty
		else return false;
	}
	
	/**
	 * This method is used to place a missile at a given position
	 * @param i position of the x co-Ordinate
	 * @param j position of the y co-Ordinate
	 * @param k position of the z co-Ordinate
	 */
	public void placeMissile(int i, int j, int k) {
		int x = i;
		int y = j;
		int z = k;
		field[x][y][z] = 0; //placing a missile at the given position
		attack[x][y][z] = 1; //replacing the other grid to get a preview of our attack
	}
}
