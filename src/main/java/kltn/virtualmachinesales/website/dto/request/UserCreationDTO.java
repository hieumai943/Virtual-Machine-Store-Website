package kltn.virtualmachinesales.website.dto.request;

import kltn.virtualmachinesales.website.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    private String firstName;

    private String lastName;

    private Gender gender;

    private String email;

    private String password;

    private String username;

    private String bio;
}