package ru.innopolis.model;

import ru.innopolis.model.enums.OrganizationStatus;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OrganizationStatus getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(OrganizationStatus organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return 29 * Objects.hash(id, createdAt, updatedAt);
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
