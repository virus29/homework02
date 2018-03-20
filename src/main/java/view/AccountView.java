package view;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class AccountView {
    //Login(Email)
    @Email
    @NotNull
    private String login;

    //Password
    @NotNull
    private String password;

    //Account name
    private String name;

    //Activation code
    private String activationCode;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
