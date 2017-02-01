package zd.model.user;

import lombok.Data;
import zd.model.Model;

import java.util.Date;

@Data
public class UserInfo extends Model {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
}
