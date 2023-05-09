package account.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BankingRequestDto {
    private Integer accountId;
    private String pw;
    private Integer amount;
}
