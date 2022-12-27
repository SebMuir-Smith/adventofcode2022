import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Day1Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader("inputs/day1.txt"));

        int tempAccumulator = 0;
        int maxAccumulator = Integer.MIN_VALUE;
        String line;
        while (file.hasNextLine()){
            line = file.nextLine();
            if (line.compareTo("") == 0){
                maxAccumulator = Math.max(tempAccumulator, maxAccumulator);
                tempAccumulator = 0;
            }else{
                tempAccumulator += Integer.parseInt(line);
            }
        }

        System.out.println(maxAccumulator);

    }
}