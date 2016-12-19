package ru.click.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "clients", schema = "common")
@Getter @Setter
public class Client implements BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "name")
    private String name;

    @Column(name = "male")
    private Boolean male;

    @Column(name = "comment")
    private String comment;

    @Column(name = "car_number")
    private String carNumber;

    @OneToMany(mappedBy = "client", fetch = LAZY)
    private List<Training> trainings;

    @OneToMany(mappedBy = "client", fetch = LAZY)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "client", fetch = LAZY)
    private List<ClientAbonement> abonements;

    @Transient
    private String fullName;

    @PostLoad
    private void setFullName() {
        this.fullName = this.getName() + " " + this.getSurname();
    }

    public String getFullName() {
        return fullName;
    }

}
