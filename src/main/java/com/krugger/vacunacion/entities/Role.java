package com.krugger.vacunacion.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "vac_trole") //manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class Role implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "pk_role", strategy = "org.hibernate.id.UUIDGenerator ")
    @Comment("identifier primary key")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Comment("Employee role name")
    @Column(name = "nome_rol", length = 20, nullable = false)
    private String nome_rol;

    @Comment("Role status:  1 = Active  2 =Disabled  ")
    @Column(name = "status", length = 1, nullable = false)
    private Boolean status;


}
