package postoffice.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class PostVO {
    private String memberid;
    private String recepientid;
    private String recepientaddress;
    private Date senddate;
    private String typename;
    private String deliveryMethod;
    private String postid;
}
