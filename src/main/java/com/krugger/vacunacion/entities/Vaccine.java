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
@Table(name = "vac_tvaccine") //manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class Vaccine implements Serializable
{
   private static final long serialVersionUUID = 1L;

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "pk_vaccine", strategy = "org.hibernate.id.UUIDGenerator ")
   @Comment("identifier primary key")
   @Column(name = "id", nullable = false)
   private UUID id;

   @Comment("Employee vaccine name ")
   @Column(name = "name", length = 20, nullable = false)
   private String name;

   @Comment("Employee date vaccine")
   @Temporal(TemporalType.DATE)
   @Column(name = "date_vaccine")
   private Date date_vaccine;

   @Comment("Employee number of doses")
   @Column(name = "number_doses",length =1,columnDefinition = "numeric(1,0)")
   private Long number_doses;

}
