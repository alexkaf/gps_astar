import java.io.* ;
import java.util.* ;
import java.lang.*;

// class Trial is the main function used to create the graph
// ???? - WILL BE CHANGED TO A HELPER FUNCTION - ????
public class Trial{

	public static void main(String []args){

		FileExecute myfile = new FileExecute(args[0]);

		//myfile.createHashTable();
		//myfile.compressMap();
		myfile.fastHash();
		//myfile.makeGraph();
		// Not sure if this is ok
		Map<Pair,Node> temp_graph = myfile.getGraph();
	}
}