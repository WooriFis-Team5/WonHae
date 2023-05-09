package member.domain.service;

import member.domain.dao.MemberDao;
import member.domain.dao.MemberDaoImpl;
import member.domain.dto.MemberDto;
import member.domain.dto.request.LoginRequestDto;
import member.domain.dto.response.LoginResponseDto;
import member.util.Crypt;

public class MemberService {
    private final MemberDao memberDao = new MemberDaoImpl();

    public void signUp(MemberDto dto) {
        String plain = dto.getPw();
        dto.setPw(Crypt.encrypt(plain));

        memberDao.save(dto);
    }

    public boolean login(LoginRequestDto request) {
        LoginResponseDto response = memberDao.findById(request.getId());

        return Crypt.decrypt(request.getPw(), response.getEncryptPW());
    }


}
