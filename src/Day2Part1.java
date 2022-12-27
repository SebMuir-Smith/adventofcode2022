import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Day2Part1 {

    static int aOffset = Character.getNumericValue('A');
    static int xOffset = Character.getNumericValue('X');

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader("inputs/day2.txt"));


        String line;
        int accumulatedScore = 0;
        while (file.hasNextLine()){
            line = file.nextLine();
            accumulatedScore += getScore(line.charAt(0), line.charAt(2));
        }

        System.out.println(accumulatedScore);
        System.out.println(getScore('B','X'));
        System.out.println(getScore('C','Y'));
        System.out.println(getScore('C','X'));
    }

    public static int getScore(char opp, char yours){
        int yoursInt = Character.getNumericValue(yours) - xOffset;
        int oppInt = Character.getNumericValue(opp) - aOffset;



        return (Math.floorMod(yoursInt - oppInt,3) == 2 ? 0 : ((Math.floorMod(yoursInt - oppInt,3) + 1)*3)) + yoursInt + 1;
    }
}