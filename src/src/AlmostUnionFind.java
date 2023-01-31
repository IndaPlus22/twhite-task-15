import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AlmostUnionFind {

    static HashMap<Integer, HashSet> setsMap = new HashMap();
    static ArrayList<HashSet> rootsMap = new ArrayList<HashSet>();
    static int n, m;

    public static void main(String[] args) throws IOException {

        System.out.println("Please input shit");

        Scanner sc = new Scanner(System.in);
        //make input into an arraylist of chars that has no spaces
        ArrayList<String> commands = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.next();
            String[] lineArray = line.split(" ");
            for (String s : lineArray) {
                commands.add(s);
            }
        }

        System.out.println(commands);

        int n = Integer.parseInt(commands.get(0));
        //remove first element in the arraylist
        commands.remove(0);
        int m = Integer.parseInt(commands.get(0));
        commands.remove(0);

        //createMaps();
        operations(commands);
    }

    static void createMaps() {
        for (int i = 1; i <= n; i++) {
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            setsMap.put(i, set);
        }
        for (int i = 1; i <= n; i++) {
            rootsMap.add(setsMap.get(i));
        }
    }

    static void operations(ArrayList<String> commands) throws IOException {
        int p, q = 0;
        while (!commands.isEmpty()) {
            int operation = Integer.parseInt(commands.get(0));
            commands.remove(0);
            switch (operation) {
                case 1 -> {
                    p = Integer.parseInt(commands.get(0));
                    commands.remove(0);
                    q = Integer.parseInt(commands.get(0));
                    commands.remove(0);
                    System.out.println("case 1: " + p + " " + q + " " + commands);
                    //union(p, q);
                }
                case 2 -> {
                    p = Integer.parseInt(commands.get(0));
                    commands.remove(0);
                    q = Integer.parseInt(commands.get(0));
                    commands.remove(0);
                    System.out.println("case 2: " + p + " " + q + " " + commands);

                    //move(p, q);
                }
                case 3 -> {
                    p = Integer.parseInt(commands.get(0));
                    commands.remove(0);
                    //ArrayList<Integer> sumEle = sumAndELe(p);
                    //System.out.println(sumEle.get(0) + " " + sumEle.get(1));
                }
                default -> {
                }
            }

        }
    }
        

    static void union(int p, int q){
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
    static void move(int p, int q){
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

   static ArrayList<Integer> sumAndELe(int p) {
        ArrayList<Integer> sumAndEle = new ArrayList<>();
        int sum = 0;
        int ele = 0;

       for (int i = 0; i < rootsMap.size(); i++) {
           if (rootsMap.get(i).contains(p)) {
               Iterator<Integer> it = setsMap.get(i).iterator();
               while (it.hasNext()) {
                   sum += it.next();
               }
               sumAndEle.add(sum);
               ele = setsMap.size();
               sumAndEle.add(ele);
           }
       }
       return sumAndEle;
   }
}
