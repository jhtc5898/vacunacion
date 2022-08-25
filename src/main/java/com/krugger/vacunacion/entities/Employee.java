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
public class Employee implements Serializable
{
   private static final long serialVersionUUID = 1L;

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "pk_employee", strategy = "org.hibernate.id.UUIDGenerator ")
   @Comment("identifier primary key")
   @Column(name = "id", nullable = false)
   private UUID id;

   @Comment("identification document")
   @Column(name = "identification_card", length = 10, nullable = false)
   private String identification_card;

   @Comment("Employee first name")
   @Column(name = "first_name", length = 50, nullable = false)
   private String first_name;

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
   @Column(name = "status", length = 1, nullable = false)
   private Boolean status;

   @Comment("Description status employee ")
   @Column(name = "status_descripction", length = 20)
   private String status_descripction;

   @Comment("Employe status vaccinated:  1 = Active  2 =Disabled  ")
   @Column(name = "status_vaccinated", length = 1, nullable = false)
   private Boolean status_vaccinated;

   @Comment("Employe status vaccinated descripction:    ")
   @Column(name = "vaccinated_descripction", length = 20)
   private String vaccinated_descripction;

   @Comment("Employee date birth")
   @Temporal(TemporalType.DATE)
   @Column(name = "date_birth")
   private Date date_birth;




}
