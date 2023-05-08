package account.domain.dao;

import account.domain.dao.dto.request.IssueRequestDto;

public interface AccountDao {
    public void issue(IssueRequestDto request);
}
