package account.domain.service;

import account.domain.dao.AccountDao;
import account.domain.dao.AccountDaoImpl;
import account.domain.dao.dto.request.IssueRequestDto;
import member.util.Crypt;

public class AccountService {
    private final AccountDao accountDao = new AccountDaoImpl();

    public void issue(IssueRequestDto request) {
        request.setPw(Crypt.encrypt(request.getPw()));
        accountDao.issue(request);
    }



}
