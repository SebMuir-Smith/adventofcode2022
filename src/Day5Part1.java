import java.util.HashMap;
import java.util.List;
import java.util.Stack;
public class Day5Part1 {

    static HashMap<Integer, Stack<Character>> stacks;
    static Range letters = new Range('A', 'Z');
    static Range numbers = new Range('1', '9');
    public static void main(String[] args){
        List<String> lines = input.lines().toList();

        // init stacks
        int nStacks = (lines.get(0).length() + 1 )/4;
        stacks = new HashMap<>(nStacks);

        // populate stacks
        int lineCounter = 0;
        String line;
        char currentChar;
        boolean exit = false;
        int currentStack;
        while (!exit){
            line = lines.get(lineCounter);
            for (int charCounter = 0; charCounter < line.length(); charCounter++){
                currentChar = line.charAt(charCounter);
                if (numbers.contains(currentChar)){
                    exit = true;
                    break;
                }
                else if (letters.contains(currentChar)){
                    currentStack = Math.floorDiv(charCounter+1,4);
                    if (!stacks.containsKey(currentStack)){
                        stacks.put(currentStack, new Stack<>());
                    }
                    stacks.get(currentStack).push(currentChar);
                }
            }
            lineCounter++;
        }

        Stack<Character> flipSourceStack;
        for (int i = 0; i < nStacks; i++){
            Stack<Character> tempStack = new Stack<>();
            flipSourceStack = stacks.get(i);
            while (!flipSourceStack.isEmpty()){
                tempStack.push(flipSourceStack.pop());
            }
            stacks.replace(i, tempStack);
        }

        lineCounter++; // skip empty line
        int times;
        int source;
        int dest;
        Stack<Character> sourceStack;
        Stack<Character> destStack;
        for (int i = lineCounter; i < lines.size(); i++){
            line = lines.get(i);
            String[] split = line.split(" ");
            times = Integer.parseInt(split[1]);
            source = Integer.parseInt(split[3])-1;
            dest = Integer.parseInt(split[5])-1;
            sourceStack = stacks.get(source);
            destStack = stacks.get(dest);

            for (int j = 0; j < times; j++){
                destStack.push(sourceStack.pop());
            }
        }

        for (int i = 0; i < nStacks; i++){
            System.out.print(stacks.get(i).peek());
        }
        return;
    }


