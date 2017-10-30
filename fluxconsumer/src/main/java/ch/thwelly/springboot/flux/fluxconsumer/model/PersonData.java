
package ch.thwelly.springboot.flux.fluxconsumer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;

public class PersonData implements Serializable {

    private Long id;
    private String lastName;
    private String firstName;
    private String alias;
    private String email;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthsday;
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private Date createDate;
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private Date upDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        final PersonData that = (PersonData) o;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("lastName", lastName)
                .add("firstName", firstName)
                .add("alias", alias)
                .add("email", email)
                .add("birthsday", birthsday)
                .add("createDate", createDate)
                .add("upDate", upDate)
                .toString();
    }
}
