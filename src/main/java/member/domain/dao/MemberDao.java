package member.domain.dao;

import member.domain.dto.MemberDto;
import member.domain.dto.response.LoginResponseDto;

public interface MemberDao {
    // Sign-Up
    public void save(MemberDto dto);

    // Login
    public LoginResponseDto findById(String id);
}
