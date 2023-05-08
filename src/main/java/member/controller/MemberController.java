package member.controller;

import global.controller.Controller;
import member.controller.command.MemberCommand;
import member.domain.service.MemberService;
import member.view.MemberInputView;
import member.view.MemberOutputView;

import java.util.HashMap;
import java.util.Map;

import static global.util.Retry.execute;
import static member.controller.command.MemberCommand.*;

public class MemberController implements Controller {
    private Map<MemberCommand, Runnable> service = new HashMap<>();
    private final MemberInputView memberInputView = new MemberInputView();
    private final MemberOutputView memberOutputView = new MemberOutputView();
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        service.put(SIGNUP, this::signUp);
        service.put(LOGIN, this::login);
        service.put(LOGOUT, this::logout);
    }

    @Override
    public void run() {
        MemberCommand command = execute(memberInputView::readLoginCommand);
        while (command.isNotPrevious()) {
            try {
                service.get(command).run();
                command = execute(memberInputView::readLoginCommand);
            } catch (Exception e) {
                new IllegalArgumentException("멤버 컨트롤러에서 예상치 못한 에러가 발생했습니다.");
            }
        }
    }

    // 회원가입 API
    /* @author : yuki
     * @param : id(아이디), pw(패스워드), name(이름), ssn(주민번호), sal(연봉)
     * @response : void
     * */
    public void signUp() {
        memberService.signUp(execute(memberInputView::readSignUp));
        memberOutputView.completeSignUp();
    }

    // 로그인 API
    /* @author : yuki
    *  @param : id(아이디), pw(비밀번호)
    *  @response : boolean
    * */
    public void login() {
        if (!memberService.login(execute(memberInputView::readIDAndPW))) {
               memberOutputView.failLogin();
        }
        memberOutputView.completeLogin();
    }

    // 차후 예정
    public void logout() {

    }

}

