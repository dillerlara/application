package br.com.application.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.application.model.Code;

import javax.persistence.*;
import java.util.List;

@Entity
public class AccountConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String email;
    private Boolean emailConfirmed = false;

    private Long phone;
    private Boolean phoneConfirmed = false;

    @JsonIgnore
    @OneToMany(mappedBy = "accountConfirmation", fetch = FetchType.LAZY)
    private List<Code> codeId;

    public AccountConfirmation() {
    }

    public AccountConfirmation(String email, Long phone) {
        this.email = email;
        this.phone = phone;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public Boolean getPhoneConfirmed() {
        return phoneConfirmed;
    }

    public void setPhoneConfirmed(Boolean phoneConfirmed) {
        this.phoneConfirmed = phoneConfirmed;
    }

    public List<Code> getCodeId() {
        return codeId;
    }

    public void setCodeId(List<Code> codeId) {
        this.codeId = codeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
