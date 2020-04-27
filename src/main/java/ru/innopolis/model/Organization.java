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

    private final Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OrganizationStatus organizationStatus;
    private final String organizationName;
    private Set<User> users;

    public Organization(Long id, String organizationName) {
        this.id = id;
        this.organizationName = organizationName;
        this.users = new HashSet<>();
    }

    @Override
    public Long getId() {
        return id;
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
                Objects.equals(organizationName, that.organizationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationName);
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
