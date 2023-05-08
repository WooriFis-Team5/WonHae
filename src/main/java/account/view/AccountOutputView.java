package account.view;

import account.domain.dao.dto.AccountDto;

public class AccountOutputView {
    public static final String YELLOW = "\u001B[33m";

    public void successIssue(AccountDto response) {
        System.out.println(YELLOW + "\n계좌 발금이 완료되셨습니다.");
        System.out.println(response.getName() + "님의 발급 계좌명은 " + response.getAccountId() + "입니다.");
    }

    public void successWithdraw(AccountDto response) {
        System.out.println(YELLOW + "\n출금이 완료되셨습니다.");
        System.out.println(response.getName() + "님의 계좌 " + response.getAccountId() + "의 잔액은 " + response.getBalance());
    }

    public void successDeposit(AccountDto response) {
        System.out.println(YELLOW + "\n입금이 완료되셨습니다.");
        System.out.println(response.getName() + "님의 계좌 " + response.getAccountId() + "의 잔액은 " + response.getBalance());
    }


}
