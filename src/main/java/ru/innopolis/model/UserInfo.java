package ru.innopolis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Представление сущности дополнительной информации о пользователя в системе.
 */
@Entity
@Table(name = "UserInfo")
public class UserInfo implements Identified<UUID> {

    private static final long serialVersionUID = -3415549133677946887L;

    private UUID id;
    private String lastName;
    private String firstName;
    private String secondName;

    public UserInfo() {
    }

    @Override@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "lastName", nullable = false, length = 25)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "firstName", nullable = false, length = 25)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "secondName", length = 25)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(id)
                .append(", lastName='").append(lastName).append('\'')
                .append(", firstName='").append(firstName).append('\'')
                .append(", secondName='").append(secondName).append('\'')
                .append('}')
                .toString();
    }
}
