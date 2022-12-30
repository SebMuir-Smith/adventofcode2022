import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15Part1 {

    public static void main(String[] args){
        List<Sensor> sensors = input.lines().map(Sensor::new).toList();
        sensors.forEach(Sensor::updateMaximums);
        Sensor.calculateRanges();
        sensors.forEach(Sensor::drawRange);
        sensors.forEach(Sensor::drawBeacon);
        Sensor.displayScreen();
        System.out.println(Sensor.cantContain(2000000));
    }

    static class Sensor{
        static Integer xMin;
        static Integer xMax;
        static Integer yMin;
        static Integer yMax;

        static int xRange;
        static int yRange;

        static char[][] screen;

        int x;
        int y;

        int beaconX;
        int beaconY;

        int dist;
        public Sensor(String line){
            Matcher m = Pattern.compile("Sensor at x=(.+), y=(.+): closest beacon is at x=(.+), y=(.+)").matcher(line);
            m.find();
            x = Integer.parseInt(m.group(1));
            y = Integer.parseInt(m.group(2));

            beaconX = Integer.parseInt(m.group(3));
            beaconY = Integer.parseInt(m.group(4));

            dist = Math.abs(x - beaconX) + Math.abs(y - beaconY);
        }

        void drawRange(){
            for (int xDiff = -dist; xDiff <= dist; xDiff++){
                for (int yDiff = Math.abs(xDiff) - dist; yDiff <= dist - Math.abs(xDiff); yDiff++){
                    draw(x + xDiff, y + yDiff, '#');
                }
            }
        }

        void drawBeacon(){
            draw(beaconX, beaconY, 'B');
        }

        static int cantContain(int y){
            int counter = 0;
            for (int x = xMin; x <= xMax; x++){
                if (screen[(y - yMin)][(x - xMin)] == '#'){
                    counter++;
                }
            }
            return counter;
        }

        void updateMaximums() {
            xMin = xMin == null ? x - dist : Math.min(xMin, x - dist);
            xMax = xMax == null ? x + dist : Math.max(xMax, x + dist);

            yMin = yMin == null ? y - dist : Math.min(yMin, y - dist);
            yMax = yMax == null ? y + dist: Math.max(yMax, y + dist);
        }

        static void calculateRanges() {
            yRange = yMax - yMin + 1;
            xRange = xMax - xMin + 1;


            screen = new char[yRange][xRange];
            for (int row = 0; row < yRange; row++) {
                for (int col = 0; col < xRange; col++) {
                    screen[row][col] = '.';
                }
            }
        }

        static void displayScreen() {
            clrscr();
            for (int row = 0; row < yRange; row++) {
                for (int col = 0; col < xRange; col++) {
                    System.out.print(screen[row][col]);
                }
                System.out.print("\n");
            }
        }

        static void draw(int x, int y, char sym) {
            screen[(y - yMin)][(x - xMin)] = sym;
        }
    }

    static final String testInput = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3""";

    static final String input = """
            Sensor at x=13820, y=3995710: closest beacon is at x=1532002, y=3577287
            Sensor at x=3286002, y=2959504: closest beacon is at x=3931431, y=2926694
            Sensor at x=3654160, y=2649422: closest beacon is at x=3702627, y=2598480
            Sensor at x=3702414, y=2602790: closest beacon is at x=3702627, y=2598480
            Sensor at x=375280, y=2377181: closest beacon is at x=2120140, y=2591883
            Sensor at x=3875726, y=2708666: closest beacon is at x=3931431, y=2926694
            Sensor at x=3786107, y=2547075: closest beacon is at x=3702627, y=2598480
            Sensor at x=2334266, y=3754737: closest beacon is at x=2707879, y=3424224
            Sensor at x=1613400, y=1057722: closest beacon is at x=1686376, y=-104303
            Sensor at x=3305964, y=2380628: closest beacon is at x=3702627, y=2598480
            Sensor at x=1744420, y=3927424: closest beacon is at x=1532002, y=3577287
            Sensor at x=3696849, y=2604845: closest beacon is at x=3702627, y=2598480
            Sensor at x=2357787, y=401688: closest beacon is at x=1686376, y=-104303
            Sensor at x=2127900, y=1984887: closest beacon is at x=2332340, y=2000000
            Sensor at x=3705551, y=2604421: closest beacon is at x=3702627, y=2598480
            Sensor at x=1783014, y=2978242: closest beacon is at x=2120140, y=2591883
            Sensor at x=2536648, y=2910642: closest beacon is at x=2707879, y=3424224
            Sensor at x=3999189, y=2989409: closest beacon is at x=3931431, y=2926694
            Sensor at x=3939169, y=2382534: closest beacon is at x=3702627, y=2598480
            Sensor at x=2792378, y=2002602: closest beacon is at x=2332340, y=2000000
            Sensor at x=3520934, y=3617637: closest beacon is at x=2707879, y=3424224
            Sensor at x=2614525, y=1628105: closest beacon is at x=2332340, y=2000000
            Sensor at x=2828931, y=3996545: closest beacon is at x=2707879, y=3424224
            Sensor at x=2184699, y=2161391: closest beacon is at x=2332340, y=2000000
            Sensor at x=2272873, y=1816621: closest beacon is at x=2332340, y=2000000
            Sensor at x=1630899, y=3675405: closest beacon is at x=1532002, y=3577287
            Sensor at x=3683190, y=2619409: closest beacon is at x=3702627, y=2598480
            Sensor at x=180960, y=185390: closest beacon is at x=187063, y=-1440697
            Sensor at x=1528472, y=3321640: closest beacon is at x=1532002, y=3577287
            Sensor at x=3993470, y=2905566: closest beacon is at x=3931431, y=2926694
            Sensor at x=1684313, y=20931: closest beacon is at x=1686376, y=-104303
            Sensor at x=2547761, y=2464195: closest beacon is at x=2120140, y=2591883
            Sensor at x=3711518, y=845968: closest beacon is at x=3702627, y=2598480
            Sensor at x=3925049, y=2897039: closest beacon is at x=3931431, y=2926694
            Sensor at x=1590740, y=3586256: closest beacon is at x=1532002, y=3577287
            Sensor at x=1033496, y=3762565: closest beacon is at x=1532002, y=3577287""";

    public static void clrscr(){
        //Clears Screen in java
        System.out.println("\n\n\n\n\n\n\n");
    }
}
