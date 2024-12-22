package StudentManegementSystem;

import java.util.Objects;

public class User {


    private String username;
    private String password;
    private String personid;
    private String phoneNumber;

    public User() {
    }

    public User(String username, String password, String personid, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.personid = personid;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return personid
     */
    public String getPersonId() {
        return personid;
    }

    /**
     * 设置
     * @param personid
     */
    public void setPersonId(String personid) {
        this.personid =personid ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && personid.equals(user.personid) && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, personid, phoneNumber);
    }
}
