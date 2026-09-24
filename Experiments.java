import java.util.ArrayList;
import java.util.HashMap;

public class Experiments {

    public static void main(String[] args) {

        HashMap<Integer, ArrayList<Integer>> finalMap =
                new HashMap<>();

        int numExperiments = 100;

        for (int i = 0; i < numExperiments; i++) {

            Board board = new Board();

            System.out.println("Before shuffle:");
            board.printClean();

            board.shuffle(10);

            System.out.println("After shuffle:");
            board.printClean();

            System.out.println("Is solved? " + board.isSolved());
            

            System.out.println("Before A*:");
            board.printClean();

            int[] result = AStar.solve(board);

            System.out.println("After A*:");
            board.printClean();

            MapTrack.trackResult(finalMap, result);
        }

        System.out.println("Results:");
        System.out.println(finalMap);

        System.out.println("\nAverages:");
        MapTrack.calcAverages(finalMap);
    }
}



