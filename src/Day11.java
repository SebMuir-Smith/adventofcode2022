import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day11 {

    private static List<Monkey> monkeys = new ArrayList<>();
    private static List<String> lines;
    private static final int step = 7;
    private static long mod;

    public static void main(String[] args){
        lines = input.lines().toList();
        IntStream.iterate(0, i -> i + step).takeWhile(i -> (i + step-2) < lines.size()).forEach(Day11::addMonkey);
        mod = monkeys.stream().map(Monkey::getTestModulo).reduce(1, (a, b) -> a*b);
        IntStream.range(0, 10000).forEach(__ -> monkeys.forEach(Monkey::handleTurn));
        long output = monkeys.stream().map(Monkey::getInspections)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1L, (a, b) -> a * b);
        System.out.println(output);
    }

    private static void addMonkey(int start){
        monkeys.add(new Monkey(lines, start));
    }

    public static void throwItem(Long item, int destination){
        monkeys.get(destination).addItem(item);
    }

    private static class Monkey{
        private ArrayList<Long> items;
        private String operator;
        private boolean oldArgumentFlag = false;
        private int argumentInt;
        private int testModulo;
        private int trueMonkey;
        private int falseMonkey;
        private long inspections = 0;
        public long getInspections(){
            return inspections;
        }

        public int getTestModulo(){
            return testModulo;
        }
        public Monkey(List<String> lines, int lineStart){
            Matcher startingItemsM = Pattern.compile(".*Starting items: (.*)").matcher(lines.get(lineStart + 1));
            startingItemsM.find();
            items = new ArrayList<>(Arrays.stream(startingItemsM.group(1).split(", ")).map(Long::parseLong).toList());

            Matcher operationM = Pattern.compile(".*Operation: new = old (.) (.*)").matcher(lines.get(lineStart + 2));
            operationM.find();
            operator = operationM.group(1);
            String argument = operationM.group(2);
            if (argument.charAt(0) == 'o'){
                oldArgumentFlag = true;
            }
            else{
                argumentInt = Integer.parseInt(argument);
            }

            Matcher testM = Pattern.compile(".*Test: divisible by (.*)").matcher(lines.get(lineStart + 3));
            testM.find();
            testModulo = Integer.parseInt(testM.group(1));

            Matcher trueMonekyM = Pattern.compile(".*If true: throw to monkey (.*)").matcher(lines.get(lineStart + 4));
            trueMonekyM.find();
            trueMonkey = Integer.parseInt(trueMonekyM.group(1));

            Matcher falseMonekyM = Pattern.compile(".*If false: throw to monkey (.*)").matcher(lines.get(lineStart + 5));
            falseMonekyM.find();
            falseMonkey = Integer.parseInt(falseMonekyM.group(1));
        }

        public void addItem(Long item){
            items.add(item);
        }


        public void handleTurn(){
            long worry;
            for (int item = 0; item < items.size(); item++){
                worry = items.get(item);
                inspections++;
                if (operator.equals("*")){
                    if (oldArgumentFlag){
                        worry *= worry;
                    }else{
                        worry *= argumentInt;
                    }
                }else{
                    worry += argumentInt;
                }

                //worry = Math.floorDiv(worry, 3);
                worry = worry % mod;

                if (worry % testModulo == 0){
                    throwItem(worry, trueMonkey);
                }else{
                    throwItem(worry, falseMonkey);
                }
            }
            items.clear();
        }
    }

    static final String testInput = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                        
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
                        
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
                        
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1""";
    static final String input = """
            Monkey 0:
              Starting items: 89, 73, 66, 57, 64, 80
              Operation: new = old * 3
              Test: divisible by 13
                If true: throw to monkey 6
                If false: throw to monkey 2
                        
            Monkey 1:
              Starting items: 83, 78, 81, 55, 81, 59, 69
              Operation: new = old + 1
              Test: divisible by 3
                If true: throw to monkey 7
                If false: throw to monkey 4
                        
            Monkey 2:
              Starting items: 76, 91, 58, 85
              Operation: new = old * 13
              Test: divisible by 7
                If true: throw to monkey 1
                If false: throw to monkey 4
                        
            Monkey 3:
              Starting items: 71, 72, 74, 76, 68
              Operation: new = old * old
              Test: divisible by 2
                If true: throw to monkey 6
                If false: throw to monkey 0
                        
            Monkey 4:
              Starting items: 98, 85, 84
              Operation: new = old + 7
              Test: divisible by 19
                If true: throw to monkey 5
                If false: throw to monkey 7
                        
            Monkey 5:
              Starting items: 78
              Operation: new = old + 8
              Test: divisible by 5
                If true: throw to monkey 3
                If false: throw to monkey 0
                        
            Monkey 6:
              Starting items: 86, 70, 60, 88, 88, 78, 74, 83
              Operation: new = old + 4
              Test: divisible by 11
                If true: throw to monkey 1
                If false: throw to monkey 2
                        
            Monkey 7:
              Starting items: 81, 58
              Operation: new = old + 5
              Test: divisible by 17
                If true: throw to monkey 3
                If false: throw to monkey 5""";
}


