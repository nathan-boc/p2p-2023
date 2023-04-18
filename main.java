public class Main {
    
    public static void main(String[] args) {
        
        int bitSize = 9;
        int numberOfNodes = 10;
        
        int[] nodes = {0, 85, 133, 182, 210, 245, 279, 324, 395, 451};
        
        
        for(int i = 0; i < nodes.length; i++) {
            if(i == nodes.length - 1) {
                System.out.println(nodes[i]);
            } else {
                System.out.print(nodes[i] + ", ");
            }
        }
    }
}
