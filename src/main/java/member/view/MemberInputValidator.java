package member.view;

import java.util.regex.Pattern;

public class MemberInputValidator {
    public static final String ssnPattern = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$";

    public void ssnValidate(String ssn) {
        if (!Pattern.matches(ssnPattern, ssn)) {
            throw new IllegalArgumentException("잘못된 주민 등록 번호 형식입니다.");
        }
    }
}
