package loan.controller;

import global.controller.Controller;
import global.view.GlobalOutputView;
import loan.controller.command.LoanCommand;
import loan.domain.dto.LoanDto;
import loan.domain.service.LoanService;
import loan.view.LoanInputView;
import loan.view.LoanOutputView;
import member.controller.command.MemberCommand;

import javax.naming.ldap.Control;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static global.util.Retry.execute;
import static loan.controller.command.LoanCommand.*;

public class LoanController implements Controller {
    private final LoanService loanService;
    private final Map<LoanCommand, Runnable> service = new EnumMap<>(LoanCommand.class);
    private final LoanInputView loanInputView = new LoanInputView();
    private final LoanOutputView loanOutputView = new LoanOutputView();
    private final GlobalOutputView globalOutputView = new GlobalOutputView();

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
        service.put(LOANITEM, this::viewLoans);
        service.put(RECOMMAND, this::recommendItem);
        service.put(REPAYMENT, this::totalRepayment);
    }

    @Override
    public void run() {
        LoanCommand command = execute(loanInputView::readLoanCommand);
        while (command.isNotPrevious()) {
            try {
                service.get(command).run();
                command = execute(loanInputView::readLoanCommand);
            } catch (Exception e) {
                new IllegalArgumentException("대출 컨트롤러에서 예상치 못한 에러가 발생했습니다.");
            }
        }
    }

    public void viewLoans() {
        try {
            loanOutputView.viewLoans(loanService.viewLoans());
        } catch (IllegalArgumentException e){
            globalOutputView.printException(e.getMessage());
        }
    }

    public void recommendItem() {

    }

    public void totalRepayment() {

    }
}
