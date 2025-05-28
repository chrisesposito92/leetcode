package medium;

import java.util.*;

class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        
        // Build adjacency lists for both trees
        List<List<Integer>> tree1 = buildTree(n, edges1);
        List<List<Integer>> tree2 = buildTree(m, edges2);
        
        // Count nodes within distance k from each node in tree1
        int[] count1 = new int[n];
        for (int i = 0; i < n; i++) {
            count1[i] = countNodesWithinDistance(tree1, i, k);
        }
        
        // Find max nodes reachable within distance k-1 from any node in tree2
        int maxInTree2 = 0;
        if (k > 0) {
            for (int i = 0; i < m; i++) {
                maxInTree2 = Math.max(maxInTree2, countNodesWithinDistance(tree2, i, k - 1));
            }
        }
        
        // For each node in tree1, the answer is nodes reachable in tree1 + max reachable in tree2
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = count1[i] + maxInTree2;
        }
        
        return result;
    }
    
    private List<List<Integer>> buildTree(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }
    
    private int countNodesWithinDistance(List<List<Integer>> tree, int start, int maxDist) {
        if (maxDist < 0) return 0;
        
        boolean[] visited = new boolean[tree.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        
        int count = 0;
        int dist = 0;
        
        while (!queue.isEmpty() && dist <= maxDist) {
            int size = queue.size();
            count += size;
            
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int neighbor : tree.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            dist++;
        }
        
        return count;
    }
}
