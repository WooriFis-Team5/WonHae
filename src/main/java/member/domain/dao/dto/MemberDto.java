package member.domain.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    private String id;
    private String pw;
    private String name;
    private String ssn;
    private String sal;
}

