package response.entity.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class CheckIdentityForm {

    @Size(min=16 , max = 16 , message = "codice fiscale non valido")
    private String cf_conferma;

    @NotBlank
    @Email
    private String email_conferma;
}
