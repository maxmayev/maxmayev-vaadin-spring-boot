package com.maxmayev.application.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "employee")

public class Employee {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private String idString;
    private String firstname;
    private String lastname;
    private String email;
    private String title;
    @JsonIgnore
    private String notes = "";

    public Employee() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + "(" + email + ")";
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * When transmitting ID to the client we need a data type that is supported
     * (long is not supported in JavaScript)
     *
     * @return String representation if {@link #id}
     */
    public String getIdString() {
        return id == null ? null : id.toString();
    }

    public void setIdString(String idString) {
        // no-op
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        } else {
            return id.intValue();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || id == null) {
            return false;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }

        if (id.equals(((Employee) obj).id)) {
            return true;
        }
        return false;
    }
}
