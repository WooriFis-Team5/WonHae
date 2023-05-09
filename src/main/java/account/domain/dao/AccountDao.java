package account.domain.dao;

import account.domain.dto.AccountDto;
import account.domain.dto.request.BankingRequestDto;
import account.domain.dto.request.IssueRequestDto;

public interface AccountDao {
    void issue(IssueRequestDto request);
    AccountDto findBySSN(String SSN);
    AccountDto findByAccountId(Integer accountId);
    void withdraw(BankingRequestDto request);

    void deposit(BankingRequestDto request);

}
