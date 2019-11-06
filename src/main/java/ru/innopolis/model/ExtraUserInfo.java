package ru.innopolis.model;

import java.util.Objects;

/**
 * ExtraUserInfo.
 *
 * @author Ilya_Sukhachev
 */
public class ExtraUserInfo extends UserInfo {

    String address;

    public ExtraUserInfo() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtraUserInfo that = (ExtraUserInfo) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }

    @Override
    public String toString() {
        return "ExtraUserInfo{" +
                "address='" + address + '\'' +
                '}';
    }
}
