package account.domain.service;

import account.domain.dao.AccountDao;
import account.domain.dao.AccountDaoImpl;
import account.domain.dao.dto.AccountDto;
import account.domain.dao.dto.request.BankingRequestDto;
import account.domain.dao.dto.request.IssueRequestDto;
import member.util.Crypt;

public class AccountService {
    private final AccountDao accountDao = new AccountDaoImpl();

    public AccountDto issue(IssueRequestDto request) {
        request.setPw(Crypt.encrypt(request.getPw()));
        accountDao.issue(request);

        return accountDao.findBySSN(request.getSSN());
    }

    public AccountDto deposit(BankingRequestDto request) {
        AccountDto response = accountDao.findByAccountId(request.getAccountId());

        if (!Crypt.decrypt(request.getPw(), response.getEncryptPW())) {
            throw new IllegalArgumentException("계좌 비밀번호가 틀리셨습니다.");
        }

        int add = request.getAmount() + response.getBalance();
        accountDao.deposit(request);
        response.setBalance(add);
        return response;

    }

    public AccountDto withDraw(BankingRequestDto request) {
        AccountDto response = accountDao.findByAccountId(request.getAccountId());

        if (!Crypt.decrypt(request.getPw(), response.getEncryptPW())) {
            throw new IllegalArgumentException("계좌 비밀번호가 틀리셨습니다.");
        }

        int difference = response.getBalance() - request.getAmount();
        if (difference < 0) {
            throw new IllegalArgumentException("통장에 잔액이 부족합니다.");
        }

        accountDao.withdraw(request);
        response.setBalance(difference);
        return response;
    }
}
