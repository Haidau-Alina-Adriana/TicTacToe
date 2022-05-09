package jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Users.findByEmail",
                query = "select u from TictactoeUsersEntity u where u.email = :inputEmail"),
        @NamedQuery(name = "Users.findByUsername",
                query = "select u from TictactoeUsersEntity u where u.username = :inputUsername"),
})

@Entity
@Table(name = "TICTACTOE_USERS", schema = "STUDENT")
public class TictactoeUsersEntity {
    private long id;
    private String email;
    private String username;
    private String password;
    private Long score;
    private String salt;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "SCORE")
    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Basic
    @Column(name = "SALT")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TictactoeUsersEntity that = (TictactoeUsersEntity) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(score, that.score) && Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, score, salt);
    }

    @Override
    public String toString() {
        return "TictactoeUsersEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", salt='" + salt + '\'' +
                '}';
    }
}
