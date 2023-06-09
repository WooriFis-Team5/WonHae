package global.controller;

import global.controller.command.ControllerMapper;
import global.controller.command.ControllerMapperCommand;
import global.view.GlobalInputView;

import static global.util.Retry.execute;

public class MapperController implements Controller {
    private final GlobalInputView globalInputView = new GlobalInputView();

    private final ControllerMapper controllerMapper;

    public MapperController(ControllerMapper controllerMapper) {
        this.controllerMapper = controllerMapper;
    }

    @Override
    public void run() {
        ControllerMapperCommand command = execute(globalInputView::readControllerMapperCommand);
        while (command.isNotQuit()) {
            Controller controller = controllerMapper.get(command);
            execute(controller::run);
            command = execute(globalInputView::readControllerMapperCommand);
        }
    }
}
