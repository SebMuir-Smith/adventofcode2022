import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day16 {

    static HashMap<String, Node> nodes = new HashMap<>();
    static ArrayList<Node> offNodes = new ArrayList<>();
    public static void main(String[] args){
        Stream<String> lines = testInput.lines();
        List<Node> nodeStream = lines.map(Node::new).toList();
        nodeStream.forEach(node -> nodes.put(node.name, node));
        nodeStream.forEach(node -> node.addDistances(nodes.size()));
        nodeStream.stream().filter(node -> node.flowRate > 0).forEach(node -> offNodes.add(node));
        makeDecision();
        System.out.println();
    }

    public static void makeDecision(){
        ArrayList<Node> onNodes = new ArrayList<>();
        int currPressure = 0;
        int TIMER_LIM = 31;
        Node current = nodes.get("AA");
        int bestMoveScore = -1;
        Node bestDest = null;
        int onScore;
        int currMoveScore;
        int totalPressure = 0;
        for (int timer = 1; timer < TIMER_LIM; timer++){
            totalPressure += currPressure;
            printHeader(timer, onNodes, currPressure);
            onScore = current.calculateTurnOffScore(timer); // get score of staying
            bestMoveScore = Integer.MIN_VALUE;
            for (String dest:
                 current.tunnels) { // get the best destination and score of moving
                currMoveScore = current.calculateMoveScore(nodes.get(dest));
                if (bestMoveScore < currMoveScore){
                    bestMoveScore = currMoveScore;
                    bestDest = nodes.get(dest);
                }
            }
            if (onScore > bestMoveScore){
                currPressure += current.flowRate;
                current.on = true;
                onNodes.add(current);
                offNodes.remove(current);
                System.out.println("You open valve " + current.name + ".");
            }
            else{
                current = bestDest;
                System.out.println("You move to valve " + current.name + ".");
            }
            System.out.println();
        }

        System.out.println(totalPressure);
    }

    private static void printHeader(int minute, ArrayList<Node> onNodes, int currPressure){
        System.out.println("== Minute " + minute + " ==");
        if (onNodes.size() > 0){
            System.out.print("Valves ");
            for (Node n:
                 onNodes) {
                System.out.print(n.name + ",");
            }
            System.out.print(" are open, releasing " + currPressure +" pressure.\n");
        }
    }

    static class Node{
        String name;
        String[] tunnels;
        int flowRate;

        HashMap<String, Integer> distances;

        boolean on = false;
        public Node(String line){
            Matcher m = Pattern.compile("Valve (.*) has flow rate=(.*); tunnels* leads* to valves* (.*)").matcher(line);
            m.find();
            name = m.group(1);
            flowRate = Integer.parseInt(m.group(2));
            tunnels = m.group(3).replaceAll(" ", "").split(",");
        }

        public int calculateTurnOffScore(int timeCounter){
            return on || flowRate == 0 ? Integer.MIN_VALUE : flowRate * (timeCounter -1);
        }

        public int calculateMoveScore(Node to){
            if (to.name == this.name){
                return Integer.MIN_VALUE;
            }
            int score = 0;
            int moveChange;
            for (Node n:
                 offNodes) {
                if (n.name == this.name){
                    continue;
                }
                moveChange = getDistance(n.name) - to.getDistance(n.name);
                if (moveChange != 0){
                    System.out.println(n.name + " " + getDistance(n.name) + " vs " + to.getDistance(n.name) + ", " + n.flowRate);
                }
                score += n.flowRate * moveChange/getDistance(n.name);
            }
            System.out.println(name + " -> " + to.name + ": " + score);
            return score;
        }


        public int getDistance(String nodeNameTo){
            return distances.get(nodeNameTo);
        }

        record DistancePair(Node node, int distance){};

        void addDistances(int nNodes){
            distances = new HashMap<>(nNodes);
            ArrayDeque<DistancePair> nodeQueue = new ArrayDeque<>();
            nodeQueue.add(new DistancePair(this, 0));
            DistancePair currentNode;
            while (!nodeQueue.isEmpty()){
                currentNode = nodeQueue.remove();
                for (String nodeName:
                        currentNode.node.tunnels) {
                    if (!distances.containsKey(nodeName)){
                        distances.put(nodeName, currentNode.distance + 1);
                        nodeQueue.add( new DistancePair(nodes.get(nodeName), currentNode.distance+1));
                    }
                }
            }
            distances.remove(name);
            distances.put(name, 0);
        }
    }

    static final String testInput = """
            Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
            Valve BB has flow rate=13; tunnels lead to valves CC, AA
            Valve CC has flow rate=2; tunnels lead to valves DD, BB
            Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
            Valve EE has flow rate=3; tunnels lead to valves FF, DD
            Valve FF has flow rate=0; tunnels lead to valves EE, GG
            Valve GG has flow rate=0; tunnels lead to valves FF, HH
            Valve HH has flow rate=22; tunnel leads to valve GG
            Valve II has flow rate=0; tunnels lead to valves AA, JJ
            Valve JJ has flow rate=21; tunnel leads to valve II""";
}
