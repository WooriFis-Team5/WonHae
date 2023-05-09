package member.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemberDto {
    private String id;
    private String pw;
    private String name;
    private String ssn;
    private Integer sal;

    @Builder(builderMethodName = "MemberDtoBuilder")
    public MemberDto(String id, String pw, String name, String ssn, Integer sal) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.ssn = ssn;
        this.sal = sal;
    }
}

