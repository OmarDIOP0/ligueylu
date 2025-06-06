package master.ipld.ligueylu.request;

import lombok.Data;
import master.ipld.ligueylu.model.enums.Role;

@Data
public class CreateClientRequest {
    private String nomComplet;
    private Role role;
    private String email;
    private String password;
    private String telephone;
    private boolean actif;
}
