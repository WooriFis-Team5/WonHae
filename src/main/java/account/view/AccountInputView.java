package account.view;

import account.controller.command.AccountCommand;
import account.domain.dto.request.BankingRequestDto;
import account.domain.dto.request.IssueRequestDto;
import account.util.AccountPrint;

import java.util.Scanner;

public class AccountInputView {
    private final Scanner sc = new Scanner(System.in);
    private final AccountInputValidator validator = new AccountInputValidator();

    public AccountCommand readAccountCommand() {
        AccountPrint.getReadCommand();
        String command = sc.nextLine().toUpperCase();

        return AccountCommand.from(command);
    }

    public IssueRequestDto readNameAndSSN() {
        AccountPrint.createIssue();
        String name = sc.nextLine();

        AccountPrint.inputSSN();
        String ssn = sc.nextLine();

        AccountPrint.readAccountPW();
        String pw = sc.nextLine();

        validator.issue(ssn, pw);
        return new IssueRequestDto(name, ssn, pw);
    }

    public BankingRequestDto readBankingInfo() {
        AccountPrint.withdraw();
        String accountId = sc.nextLine();

        AccountPrint.getAccountPW();
        String pw = sc.nextLine();
        validator.pwValidate(pw);

        AccountPrint.howMuch();
        String amount = sc.nextLine();

        return new BankingRequestDto(Integer.parseInt(accountId), pw, Integer.parseInt(amount));
    }
}
