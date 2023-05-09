package loan.domain.dao;

import global.util.DBUtil;
import loan.domain.dto.LoanDto;
import loan.domain.dto.request.RecommendRequestDto;
import loan.domain.dto.response.RecommendResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanDaoImpl implements LoanDao {
    @Override
    public List<LoanDto> findAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LoanDto> loans = new ArrayList<>();
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("SELECT * FROM loan");
            rs = ps.executeQuery();

            while (rs.next()) {
                loans.add(LoanDto.builder()
                        .code(rs.getInt(1))
                        .loanName(rs.getString(2))
                        .loanMinAmount(rs.getInt(3))
                        .loanMaxAmount(rs.getInt(4))
                        .loan_rate(rs.getDouble(5))
                        .term(rs.getInt(6))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("대출 정보를 가져오는 도중 에러가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps, rs);
        }
        return loans;
    }


    @Override
    public RecommendResponseDto findCreditBySSN(String SSN) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RecommendResponseDto response = null;
        try {
            con = DBUtil.getConnection();
            String sql = "SELECT customer_wage, balance " +
                    "FROM account " +
                    "JOIN customer ON account.owner_ssn = customer.ssn " +
                    "WHERE account.owner_ssn = ?";

            ps.setString(1, SSN);

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                response = RecommendResponseDto.builder()
                        .salary(rs.getInt(1))
                        .balance(rs.getInt(2))
                        .build();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("대출 정보를 가져오는 도중 에러가 발생했습니다.");
        } finally {
            DBUtil.close(con, ps, rs);
        }
        return response;

    }
}
