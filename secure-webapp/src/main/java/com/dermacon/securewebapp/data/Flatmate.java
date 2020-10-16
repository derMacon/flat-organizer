package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Flatmate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flatmateId;

    private String firstname;
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private String surname;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    Flatmate() {
    }

    public Flatmate(String firstname, String surname, Date birthday, Room room, User user) {
        this.firstname = firstname;
        this.surname = surname;
        this.birthday = birthday;
        this.room = room;
        this.user = user;
    }

    public long getFlatmateId() {
        return flatmateId;
    }

    public void setFlatmateId(long flatmateId) {
        this.flatmateId = flatmateId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Flatmate{" +
                "flatmate_id=" + flatmateId +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", room=" + room +
                ", user=" + user +
                '}';
    }
}
