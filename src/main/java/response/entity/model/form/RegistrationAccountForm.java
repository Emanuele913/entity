package response.entity.model.form;


import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class RegistrationAccountForm {

    @NotBlank
    @Size(min=2, max=20)
    private String username;

    @NotBlank
    @Size(min=2, max=20)
    private String password;

    @NotBlank
    @Size(min=2, max=20)
    private String conferma_password;

    @NotBlank
    private String data_registrazione;







}
