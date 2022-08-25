package com.krugger.vacunacion.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "vac_tphone")
public class Phone implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "pk_phone", strategy = "org.hibernate.id.UUIDGenerator ")
   @Comment("identifier primary key")
   @Column(name = "id", nullable = false)
   private UUID id;

   @Comment("Employee phone priority ")
   @Column(name = "phone_priority", length = 15, nullable = false)
   private String phone_priority;

   @Comment("Employee phone priority ")
   @Column(name = "phone_priority_operator", length = 20,columnDefinition = "varchar(20) default 'N/A' ")
   private String phone_priority_operator;

   @Comment("Employee home phone ")
   @Column(name = "phone_home", length = 15)
   private String phone_home;

   @Comment("Phone status:  1 = Active  2 =Disabled  ")
   @Column(name = "status", length = 1, nullable = false)
   private Boolean status;

   @ManyToOne
   @JoinColumn(name = "id_employee", nullable = false)
   private Employee id_employee;


}
