import java.util.ArrayList;
import java.util.Scanner; 

public class Main {
    
    private static final int bitSize = 9;
    private static final int maxKeyValue = (int) Math.pow(2, bitSize);
    
    // Number of cols in a finger table
    private static final int numCols = 3;
    
    

	public static void main(String[] args) {
        
        int[] nodes = {0, 85, 133, 182, 210, 245, 279, 324, 395, 451};
        
        // Initialize all finger tables
        ArrayList<int[][]> fingerTables = generateFingerTables(nodes);

        
        Scanner scanner = new Scanner(System.in);
		
        System.out.println("______________________\n");
		System.out.println("Peer-to-Peer Networks COSC1235");
		System.out.println("Semester 1 2023 - Progressive Test 2");
		
		System.out.println("\nChord DHT P2P Network");
		System.out.println("Please select option 2 for demonstration of finding a key in the network.");
		
		while(true) {
			System.out.println("\n______________________\n");
			System.out.println("\n---- Menu ----");
			System.out.println("[1] View team members");
			System.out.println("[2] Run demonstration");
			System.out.println("[3] Show nodes");
			System.out.println("[4] Show generated finger tables");
			
			System.out.println("\nEnter option...");
			String input = scanner.nextLine();
			
			System.out.println("\n______________________\n");
			
			if(input.equals("1")) {
				System.out.println("Team Members:");
				System.out.println("Nathan Boc s3717205");
				System.out.println("Sifan Gao s3753605");
				System.out.println("Josiah Webster s3785546");
				System.out.println("Connor Forster s3781475");
				
			} else if(input.equals("2")) {
				System.out.println("option 2");
				
			} else if(input.equals("3")) {
				System.out.println("Nodes in the network:");
				printNodes(nodes);
				
			} else if(input.equals("4")) {
				printFingerTables(fingerTables, nodes);
				
			} else {
				System.out.println("Invalid input. Please try again.");
			}
			
		}
        
        // NEXT: Searching for keys..
    }

    public static ArrayList<int[][]> generateFingerTables(int[] nodes) {
    	
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
       
        return fingerTables;
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
    
    public static void printFingerTables(ArrayList<int[][]> fingerTables, int[] nodes) {
    	
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
        	
        	if(i != fingerTables.size() - 1) {
        		System.out.println("\n____________________");
        	}
        }
    	
    }
}
