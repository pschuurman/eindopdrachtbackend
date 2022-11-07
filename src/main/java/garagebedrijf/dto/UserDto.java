package garagebedrijf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    public String username;

    @NotBlank
    @Size(min=3, max=12)
    public String password;

    @NotBlank
    public String[] roles;

}

