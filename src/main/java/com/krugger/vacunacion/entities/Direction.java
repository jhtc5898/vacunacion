package com.krugger.vacunacion.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "vac_tdirection") //Manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class Direction implements Serializable
{
   private static final long serialVersionUUID = 1L;

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "pk_direction", strategy = "org.hibernate.id.UUIDGenerator ")
   @Comment("identifier primary key")
   @Column(name = "id", nullable = false)
   private UUID id;

   @Comment("Primary street employee ")
   @Column(name = "primary_street", length = 20, columnDefinition = "varchar(20) default 'N/A' ")
   private String primary_street;

   @Comment("Secondary street employee ")
   @Column(name = "secondary_street", length = 20, columnDefinition = "varchar(20) default 'N/A' ")
   private String secondary_street;

   @Comment("Employed postal code ")
   @Column(name = "postal_code", length = 20, columnDefinition = "varchar(20) default 'N/A' ")
   private String postal_code;

   @Comment("City where the employee resides ")
   @Column(name = "city", length = 20, nullable = false)
   private String city;

   @Comment("Province where the employee resides ")
   @Column(name = "province", length = 20, nullable = false)
   private String province;

   @Comment("Country where the employee resides ")
   @Column(name = "country", length = 30, nullable = false)
   private String country;

   @ManyToOne
   @JoinColumn(name = "id_employe")
   private Employee id_employe;


}
