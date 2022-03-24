package dto.login;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
    private boolean rememberId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberId() {
        return rememberId;
    }

    public void setRememberId(boolean rememberId) {
        this.rememberId = rememberId;
    }

}
