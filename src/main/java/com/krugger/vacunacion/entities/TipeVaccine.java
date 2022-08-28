package com.krugger.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Data
@Table(name = "vac_ttipe_vaccine") //manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class TipeVaccine implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @JsonIgnore
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "pk_tipe_vaccine", strategy = "org.hibernate.id.UUIDGenerator ")
    @Comment("identifier primary key")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Comment("Name vaccine")
    @Column(name = "name_vaccine", length = 50, nullable = false)
    private String namevaccine;

    @Comment("Employe status:  1 = Active  2 =Disabled  ")
    @Column(name = "status", length = 1, columnDefinition = "boolean default true")
    private Boolean status = true;


}
