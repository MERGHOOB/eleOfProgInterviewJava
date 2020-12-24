import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DutchFlag {


    /**
     * only values present here is 0,1,2
     */
    private void partition(List<Integer> values, int index) {

        if (index < 0 || index >= values.size()) {
            return;
        }


        int smaller = 0, equal = 0, larger = values.size();
        while (equal < larger) {


            if (values.get(equal) < values.get(index)) {
                Collections.swap(values, smaller++, equal++);
            } else if (values.get(equal).equals(values.get(index))) {
                equal++;
            } else {
                Collections.swap(values, equal, --larger);
            }

        }

    }

    public static void main(String[] args) {


        DutchFlag df = new DutchFlag();

        Integer[] arr = {0, 1, 2, 0, 2, 1, 1};

        int index = 1;
        df.partition(Arrays.asList(arr), index);

        for (int v : arr) {
            System.out.print(v + " - ");
        }
        System.out.println();


    }


}