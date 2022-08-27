package com.krugger.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "vac_trole") //manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class Role implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "pk_role", strategy = "org.hibernate.id.UUIDGenerator ")
    @Comment("identifier primary key")
    @JsonIgnore
    @Column(name = "id", nullable = false)
    private UUID id;

    @Comment("Employee role name")
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Comment("Role status:  1 = Active  2 =Disabled  ")
    @Column(name = "status", length = 1, nullable = false)
    private Boolean status;

    public Role(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public Role() {

    }
}
