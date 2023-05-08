package account.domain.dao;


import account.domain.dao.dto.request.IssueRequestDto;
import global.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void issue(IssueRequestDto request) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement("SELECT *  FROM account WHERE owner_ssn = ? AND owner_name = ?");
            ps.setString(1, request.getSSN());
            ps.setString(2, request.getName());

            rs = ps.executeQuery();

            if (rs.next())
                throw new IllegalArgumentException("이미 계좌가 존재합니다");

            ps = con.prepareStatement(
                    "INSERT INTO account (owner_name, owner_ssn, account_pw" +
                            ", balance, created_at, updated_at, activate)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, request.getName());
            ps.setString(2, request.getSSN());
            ps.setString(3, request.getPw());
            ps.setInt(4, 0);
            ps.setTimestamp(5, date);
            ps.setTimestamp(6, date);
            ps.setBoolean(7, true);
            ps.execute();

            con.commit();
        } catch (SQLException e) {
            throw new IllegalArgumentException("계좌를 찾는 도중 에러가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps, rs);
        }
    }
}
