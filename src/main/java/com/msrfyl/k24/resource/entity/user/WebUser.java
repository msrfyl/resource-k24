package com.msrfyl.k24.resource.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msrfyl.k24.resource.AppContext;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}, name = "uniqueUsername")
})
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(nullable = false, length = 12)
    String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 256)
    String password;
    @Column(length = 100)
    String name;

    public WebUser() {
    }

    public WebUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public WebUser(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @PrePersist
    @PreUpdate
    private void beforeSaveData() {
        username = username.trim();
        password = AppContext.getBean(PasswordEncoder.class).encode(password);
    }

}
