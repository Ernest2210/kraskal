import java.util.ArrayList;

public class Kraskal {
    public static int countOfInterations = 0;

    private static void sortEdges(ArrayList<Integer[]> edges, int low, int high){
        if(edges.size() == 0){
            return;
        }
        if(low >= high){
            return;
        }

        int selectedIndex = (int)((Math.random() * (high - low + 1)) + low);
        int selectedWeight = edges.get(selectedIndex)[2];

        int i = low, j = high;
        while (i <= j) {
            while (edges.get(i)[2] < selectedWeight) {
                countOfInterations++;
                i++;
            }

            while (edges.get(j)[2] > selectedWeight) {
                j--;
                countOfInterations++;
            }

            if (i <= j) {//меняем местами
                Integer[] temp = edges.get(i);
                edges.set(i, edges.get(j));
                edges.set(j, temp);
                i++;
                j--;
            }
        }

        if (low < j)
            sortEdges(edges, low, j);
        if (high > i)
            sortEdges(edges, i, high);
    }

    public static ArrayList<Integer[]> getMinimalOstov(int countOfNode, ArrayList<Integer[]> edges){
        ArrayList<Integer[]> result = new ArrayList<>();
        DSU trees = new DSU(countOfNode); //N
        sortEdges(edges, 0, edges.size()-1); // N*log(N)
        for(int i = 0; i < edges.size(); i++){
            if(trees.get(edges.get(i)[0]) != trees.get(edges.get(i)[1])){
                result.add(edges.get(i)); //1...n
                trees.union(edges.get(i)[0], edges.get(i)[1]);//1...n
            }
        }
        return result;
    }
}
