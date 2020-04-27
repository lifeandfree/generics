package ru.innopolis.model;

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
    public String toString() {
        return "ExtraUserInfo{" +
                "address='" + address + '\'' +
                '}';
    }
}
