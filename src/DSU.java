

public class DSU {
    int[] parent;
    int[] rank;

    public DSU(int countOfNodes){
        parent = new int[countOfNodes];
        rank = new int[countOfNodes];
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int get(int x){
        if(parent[x] != x){
            parent[x] = get(parent[x]);
        }
        Kraskal.countOfInterations++;
        return parent[x];
    }

    public void union(int x, int y){
        x = get(x);
        y = get(y);

        if(x != y){
            if(rank[x] == rank[y]){
                rank[x]++;
            }
            if(rank[x] < rank[y]){
                parent[x] = y;
            }else{
                parent[y] = x;
            }
        }
    }

    public static void main(String[] args) {
        DSU dsu = new DSU(10);
        dsu.union(0,1);
        dsu.union(1,2);
        dsu.union(2,3);
        dsu.union(3,4);
        dsu.union(5,6);
        dsu.union(6,7);
        dsu.union(7,8);
        dsu.union(8,9);
        dsu.union(5, 0);
        for (int i = 0; i< dsu.parent.length; i++){
            System.out.println(dsu.parent[i]);

        }
        System.out.println();
        for (int i = 0; i< dsu.parent.length; i++){
            System.out.println(dsu.rank[i]);

        }
    }
}
