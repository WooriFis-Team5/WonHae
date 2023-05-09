package account.controller;

import account.controller.command.AccountCommand;
import account.domain.dto.AccountDto;
import account.domain.service.AccountService;
import account.view.AccountInputView;
import account.view.AccountOutputView;
import global.controller.Controller;
import global.view.GlobalOutputView;

import java.util.EnumMap;
import java.util.Map;

import static account.controller.command.AccountCommand.*;
import static global.util.Retry.execute;

public class AccountController implements Controller {
    private final Map<AccountCommand, Runnable> service = new EnumMap<>(AccountCommand.class);
    private final AccountInputView inputView = new AccountInputView();
    private final AccountOutputView outputView = new AccountOutputView();
    private final GlobalOutputView globalOutputView = new GlobalOutputView();
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        service.put(ISSUE, this::issue);
        service.put(DEPOSIT, this::deposit);
        service.put(WITHDRAW, this::withdraw);
        service.put(ISACTIVATED, this::isActivated);
    }

    @Override
    public void run() {
        AccountCommand command = execute(inputView::readAccountCommand);

        while (command.isNotPrevious()) {
            try {
                service.get(command).run();
                command = execute(inputView::readAccountCommand);
            } catch (Exception e) {
                throw new IllegalArgumentException("어카운트 컨트롤러에서 예상치 못한 에러가 발생했습니다.");
            }
        }
    }

    public void issue() {
        try {
            AccountDto response = accountService.issue(execute(inputView::readNameAndSSN));
            outputView.successIssue(response);
        } catch (IllegalArgumentException e) {
            globalOutputView.printException(e.getMessage());
        }
    }

    public void deposit() {
        try {
            AccountDto response = accountService.deposit(execute(inputView::readBankingInfo));
            outputView.successDeposit(response);
        } catch (IllegalArgumentException e) {
            globalOutputView.printException(e.getMessage());
        }
    }

    // 계좌 출금 API
    /* @author : yuki
     * @param : accountId(계좌번호), pw(계좌 비밀번호), amount(입금액)
     * @response : AccountDto
     * */
    public void withdraw() {
        try {
            AccountDto response = accountService.withDraw(execute(inputView::readBankingInfo));
            outputView.successWithdraw(response);
        } catch (IllegalArgumentException e) {
            globalOutputView.printException(e.getMessage());
        }
    }

    public void isActivated() {

    }
}
