package member.domain.dao;

import member.domain.dao.dto.MemberDto;
import member.domain.dao.dto.request.LoginRequestDto;
import member.domain.dao.dto.response.LoginResponseDto;

public interface MemberDao {
    // Sign-Up
    public void save(MemberDto dto);

    // Login
    public LoginResponseDto findById(String id);
}
