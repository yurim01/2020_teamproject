import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ClosetPair {

    static Point p[];

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // 좌표설정


    static int getdistancepow(Point p1, Point p2) {
        return (p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y);
    } //두 점 사이의 거리를 구하는 함수 p1과 p2사이의 거리

    static int bruteforce(int left, int right) {
        int d_min = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            for (int j =i+1; j<= right; j++) {
                int d = getdistancepow(p[i],p[j]);
                if (d_min > d) d_min = d;
            }
        }
        return d_min;
    } // 완전탐색, 모든 경우의 수를 직접 대입해보는 방법 경우의 수가 적다면 이용

    static int divcon(int left, int right) {

        int size = right - left +1;
        if (size <= 3)
            return bruteforce(left,right); // 수가 적다면 완전 탐색 이용

        int mid = (left + right) /2;
        int d_s_left = divcon(left,mid); //왼쪽구역
        int d_s_right = divcon(mid+1,right); //오른쪽구역

        int d_min = Math.min(d_s_left, d_s_right); //왼쪽구역과 오른쪽구역에서 구한d의 값 중 작은값 찾기

        List<Point> s_mid = new ArrayList<>(); // 6) 과정 왼쪽구역점과 오른쪽 구역의 점 사이의 거리가 d보다 작을시
        for(int i = left; i<= right; i++) {
            int d_x = p[i].x - p[mid].x;
            if(d_x*d_x < d_min)
                s_mid.add(p[i]); //리스트에 추가
        }

        Collections.sort(s_mid, (p1,p2) -> p1.y - p2.y); //추가한 리스트들을 y축 좌표 기준 오름차순 정렬
        int c_size = s_mid.size();
        for(int i =0; i< c_size -1;i++) {
            for(int j = i+1; j<c_size; j++) {
                int d_y = s_mid.get(j).y - s_mid.get(i).y;
                if(d_y*d_y < d_min) {
                    int d = getdistancepow(s_mid.get(i), s_mid.get(j));
                    if(d_min > d) d_min = d;
                }
                else break;
            }
        }
        return d_min;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // 점 개수 입력
        int n = sc.nextInt();
        p = new Point[n];
        for(int i =0; i<n; i++)
            p[i] = new Point (sc.nextInt(),sc.nextInt()); //점 좌표 입력
        sc.close();

        Arrays.sort(p,(p1,p2) -> p1.x - p2.x); // x좌표 기준 오름차순 정렬

        System.out.println(divcon(0,n-1)); // 분할정복 진행
   }
}