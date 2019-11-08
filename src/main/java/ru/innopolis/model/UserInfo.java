package ru.innopolis.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Представление сущности дополнительной информации о пользователя в системе.
 */
public class UserInfo implements Identified<User>, Comparable<UserInfo> {

    private static final long serialVersionUID = -3415549133677946887L;

    private User id;
    private String lastName;
    private String firstName;
    private String secondName;

    public UserInfo() {
    }

    @Override
    public User getId() {
        return id;
    }

    public void setId(User id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserInfo userInfo = (UserInfo) o;
//        return Objects.equals(id, userInfo.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(lastName, userInfo.lastName) &&
                Objects.equals(firstName, userInfo.firstName) &&
                Objects.equals(secondName, userInfo.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, secondName);
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

    @Override
    public int compareTo(UserInfo o) {
        return lastName.compareTo(o.lastName);
    }
}
