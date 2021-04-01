package response.entity.model.form;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
