import global.controller.Controller;
import global.controller.MapperController;
import global.controller.command.ControllerMapper;
import global.util.Print;

public class Application {
    public static void main(String[] args) {
        Print.startWonHae();
        Controller controller = new MapperController(controllerMapper());
        controller.run();
    }

    private static ControllerMapper controllerMapper() {
        ControllerMapper mapper = new ControllerMapper();

        /*
        mapper.put(LOGIN, new LoginController(loginService()));
        mapper.put(ACCOUNT, new AccountController());
        mapper.put(LOAN, new LoanController());
        */

        return new ControllerMapper();
    }
}
