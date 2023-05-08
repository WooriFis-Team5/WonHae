package account.domain.dao;


import account.domain.dto.AccountDto;
import account.domain.dto.request.BankingRequestDto;
import account.domain.dto.request.IssueRequestDto;
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

    @Override
    public AccountDto findBySSN(String SSN) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDto response = null;
        try {
            con = DBUtil.getConnection();

            ps = con.prepareStatement("SELECT account_id, owner_name, account_pw, balance FROM account WHERE owner_ssn = ?");
            ps.setString(1, SSN);

            rs = ps.executeQuery();

            if (rs.next()) {
                response = AccountDto.builder()
                        .accountId(rs.getInt(1))
                        .name(rs.getString(2))
                        .encryptPW(rs.getString(3))
                        .balance(rs.getInt(4))
                        .build();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("예금주 주민번호를 통해 계좌 정보를 찾는 도중 에러가 발생했습니다.");
        }
        return response;
    }

    @Override
    public AccountDto findByAccountId(Integer accountId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDto response = null;
        try {
            con = DBUtil.getConnection();

            ps = con.prepareStatement("SELECT account_id, owner_name, account_pw, balance FROM account WHERE account_id = ?");
            ps.setInt(1, accountId);

            rs = ps.executeQuery();

            if (rs.next()) {
                response = AccountDto.builder()
                        .accountId(rs.getInt(1))
                        .name(rs.getString(2))
                        .encryptPW(rs.getString(3))
                        .balance(rs.getInt(4))
                        .build();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("계좌아이디를 통해 계좌 정보를 찾는 도중 에러가 발생했습니다.");
        }
        return response;
    }

    @Override
    public void deposit(BankingRequestDto request) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("UPDATE account SET balance = balance + ?, updated_at = ? WHERE account_id = ?");

            ps.setInt(1, request.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(3, request.getAccountId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("계좌 입금 과정 중 에러가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps);
        }
    }

    @Override
    public void withdraw(BankingRequestDto request) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("UPDATE account SET balance = balance - ?, updated_at = ? WHERE account_id = ?");

            ps.setInt(1, request.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(3, request.getAccountId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("계좌 출금 과정 중 에러가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps);
        }
    }
}
