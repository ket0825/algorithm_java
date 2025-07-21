/*
수열 정렬 스페셜 저지
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	128 MB	18764	10261	8864	57.342%
문제
P[0], P[1], ...., P[N-1]은 0부터 N-1까지(포함)의 수를 한 번씩 포함하고 있는 수열이다. 수열 P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B가 된다. 적용하는 방법은 B[P[i]] = A[i]이다.

배열 A가 주어졌을 때, 수열 P를 적용한 결과가 비내림차순이 되는 수열을 찾는 프로그램을 작성하시오. 비내림차순이란, 각각의 원소가 바로 앞에 있는 원소보다 크거나 같을 경우를 말한다. 만약 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력한다.

입력
첫째 줄에 배열 A의 크기 N이 주어진다. 둘째 줄에는 배열 A의 원소가 0번부터 차례대로 주어진다. N은 50보다 작거나 같은 자연수이고, 배열의 원소는 1,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 비내림차순으로 만드는 수열 P를 출력한다.

예제 입력 1
3
2 3 1
예제 출력 1
1 2 0
예제 입력 2
4
2 1 3 1
예제 출력 2
2 0 3 1
예제 입력 3
8
4 1 6 1 3 6 1 4
예제 출력 3
4 0 6 1 3 7 2 5

 */

import java.io.*;
import java.util.*;


//public class SortPermutation {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        // N개를 담는 0...N-1 배열 생성.
//        int[] P = new int[N];
//        int[] arr = new int[N];
//        int t = -1;
//        while (++t < N) {
//            arr[t] = sc.nextInt();
//        }
//
//        int idx = 0;
//        int used = -1;
//        ArrayList<Integer> minIndices = new ArrayList<Integer>();
//
//        for (int i = 0; i < N; i++) {
//            int minVal = Integer.MAX_VALUE;
//
//            for (int j = 0; j < N; j++) {
//                if (arr[j] != used) {
//
//                    if (arr[j] < minVal) {
////                        System.out.println("더 작음! arr[j]: " +arr[j] + " minVal: " + minVal);
//                        minIndices.clear(); // 정리 후 새롭게 저장.
//                        minIndices.add(j);
//                        minVal = arr[j];
//                    } else if (arr[j] == minVal) {
//                        minIndices.add(j); // 인덱스 저장
//                    }
//                }
//            }
//            for (int j: minIndices) {
////                System.out.println("arr[j] 사용 처리 |  arr[j]: " + arr[j] + " | j: " + j + " | idx: " + idx);
//                arr[j] = used; // 사용한 것으로 처리
//                P[j] = idx; // 사전순으로 앞서는 것을 표현.
//                idx++;
//            }
//            minIndices.clear();
//
//            if (idx == N) break; // 바로 종료.
//
//        }
//
//        // 출력
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < P.length; i++) {
//            sb.append(P[i]);
//            if (i < arr.length - 1) {
//                sb.append(" ");
//            }
//        }
//        System.out.println(sb.toString());
//    }
//}

// 누적합 이용.
public class SortPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] b = new int[1001];
        int[] a = new int[n]; //입력받은 수열
        int[] p = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            a[i] = input;
            b[input]++; //input에 해당하는 인덱스에 갯수 저장
        }
        b[0]--; //인덱스를 0부터 시작하도록
        for (int i = 1; i <= 1000; i++) {
            b[i] += b[i-1]; // count_sum으로 해당 인덱스에 비내림차순으로 몇번째인지 저장
            // 누적합으로 몇번째 값인지 파악 가능.
        }
        for (int i = n-1; i >= 0; i--) {
            p[i] = b[a[i]]--; //0부터 n-1까지 하게되면 동일한 인덱스에서 사전의 역순으로 나타나기
            //때문에 n-1부터 역순으로 탐색
            // --를 하는 것은 한 개 소진했다는 뜻임.

        }
        for (int i = 0; i < n; i++) {
            sb.append(p[i]).append(" ");
        }
        System.out.println(sb);
    }
}

// 일반적인 해.
//import java.util.*;
//
//public class SortPermutation {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//
//        // 배열을 인덱스와 함께 저장하는 클래스
//        class Element {
//            int value;
//            int index;
//
//            Element(int value, int index) {
//                this.value = value;
//                this.index = index;
//            }
//        }
//
//        // 원소들을 Element 객체로 변환
//        Element[] elements = new Element[N];
//        for (int i = 0; i < N; i++) {
//            elements[i] = new Element(sc.nextInt(), i);
//        }
//
//        // 값으로 정렬하고, 같은 값이면 인덱스로 정렬 (사전순)
//        Arrays.sort(elements, (a, b) -> {
//            if (a.value != b.value) {
//                return Integer.compare(a.value, b.value);
//            }
//            return Integer.compare(a.index, b.index);
//        });
//
//        // 순열 P 생성
//        int[] P = new int[N];
//        for (int i = 0; i < N; i++) {
//            P[elements[i].index] = i;
//        }
//
//        // 출력
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < N; i++) {
//            sb.append(P[i]);
//            if (i < N - 1) {
//                sb.append(" ");
//            }
//        }
//        System.out.println(sb.toString());
//    }
//}