package loan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendDto {
    private String loanName;
    private Integer loanTerm;
    private Long total_repayment;
    private Long monthPay;
}
