package loan.domain.dao;

import loan.domain.dto.LoanDto;
import loan.domain.dto.response.RecommendResponseDto;

import java.util.List;

public interface LoanDao {
    List<LoanDto> findAll();

    RecommendResponseDto findCreditBySSN(String SSN);
}
