import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    static ArrayDeque<String> commands = new ArrayDeque<>();
    static final int rows = 6;
    static final int cols = 40;
    static char[][] screen = new char[rows][cols];
    public static void main(String[] args) {
        input.lines().forEach(commands::addLast);

        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                screen[row][col] = '.';
            }
        }

        int cycleCounter = 1;
        int rowCounter = 0;
        int colCounter = 0;
        int x = 1;
        String currentCommand;
        Matcher m;
        int signalSum = 0;
        int signalStrength;
        while (!commands.isEmpty()){

            if (cycleCounter == 20 || ((cycleCounter -20) % 40 == 0)){
                signalStrength = cycleCounter * x;
                signalSum += signalStrength;
                System.out.println(cycleCounter + ", " + signalStrength);
            }

            if (colCounter == (x-1) || colCounter == (x) || colCounter == (x+1)){
                screen[rowCounter][colCounter] = '#';
            }

            currentCommand = commands.removeFirst();
            switch (currentCommand.substring(0,4)){
                case "noop":
                    break;
                case "addx":
                    commands.addFirst("2" + currentCommand); // 2nd-addx
                    break;
                case "2add":
                    m = Pattern.compile("2addx (.*)").matcher(currentCommand);
                    m.find();
                    x += Integer.parseInt(m.group(1));
                    break;
            }
            cycleCounter++;
            colCounter++;
            if (colCounter == cols){
                colCounter = 0;
                rowCounter++;
            }
        }
        System.out.println(signalSum);

        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                System.out.print(screen[row][col]);
            }
            System.out.print("\n");
        }
    }



static final String input2 = """
        addx 15
        addx -11
        addx 6
        addx -3
        addx 5
        addx -1
        addx -8
        addx 13
        addx 4
        noop
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx -35
        addx 1
        addx 24
        addx -19
        addx 1
        addx 16
        addx -11
        noop
        noop
        addx 21
        addx -15
        noop
        noop
        addx -3
        addx 9
        addx 1
        addx -3
        addx 8
        addx 1
        addx 5
        noop
        noop
        noop
        noop
        noop
        addx -36
        noop
        addx 1
        addx 7
        noop
        noop
        noop
        addx 2
        addx 6
        noop
        noop
        noop
        noop
        noop
        addx 1
        noop
        noop
        addx 7
        addx 1
        noop
        addx -13
        addx 13
        addx 7
        noop
        addx 1
        addx -33
        noop
        noop
        noop
        addx 2
        noop
        noop
        noop
        addx 8
        noop
        addx -1
        addx 2
        addx 1
        noop
        addx 17
        addx -9
        addx 1
        addx 1
        addx -3
        addx 11
        noop
        noop
        addx 1
        noop
        addx 1
        noop
        noop
        addx -13
        addx -19
        addx 1
        addx 3
        addx 26
        addx -30
        addx 12
        addx -1
        addx 3
        addx 1
        noop
        noop
        noop
        addx -9
        addx 18
        addx 1
        addx 2
        noop
        noop
        addx 9
        noop
        noop
        noop
        addx -1
        addx 2
        addx -37
        addx 1
        addx 3
        noop
        addx 15
        addx -21
        addx 22
        addx -6
        addx 1
        noop
        addx 2
        addx 1
        noop
        addx -10
        noop
        noop
        addx 20
        addx 1
        addx 2
        addx 2
        addx -6
        addx -11
        noop
        noop
        noop""";
    static final String input = """
noop
addx 25
addx -5
addx -14
addx 4
noop
addx 2
addx 3
noop
noop
noop
noop
addx 3
addx 5
addx 2
noop
noop
addx 5
noop
noop
noop
addx 1
addx 2
addx 5
addx -40
addx 5
noop
addx 26
addx -20
addx -3
addx 2
noop
addx -4
addx 9
addx 5
addx 2
addx 11
addx -10
addx 2
addx 5
addx 2
addx 5
noop
noop
noop
addx -31
addx 32
addx -37
addx 1
addx 8
addx 13
addx -15
addx 4
noop
addx 5
noop
addx 3
addx -2
addx 4
addx 1
addx 4
addx -14
addx 15
addx 4
noop
noop
noop
addx 3
addx 5
addx -40
noop
addx 5
addx 8
addx -3
noop
addx 2
addx 9
addx -4
noop
noop
noop
noop
addx 5
addx -9
addx 10
addx 4
noop
noop
addx 5
addx -19
addx 24
addx -2
addx 5
addx -40
addx 22
addx -19
addx 2
addx 5
addx 2
addx 5
noop
noop
addx -2
addx 2
addx 5
addx 3
noop
addx 2
addx 2
addx 3
addx -2
addx 10
addx -3
addx 3
noop
addx -40
addx 2
addx 11
addx -5
addx -1
noop
addx 3
addx 7
noop
addx -2
addx 5
addx 2
addx 3
noop
addx 2
addx 6
addx -5
addx 2
addx -18
addx 26
addx -1
noop
noop
noop
noop""";
}

