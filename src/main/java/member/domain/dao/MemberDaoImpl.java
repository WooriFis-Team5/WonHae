package member.domain.dao;

import global.util.DBUtil;
import member.domain.dao.dto.MemberDto;
import member.domain.dao.dto.response.LoginResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            ps.setLong(5, dto.getSal());

            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new IllegalArgumentException("회원 가입 멤버 정보를 저장하는데 오류가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps);
        }
    }

    @Override
    public LoginResponseDto findById(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LoginResponseDto response = null;

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("SELECT customer_pw, customer_name FROM customer where customer_id = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                response = LoginResponseDto.builder()
                        .encryptPW(rs.getString(1))
                        .name(rs.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 아이디는 존재하지 않습니다.");
        } finally {
            DBUtil.close(con, ps, rs);
        }
        return response;
    }
}
