package com.krugger.vacunacion.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "vac_temployee") //manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class Employee implements Serializable {

    private static final long serialVersionUUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "pk_employee", strategy = "org.hibernate.id.UUIDGenerator ")
    @Comment("identifier primary key")
    @Column(name = "id", nullable = false)
    private UUID id;
    @Comment("identification document")
    @Column(name = "identification_card", nullable = false, unique = true, length = 10)
    private String identificationCard;
    @Comment("Employee first name")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstname;
    @Comment("Employee second name")
    @Column(name = "second_name", length = 50, nullable = false)
    private String second_name;
    @Comment("Employee first surname")
    @Column(name = "first_surname", length = 50, nullable = false)
    private String first_surname;
    @Comment("Employee second surname")
    @Column(name = "second_surname", length = 50, nullable = false)
    private String second_surname;
    @Comment("Employee mail")
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Comment("Employe status:  1 = Active  2 =Disabled  ")
    @Column(name = "status", length = 1, columnDefinition = "boolean default true")
    private Boolean status = true;
    @Comment("Employe gender:   M =Masculino F = Femenino")
    @Column(name = "gender", length = 1)
    private String gender;
    @Comment("Description status employee ")
    @Column(name = "status_descripction", length = 20)
    private String status_descripction;
    @Comment("Employe status vaccinated:  1 = Active  2 =Disabled  ")
    @Column(name = "status_vaccinated", length = 1)
    private Boolean status_vaccinated;
    @Comment("Employe status vaccinated descripction:    ")
    @Column(name = "vaccinated_descripction", length = 20)
    private String vaccinated_descripction;
    @Comment("Employee date birth")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_birth")
    private Date date_birth;

    public Employee() {

    }

    public Employee(String identificationCard, String firstname, String second_name, String first_surname, String second_surname, String email) {
        this.identificationCard = identificationCard;
        this.firstname = firstname;
        this.second_name = second_name;
        this.first_surname = first_surname;
        this.second_surname = second_surname;
        this.email = email;
    }

    public Employee(UUID id, String identificationCard, String firstname, String second_name, String first_surname, String second_surname, String email) {
        this.id = id;
        this.identificationCard = identificationCard;
        this.firstname = firstname;
        this.second_name = second_name;
        this.first_surname = first_surname;
        this.second_surname = second_surname;
        this.email = email;
    }
}
