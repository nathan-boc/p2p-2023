import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        
        int bitSize = 9;
        int maxKeyValue = (int) Math.pow(2, bitSize);
        
        // Number of cols in a finger table
        int numCols = 3;
        
        int[] nodes = {0, 85, 133, 182, 210, 245, 279, 324, 395, 451};
        
        printNodes(nodes);
        
        // Initialise all finger tables
        ArrayList<int[][]> fingerTables = new ArrayList<int[][]>();
        
        // Loop to calculate finger table values
        for(int i = 0; i < nodes.length; i++) {
        	
        	int[][] fingerTable = new int[bitSize][numCols];
        	
        	for(int j = 0; j < bitSize; j++) {
        		
        		int targetValue = (int) Math.pow(2,j) + nodes[i];
        		
        		// Finding the link node value
        		double linkNode = Double.NaN;
        		int currNode = i;
        		
        		while(Double.isNaN(linkNode)) {
        			
        			// Check if current node holds the target key value 
        			if(targetValue <= nodes[currNode]) {
        				linkNode = nodes[currNode];
        				
        			// Checks if on the final node
        			} else if(currNode == nodes.length - 1) {
        				
        				// Mod the target value if it exceeds maxKeyValue
        				if(targetValue >= maxKeyValue) {
        					targetValue = targetValue % maxKeyValue;
            				currNode = 0;
            			
            			// Link node is the first node only if currently on the final node and
            			// target is not over the max key value
        				} else {
        					linkNode = nodes[0];
        				}
        				
        				
        			} else {
        				currNode++;
        			}
        		}
        		
        		// Set values for current row in finger tables
        		fingerTable[j][0] = j;
        		fingerTable[j][1] = targetValue;
        		fingerTable[j][2] = (int) linkNode;
        		
        	}
        	
        	fingerTables.add(i, fingerTable);
        }
        
        printFingerTables(fingerTables, nodes, bitSize, numCols);
        
        
      
        
        // NEXT: Searching for keys..
    }
    
    public ArrayList<int[][]> generateFingerTables() {
    	return null;
    }
    
    
    public static void printNodes(int[] nodes) {
    	
    	// Print nodes to console
        for(int i = 0; i < nodes.length; i++) {
            if(i == nodes.length - 1) {
                System.out.println(nodes[i]);
            } else {
                System.out.print(nodes[i] + ", ");
            }
        }
    }
    
    public static void printFingerTables(ArrayList<int[][]> fingerTables, int[] nodes, int bitSize, int numCols) {
    	
        for(int i = 0; i < fingerTables.size(); i++) {
        	
        	System.out.println("\n\nFinger Table for Node " + nodes[i]);
        	System.out.println("\n  i  Target  Link");
        	System.out.println("-----------------");
        	
        	for(int j = 0; j < bitSize; j++) {
        		
        		System.out.print("| ");
        		
        		for(int k = 0; k < numCols; k++) {
        			
        			int cellSpaces = 0;
        			
        			if(k != 0) {
        				cellSpaces = 3 - String.valueOf(fingerTables.get(i)[j][k]).length();
        			}
        			
        			String padding = " ".repeat(cellSpaces);
        			
        			System.out.print(padding + fingerTables.get(i)[j][k] + " | ");
        		}
        		
        		System.out.println("");
        	}
        	
        	System.out.println("\n____________________");
        }
    	
    }
}
