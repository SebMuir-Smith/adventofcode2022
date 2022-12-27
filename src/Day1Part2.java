import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Day1Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader("inputs/day1.txt"));

        int tempAccumulator = 0;
        int firstAccumulator = Integer.MIN_VALUE;
        int secondAccumulator = Integer.MIN_VALUE;
        int thirdAccumulator = Integer.MIN_VALUE;
        String line;
        while (file.hasNextLine()){
            line = file.nextLine();
            if (line.compareTo("") == 0){
                if (tempAccumulator >= firstAccumulator){
                    thirdAccumulator = secondAccumulator;
                    secondAccumulator = firstAccumulator;
                    firstAccumulator = tempAccumulator;
                }
                else if (tempAccumulator >= secondAccumulator){
                    thirdAccumulator = secondAccumulator;
                    secondAccumulator = tempAccumulator;
                }
                else if (tempAccumulator >= thirdAccumulator){
                    thirdAccumulator = tempAccumulator;
                }
                tempAccumulator = 0;
            }else{
                tempAccumulator += Integer.parseInt(line);
            }
        }

        System.out.println(firstAccumulator + secondAccumulator + thirdAccumulator);

    }
}