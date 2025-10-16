/*
문제
N행 M열의 표 A가 있고, 표의 각 칸에는 숫자가 하나씩 적혀있다.

연두는 서로 다른 1개 이상의 칸을 선택하려고 하는데, 행의 번호가 선택한 순서대로 등차수열을 이루고 있어야 하고, 열의 번호도 선택한 순서대로 등차수열을 이루고 있어야 한다. 이렇게 선택한 칸에 적힌 수를 순서대로 이어붙이면 정수를 하나 만들 수 있다.

연두가 만들 수 있는 정수 중에서 가장 큰 완전 제곱수를 구해보자. 완전 제곱수란 어떤 정수를 제곱한 수이다.

입력
첫째 줄에 N, M이 주어진다. 둘째 줄부터 N개의 줄에는 표에 적힌 숫자가 1번 행부터 N번 행까지 순서대로 한 줄에 한 행씩 주어진다. 한 행에 적힌 숫자는 1번 열부터 M번 열까지 순서대로 주어지고, 공백없이 모두 붙여져 있다.

출력
첫째 줄에 연두가 만들 수 있는 가장 큰 완전 제곱수를 출력한다. 만약, 완전 제곱수를 만들 수 없는 경우에는 -1을 출력한다.

제한
1 ≤ N, M ≤ 9
표에 적힌 숫자는 0보다 크거나 같고, 9보다 작거나 같다.
예제 입력 1
2 3
123
456
예제 출력 1
64
만들 수 있는 세자리 수는 123, 321, 456, 654이다. 이 중 완전 제곱수는 없기 때문에 정답은 64가 된다.

예제 입력 2
5 5
00000
00000
00200
00000
00000
예제 출력 2
0
0은 완전 제곱수이고, 입력으로 주어진 표에서 만들 수 있는 가장 큰 완전 제곱수이다.

예제 입력 3
6 7
3791178
1283252
4103617
8233494
8725572
2937261
예제 출력 3
320356
모든 i번 행의 i번 열에 적힌 수를 이어붙이면 320356을 만들 수 있고, 이 수는 5662 = 320356 이다.

예제 입력 4
5 9
135791357
357913579
579135791
791357913
913579135
예제 출력 4
9
홀수 숫자 두 개로 끝나는 완전 제곱수는 없다. 따라서, 만들 수 있는 가장 큰 완전 제곱수는 9이다.

예제 입력 5
9 9
553333733
775337775
777537775
777357333
755553557
355533335
373773573
337373777
775557777
예제 출력 5
-1
3, 5, 7만을 이용해 완전 제곱수를 만들 수 없다.

예제 입력 6
9 9
257240281
197510846
014345401
035562575
974935632
865865933
684684987
768934659
287493867
예제 출력 6
95481
다음과 같이 굵게 표시된 숫자를 이어붙이면 95481을 만들 수 있다.

257240281
197510846
014345401
035562575
974935632
865865933
684684987
768934659
287493867
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class FindSquareNum1025{

    public static void main(String[] args) throws Exception{
        // 1. 시작 위치: 왼위, 왼아래, 오위, 오아래.
        // 2. 만들어진 숫자, 만들어진 숫자 뒤집는 경우.
        // 3. col별 0,1,2, row별 0,1,2를 이중 for문으로 처리하면 빠짐없이 처리 가능.
        // 4. 매 숫자는 square를 하여 정수인지 아닌지 확인하고, 이에 대한 최댓값 확인.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        int[][] board = new int[N][M];
        for (int n = 0; n < N; n++) {
            String temp = sc.nextLine();
            for (int m = 0; m < M; m++) {
                board[n][m] = Integer.parseInt(String.valueOf(temp.charAt(m)));
            }
        }

        int maxSquareNum = -1;
        maxSquareNum = getMaxSquare(board, maxSquareNum);
        // board 열 뒤집기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M/2; j++) {
                int temp = board[i][M-1-j];
                board[i][M-1-j] = board[i][j];
                board[i][j] = temp;
            }
        }
        maxSquareNum = getMaxSquare(board, maxSquareNum);
        // 행 뒤집기
        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < M; j++) {
                int temp = board[N-1-i][j];
                board[N-1-i][j] = board[i][j];
                board[i][j] = temp;
            }
        }
        maxSquareNum = getMaxSquare(board, maxSquareNum);
        // 다시 열 뒤집기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                int temp = board[i][M-1-j];
                board[i][M-1-j] = board[i][j];
                board[i][j] = temp;
            }
        }
        maxSquareNum = getMaxSquare(board, maxSquareNum);
        System.out.println(maxSquareNum);
    }

    public static void printBoard(int[][] board) {
        for (int n = 0; n < board.length; n++) {
            for (int m = 0; m < board[0].length; m++) {
                System.out.print(board[n][m]);
            }
            System.out.println();
        }
    }

    public static int calcMaxSquareNum(StringBuilder sb, int maxSquareNum) {
        double maxs = Math.sqrt(Double.parseDouble(sb.toString()));
        if (maxs == (int) maxs) {
            maxSquareNum = Math.max(Integer.parseInt(sb.toString()), maxSquareNum);
        }
        maxs = Math.sqrt(Double.parseDouble(sb.reverse().toString()));
        if (maxs == (int) maxs) {
            maxSquareNum = Math.max(Integer.parseInt(sb.toString()), maxSquareNum);
        }
        sb.reverse();
        return maxSquareNum;
    }

    public static int getMaxSquare(int[][] board, int maxSquareNum) {
        int N = board.length;
        int M = board[0].length;
        StringBuilder numberString = new StringBuilder();

        if (N == 1 && M == 1) {
            numberString.append(board[0][0]);
            maxSquareNum = calcMaxSquareNum(numberString, maxSquareNum);
        }

        // 0부터 시작 지점.
        for (int rStart = 0; rStart < N; rStart++) {
            for (int cStart = 0; cStart < M; cStart++) {
                // 간격 (0, 0부터 시작)
                for (int rStep = -rStart; rStep < N; rStep++) {
                    for (int cStep = -cStart; cStep < M; cStep++) {
                        if (rStep == 0 && cStep == 0) continue;
                        numberString = new StringBuilder();
                        // 시작
                        int r = rStart;
                        int c = cStart;
                        while (r < N && r > -1 && c < M && c > -1) {
                            if (rStep == 0 && cStep == 0) break;
                            numberString.append(board[r][c]);
                            if (!numberString.toString().isEmpty()) {
                                maxSquareNum = calcMaxSquareNum(numberString, maxSquareNum);
                            }
                            r+=rStep;
                            c+=cStep;
                        }
                    }
                }

            }
        }
        return maxSquareNum;
    }
}

/*
출처: f2410546  / 깔끔한 풀이
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];;
        for(int i=0; i<N; i++){
            String row = br.readLine().trim();
            for(int j=0; j<M; j++){
                grid[i][j]=row.charAt(j)-'0';
            }
        }
        int ans = -1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int dr = -N; dr <= N; dr++) {
                    for (int dc = -M; dc <= M; dc++) {
                        if (dr == 0 && dc == 0) continue;

                        int x = r, y = c;
                        int num = 0;
                        while (0 <= x && x < N && 0 <= y && y < M) {
                            int digit = grid[x][y];
                            num = num * 10 + digit;
                            if (isSquare(num)) ans = Math.max(ans, num);

                            x += dr;
                            y += dc;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
    static boolean isSquare(int n) {
        if (n < 0) return false; // 음수는 제곱수 X
        int root = (int) Math.sqrt(n);
        return root * root == n;
    }
}
*/