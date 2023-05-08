package account.domain.dao;

import account.domain.dao.dto.AccountDto;
import account.domain.dao.dto.request.IssueRequestDto;
import account.domain.dao.dto.request.BankingRequestDto;

public interface AccountDao {
    void issue(IssueRequestDto request);
    AccountDto findByAccountId(Integer accountId);
    void withdraw(BankingRequestDto request);

    void deposit(BankingRequestDto request);
}
