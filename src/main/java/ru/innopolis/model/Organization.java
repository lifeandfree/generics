package ru.innopolis.model;

import ru.innopolis.model.enums.OrganizationStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Представление Организации в системе
 */
public class Organization implements Identified<Long>, CreateAtIdentified {

    private static final long serialVersionUID = -9005741475704378708L;

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OrganizationStatus organizationStatus;
    private String organizationName;
    private Set<User> users;

    public Organization() {
        this.users = new HashSet<>();
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "organizationStatus", nullable = false, length = 100, unique = false)
    public OrganizationStatus getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(OrganizationStatus organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "organizationName", nullable = false, length = 255, unique = true)
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @ManyToMany(targetEntity = User.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", organizationStatus=" + organizationStatus +
                ", organizationName='" + organizationName + '\'' +
                ", users=" + users +
                '}';
    }
}
