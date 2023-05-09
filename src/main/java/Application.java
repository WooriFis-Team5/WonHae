import account.controller.AccountController;
import account.domain.service.AccountService;
import global.controller.Controller;
import global.controller.MapperController;
import global.controller.command.ControllerMapper;
import global.util.Print;
import loan.controller.LoanController;
import loan.domain.service.LoanService;
import member.controller.MemberController;
import member.domain.service.MemberService;

import static global.controller.command.ControllerMapperCommand.*;

public class Application {
    public static void main(String[] args) {
        Print.startWonHae();
        Controller controller = new MapperController(controllerMapper());
        controller.run();
    }

    private static ControllerMapper controllerMapper() {
        ControllerMapper mapper = new ControllerMapper();

        mapper.put(LOGIN, new MemberController(memberService()));
        mapper.put(ACCOUNT, new AccountController(accountService()));
        mapper.put(LOAN, new LoanController(loanService()));
        return mapper;
    }


    private static MemberService memberService() {
        return new MemberService();
    }

    private static AccountService accountService() {
        return new AccountService();
    }

    private static LoanService loanService() {
        return new LoanService();
    }
}