    static String input = """
                             [Q]         [N]             [N]   \s
                             [H]     [B] [D]             [S] [M]
                             [C]     [Q] [J]         [V] [Q] [D]
                             [T]     [S] [Z] [F]     [J] [J] [W]
                             [N] [G] [T] [S] [V]     [B] [C] [C]
                             [S] [B] [R] [W] [D] [J] [Q] [R] [Q]
                             [V] [D] [W] [G] [P] [W] [N] [T] [S]
                             [B] [W] [F] [L] [M] [F] [L] [G] [J]
                              1   2   3   4   5   6   7   8   9\s
                             
                             move 3 from 6 to 2
                             move 2 from 8 to 7
                             move 3 from 3 to 8
                             move 2 from 5 to 3
                             move 5 from 9 to 7
                             move 5 from 3 to 5
                             move 1 from 4 to 2
                             move 3 from 2 to 1
                             move 2 from 9 to 6
                             move 4 from 1 to 4
                             move 6 from 5 to 8
                             move 1 from 6 to 3
                             move 8 from 8 to 9
                             move 5 from 9 to 2
                             move 1 from 3 to 4
                             move 11 from 7 to 2
                             move 1 from 4 to 1
                             move 1 from 5 to 9
                             move 1 from 3 to 9
                             move 1 from 9 to 5
                             move 21 from 2 to 6
                             move 2 from 8 to 4
                             move 5 from 8 to 6
                             move 4 from 9 to 7
                             move 2 from 5 to 6
                             move 5 from 4 to 2
                             move 4 from 7 to 2
                             move 20 from 6 to 9
                             move 7 from 2 to 7
                             move 1 from 2 to 6
                             move 7 from 9 to 6
                             move 3 from 7 to 9
                             move 7 from 1 to 9
                             move 3 from 7 to 4
                             move 1 from 2 to 5
                             move 1 from 5 to 2
                             move 1 from 1 to 9
                             move 23 from 9 to 1
                             move 1 from 2 to 4
                             move 1 from 9 to 6
                             move 1 from 1 to 5
                             move 20 from 1 to 7
                             move 1 from 5 to 9
                             move 12 from 4 to 2
                             move 2 from 1 to 3
                             move 1 from 3 to 5
                             move 4 from 2 to 9
                             move 2 from 6 to 4
                             move 9 from 7 to 4
                             move 11 from 6 to 7
                             move 7 from 2 to 8
                             move 1 from 5 to 7
                             move 2 from 9 to 7
                             move 1 from 6 to 4
                             move 6 from 8 to 2
                             move 3 from 7 to 1
                             move 6 from 2 to 4
                             move 1 from 3 to 2
                             move 7 from 4 to 3
                             move 1 from 8 to 5
                             move 3 from 6 to 7
                             move 1 from 2 to 9
                             move 1 from 6 to 7
                             move 4 from 4 to 1
                             move 1 from 3 to 1
                             move 22 from 7 to 6
                             move 3 from 7 to 6
                             move 4 from 7 to 6
                             move 5 from 4 to 5
                             move 26 from 6 to 2
                             move 8 from 1 to 9
                             move 2 from 6 to 5
                             move 9 from 9 to 5
                             move 2 from 9 to 4
                             move 1 from 5 to 3
                             move 1 from 9 to 5
                             move 1 from 5 to 6
                             move 1 from 4 to 3
                             move 3 from 5 to 8
                             move 1 from 6 to 2
                             move 1 from 6 to 1
                             move 1 from 1 to 8
                             move 4 from 5 to 2
                             move 7 from 2 to 4
                             move 8 from 5 to 3
                             move 1 from 5 to 7
                             move 12 from 2 to 8
                             move 6 from 3 to 8
                             move 1 from 7 to 6
                             move 10 from 3 to 4
                             move 11 from 8 to 7
                             move 6 from 8 to 3
                             move 11 from 7 to 4
                             move 1 from 6 to 3
                             move 6 from 3 to 1
                             move 6 from 1 to 5
                             move 15 from 4 to 7
                             move 1 from 3 to 5
                             move 7 from 2 to 3
                             move 5 from 5 to 9
                             move 2 from 3 to 8
                             move 1 from 9 to 4
                             move 1 from 9 to 7
                             move 1 from 4 to 5
                             move 5 from 7 to 8
                             move 13 from 4 to 1
                             move 8 from 8 to 2
                             move 2 from 2 to 7
                             move 7 from 7 to 4
                             move 1 from 5 to 1
                             move 1 from 5 to 9
                             move 3 from 8 to 9
                             move 7 from 9 to 8
                             move 1 from 5 to 2
                             move 6 from 8 to 2
                             move 6 from 7 to 6
                             move 2 from 2 to 7
                             move 2 from 8 to 3
                             move 3 from 4 to 5
                             move 1 from 7 to 1
                             move 3 from 3 to 5
                             move 4 from 4 to 6
                             move 3 from 6 to 3
                             move 11 from 2 to 9
                             move 5 from 3 to 4
                             move 1 from 1 to 4
                             move 7 from 9 to 4
                             move 1 from 6 to 4
                             move 5 from 5 to 4
                             move 9 from 1 to 3
                             move 4 from 6 to 3
                             move 2 from 1 to 7
                             move 3 from 9 to 8
                             move 1 from 9 to 5
                             move 5 from 3 to 4
                             move 3 from 4 to 6
                             move 3 from 7 to 5
                             move 4 from 2 to 4
                             move 10 from 3 to 1
                             move 2 from 8 to 9
                             move 1 from 8 to 4
                             move 2 from 2 to 9
                             move 5 from 5 to 8
                             move 2 from 6 to 3
                             move 4 from 9 to 4
                             move 2 from 3 to 7
                             move 2 from 6 to 3
                             move 1 from 6 to 1
                             move 1 from 8 to 5
                             move 1 from 5 to 8
                             move 1 from 4 to 8
                             move 17 from 4 to 2
                             move 11 from 4 to 2
                             move 1 from 8 to 6
                             move 28 from 2 to 3
                             move 10 from 3 to 1
                             move 3 from 8 to 1
                             move 1 from 7 to 8
                             move 1 from 7 to 1
                             move 1 from 6 to 5
                             move 10 from 1 to 5
                             move 20 from 3 to 5
                             move 3 from 1 to 6
                             move 3 from 8 to 1
                             move 18 from 5 to 1
                             move 4 from 4 to 6
                             move 4 from 5 to 1
                             move 1 from 6 to 8
                             move 7 from 5 to 8
                             move 2 from 5 to 3
                             move 34 from 1 to 8
                             move 4 from 1 to 7
                             move 36 from 8 to 6
                             move 6 from 8 to 4
                             move 3 from 6 to 4
                             move 1 from 1 to 2
                             move 1 from 3 to 2
                             move 1 from 3 to 5
                             move 1 from 1 to 8
                             move 1 from 7 to 2
                             move 3 from 2 to 8
                             move 3 from 8 to 1
                             move 2 from 7 to 5
                             move 5 from 6 to 4
                             move 31 from 6 to 4
                             move 1 from 7 to 3
                             move 13 from 4 to 7
                             move 2 from 5 to 9
                             move 1 from 1 to 9
                             move 1 from 3 to 1
                             move 11 from 4 to 9
                             move 12 from 4 to 3
                             move 4 from 9 to 1
                             move 1 from 9 to 8
                             move 1 from 5 to 9
                             move 3 from 6 to 5
                             move 3 from 5 to 1
                             move 11 from 7 to 8
                             move 6 from 4 to 8
                             move 3 from 3 to 8
                             move 5 from 1 to 6
                             move 1 from 7 to 3
                             move 5 from 8 to 3
                             move 2 from 4 to 7
                             move 8 from 8 to 4
                             move 5 from 8 to 2
                             move 2 from 2 to 1
                             move 7 from 9 to 2
                             move 5 from 6 to 7
                             move 6 from 2 to 4
                             move 3 from 9 to 1
                             move 3 from 1 to 4
                             move 2 from 2 to 1
                             move 5 from 1 to 2
                             move 6 from 2 to 9
                             move 4 from 7 to 6
                             move 2 from 9 to 6
                             move 1 from 2 to 5
                             move 1 from 6 to 5
                             move 5 from 3 to 1
                             move 1 from 5 to 3
                             move 2 from 6 to 1
                             move 1 from 9 to 7
                             move 3 from 7 to 3
                             move 4 from 8 to 4
                             move 1 from 5 to 6
                             move 9 from 1 to 4
                             move 4 from 6 to 8
                             move 2 from 7 to 4
                             move 2 from 1 to 9
                             move 10 from 3 to 1
                             move 7 from 1 to 3
                             move 1 from 1 to 2
                             move 1 from 2 to 4
                             move 2 from 3 to 8
                             move 6 from 8 to 9
                             move 2 from 1 to 2
                             move 30 from 4 to 3
                             move 29 from 3 to 7
                             move 2 from 2 to 4
                             move 7 from 9 to 5
                             move 6 from 4 to 8
                             move 5 from 8 to 9
                             move 5 from 5 to 7
                             move 1 from 5 to 4
                             move 17 from 7 to 9
                             move 6 from 3 to 9
                             move 4 from 3 to 7
                             move 1 from 8 to 6
                             move 17 from 9 to 8
                             move 8 from 9 to 3
                             move 1 from 5 to 6
                             move 9 from 8 to 7
                             move 3 from 9 to 5
                             move 1 from 4 to 5
                             move 2 from 6 to 1
                             move 3 from 3 to 8
                             move 2 from 3 to 5
                             move 1 from 3 to 8
                             move 10 from 8 to 4
                             move 2 from 1 to 9
                             move 1 from 8 to 1
                             move 1 from 1 to 5
                             move 1 from 8 to 6
                             move 4 from 4 to 5
                             move 1 from 3 to 9
                             move 3 from 9 to 6
                             move 1 from 9 to 8
                             move 2 from 9 to 1
                             move 2 from 1 to 7
                             move 1 from 9 to 1
                             move 3 from 4 to 6
                             move 2 from 4 to 9
                             move 1 from 1 to 8
                             move 2 from 8 to 1
                             move 5 from 6 to 2
                             move 2 from 1 to 4
                             move 2 from 9 to 1
                             move 2 from 6 to 3
                             move 2 from 3 to 1
                             move 2 from 4 to 7
                             move 4 from 1 to 5
                             move 15 from 5 to 4
                             move 4 from 2 to 5
                             move 7 from 4 to 2
                             move 4 from 4 to 5
                             move 1 from 3 to 9
                             move 3 from 5 to 2
                             move 9 from 2 to 1
                             move 3 from 5 to 4
                             move 1 from 5 to 3
                             move 1 from 9 to 7
                             move 1 from 5 to 8
                             move 4 from 1 to 6
                             move 1 from 3 to 2
                             move 2 from 1 to 2
                             move 3 from 2 to 8
                             move 14 from 7 to 2
                             move 2 from 6 to 4
                             move 19 from 7 to 8
                             move 1 from 7 to 1
                             move 23 from 8 to 2
                             move 33 from 2 to 1
                             move 1 from 7 to 1
                             move 7 from 4 to 3
                             move 1 from 6 to 2
                             move 15 from 1 to 7
                             move 6 from 2 to 8
                             move 1 from 8 to 2
                             move 1 from 2 to 8
                             move 2 from 3 to 8
                             move 3 from 8 to 5
                             move 1 from 6 to 1
                             move 2 from 4 to 7
                             move 1 from 5 to 9
                             move 3 from 8 to 3
                             move 1 from 2 to 6
                             move 18 from 1 to 4
                             move 1 from 6 to 3
                             move 2 from 5 to 1
                             move 2 from 8 to 2
                             move 5 from 1 to 9
                             move 15 from 4 to 9
                             move 5 from 9 to 5
                             move 1 from 1 to 5
                             move 1 from 1 to 3
                             move 1 from 1 to 2
                             move 3 from 2 to 8
                             move 9 from 9 to 8
                             move 11 from 8 to 4
                             move 1 from 8 to 3
                             move 4 from 7 to 8
                             move 3 from 3 to 1
                             move 3 from 3 to 7
                             move 3 from 5 to 8
                             move 3 from 5 to 3
                             move 5 from 9 to 7
                             move 9 from 4 to 3
                             move 1 from 8 to 9
                             move 9 from 3 to 7
                             move 2 from 3 to 2
                             move 1 from 4 to 1
                             move 1 from 8 to 6
                             move 10 from 7 to 1
                             move 2 from 2 to 6
                             move 2 from 6 to 8
                             move 2 from 9 to 4
                             move 14 from 1 to 9
                             move 3 from 4 to 7
                             move 1 from 6 to 3
                             move 2 from 8 to 4
                             move 8 from 7 to 5
                             move 6 from 7 to 5
                             move 12 from 9 to 3
                             move 3 from 9 to 8
                             move 8 from 8 to 2
                             move 7 from 2 to 1
                             move 1 from 7 to 2
                             move 6 from 7 to 2
                             move 7 from 3 to 6
                             move 1 from 6 to 3
                             move 7 from 2 to 1
                             move 5 from 4 to 8
                             move 2 from 7 to 9
                             move 1 from 2 to 7
                             move 4 from 6 to 1
                             move 2 from 8 to 1
                             move 1 from 7 to 6
                             move 2 from 6 to 1
                             move 3 from 3 to 7
                             move 1 from 4 to 6
                             move 7 from 3 to 8
                             move 6 from 8 to 1
                             move 1 from 9 to 7
                             move 22 from 1 to 9
                             move 2 from 7 to 2
                             move 3 from 3 to 2
                             move 5 from 1 to 3
                             move 2 from 2 to 7
                             move 2 from 6 to 9
                             move 3 from 9 to 4
                             move 2 from 4 to 5
                             move 1 from 4 to 7
                             move 1 from 1 to 9
                             move 13 from 9 to 7
                             move 3 from 9 to 5
                             move 14 from 5 to 3
                             move 5 from 9 to 5
                             move 2 from 9 to 7
                             move 9 from 5 to 3
                             move 15 from 3 to 2
                             move 12 from 7 to 3
                             move 3 from 2 to 7
                             move 8 from 7 to 5
                             move 4 from 8 to 9
                             move 1 from 9 to 6
                             move 1 from 7 to 5
                             move 14 from 2 to 7
                             move 2 from 9 to 4
                             move 1 from 6 to 5
                             move 18 from 3 to 2
                             move 5 from 3 to 9
                             move 2 from 3 to 6
                             move 2 from 4 to 8
                             move 15 from 7 to 6
                             move 1 from 9 to 1
                             move 2 from 8 to 3
                             move 1 from 7 to 9
                             move 6 from 9 to 6
                             move 2 from 3 to 7
                             move 3 from 5 to 8
                             move 8 from 5 to 3
                             move 2 from 7 to 9
                             move 22 from 6 to 9
                             move 12 from 2 to 3
                             move 1 from 1 to 9
                             move 1 from 2 to 6
                             move 1 from 6 to 5
                             move 6 from 2 to 6
                             move 7 from 6 to 3
                             move 20 from 9 to 4
                             move 5 from 9 to 3
                             move 7 from 3 to 5
                             move 14 from 4 to 6
                             move 2 from 4 to 1
                             move 2 from 8 to 3
                             move 2 from 1 to 5
                             move 9 from 6 to 1
                             move 20 from 3 to 4
                             move 5 from 6 to 8
                             move 1 from 5 to 9
                             move 1 from 9 to 6
                             move 9 from 5 to 7
                             move 1 from 6 to 5
                             move 2 from 3 to 4
                             move 4 from 8 to 2
                             move 2 from 8 to 4
                             move 3 from 3 to 7
                             move 5 from 1 to 7
                             move 4 from 2 to 7
                             move 1 from 1 to 3
                             move 3 from 3 to 6
                             move 4 from 7 to 3
                             move 1 from 1 to 4
                             move 3 from 3 to 5
                             move 1 from 1 to 7
                             move 28 from 4 to 3
                             move 20 from 3 to 5
                             move 16 from 5 to 6
                             move 3 from 3 to 2
                             move 2 from 3 to 6
                             move 6 from 7 to 5
                             move 1 from 3 to 6
                             move 1 from 2 to 1
                             move 10 from 6 to 8
                             move 2 from 1 to 5
                             move 1 from 4 to 8
                             move 1 from 6 to 9
                             move 2 from 2 to 5
                             move 10 from 7 to 4
                             move 2 from 3 to 4
                             move 1 from 3 to 8
                             move 1 from 9 to 4
                             move 6 from 4 to 1
                             move 10 from 8 to 6
                             move 1 from 1 to 4
                             move 8 from 4 to 9
                             move 3 from 1 to 5
                             move 14 from 5 to 8
                             move 2 from 7 to 5
                             move 3 from 9 to 7
                             move 5 from 9 to 5
                             move 2 from 7 to 3
                             move 16 from 6 to 9
                             move 3 from 6 to 3
                             move 1 from 1 to 5
                             move 1 from 1 to 4
                             move 1 from 7 to 3
                             move 2 from 6 to 1
                             move 2 from 5 to 7
                             move 2 from 7 to 1
                             move 3 from 3 to 8
                             move 12 from 5 to 4
                             move 1 from 5 to 8
                             move 1 from 1 to 4
                             move 9 from 4 to 1
                             move 11 from 1 to 7
                             move 10 from 7 to 4
                             move 3 from 3 to 7
                             move 1 from 1 to 7
                             move 5 from 4 to 5
                             move 8 from 4 to 1
                             move 1 from 4 to 1
                             move 5 from 5 to 4
                             move 2 from 7 to 5
                             move 2 from 7 to 3
                             move 9 from 1 to 7
                             move 16 from 8 to 5
                             move 3 from 8 to 7
                             move 6 from 4 to 3
                             move 17 from 5 to 1
                             move 14 from 1 to 2
                             move 7 from 2 to 4
                             move 5 from 2 to 6""";
}
