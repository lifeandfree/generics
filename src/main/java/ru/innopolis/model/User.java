package ru.innopolis.model;

import ru.innopolis.model.enums.Role;
import ru.innopolis.model.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Представление сущности пользователя в системе
 */
@Entity
@Table(name = "Users")
public class User implements Identified<UUID>, IUUIDIdentified<String, UUID>, CreateAtIdentified {

    private static final long serialVersionUID = -7931737332645464539L;

    private UUID id;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserStatus userStatus;
    private String username;
    private String password;
    private String email;
    private Role role;
    private UserInfo userInfo;

    public User() {
        this.role = Role.ROLE_OPERATOR;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    @Column(name = "uuid", unique = true, nullable = false, length = 36)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "username", nullable = false, length = 255, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, length = 255, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 100, unique = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "userStatus", nullable = false, length = 100, unique = false)
    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    @Column(name = "createdAt", nullable = false)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    @Column(name = "updatedAt", nullable = true)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userinfo", nullable = true, unique = false)
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("User{").append("id=").append(id).append(", uuid='").append(uuid).append('\'').append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append(", userStatus=").append(userStatus).append(", username='").append(username).append('\'').append(", password='").append(password).append('\'').append(", email='").append(email).append('\'').append(", role=").append(role).append(", userInfo=").append(userInfo).append('}').toString();
    }
}
