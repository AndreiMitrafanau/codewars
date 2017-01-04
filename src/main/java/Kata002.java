import java.util.Arrays;
import java.util.stream.Stream;

public class Kata002 {
    public static String[] streetFighterSelection(String[][] fighters, int[] position, String[] moves) {
        int[] currentPosition = Arrays.copyOf(position, position.length);
        Stream<int[]> moveTrace = Arrays.stream(moves).map(move -> {
            if (move.equalsIgnoreCase("UP")) {
                return doUp(currentPosition);
            } else if (move.equalsIgnoreCase("DOWN")) {
                return doDown(currentPosition);
            } else if (move.equalsIgnoreCase("LEFT")) {
                return doLeft(currentPosition);
            } else if (move.equalsIgnoreCase("RIGHT")) {
                return doRight(currentPosition);
            } else throw new RuntimeException("Move name is invalid!");
        });

        return mapToName(fighters, moveTrace);
    }

    protected static String[] mapToName(String[][] fighters, Stream<int[]> moveTrace) {
        return moveTrace.map(x -> fighters[x[0]][x[1]]).toArray(String[]::new);
    }

    protected static int[] doUp(int[] currentPosition) {
        if (currentPosition[0] != 0) currentPosition[0] = 0;
        return currentPosition;
    }


    protected static int[] doDown(int[] currentPosition) {
        if (currentPosition[0] == 0) currentPosition[0] = 1;
        return currentPosition;
    }

    protected static int[] doLeft(int[] currentPosition) {
        if (currentPosition[1] == 0) currentPosition[1] = 5;
        else currentPosition[1] = currentPosition[1] - 1;
        return currentPosition;
    }

    protected static int[] doRight(int[] currentPosition) {
        if (currentPosition[1] == 5) currentPosition[1] = 0;
        else currentPosition[1] = currentPosition[1] + 1;
        return currentPosition;
    }
}


