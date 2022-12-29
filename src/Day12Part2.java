import java.util.ArrayList;
import java.util.List;

public class Day12Part2 {
    private static int[][] mapData;
    private static Vertex[][] vertices;

    private static ArrayList<Vertex> queue = new ArrayList<>();
    private static List<String> lines;
    static int rows;
    static int cols;
    static int startRow;
    static int startCol;
    static int endRow;
    static int endCol;

    static class Vertex implements Comparable<Vertex>{
        public int distance = Integer.MAX_VALUE-2;
        public int row;
        public int col;
        public boolean isEnd;

        public boolean removed = false;
        public Vertex previous;
        public int mapHeight;

        public Vertex(int row, int col){
            this.row = row;
            this.col = col;
            mapHeight = getMapHeight();
        }

        private ArrayList<Vertex> getNeighbours(){
            ArrayList<Vertex> output = new ArrayList<>();
            if (row + 1 < rows){
                output.add(vertices[row+1][col]);
            }
            if (row - 1 > -1){
                output.add(vertices[row-1][col]);
            }
            if (col + 1 < cols){
                output.add(vertices[row][col + 1]);
            }
            if (col - 1 > -1){
                output.add(vertices[row][col -1]);
            }

            return output;
        }

        public void updateAdjacentNeighbours(){
            ArrayList<Vertex> neighbours = getNeighbours();
            for (Vertex v: neighbours
                 ) {
                if (!v.removed && (mapHeight) < (v.mapHeight+2)){
                    if (v.distance > distance + 1){
                        v.distance = distance + 1;
                        v.previous = this;
                    }
                }
            }
        }

        private int getMapHeight(){
            return mapData[row][col];
        }


        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(distance, o.distance);
        }

