package account.util;

public class AccountPrint {
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    // Input
    public static void getReadCommand() {
        System.out.println(GREEN + "\n기능을 선택하세요.");
        System.out.println("1. 계좌 생성");
        System.out.println("2. 계좌 입금");
        System.out.println("3. 계좌 출급");
        System.out.println("P. 이전");
        System.out.print(PURPLE + "명령어를 입력하세요 : ");
    }

    public static void createIssue() {
        System.out.println(GREEN + "\n계좌를 생성하기 위해 본인 확인 작업을 진행해 주세요");
        System.out.print(PURPLE + "이름을 입력해 주세요 : ");
    }

    public static void inputSSN() {
        System.out.print(PURPLE + "주민번호를 입력해 주세요 (\"-\"로 구분해주세요) : ");
    }

    public static void getAccountPW() {
        System.out.print(PURPLE + "계좌에 설정하실 비밀번호 4자리를 입력해 주세요 : ");
    }
}
