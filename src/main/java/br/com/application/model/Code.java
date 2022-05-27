package br.com.application.model;

import javax.persistence.*;

import br.com.application.enums.CodeType;
import br.com.application.model.user.AccountConfirmation;

import java.time.LocalDateTime;

@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private AccountConfirmation accountConfirmation;

    @Enumerated(EnumType.STRING)
    private CodeType type;

    private LocalDateTime expiration;

    private Integer code;

    private Boolean used = false;

    private LocalDateTime usedIn;

    @PrePersist
    private void prePersist() {
        expiration = LocalDateTime.now().plusMinutes(5);
    }

    public Code() {
    }

    public Code(Long id, AccountConfirmation accountConfirmation, CodeType type, LocalDateTime expiration, Integer code, Boolean used, LocalDateTime usedIn) {
        this.id = id;
        this.accountConfirmation = accountConfirmation;
        this.type = type;
        this.expiration = expiration;
        this.code = code;
        this.used = used;
        this.usedIn = usedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AccountConfirmation getAccountConfirmation() {
        return accountConfirmation;
    }

    public void setAccountConfirmation(AccountConfirmation accountConfirmation) {
        this.accountConfirmation = accountConfirmation;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public LocalDateTime getUsedIn() {
        return usedIn;
    }

    public void setUsedIn(LocalDateTime usedIn) {
        this.usedIn = usedIn;
    }

    public CodeType getType() {
        return type;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public boolean isUsed() {
        return used;
    }

}
