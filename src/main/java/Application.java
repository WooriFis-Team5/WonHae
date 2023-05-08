import global.controller.Controller;
import global.controller.MapperController;
import global.controller.command.ControllerMapper;
import global.util.Print;
import member.controller.MemberController;
import member.domain.service.MemberService;

import static global.controller.command.ControllerMapperCommand.LOGIN;

public class Application {
    public static void main(String[] args) {
        Print.startWonHae();
        Controller controller = new MapperController(controllerMapper());
        controller.run();
    }

    private static ControllerMapper controllerMapper() {
        ControllerMapper mapper = new ControllerMapper();

        mapper.put(LOGIN, new MemberController(memberService()));
        /*mapper.put(ACCOUNT, new AccountController());
        mapper.put(LOAN, new LoanController());
        */

        return mapper;
    }

    private static MemberService memberService() {
        return new MemberService();
    }
}
