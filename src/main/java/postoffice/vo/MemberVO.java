package postoffice.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class MemberVO {
    private String name;
    private String contact;
    private String address;
    private String email;
    private String memberid;
    private String password;
	
}
