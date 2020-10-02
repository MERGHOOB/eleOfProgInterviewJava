
import java.util.*;

public class MazeSearch {


    public static List<Coordinate> search(final List<List<Color>> maze, //2-D array
                                          Coordinate s,
                                          Coordinate e
    ) {

        List<Coordinate> path = new ArrayList<>();

        maze.get(s.x).set(s.y, Color.BLACK); // to make something visited paint it black
        path.add(s);

        if (!searchMazeWithDFS(maze, s, e, path)) {
            path.remove(path.size() - 1); // remove the last one
        }

        return path; // empty path means no path
    }

    private static boolean searchMazeWithDFS(List<List<Color>> maze, Coordinate cur, Coordinate e, List<Coordinate> path) {

        if (cur.equals(e)) { // override the equal method for Coordinate
            return true;
        }

        final int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] s : shift) {

            Coordinate next = new Coordinate(cur.x + s[0], cur.y + s[1]);
            if (!isFeasible(next, maze)) {
                continue;
            }

            maze.get(next.x).set(next.y, Color.BLACK);
            path.add(next);
            if (searchMazeWithDFS(maze, next, e, path)) {
                return true;
            }
            path.remove(path.size() - 1);

        }
        return false;

    }

    private static boolean isFeasible(Coordinate v, List<List<Color>> maze) {
        if (v.x < 0 || v.x >= maze.size() || v.y < 0 || v.y >= maze.get(v.x).size() || maze.get(v.x).get(v.y) == Color.BLACK) {
            return false;
        }
        return true;

    }


    private static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate) o;
            if (x != that.x || y != that.y) {
                return false;
            }

            return true;
        }


    }

}


enum Color {
    WHITE, BLACK
}
