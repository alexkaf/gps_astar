import java.lang.* ;
import java.util.* ;

// class Node from which any object represents a graph node
public class Node{

	private double cordX,cordY;
	private int roadID;
	private Vector<Node> hasLink;
	private Vector<Double> distanceFromNode;

	// Constructor to just create a node with the attributes it needs
	// Arguments: coordinates of the road and its ID
	// Returns: void
	Node(double cordX, double cordY, int roadID){

		this.cordX = cordX;
		this.cordY = cordY; 
		this.roadID = roadID;
		this.hasLink = new Vector<Node>();
		this.distanceFromNode = new Vector<Double>();

	}

	// void linkNodeWith connects a node with an other node by adding it on its hasLink vector
	// Arguments: the node to be connected
	// Returns: void
	public void linkNodeWith(Node nodeToLink){
		this.hasLink.add(nodeToLink);
		this.distanceFromNode.add(General.eucleidian(nodeToLink.getX(),nodeToLink.getY()),this.cordX,this.cordY,"K");
	}

	// int getID(), double getX() and double getY() return the οbvious values
	// Arguments: void
	// Returns: the roadID, cordX and cordY respectively
	public int getID(){
		return this.roadID;
	}

	public double getX(){
		return this.cordX;
	}

	public double getY(){
		return this.cordY;
	}

	// Vector<Node> getNeighboors() returns a vector of the neighboors of this node
	// Arguments: void
	// Returns: void
	public Vector<Node> getNeighboors(){
		return this.hasLink;
	}

	// Vector<Double> getDistances() returns a vector with the respective distance of any neighbooring node
	// from this node
	// Argumnets: void
	// Returns: void
	public Vector<Double> getDistances(){
		return this.distanceFromNode;
	}

	// void showState just prints the coordinates and ID of the node
	// Arguments: void
	// Returns: void
	public void showState(){
		System.out.println(cordX + " " + cordY + " " + roadID);
	}

	// void isConnected shows all the nodes connected to this node
	// Arguments: void
	// Returns: void
	public void isConnected(){
		for(int i=0; i<this.hasLink.size();i++){
			hasLink.elementAt(i).showState();
		}
	}

	// void isConnectedPlus shows all the connected nodes to this node + the eucleidian distance between each of them
	// Arguments: void
	// Returns: void
	public void isConnectedPlus(){
		for(int i=0; i<this.hasLink.size();i++){
			hasLink.elementAt(i).showState();
			System.out.println(this.distanceFromNode.elementAt(i));
		}
	}

	// void calculateEucleidian() calculates the eucleidina distance of this node to all its neighboors
	// Arguments: void
	// Returns: void
	public void calculateEucleidian(){
		this.distanceFromNode = new Vector<Double>();
		for(int i=0; i<this.hasLink.size(); i++){
			distanceFromNode.add(General.eucleidian(this.cordY,this.cordX,this.hasLink.elementAt(i).getY(),this.hasLink.elementAt(i).getX(),"K"));
		}
	}
}