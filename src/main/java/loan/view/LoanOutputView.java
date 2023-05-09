package loan.view;

import loan.domain.dto.LoanDto;

import java.util.List;

public class LoanOutputView {
    public static final String YELLOW = "\u001B[33m";
    public void viewLoans(List<LoanDto> loans) {
        System.out.println(YELLOW);
        loans.forEach(i -> System.out.printf(i.toString(), i.getLoanMinAmount(), i.getLoanMaxAmount()));
    }
}

