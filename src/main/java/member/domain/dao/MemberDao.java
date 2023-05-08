package member.domain.dao;

import member.domain.dao.dto.MemberDto;

public interface MemberDao {
    // Sign-Up
    public void save(MemberDto dto);
}
