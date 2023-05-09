package loan.view;

import loan.controller.command.LoanCommand;
import loan.domain.dto.request.RecommendRequestDto;
import loan.util.LoanPrint;

import java.util.Scanner;

public class LoanInputView {
    private final Scanner sc = new Scanner(System.in);
    private final LoanInputValidator validator = new LoanInputValidator();

    public LoanCommand readLoanCommand() {
        LoanPrint.getReadCommand();
        String command = sc.nextLine().toUpperCase();

        return LoanCommand.from(command);
    }

    public RecommendRequestDto readLoanRecommend(){
        LoanPrint.getRecommend();
        String name = sc.nextLine();

        LoanPrint.inputSSN();
        String ssn = sc.nextLine();

        LoanPrint.inputWant();
        String want = sc.nextLine();

        validator.ssnValidate(ssn);
        return new RecommendRequestDto(name, ssn, Integer.parseInt(want));
    }
}
