import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class greedy1 {
    /*
     * 파파 파스타 가게는 점심 추천 파스타와 생과일 쥬스 세트 메뉴가 인기가 좋다.
이 세트 메뉴를 주문하면 그 날의 3 종류의 파스타와 2 종류의 생과일 쥬스에서 하나씩 선택한다.
파스타와 생과일 쥬스의 가격 합계에서 10%를 더한 금액이 대금된다.
어느 날의 파스타와 생과일 쥬스의 가격이 주어 졌을 때, 그 날 세트 메뉴의 대금의 최소값을 구하는 프로그램을 작성하라.

입력은 5 행으로 이루어지며, 한 줄에 하나씩 양의 정수가 적혀있다.
1행의 정수는 첫 번째 파스타 가격이다.
2행의 정수는 두 번째 파스타 가격이다.
3행의 정수는 세 번째 파스타 가격이다.
4행의 정수는 첫 번째 생과일 쥬스 가격이다.
5행의 정수는 두 번째 생과일 쥬스의 가격이다.
(모든 파스타와 생과일 쥬스의 가격은 100 원이상 2000원 이하이다.)

그날 세트 메뉴의 최소 대금을 소수 첫째자리까지 출력하시오.
     */
    public static void main(String[] args) {

        /* test 위해서 
        Scanner s = new Scanner(System.in);

        float pasta1 = s.nextInt();
        float pasta2 = s.nextInt();
        float pasta3 = s.nextInt();
        float juice1 = s.nextInt();
        float juice2 = s.nextInt();
        */

        float pasta1 = 800;
        float pasta2 = 700;
        float pasta3 = 900;
        float juice1 = 198;
        float juice2 = 330;

        ArrayList<Float> pasta = new ArrayList<>();
        ArrayList<Float> juice = new ArrayList<>();

        pasta.add(pasta1);
        pasta.add(pasta2);
        pasta.add(pasta3);
        juice.add(juice1);
        juice.add(juice2);

        System.out.println(pasta);
        System.out.println(juice);

        Collections.sort(pasta);
        Collections.sort(juice);

        System.out.println(pasta);
        System.out.println(juice);

        System.out.println( String.format("%.1f",  (pasta.get(0) + juice.get(0)) * 1.1));


        
    }
}
