/*
* 농장 관리 다국어

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	128 MB	6888	3163	2558	46.383%
문제
농부 민식이가 관리하는 농장은 N×M 격자로 이루어져 있다. 민식이는 농장을 관리하기 위해 산봉우리마다 경비원를 배치하려 한다. 이를 위해 농장에 산봉우리가 총 몇 개 있는지를 세는 것이 문제다.

산봉우리의 정의는 다음과 같다. 산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합으로 이루어져 있다. 여기서 "인접하다"의 정의는 X좌표 차이와 Y좌표 차이가 모두 1 이하인 경우이다. 또한 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야한다.

문제는 격자 내에 산봉우리의 개수가 총 몇 개인지 구하는 것이다.

입력
첫째 줄에 정수 N(1 < N ≤ 100), M(1 < M ≤ 70)이 주어진다. 둘째 줄부터 N+1번째 줄까지 각 줄마다 격자의 높이를 의미하는 M개의 정수가 입력된다. 격자의 높이는 500보다 작거나 같은 음이 아닌 정수이다.

출력
첫째 줄에 산봉우리의 개수를 출력한다.

예제 입력 1
8 7
4 3 2 2 1 0 1
3 3 3 2 1 0 1
2 2 2 2 1 0 0
2 1 1 1 1 0 0
1 1 0 0 0 1 0
0 0 0 1 1 1 0
0 1 2 2 1 1 0
0 1 1 1 2 1 0
예제 출력 1
3
* */

// 더 좋은 방법 존재: 탐색하다가 중간에 return을 안하면 됨.
import java.util.*;
import java.math.*;
import java.io.*;

public class FarmManaging1245 {
    static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int N;
    static int M;
    static int[][] mountain;
    static boolean[][] visited;
    static final LinkedList<int[]> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();
        mountain = new int[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                mountain[r][c] = sc.nextInt();
            }
            sc.nextLine();
        }

        // 전체 확인
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!visited[r][c] && isTop(r, c)) answer+=1;
            }
        }
        System.out.println(answer);
    }

    public static boolean isTop(int startR, int startC) {
        int curHeight = mountain[startR][startC];
        visited[startR][startC] = true;
        q.add(new int[]{startR, startC});
//        System.out.println("탐색 시작: " + startR + ", " + startC + ", 높이: " + curHeight);
        int[] temp;
        int r, c, nr, nc;

        // 일단 같은 거 다 넣고, visited 처리하고, 그 다음 주위를 체크하기.
        Set<int[]> checkSet = new HashSet<>();
        while (!q.isEmpty()) {
            temp = q.poll();
            r = temp[0]; c = temp[1];
            checkSet.add(new int[] {r,c});
            for (int idx = 0; idx < 8; idx++) {
                nr = r + dx[idx]; nc = c + dy[idx];
                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                    if (mountain[nr][nc] == curHeight && !visited[nr][nc]) {
//                        System.out.println("추가: " + nr + ", " + nc + ", 높이: " + curHeight + ", 다음 높이: " + mountain[nr][nc]);
                        visited[nr][nc] = true;
                        q.add(new int[] {nr, nc});
                    }
                }
            }
        }

        for (int[] rc: checkSet) {
            r = rc[0]; c = rc[1];
            for (int idx = 0; idx < 8; idx++) {
                nr = r + dx[idx]; nc = c + dy[idx];
                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
//                    System.out.println("확인: " + nr + ", " + nc + ", 높이: " + curHeight + ", 다음 높이: " + mountain[nr][nc]);
                    if (mountain[nr][nc] > curHeight) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
