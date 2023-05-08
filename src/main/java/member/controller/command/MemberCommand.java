package member.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MemberCommand {
    SIGNUP("1"),
    LOGIN("2"),
    LOGOUT("3"),
    PREVIOUS("P");

    private final String command;

    public static MemberCommand from(String command) {
        return Arrays.stream(values())
                .filter(i -> i.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령을 요청하셨습니다."));
    }

    public boolean isNotPrevious() {
        return this != PREVIOUS;
    }
}
