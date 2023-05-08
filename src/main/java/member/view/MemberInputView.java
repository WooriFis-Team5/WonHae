package member.view;

import member.controller.command.MemberCommand;
import member.domain.dao.dto.MemberDto;
import member.domain.dao.dto.request.LoginRequestDto;
import member.util.MemberPrint;

import java.util.Scanner;

public class MemberInputView {
    private final Scanner sc = new Scanner(System.in);
    private final MemberInputValidator validator = new MemberInputValidator();

    public MemberCommand readLoginCommand() {
        MemberPrint.getReadCommand();
        String command = sc.nextLine().toUpperCase();

        return MemberCommand.from(command);
    }

    public MemberDto readSignUp() {
        MemberPrint.signUp();
        String[] info = sc.nextLine().split(", ");


        return MemberDto.MemberDtoBuilder()
                .id(info[0])
                .pw(info[1])
                .name(info[2])
                .ssn(info[3])
                .sal(Integer.parseInt(info[4])).build();
    }

    public LoginRequestDto readIDAndPW() {
        MemberPrint.login();
        String id = sc.nextLine();

        MemberPrint.getLoginPW();
        String pw = sc.nextLine();

        return new LoginRequestDto(id, pw);
    }
}