        public void addMaps(char[][] screen, char prev){
            screen[row][col] = prev;
            char icon = '.';
            if (previous == null){
                return;
            }else{
                if (previous.row > row){
                    icon = '^';
                }
                else if (previous.row < row){
                    icon = 'v';
                }
                else if (previous.col < col){
                    icon = '>';
                }
                else if (previous.col > col){
                    icon = '<';
                }
            }
           previous.addMaps(screen, icon);
        }
    }
    public static void main(String[] args){
        lines = input.lines().toList();
        rows = lines.size();
        cols = lines.get(0).length();
        parseMap();
        fillVertices();

        Vertex curr;

        do{
            queue.sort(Vertex::compareTo);
            curr = queue.remove(0);
            curr.removed = true;
            if (curr.distance == Integer.MAX_VALUE-2 || curr.mapHeight == 'a'){
                break;
            }
            curr.updateAdjacentNeighbours();
        }
        while (!queue.isEmpty());

        System.out.println(curr.distance);
        printPath(curr);
    }

    private static void printPath(Vertex end){
        char[][] screen = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                screen[row][col] = '.';
            }
        }

        end.addMaps(screen, 'E');
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(screen[row][col]);
            }
            System.out.print("\n");
        }
    }

    private static void fillVertices(){
        Vertex v;
        vertices = new Vertex[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                v = new Vertex(row, col);
                vertices[row][col] = v;
                queue.add(v);
            }
        }

        vertices[startRow][startCol].distance = 0;
        vertices[endRow][endCol].isEnd = true;

    }

    private static void parseMap(){
        mapData = new int[rows][cols];

        String line;
        char currChar;
        for (int row = 0; row < rows; row++){
            line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                currChar = line.charAt(col);
                if (currChar == 'E'){
                    startCol = col;
                    startRow = row;
                    mapData[row][col] = 'z';
                }
                else if (currChar == 'S'){
                    endCol = col;
                    endRow = row;
                    mapData[row][col] = 'a';
                }
                else{
                    mapData[row][col] = (int) currChar;
                }
            }
        }
    }

    private static final String testInput = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi""";

    private static final String input = """
            abccccaaaaaaacccaaaaaaaccccccccccccccccccccccccccccccccccaaaa
            abcccccaaaaaacccaaaaaaaaaaccccccccccccccccccccccccccccccaaaaa
            abccaaaaaaaaccaaaaaaaaaaaaaccccccccccccccccccccccccccccaaaaaa
            abccaaaaaaaaaaaaaaaaaaaaaaacccccccccaaaccccacccccccccccaaacaa
            abaccaaaaaaaaaaaaaaaaaacacacccccccccaaacccaaaccccccccccccccaa
            abaccccaaaaaaaaaaaaaaaacccccccccccccaaaaaaaaaccccccccccccccaa
            abaccccaacccccccccaaaaaacccccccccccccaaaaaaaacccccccccccccccc
            abcccccaaaacccccccaaaaaaccccccccijjjjjjaaaaaccccccaaccaaccccc
            abccccccaaaaacccccaaaacccccccciiijjjjjjjjjkkkkkkccaaaaaaccccc
            abcccccaaaaacccccccccccccccccciiiirrrjjjjjkkkkkkkkaaaaaaccccc
            abcccccaaaaaccccccccccccccccciiiirrrrrrjjjkkkkkkkkkaaaaaccccc
            abaaccacaaaaacccccccccccccccciiiqrrrrrrrrrrssssskkkkaaaaacccc
            abaaaaacaaccccccccccccccccccciiiqqrtuurrrrrsssssskklaaaaacccc
            abaaaaacccccccccccaaccccccccciiqqqttuuuurrssusssslllaaccccccc
            abaaaaaccccccccaaaaccccccccciiiqqqttuuuuuuuuuuusslllaaccccccc
            abaaaaaacccccccaaaaaaccccccciiiqqqttxxxuuuuuuuusslllccccccccc
            abaaaaaaccccaaccaaaaacccccchhiiqqtttxxxxuyyyyvvsslllccccccccc
            abaaacacccccaacaaaaaccccccchhhqqqqttxxxxxyyyyvvsslllccccccccc
            abaaacccccccaaaaaaaacccccchhhqqqqtttxxxxxyyyvvssqlllccccccccc
            abacccccaaaaaaaaaaccaaacchhhpqqqtttxxxxxyyyyvvqqqlllccccccccc
            SbaaacaaaaaaaaaaaacaaaaahhhhppttttxxEzzzzyyvvvqqqqlllcccccccc
            abaaaaaaacaaaaaacccaaaaahhhppptttxxxxxyyyyyyyvvqqqlllcccccccc
            abaaaaaaccaaaaaaaccaaaaahhhppptttxxxxywyyyyyyvvvqqqmmcccccccc
            abaaaaaaacaaaaaaacccaaaahhhpppsssxxwwwyyyyyyvvvvqqqmmmccccccc
            abaaaaaaaaaaaaaaacccaacahhhpppssssssswyyywwvvvvvqqqmmmccccccc
            abaaaaaaaacacaaaacccccccgggppppsssssswwywwwwvvvqqqqmmmccccccc
            abcaaacaaaccccaaaccccccccgggppppppssswwwwwrrrrrqqqmmmmccccccc
            abcaaacccccccccccccccccccgggggpppoosswwwwwrrrrrqqmmmmddcccccc
            abccaacccccccccccccccccccccgggggoooosswwwrrrnnnmmmmmddddccccc
            abccccccccccccccccccccccccccgggggooossrrrrrnnnnnmmmddddaccccc
            abaccccaacccccccccccccccccccccgggfoossrrrrnnnnndddddddaaacccc
            abaccaaaaaaccccccccccccccccccccgffooorrrrnnnneeddddddaaaacccc
            abaccaaaaaacccccccccccccccccccccfffooooonnnneeeddddaaaacccccc
            abacccaaaaaccccccccaaccaaaccccccffffoooonnneeeeccaaaaaacccccc
            abcccaaaaacccccccccaaccaaaaccccccffffoooneeeeeaccccccaacccccc
            abaccaaaaaccccccccaaaacaaaaccccccafffffeeeeeaaacccccccccccccc
            abacccccccccccccccaaaacaaacccccccccffffeeeecccccccccccccccaac
            abaaaacccccccaaaaaaaaaaaaaacccccccccfffeeeccccccccccccccccaaa
            abaaaacccccccaaaaaaaaaaaaaaccccccccccccaacccccccccccccccccaaa
            abaacccccccccaaaaaaaaaaaaaaccccccccccccaacccccccccccccccaaaaa
            abaaaccccccccccaaaaaaaaccccccccccccccccccccccccccccccccaaaaaa""";
}
