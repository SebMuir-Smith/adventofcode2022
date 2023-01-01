import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day16Part2 {

    static HashMap<String, Node> nodes = new HashMap<>();

    static ArrayList<Node> offNodesProd = new ArrayList<>();
    static ArrayList<Node> offNodes = new ArrayList<>();

    static HashMap<String, Integer> cache;
    static int TIMER_LIM = 30;
    public static void main(String[] args){
        Stream<String> lines = input.lines();
        List<Node> nodeStream = lines.map(Node::new).toList();
        nodeStream.forEach(node -> nodes.put(node.name, node));
        nodeStream.forEach(node -> node.addDistances(nodes.size()));
        nodeStream.stream().filter(node -> node.flowRate > 0).forEach(node -> offNodesProd.add(node));
        offNodesProd.sort(Node::compareTo);
        offNodes = offNodesProd;
        int highScore = getBestScore();
        System.out.println(highScore);
        TIMER_LIM = 26;
        int highScore2 = getElephantBestScore();
        System.out.println(highScore2);
    }

    public static int getElephantBestScore(){
        ArrayList<Node> humanNodes = new ArrayList<>();
        ArrayList<Node> elephantNodes = new ArrayList<>();
        boolean trash;
        int humanScore;
        int elephantScore;
        int maxScore = -1;
        int iterations = (int) Math.pow(2, offNodes.size());
        int nNodes = offNodesProd.size();
        boolean isHuman;
        for (int iteration = 0; iteration < iterations; iteration++){

            for (int itemN = 0; itemN < nNodes; itemN++){
                isHuman = (((iteration >>> itemN) & 1) != 0);
                trash = isHuman ? humanNodes.add(offNodesProd.get(itemN)) : elephantNodes.add(offNodesProd.get(itemN));
            }
            offNodes = humanNodes;
            humanScore = getBestScore();
            offNodes = elephantNodes;
            elephantScore = getBestScore();
            maxScore = Math.max(humanScore + elephantScore, maxScore);
            humanNodes.clear();
            elephantNodes.clear();
            System.out.println((iteration + 1) + "/" + iterations + ":" + (maxScore));
        }

        return maxScore;
    }

    public static int DFSAddOptions(Node destNode, int distance, int currentPressure, int currentPressureRate, int currentTime){
        // step 1: move to destination
        int destTime = currentTime + distance;

        if (destTime +2 > TIMER_LIM){
            return currentPressure + (currentPressureRate) * (TIMER_LIM - currentTime + 1);
        }
        while (currentTime < destTime){
            currentPressure += currentPressureRate;
            currentTime++;
        }

        // step 2: turn on
        currentTime++;
        currentPressure += currentPressureRate;
        currentPressureRate += destNode.flowRate;

        // step 3: get hash and check cache
        /*String hash = getCacheHash(destNode.name, currentPressureRate, currentTime);
        Integer lastBest = cache.get(hash);
        if (lastBest != null){
            if (lastBest >= currentPressure){
                return lastBest;
            }
            else{
                // update max and continue
                cache.replace(hash, lastBest, currentPressure);
            }
        }else{
            cache.put(hash, currentPressure);
        }*/


        // step 3: explore dests
        if (offNodes.size() != 0) {
            int maxScore = -1;
            int newScore;
            Node toNode;
            for (int nodeN = 0; nodeN < offNodes.size(); nodeN++){
                // Take the destination out of the pool as we're about to visit it
                toNode = offNodes.remove(nodeN);

                newScore = DFSAddOptions(toNode, destNode.getDistance(toNode), currentPressure, currentPressureRate, currentTime);
                maxScore = Math.max(newScore, maxScore);

                // Add the destiniation to the pool as we're going back up the stack
                offNodes.add(nodeN, toNode);

            }

            return maxScore;
        }else {
            return currentPressure + (currentPressureRate) * (TIMER_LIM - currentTime + 1);
        }

    }

    static String getCacheHash(String currentNodeName, int currentPressureRate, int time){
        return currentNodeName + "," + currentPressureRate + "," + time;
    }


    public static int getBestScore(){
        cache = new HashMap<>();
        Node current = nodes.get("AA");
        int maxScore = -1;
        int newScore;
        Node toNode;
        for (int nodeN = 0; nodeN < offNodes.size(); nodeN++){
            // Take the destination out of the pool as we're about to visit it
            //System.out.println((nodeN + 1) + "/" + offNodes.size());
            toNode = offNodes.remove(nodeN);

            newScore = DFSAddOptions(toNode, current.getDistance(toNode), 0, 0, 1);
            maxScore = Math.max(newScore, maxScore);

            // Add the destiniation to the pool as we're going back up the stack
            offNodes.add(nodeN, toNode);
        }
        return maxScore;
    }


    static class Node implements Comparable<Node>{
        String name;
        private String[] tunnelNames;
        Node[] tunnels;
        int flowRate;
        HashMap<Node, Integer> distances;

        public Node(String line){
            Matcher m = Pattern.compile("Valve (.*) has flow rate=(.*); tunnels* leads* to valves* (.*)").matcher(line);
            m.find();
            name = m.group(1);
            flowRate = Integer.parseInt(m.group(2));
            tunnelNames = m.group(3).replaceAll(" ", "").split(",");
        }

        public int getDistance(Node nodeNameTo){
            return distances.get(nodeNameTo);
        }

        @Override
        public int compareTo(Node o) {
            return -Integer.compare(flowRate, o.flowRate);
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
                        currentNode.node.tunnelNames) {
                    Node destNode = nodes.get(nodeName);
                    if (!distances.containsKey(destNode)){
                        distances.put(destNode, currentNode.distance + 1);
                        nodeQueue.add( new DistancePair(nodes.get(nodeName), currentNode.distance+1));
                    }
                }
            }
            distances.remove(name);
            distances.put(this, 0);
        }

        @Override
        public String toString() {
            return "Node{" +
                   "name='" + name + '\'' +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return name.equals(node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
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

    static final String input = """
            Valve QZ has flow rate=0; tunnels lead to valves IR, FA
            Valve FV has flow rate=0; tunnels lead to valves AA, GZ
            Valve GZ has flow rate=0; tunnels lead to valves FV, PO
            Valve QL has flow rate=0; tunnels lead to valves MR, AA
            Valve AA has flow rate=0; tunnels lead to valves QL, GQ, EV, FV
            Valve SQ has flow rate=23; tunnel leads to valve ZG
            Valve PK has flow rate=8; tunnels lead to valves MN, GN, WF, TY, CX
            Valve GQ has flow rate=0; tunnels lead to valves AA, MT
            Valve TI has flow rate=22; tunnels lead to valves GM, CS
            Valve JU has flow rate=17; tunnels lead to valves TT, RR, UJ, JY
            Valve YD has flow rate=7; tunnels lead to valves AT, ZS, BS
            Valve YB has flow rate=0; tunnels lead to valves EA, MW
            Valve FA has flow rate=0; tunnels lead to valves QZ, JT
            Valve TN has flow rate=0; tunnels lead to valves ZS, PO
            Valve MW has flow rate=0; tunnels lead to valves YB, YL
            Valve XN has flow rate=0; tunnels lead to valves VL, VM
            Valve MN has flow rate=0; tunnels lead to valves PK, TT
            Valve IP has flow rate=9; tunnels lead to valves YC, SA, CH, PI
            Valve PD has flow rate=0; tunnels lead to valves YZ, VM
            Valve ZS has flow rate=0; tunnels lead to valves TN, YD
            Valve PC has flow rate=0; tunnels lead to valves MR, XT
            Valve VM has flow rate=13; tunnels lead to valves CX, XN, PD
            Valve PO has flow rate=4; tunnels lead to valves GZ, TN, SA, XT, BM
            Valve GN has flow rate=0; tunnels lead to valves PK, YL
            Valve YL has flow rate=5; tunnels lead to valves MT, YZ, GN, SU, MW
            Valve IR has flow rate=6; tunnels lead to valves LK, PI, BM, QZ, EV
            Valve GM has flow rate=0; tunnels lead to valves TI, RH
            Valve CS has flow rate=0; tunnels lead to valves UJ, TI
            Valve EA has flow rate=18; tunnels lead to valves VL, YB, WF, JY
            Valve LK has flow rate=0; tunnels lead to valves IR, MR
            Valve BM has flow rate=0; tunnels lead to valves IR, PO
            Valve JZ has flow rate=0; tunnels lead to valves RH, RR
            Valve SA has flow rate=0; tunnels lead to valves IP, PO
            Valve XT has flow rate=0; tunnels lead to valves PO, PC
            Valve YC has flow rate=0; tunnels lead to valves IP, IL
            Valve RH has flow rate=15; tunnels lead to valves WJ, JZ, GM
            Valve CH has flow rate=0; tunnels lead to valves IP, BS
            Valve JY has flow rate=0; tunnels lead to valves EA, JU
            Valve TY has flow rate=0; tunnels lead to valves WJ, PK
            Valve WJ has flow rate=0; tunnels lead to valves TY, RH
            Valve IL has flow rate=0; tunnels lead to valves YC, MR
            Valve BS has flow rate=0; tunnels lead to valves YD, CH
            Valve AT has flow rate=0; tunnels lead to valves YD, UX
            Valve UJ has flow rate=0; tunnels lead to valves CS, JU
            Valve VL has flow rate=0; tunnels lead to valves EA, XN
            Valve JT has flow rate=21; tunnels lead to valves ZG, FA
            Valve UX has flow rate=10; tunnel leads to valve AT
            Valve RR has flow rate=0; tunnels lead to valves JZ, JU
            Valve TT has flow rate=0; tunnels lead to valves JU, MN
            Valve MT has flow rate=0; tunnels lead to valves GQ, YL
            Valve EV has flow rate=0; tunnels lead to valves AA, IR
            Valve ZG has flow rate=0; tunnels lead to valves JT, SQ
            Valve WF has flow rate=0; tunnels lead to valves EA, PK
            Valve YZ has flow rate=0; tunnels lead to valves PD, YL
            Valve MR has flow rate=3; tunnels lead to valves LK, IL, QL, SU, PC
            Valve PI has flow rate=0; tunnels lead to valves IR, IP
            Valve CX has flow rate=0; tunnels lead to valves VM, PK
            Valve SU has flow rate=0; tunnels lead to valves YL, MR""";
}
