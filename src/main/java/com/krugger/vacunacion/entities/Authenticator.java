package com.krugger.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "vac_tauthenticator")//manejamos acronimo: 'vac'= esquema  y 'T' = Tabla
public class Authenticator implements Serializable {
    private static final long serialVersionUUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "pk_tauthenticator", strategy = "org.hibernate.id.UUIDGenerator ")
    @Comment("identifier primary key")
    @JsonIgnore
    @Column(name = "id", nullable = false)
    private UUID id;

    @Comment("Nickname employee ")
    @Column(name = "nickname", nullable = false, unique = true, length = 20)
    private String nickname;

    @Comment("Employee password ")
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Comment("Employee authentication ")
    @Column(name = "status", length = 1)
    private Boolean status = true;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public Authenticator(String nickname, String password, Employee employee,Role rol) {
        this.nickname = nickname;
        this.password = password;
        this.employee = employee;
        this.role=rol;
    }

    public Authenticator() {

    }
}
