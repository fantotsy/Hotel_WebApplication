package ua.fantotsy.entities;

/**
 * Class {@code Guest} stores information about guests.
 *
 * @author fantotsy
 * @version 1.0
 */
public class Guest {
    private Integer guestId;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String login;
    private String password;

    public Guest() {
    }

    public Guest(String name, String lastName, String phoneNumber, String email, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Guest(Integer guestId, String name, String lastName, String phoneNumber, String email, String login) {
        this.guestId = guestId;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
    }

    public Guest(String name, String lastName, String phoneNumber, String email, String login) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
    }

    public Guest(String name, String lastName, String login) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;

        Guest guest = (Guest) o;

        if (!guestId.equals(guest.guestId)) return false;
        if (!name.equals(guest.name)) return false;
        if (!lastName.equals(guest.lastName)) return false;
        if (!phoneNumber.equals(guest.phoneNumber)) return false;
        if (!email.equals(guest.email)) return false;
        if (!login.equals(guest.login)) return false;
        return password.equals(guest.password);
    }

    @Override
    public int hashCode() {
        int result = guestId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}