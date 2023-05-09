package loan.domain.service;

import loan.domain.dao.LoanDaoImpl;
import loan.domain.dto.LoanDto;
import loan.domain.dto.RecommendDto;
import loan.domain.dto.request.RecommendRequestDto;
import loan.domain.dto.response.RecommendResponseDto;

import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private final LoanDaoImpl loanDao = new LoanDaoImpl();

    public List<LoanDto> viewLoans() {
        return loanDao.findAll();
    }

   /* public void recommendLoans(RecommendRequestDto request) {
        RecommendResponseDto creditInfo = loanDao.findCreditBySSN(request.getSsn());
        List<LoanDto> loans = loanDao.findAll();
        List<RecommendDto> recommends = new ArrayList<>();
        int want = request.getWant();

        for (LoanDto loan : loans) {
            double rate = loan.getLoan_rate();
            int term = loan.getTerm();
            if(loan.getLoanName().contains("신용") && creditInfo.getSalary() > 8000 && want > loan.getLoanMinAmount()){

                long total = calculateLoanPayment(want, rate, term);
                recommends.add(new RecommendDto(loan.getLoanName(), term, total,  Math.round((double) total/12)));
            }

            if(loan.getLoanName().contains("담보") || creditInfo.getBalance() )
        }
    }*/

    public long calculateLoanPayment(int loanAmount, double annualInterestRate, int loanTermInMonths) {
        double monthlyInterestRate = annualInterestRate / 12;
        double payment = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermInMonths) / (Math.pow(1 + monthlyInterestRate, loanTermInMonths) - 1);
        return Math.round(payment);
    }
}
