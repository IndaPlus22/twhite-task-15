import java.util.*;

public class AlmostUnionFind {

    static int n, m;
    static Kattio io = new Kattio(System.in, System.out);

    private HashMap<Integer, HashSet> setsMap;
    private ArrayList<HashSet> rootsMap;

    public AlmostUnionFind(int n) {
        setsMap = new HashMap();
        rootsMap = new ArrayList<HashSet>();

        for (int i = 1; i <= n; i++) {
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            setsMap.put(i, set);
        }        
        for (int i = 1; i <= n; i++) {
            rootsMap.add(setsMap.get(i));
        }

    }

    public static void main(String[] args) {

        n = io.getInt();
        m = io.getInt();

        AlmostUnionFind almostUnionFind = new AlmostUnionFind(n);

        for (int i = 0; i < m; i++) {
            int operation = io.getInt();
            int p = io.getInt();
            int q = io.getInt();
            switch (operation) {
                case 1 -> {
                    almostUnionFind.union(p, q);
                }
                case 2 -> {
                    almostUnionFind.move(p, q);
                }
                case 3 -> {
                    int z = io.getInt();
                    int sum = almostUnionFind.sum(z);
                    int count = almostUnionFind.count(z);
                    System.out.println(count + " " + sum);
                    io.println(count + " " + sum);
                }
                default -> {
                }
            }
        }
        io.close();
    }    

    public void union(int p, int q){
        /*
        * 1. Find the root of p and q
        * 2. If the roots are the same, do nothing
        * 3. If the roots are different, merge the sets by adding all elements from the set with highest root to the set with the lowest root
        * 4. Remove the set with the highest root
        * 5. Update the rootsMap so the root of the set with the highest root is the same as the root of the set with the lowest root
        */
        for (int i = 0; i < rootsMap.size(); i++) {
            if(rootsMap.get(i).contains(p) && rootsMap.get(i).contains(q)){
                return;
            }
            if (rootsMap.get(i).contains(p)) {
                for (int j = 0; j < rootsMap.size(); j++) {
                    if (rootsMap.get(j).contains(q)) {
                        if (i == j) {
                            return;
                        } else {
                            if (i < j) {
                                setsMap.get(i).addAll(setsMap.get(j));
                                setsMap.remove(j);
                            } else {
                                setsMap.get(j).addAll(setsMap.get(i));
                                setsMap.remove(i);
                            }
                        }
                    }
                }
            }
        }

    }

    /* 
     * 1. Find the root of p and q
     * 2. If the roots are the same, do nothing
     * 3. If the roots are different, move p to the set with q
     * 4. If the set with q only contains q, remove the set with q
     * 5. Update the rootsMap so the root of the set with q is the same as the root of the set with p
    */
    public void move(int p, int q){
        for (int i = 0; i < rootsMap.size(); i++) {
            if(rootsMap.get(i).contains(p) && rootsMap.get(i).contains(q)){
                return;
            }
            if (rootsMap.get(i).contains(p)) {
                for (int j = 0; j < rootsMap.size(); j++) {
                    if (rootsMap.get(j).contains(q)) {
                        if (i == j) {
                            return;
                        } else {
                            setsMap.get(i).remove(p);
                            setsMap.get(j).add(p);
                            if (setsMap.get(j).size() == 1) {
                                setsMap.remove(j);
                            }
                        }
                    }
                }
            }
        }
   }

   public int sum(int p){
        int sum = 0;
        for (int i = 0; i < rootsMap.size(); i++) {
            if (rootsMap.get(i).contains(p)) {
            Iterator<Integer> it = setsMap.get(i).iterator();
            while (it.hasNext()) {
                    sum += it.next();
            }
            }
        }
        return sum;
   }

   public int count(int p){
        int elements = 0;
        for (int i = 0; i < rootsMap.size(); i++) {
            if (rootsMap.get(i).contains(p)) {
                elements = setsMap.get(i).size();
            }
        }
        return elements;
   }
}
