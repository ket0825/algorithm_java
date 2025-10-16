/*
노드사이의 거리

시간 제한	메모리 제한
2 초	128 MB
문제
N개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

입력
첫째 줄에 노드의 개수
N과 거리를 알고 싶은 노드 쌍의 개수
M이 입력되고 다음
N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에는 거리를 알고 싶은
M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.

출력
M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

제한
2≤N≤1000
1≤M≤1000
트리 상에 연결된 두 점과 거리는 10000 이하인 자연수이다.
트리 노드의 번호는 1부터 N까지 자연수이며, 두 노드가 같은 번호를 갖는 경우는 없다.

예제 입력 1
4 2
2 1 2
4 3 2
1 4 3
1 2
3 2
예제 출력 1
2
7

예제 입력 2
4 2
1 2 3
1 3 3
1 4 3
4 1
4 3
예제 출력 2
3
6
 */

// DFS

import java.util.*;
import java.io.*;
import java.math.*;

public class DistanceBetweenNodes1240{

    public static class Node {
        public int adj;
        public int distance;

        Node(int adj, int dist) {
            this.adj = adj;
            this.distance = dist;
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        boolean[] visited = new boolean[N+1];
        sc.nextLine();

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int d = sc.nextInt();
            graph.get(a).add(new Node(b, d));
            graph.get(b).add(new Node(a, d));
            sc.nextLine();
        }

        while (M-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            visited[start] = true;
            System.out.println(getNext(start, end, 0, visited, graph));
            visited[start] = false;
            sc.nextLine();
        }
    }

    public static int getNext(int current, int dest, int total, boolean[] visited, List<List<Node>> graph) {
//        System.out.println("현재 위치: " + current);
        if (current == dest) {
            return total;
        }

        for (Node node: graph.get(current)) {
            if(!visited[node.adj]) {
                visited[node.adj] = true;
                int res = getNext(node.adj, dest, total + node.distance, visited, graph);
                visited[node.adj] = false;
                if (res > 0) {
                    return res;
                }
            }
        }
        return -1;
    }
}

