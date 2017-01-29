package zd.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
}
