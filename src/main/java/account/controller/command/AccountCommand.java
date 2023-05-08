package account.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AccountCommand {
    ISSUE("1"),
    DEPOSIT("2"),
    WITHDRAW("3"),
    ISACTIVATED("4"),
    PREVIOUS("P");

    private final String command;

    public static AccountCommand from(String command) {
        return Arrays.stream(values())
                .filter(i -> i.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령을 요청하셨습니다."));
    }

    public boolean isNotPrevious() {
        return this != PREVIOUS;
    }
    }
