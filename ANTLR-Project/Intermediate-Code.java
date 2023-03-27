import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    
static ArrayList<Integer> executedBlocks = new ArrayList<Integer>();
public static void main(String[] args) { /* Block number 0 */
executedBlocks.add(0);
        int x = 0;
        int y;
        if (x == 0) { /* Block number 1 */
executedBlocks.add(1);
            y = 10;
        } else { /* Block number 2 */
executedBlocks.add(2);
            if (true) { /* Block number 3 */
executedBlocks.add(3);
                x++;
            }
            y = 1;
        }
        if (true) { /* Block number 4 */
executedBlocks.add(4);
            y++;
        }
        int a [] = {1, 2, 3};
        for (int i = 0; i < 6; i++){ /* Block number 5 */
executedBlocks.add(5);

        }
        if (true) { /* Block number 6 */
executedBlocks.add(6);
            x--;
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
}
}
/* Hence, there are 7 code blocks */
