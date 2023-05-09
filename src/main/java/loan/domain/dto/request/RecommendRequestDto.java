package loan.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendRequestDto {
    private String name;
    private String ssn;
    private Integer want;
}
