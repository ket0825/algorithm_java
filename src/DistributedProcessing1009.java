/*
문제
재용이는 최신 컴퓨터 10대를 가지고 있다.

어느 날 재용이는 많은 데이터를 처리해야 될 일이 생겨서

각 컴퓨터에 1번부터 10번까지의 번호를 부여하고,

10대의 컴퓨터가 다음과 같은 방법으로 데이터들을 처리하기로 하였다.

1번 데이터는 1번 컴퓨터, 2번 데이터는 2번 컴퓨터, 3번 데이터는 3번 컴퓨터,

10번 데이터는 10번 컴퓨터, 11번 데이터는 1번 컴퓨터, 12번 데이터는 2번 컴퓨터

총 데이터의 개수는 항상 a^b개의 형태로 주어진다.

재용이는 문득 마지막 데이터가 처리될 컴퓨터의 번호가 궁금해졌다.

이를 수행해주는 프로그램을 작성하라.

입력
입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.

그 다음 줄부터 각각의 테스트 케이스에 대해 정수 a와 b가 주어진다.
(1 ≤ a < 100, 1 ≤ b < 1,000,000)

출력
각 테스트 케이스에 대해 마지막 데이터가 처리되는 컴퓨터의 번호를 출력한다.

예제 입력 1
5
1 6
3 7
6 2
7 100
9 635

5
100 1000000
1 1
1 10000000
100 1
99 999999

5
98 5
12 8
9 11
8 12
7 13


예제 출력 1
1
7
6
1
9
*/

import java.io.*;

public class DistributedProcessing1009 {
    public static void main(String args[]) throws IOException {
        InputStreamReader inStream = new InputStreamReader(System.in);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);
        BufferedReader br = new BufferedReader(inStream);

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i ++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int res = 1;
            if (a % 10 == 0) {
                res = 10;
            } else {
                // 가장 끝자리만 중요하기에 b를 곱할 때마다 나머지 연산 진행
                // 단, int의 최대 자리인 21억 이전까지인 100 * 00 00 00 이므로 4씩 곱해가며 진행.
                int bRemainder = Math.floorMod(b, 4); // b의 4에 해당하는 나머지.
                int bQuotient = Math.floorDiv(b, 4);
                for (int j = 0; j < bQuotient; j++) {
                    res *= (int) Math.pow(a, 4); // a 4번 곱하기
                    res = Math.floorMod(res, 10); // 다시 10의 나머지 구하기
                }
                if (bRemainder != 0) {
                    res *= (int) Math.pow(a, bRemainder); // 나머지만큼 곱하기
                    res = Math.floorMod(res, 10); // 다시 10의 나머지 구하기
                }
            }
            bw.write(String.valueOf(res) +"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
* 출처: hyun7914.
* 제일 깔끔.
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();

		int[][] P= {{0},
					{1},
					{2,4,8,6},
					{3,9,7,1},
					{4,6},
					{5},
					{6},
					{7,9,3,1},
					{8,4,2,6},
					{9,1}};

		for(int i=0;i<T;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			a%=10;
			if(a==0) {
				System.out.println(10);
			}else {
				int index=(b-1)%P[a].length;
				System.out.println(P[a][index]);
			}
			}

		}
	}



*/