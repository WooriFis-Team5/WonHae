package account.view;

import java.util.regex.Pattern;

public class AccountInputValidator {
    public static final String ssnPattern = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$";

    public void issue(String ssn, String pw) {
        ssnValidate(ssn);
        pwValidate(pw);
    }

    public void ssnValidate(String ssn) {
        if (!Pattern.matches(ssnPattern, ssn)) {
            throw new IllegalArgumentException("잘못된 주민 등록 번호 형식입니다.");
        }
    }

    public void pwValidate(String pw) {
        if (pw.length() != 4) {
            throw new IllegalArgumentException("계좌 비밀번호는 반드시 4자리 숫자여야 합니다.");
        }
    }
}
