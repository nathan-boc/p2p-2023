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
        		
        		int modTargetValue = targetValue;
        		
        		// Finds the remainder of the max key value
        		if(targetValue >= maxKeyValue) {
        			modTargetValue = targetValue % maxKeyValue;
        			
        		}
        		
        		// Code for finding the link node value
        		int linkNode = 0;
        		int x = 0;
        		
        		int currNode = i;
        		
        		while(modTargetValue > nodes[currNode + x]) {
        			
        			if(currNode + x == nodes.length - 1) {
        				break;
        			}
        			
        			if(currNode == nodes.length - 1) {
        				
        				if(x != 0) {
        					if(modTargetValue <= nodes[x]) {
                				linkNode = nodes[x];
                				
            				} else {
            					x++;
            				}
        					
        				} else {
        					if(modTargetValue <= nodes[i + x]) {
                				linkNode = nodes[i + x];
                				
        					} else if(modTargetValue <= nodes[x])  {
        						linkNode = nodes[x];
        						
        					} else {
        						x++;
        					}
        				}
        			
        			} else if(modTargetValue <= nodes[i + x]) {
        				linkNode = nodes[i + x];
        				
        			} else {
        				x++;
        			}
        		}
        		
        		// Set values for current row in finger tables
        		fingerTable[j][0] = j;
        		fingerTable[j][1] = targetValue;
        		fingerTable[j][2] = linkNode;
        		
        	}
        	
        	fingerTables.add(i, fingerTable);
        }
        
        
        // Loop for printing out finger tables
        for(int i = 0; i < fingerTables.size(); i++) {
        	
        	System.out.println("Finger Table for Node " + nodes[i]);
        	System.out.println("\ni  Target  Link");
        	
        	for(int j = 0; j < bitSize; j++) {
        		
        		for(int k = 0; k < numCols; k++) {
        			System.out.print(fingerTables.get(i)[j][k] + " | ");
        		}
        		
        		System.out.println("");
        		
        		
        		
        	}
        	System.out.println("\n\n");
    		
        }
      
        
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
}
