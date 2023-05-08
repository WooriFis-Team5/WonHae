package member.domain.dao;

import global.util.DBUtil;
import member.domain.dao.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {
    @Override
    public void save(MemberDto dto) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement("INSERT INTO customer (customer_id, customer_pw, customer_name, ssn, customer_wage) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getSsn());
            ps.setLong(5, Integer.parseInt(dto.getSal()));

            ps.executeUpdate();
            con.commit();
        } catch (SQLException e){
            throw new IllegalArgumentException("회원 가입 멤버 정보를 저장하는데 오류가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps);
        }
    }
}
