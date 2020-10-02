

import java.util.*;

public class PaintABooleanMatrix {



		public void flipColor(List<List<Boolean>> matrix, int x, int y) {


			int [] [] dirs = {{0,1}, {1,0}, {-1,0}, {0,-1}};

			boolean color = matrix.get(x).get(y);

			matrix.get(x).set(y, !color); //flip the color

			Queue<Coordinate> queue = new LinkedList<>();
			queue.add(new Coordinate(x, y));

			while(!queue.isEmpty()) {

					Coordinate cur = queue.element();
			
					for(int []dir : dirs) {
						Coordinate next  = new Coordinate(x + dir[0], y + dir[1]);
						if(next.x < 0 || next.x >= matrix.size() || next.y < 0 || next.y >= matrix.get(x).size() || matrix.get(x).get(y) != color) {
							continue;
						}
						//flips the color and add to queue
						matrix.get(x).set(y, !color);
						queue.add(next);

					}


			}

		}

    private static class Coordinate {

        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x; this.y = y;
        }

    }

}




