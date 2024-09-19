import java.util.ArrayList;
import java.util.List;

public class RiverCrossing {
    public static void main(String[] args) {
        RiverCrossing obj = new RiverCrossing();
        obj.solutionLists();
    }   
    
    ArrayList<String> left;
    ArrayList<String> right;
    public RiverCrossing() {
        left = new ArrayList<>();
        right = new ArrayList<>();
        left.add("monk");
        left.add("tiger");
        left.add("cabbage");
        left.add("goat");
    }

    public void moveTarget(String item, List<String> source, List<String> destination) {
        source.remove(item);
        destination.add(item);
    }

    public void printState() {
        System.out.println("Left Bank : " + left);
        System.out.println("Right Bank : " + right);
        System.out.println();
    } 

    public void solutionLists() {
        // initially
        printState();
        
        // 1st pass.
        moveTarget("monk", left, right);
        moveTarget("goat", left, right);
        printState();

        // 2nd pass.
        moveTarget("monk", right, left);
        printState();
        
        // 3rd pass.
        moveTarget("monk", left, right);
        moveTarget("tiger", left, right);
        printState();
        
        // 4th pass.
        moveTarget("monk", right, left);
        moveTarget("goat", right, left);
        printState();

        // 5th pass.
        moveTarget("monk", left, right);
        moveTarget("cabbage", left, right);
        printState();

        // 6th pass.
        moveTarget("monk", right, left);
        printState();

        //7th pass.
        moveTarget("monk", left, right);
        moveTarget("goat", left, right);
        printState();


    }
}
