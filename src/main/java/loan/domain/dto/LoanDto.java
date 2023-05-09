package loan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoanDto {
    private Integer code;
    private String loanName;
    private Integer loanMinAmount;
    private Integer loanMaxAmount;
    private Double loan_rate;
    private Integer term;

    @Override
    public String toString() {
        return "대출명 : " + getLoanName() + ", 최소 대출 금액 %4d(만)₩"
                + ", 최대 대출 금액 %4d(만)₩, 이자율 " + getLoan_rate() + " 약정 기간 " + getTerm() + "\n";
    }
}
