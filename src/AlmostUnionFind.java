import java.util.*;

@SuppressWarnings("unchecked")
public class AlmostUnionFind {

    static int n, m;
    static Kattio io = new Kattio(System.in, System.out);

    private HashMap<Integer, HashSet> rootsMap;

    public AlmostUnionFind(int n) {
        rootsMap = new HashMap<Integer, HashSet>();

        // Add each set to the rootsMap
        for (int i = 1; i <= n; i++) {
            HashSet<Integer> set = new HashSet<Integer>();
            set.add(i);
            rootsMap.put(i, set);
        }

    }

    public static void main(String[] args) {

        n = io.getInt();
        m = io.getInt();

        AlmostUnionFind almostUnionFind = new AlmostUnionFind(n);

        for (int i = 0; i < m; i++) {
            int operation = io.getInt();
            switch (operation) {
                case 1: {
                    int p = io.getInt();
                    int q = io.getInt();
                    almostUnionFind.union(p, q);
                    break;
                }
                case 2: {
                    int p = io.getInt();
                    int q = io.getInt();
                    almostUnionFind.move(p, q);
                    break;
                }
                case 3: {
                    int p = io.getInt();
                    int sum = almostUnionFind.sum(p);
                    int count = almostUnionFind.count(p);
                    io.println(count + " " + sum);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        io.close();
    }

    public void union(int p, int q){
            // If the roots of p and q are the same, do nothing
            if(rootsMap.get(p) == rootsMap.get(q)){
                return;
            }
            // Add the set with the largest root to the set with the smallest root
            // Make the largest root the key to the new set
            if (p < q) {
                rootsMap.get(p).addAll(rootsMap.get(q));
                rootsMap.put(q, rootsMap.get(p));
            } else {
                rootsMap.get(q).addAll(rootsMap.get(p));
                rootsMap.put(p, rootsMap.get(q));
            }

    }

    //Move p to the set containing q. If p and q are already in the same set, ignore this command
    public void move(int p, int q){
        // If the roots of p and q are the same, do nothing
        if(rootsMap.get(p) == rootsMap.get(q)){
            return;
        }
        rootsMap.get(q).add(p);
        rootsMap.get(p).remove(p);
        rootsMap.put(p, rootsMap.get(q));
   }

   public int sum(int p){
        int sum = 0;
        HashSet<Integer> set = rootsMap.get(p);
        for (int x : set) {
            sum += x;
        }
        return sum;
   }

   public int count(int p){
        int elements = 0;
        elements = rootsMap.get(p).size();
        return elements;
   }
}