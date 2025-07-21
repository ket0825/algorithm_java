/*
시간 제한	메모리 제한
2 초	128 MB
문제
조규현과 백승환은 터렛에 근무하는 직원이다. 하지만 워낙 존재감이 없어서 인구수는 차지하지 않는다. 다음은 조규현과 백승환의 사진이다.

이석원은 조규현과 백승환에게 상대편 마린(류재명)의 위치를 계산하라는 명령을 내렸다. 조규현과 백승환은 각각 자신의 터렛 위치에서 현재 적까지의 거리를 계산했다.

조규현의 좌표
$(x_1, y_1)$와 백승환의 좌표
$(x_2, y_2)$가 주어지고, 조규현이 계산한 류재명과의 거리
$r_1$과 백승환이 계산한 류재명과의 거리
$r_2$가 주어졌을 때, 류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수
$T$가 주어진다. 각 테스트 케이스는 다음과 같이 이루어져 있다.

한 줄에 공백으로 구분 된 여섯 정수
$x_1$,
$y_1$,
$r_1$,
$x_2$,
$y_2$,
$r_2$가 주어진다.

출력
각 테스트 케이스마다 류재명이 있을 수 있는 위치의 수를 출력한다. 만약 류재명이 있을 수 있는 위치의 개수가 무한대일 경우에는
$-1$ 출력한다.

제한
 
$-10\,000 ≤ x_1, y_1, x_2, y_2 ≤ 10\,000$ 
 
$1 ≤ r_1, r_2 ≤ 10\,000$ 
예제 입력 1
3
0 0 13 40 0 37
0 0 3 0 7 4
1 1 1 1 1 5
예제 출력 1
2
1
0

3
0 0 5 0 0 6
0 0 10 0 0 10
1 1 5 4 5 5

3
1 0 1 5 0 5
1 0 1 6 0 9
1 0 5 6 0 5

 */

import java.util.*;

public class Turret1002 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        ArrayDeque<Integer> numList = new ArrayDeque<Integer>();
        int x1, y1, r1, x2, y2, r2;
        int T = Integer.parseInt(sc.nextLine());
        for (int t = 0; t < T; t++){
            st = new StringTokenizer(sc.nextLine());
            while (st.hasMoreTokens()) {
                numList.add(Integer.parseInt(st.nextToken()));
            }
            x1 = numList.remove();
            y1 = numList.remove();
            r1 = numList.remove();
            x2 = numList.remove();
            y2 = numList.remove();
            r2 = numList.remove();

            if (x1 == x2 && y1 == y2) {
                if (r1 == r2) {
                    System.out.println(-1); // 답이 무한개.
                } else {
                    System.out.println(0); // 답이 0.
                }
            } else {
                double distance = Math.sqrt(Math.pow(Math.abs(x2-x1), 2) + Math.pow(Math.abs(y2-y1), 2));
                if (r1+r2 == distance ||  Math.abs(r2 - r1) == distance) {
                    System.out.println(1);
                } else if (r1 + r2 < distance || Math.abs(r2 - r1) > distance) {
                    System.out.println(0);
                } else {
                    System.out.println(2);
                }
            }
            numList.clear();
        }
    }
}
