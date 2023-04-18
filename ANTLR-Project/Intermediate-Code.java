import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {
    
static ArrayList<Integer> executedBlocks = new ArrayList<Integer>();
static HashMap<Integer, Integer> checkedBlocks = new HashMap<>();
static ArrayList<Integer> order = new ArrayList<>();

public static void main(String[] args) {
checkedBlocks.put(0, 0);
order.add(0);
 /* Block number 0 */
    if(checkedBlocks.get(0) == null || checkedBlocks.get(0) == 0){
       checkedBlocks.put(0, 2);
    }
    executedBlocks.add(0);
    checkedBlocks.put(1, 0);
    order.add(1);

        int x = 0;
        int y = 1;

        
if (x==0) {
if (checkedBlocks.get(1) == 0 ||  checkedBlocks.get(1) == 1) {
checkedBlocks.put(1, 2);
}
} else { 
if (checkedBlocks.get(1) == 2) {
checkedBlocks.put(1, 1);
}
}

if (y==4) {
if (checkedBlocks.get(1) == 0 ||  checkedBlocks.get(1) == 1) {
checkedBlocks.put(1, 2);
}
} else { 
if (checkedBlocks.get(1) == 2) {
checkedBlocks.put(1, 1);
}
}
if (x == 0 || y == 4) { /* Block number 1 */
    if(checkedBlocks.get(1) == null || checkedBlocks.get(1) == 0){
       checkedBlocks.put(1, 2);
    }
    executedBlocks.add(1);
    checkedBlocks.put(2, 0);
    order.add(2);

            y = 10;
            for (int i = 0; i < 20; i++){ /* Block number 2 */
    if(checkedBlocks.get(2) == null || checkedBlocks.get(2) == 0){
       checkedBlocks.put(2, 2);
    }
    executedBlocks.add(2);
    checkedBlocks.put(3, 0);
    order.add(3);


            }
        } else { /* Block number 3 */
    if(checkedBlocks.get(3) == null || checkedBlocks.get(3) == 0){
       checkedBlocks.put(3, 2);
    }
    executedBlocks.add(3);
    checkedBlocks.put(4, 0);
    order.add(4);

            if (true) { /* Block number 4 */
    if(checkedBlocks.get(4) == null || checkedBlocks.get(4) == 0){
       checkedBlocks.put(4, 2);
    }
    executedBlocks.add(4);
    checkedBlocks.put(5, 0);
    order.add(5);

                x++;
            }
            y = 1;
        }
        if (true) { /* Block number 5 */
    if(checkedBlocks.get(5) == null || checkedBlocks.get(5) == 0){
       checkedBlocks.put(5, 2);
    }
    executedBlocks.add(5);
    checkedBlocks.put(6, 0);
    order.add(6);

            y++;
        }
        int a [] = {1, 2, 3};
        for (int i = 0; i < 6; i++){ /* Block number 6 */
    if(checkedBlocks.get(6) == null || checkedBlocks.get(6) == 0){
       checkedBlocks.put(6, 2);
    }
    executedBlocks.add(6);
    checkedBlocks.put(7, 0);
    order.add(7);


        }
        if (true) { /* Block number 7 */
    if(checkedBlocks.get(7) == null || checkedBlocks.get(7) == 0){
       checkedBlocks.put(7, 2);
    }
    executedBlocks.add(7);
    checkedBlocks.put(8, 0);
    order.add(8);

            x--;
        }
    
    int lastKey = order.remove(order.size() - 1);
    checkedBlocks.remove(lastKey);
    for (int i = 0; i < lastKey; i++) {
        if (checkedBlocks.get(i) == null) {
            checkedBlocks.put(i, 0);
        }
    }

    String outputFileName = "Visited-Blocks.txt";
    try (FileWriter fileWriter = new FileWriter(outputFileName)) {
        int previous = executedBlocks.get(0);
        int count = 1;
        for (int i = 1; i < executedBlocks.size(); i++) {
            if (executedBlocks.get(i) == previous) {
                count++;
            } else {
                if(count == 1){
                    fileWriter.write("Block number " + previous + " is visited only once\n");
                } else {
                    fileWriter.write("Block number " + previous + " is visited " + count + " times\n");
                }
                count = 1;
            }
            previous = executedBlocks.get(i);
        }
        if(count == 1){
            fileWriter.write("Block number " + previous + " is visited only once\n");
        } else {
            fileWriter.write("Block number " + previous + " is visited " + count + " times\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
            FileOutputStream fileOut = new FileOutputStream("Blocks-Hash-Map.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(checkedBlocks);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in Blocks-Hash-Map.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
/* Hence, there are 6 code blocks */
