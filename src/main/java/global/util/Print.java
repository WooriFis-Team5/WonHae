package global.util;

public class Print {
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[36m";

    public static final void startWonHae() {
        System.out.println(RED + "***    ___________________________________________________________   ***");
        System.out.println("***    __ |     / /__/ __ \\___/ | / /___/ / / /___/   | ___/ ____/   ***");
        System.out.println("***    __ | /| / /  / / / /  /  |/ /   / /_/ /   / /| |   / ____/    ***");
        System.out.println("***    __ |/ |/ /  / /_/ /  / /|  /   / __  /   / ___ |  / _____     ***");
        System.out.println("***    ____/|__/   \\____/  /_/ |_/   /_/ /_/   /_/  |_| /_____/      ***");
    }

    public static void getReadCommand() {
        System.out.println(GREEN + "\n기능을 선택하세요.");
        System.out.println("1. 로그인");
        System.out.println("2. 계좌 업무");
        System.out.println("3. 대출 업무");
        System.out.println("Q. 종료");
        System.out.print(PURPLE + "명령어를 입력하세요 : ");
    }
}
