
package ch.thwelly.springboot.flux.fluxprovider.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "person")
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 150, nullable = false)
    private String lastName;
    @Column(name = "lastname", length = 100, nullable = false)
    private String firstName;
    @Column(name = "alias", length = 100)
    private String alias;
    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Column(name = "birthsday")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthsday;

    @Column(name = "created", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private Date createDate;
    @Column(name = "updated")
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private Date upDate;

    public Long getId() {
        return id;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthsday() {
        return birthsday;
    }

    public void setBirthsday(Date birthsday) {
        this.birthsday = birthsday;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonEntity that = (PersonEntity) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(lastName, that.lastName) &&
                Objects.equal(firstName, that.firstName) &&
                Objects.equal(alias, that.alias) &&
                Objects.equal(email, that.email) &&
                Objects.equal(birthsday, that.birthsday) &&
                Objects.equal(createDate, that.createDate) &&
                Objects.equal(upDate, that.upDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, lastName, firstName, alias, email, birthsday, createDate, upDate);
    }
}
