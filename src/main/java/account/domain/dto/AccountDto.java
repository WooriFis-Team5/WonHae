package account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {
    private Integer accountId;
    private String name;
    private String encryptPW;
    private Integer balance;
}
