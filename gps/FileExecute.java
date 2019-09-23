import java.io.* ;
import java.util.* ;
import java.lang.* ;

// class FileExecute that reads a file and does operations on it such as showing it, parsing it creating nodes(useless) and creating
// its graph representation.
public class FileExecute{

	// Class variables
	private File file_dr;
	private FileReader fileCords;
	private String filename;
	private BufferedReader fileReader;
	private Map<Pair,Vector<Node>> roadsMap;
	private Map<Pair,Node> compressedMap;
	private Map<Pair,Node> fastMap;

	// Constructor to just open the file and create a reader
	// Argument: the name of the file
	// Returns : void
	FileExecute(String filename){
		this.file_dr = new File(filename);
		this.filename = filename;

		try{
			this.fileCords = new FileReader(file_dr);
			this.roadsMap = new HashMap<Pair,Vector<Node>>();
			this.compressedMap = new HashMap<Pair,Node>();
		}
		catch(FileNotFoundException exc){
			System.out.println("File not found.");
		}
	}

	// void showFile to just show the context of the file
	// Arguments: void
	// Returns: void
	public void showFile(){

		try{
			fileReader = new BufferedReader(this.fileCords);
			String aLine;
			while((aLine = fileReader.readLine()) != null){
				System.out.println(aLine);
			}
		}
		catch(IOException exc){
			System.out.println("Could not read from file.");
		}
	}

	// void fileParser to parse the whole csv nodes file and create the nodes of the map
	// Arguments: void
	// Returns: void	
	// WARNING !!! Useless method !!!
	public void fileParser(){	

		try{
			fileReader = new BufferedReader(this.fileCords);
			String aLine;
			aLine = fileReader.readLine();
			String[] separated_vals;
			while((aLine = fileReader.readLine()) != null){
				//String[] separated_vals = aLine.split(","); - Auto eixa prin
				separated_vals = aLine.split(",");
				Double cordX = new Double(Double.parseDouble(separated_vals[0]));
				Double cordY = new Double(Double.parseDouble(separated_vals[1]));
				Integer roadID = new Integer(Integer.parseInt(separated_vals[2]));
				Node newNode = new Node(cordX,cordY,roadID);
			}
		}
		catch(IOException exc){
			System.out.println("Could not read from file.");
		}
	}

	// void createHashTable to organize all the nodes in a hash table having the coordinates as key - named Pair,
	// each entry has a vector with values of type Node, meaning all the nodes with the same coordinates - those are the crosspaths
	// Arguments : void - already existing, uses private Map<Pair,Vector<Node>> roadsMap
	// Returns : void - everything is stored in private roadsMap
	public void createHashTable(){

		try{
			fileReader = new BufferedReader(this.fileCords);
			String aLine;
			Node newNode = new Node(0.0,0.0,0);
			Pair thisPair = new Pair(0,0);			//initialise
			aLine = fileReader.readLine();
			String[] separated_vals;

			while((aLine = fileReader.readLine()) != null){

				separated_vals = aLine.split(",");
				double cordX = Double.parseDouble(separated_vals[0]);
				double cordY = Double.parseDouble(separated_vals[1]);
				int roadID = Integer.parseInt(separated_vals[2]);
				thisPair = new Pair(cordX,cordY);
				newNode = new Node(cordX,cordY,roadID);

				if (roadsMap.get(thisPair) == null){
					Vector<Node> toAddNode = new Vector<Node>();
					toAddNode.add(newNode);
					roadsMap.put(thisPair,toAddNode);
				}else{
					roadsMap.get(thisPair).add(newNode);
				}
			}
		}
		catch(IOException exc){
			System.out.println("Could not read from file.");
		}
	}

