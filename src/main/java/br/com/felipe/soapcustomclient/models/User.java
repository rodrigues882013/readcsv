package br.com.felipe.soapcustomclient.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "id")
    private Integer Id;

    @Column(name = "username")
    private String username;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Id != null ? !Id.equals(user.Id) : user.Id != null) return false;
        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
