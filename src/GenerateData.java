import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GenerateData {
    private static final int COUNT_OF_DATA_SET = 100;
    private static final int MAX_EDGES = 10_000;
    private static final int MIN_EDGES = 100;
    private static final int MAX_WEIGHT = 100;

    public static void main(String[] args) {
        try(FileWriter file = new FileWriter("data.txt")){
            for (int i = 0; i < COUNT_OF_DATA_SET; i++) {
                int countOfEdges = (int)(Math.random() * (MAX_EDGES - MIN_EDGES) + MIN_EDGES);
                int maxNodes = (int)(countOfEdges+1);
                int minNodes = (int)((3 + Math.sqrt(1 + 8 * countOfEdges)) / 2)+1;
                int countOfNodes =  (int)(Math.random() * (maxNodes - minNodes) + minNodes);
                file.write("DataSet:" + countOfEdges + ":" + countOfNodes + "\n");
                Set<Set<Integer>> edges = new HashSet<>();
                while (edges.size() < countOfEdges){
                    int node1;
                    int node2;
                    do{
                        node1 = (int)(Math.random() * (countOfNodes-1));
                        node2 = (int)(Math.random() * (countOfNodes-1));
                    }while (node1 == node2);

                    Set<Integer> e = new HashSet<>();
                    e.add(node1);
                    e.add(node2);
                    edges.add(e);
                }
                for(Set<Integer> edge: edges){
                    StringBuilder line = new StringBuilder();
                    for (Integer num: edge) {
                        line.append(num + ":");
                    }
                    line.append((int)(Math.random() * MAX_WEIGHT + 1));
                    file.write(line.toString() + '\n');
                }
                System.out.println(i);
            }
            file.write("EOD");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
