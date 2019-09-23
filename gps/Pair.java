import java.util.* ; 

// class Pair which is used in the comparisons at hashing.
public class Pair{

	private double cordX,cordY;

	// constructor that stores the coordinates in variables
	// Arguments: node's coordinates
	// Returns: void
	Pair(double cordX, double cordY){

		this.cordX = cordX;
		this.cordY = cordY;
	}

	// double getX
	// Arguments: void
	// Returns: cordX
	public double getX(){
		return cordX;
	}

	// double getY
	// Arguments: void
	// Returns: cordY
	public double getY(){
		return cordY;
	}

	// boolean equals that compares two objects of class Pair
	// *** Overrides default equals() function
	// Arguments: object of class Pair to compare with
	// Returns: true if two objects have the same coordinates, else false
	@Override
	public boolean equals(Object otherObject){

		if ((otherObject == null)||(otherObject.getClass() != this.getClass())){
			return false;
		}

		Pair newObject = (Pair) otherObject;

		return this.cordX == newObject.getX() && this.cordY == newObject.getY();
		
	}

	// int hashCode() returns a hashcode for this Pair
	// *** Overrides default hashCode() function
	// Arguments: void
	// Returns: int hash key
	@Override
	public int hashCode(){
		return Objects.hash(cordX,cordY);
	}

}