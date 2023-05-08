package member.domain.service;

import member.domain.dao.MemberDao;
import member.domain.dao.MemberDaoImpl;
import member.domain.dao.dto.MemberDto;
import member.util.Crypt;

import java.sql.SQLException;

public class MemberService {
    private final MemberDao memberDao = new MemberDaoImpl();

    public void signUp(MemberDto dto) {
        String plain = dto.getPw();
        dto.setPw(Crypt.encrypt(plain));

        memberDao.save(dto);
    }
}
