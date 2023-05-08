package global.view;

import global.controller.command.ControllerMapperCommand;
import global.util.Print;

import java.util.Scanner;

public class GlobalInputView {
    private final Scanner sc = new Scanner(System.in);

    public ControllerMapperCommand readControllerMapperCommand() {
        Print.getReadCommand();
        String command = sc.nextLine().toUpperCase();
        return ControllerMapperCommand.from(command);
    }
}
