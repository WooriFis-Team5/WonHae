package member.view;

import member.domain.dao.dto.MemberDto;
import member.domain.dao.dto.response.LoginResponseDto;

public class MemberOutputView {
    public void completeSignUp() {
        System.out.println("\nSign-Up complete!!!");
    }

    public void completeLogin() {
        System.out.println("\n로그인에 성공하셨습니다.");
    }

    public void failLogin() {
        System.out.println("\n로그인에 실패하셨습니다.");
    }
}