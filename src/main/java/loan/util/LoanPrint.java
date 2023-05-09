package loan.util;

public class LoanPrint {
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    // Input
    public static void getReadCommand() {
        System.out.println(GREEN + "\n기능을 선택하세요.");
        System.out.println("1. 대출 상품 보기");
        // 예적금 담보 대출
        // 직장인 신용 대출

        System.out.println("2. 추천 상품 보기");

        System.out.println("P. 이전");
        System.out.print(PURPLE + "명령어를 입력하세요 : ");
    }

    public static void getRecommend() {
        System.out.println(GREEN + "\n대출 상품을 추천하기 앞서 회원님의 정보를 확인하는 작업입니다");
        System.out.print(PURPLE + "이름을 입력해 주세요 : ");
    }

    public static void inputSSN() {
        System.out.print(PURPLE + "주민번호를 입력해 주세요 (\"-\"로 구분해주세요) : ");
    }

    public static void inputWant() {
        System.out.print("원하시는 대출 금액을 입력해 주세요 : ");
    }
}

/* 3.5  // 5.6
* loan_name varchar(30)
* loan_min Int,
* loan_max Int,
* loan_rate
* period
*/