	// void compressMap compresses the HashMap since when 2 or more nodes share the same coordinates there is no need to keep them
	// all - THEY ARE THE SAME NODE - gives roadID 0 - (Could be done at createHashTable - will try it later) -> !!!! REMEMBER !!!! <-
	// Arguments: void since it only uses the roadsMap hashtable
	// Returns: void since it stores the new HashMap in private variable compressedMap - it is not ready yet to return -  
	public void compressMap(){

		for(Pair key : roadsMap.keySet()){
			if(roadsMap.get(key).size() != 1){
				double tempX = key.getX();
				double tempY = key.getY();
				compressedMap.put(key,new Node(tempX,tempY,0));
			}else{
				compressedMap.put(key,roadsMap.get(key).firstElement());
			}
		}
		System.out.println(compressedMap.keySet().size());
	}

	// void fastHash tries to achieve a faster hashing with not adding a node if it already exists
	// Arguments: void - uses the Map<Pair,Node> fastMap variable
	// Returns: void
	// - Not Sure If Working Properly - SHOULD CHECK
	// UPDATE: 90% not working well !!!
	public void fastHash(){

		try{
			fileReader = new BufferedReader(this.fileCords);
			String aLine;
			Node newNode = new Node(0.0,0.0,0);
			Pair thisPair = new Pair(0,0);
			String[] separated_vals;
			fastMap = new HashMap<Pair,Node>();
			aLine = fileReader.readLine();

			while((aLine = fileReader.readLine()) != null){

				separated_vals = aLine.split(",");
				double cordX = Double.parseDouble(separated_vals[0]);
				double cordY = Double.parseDouble(separated_vals[1]);
				int roadID = Integer.parseInt(separated_vals[2]);
				thisPair = new Pair(cordX,cordY);
				newNode = new Node(cordX,cordY,roadID);

				if(fastMap.get(thisPair) == null){
					fastMap.put(thisPair,new Node(cordX,cordY,0));
				}
			}
		}
		catch(IOException exc){
			System.out.println("Could not read from file.");
		}
	}

	// void makeGraph makes the graph of the nodes taking the hashMap and connecting the nodes according to the input file
	// in the end it also computes the eucleidian distances between the nodes
	// Arguments: void 
	// Returns: void
	public void makeGraph(){

		try{
			this.file_dr = new File(filename);
			this.fileCords = new FileReader(file_dr);
			fileReader = new BufferedReader(this.fileCords);
			String aLine;
			aLine = fileReader.readLine();
			aLine = fileReader.readLine();
			String[] separated_vals = aLine.split(",");
			double prevX = Double.parseDouble(separated_vals[0]);
			double prevY = Double.parseDouble(separated_vals[1]);
			int prevID = Integer.parseInt(separated_vals[2]);

			while((aLine = fileReader.readLine()) != null){

				separated_vals = aLine.split(",");
				double cordX = Double.parseDouble(separated_vals[0]);
				double cordY = Double.parseDouble(separated_vals[1]);
				int roadID = Integer.parseInt(separated_vals[2]);

				if(prevID == roadID){
					//this.compressedMap.get(new Pair(cordX,cordY)).linkNodeWith(this.compressedMap.get(new Pair(prevX,prevY)));
					//this.compressedMap.get(new Pair(prevX,prevY)).linkNodeWith(this.compressedMap.get(new Pair(cordX,cordY)));
					this.fastMap.get(new Pair(cordX,cordY)).linkNodeWith(this.fastMap.get(new Pair(prevX,prevY)));
					this.fastMap.get(new Pair(prevX,prevY)).linkNodeWith(this.fastMap.get(new Pair(cordX,cordY)));
					prevX = cordX;
					prevY = cordY;
				}else{
					prevID = roadID;
					prevX = cordX;
					prevY = cordY;
				}

			}
			/*
			for(Pair key : compressedMap.keySet()){
				this.compressedMap.get(key).calculateEucleidian();
			}
			*/
		}
		catch(FileNotFoundException exc){
			System.out.println("File not found.");
		}
		catch(IOException exc){
			System.out.println("Could not read from file.");
		}
	}

	public Map<Pair,Node> getGraph(){
		return fastMap;
	}

}
































