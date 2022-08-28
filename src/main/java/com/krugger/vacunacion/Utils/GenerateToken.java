package com.krugger.vacunacion.Utils;

import lombok.Data;

import org.springframework.stereotype.Component;

@Component
@Data
public class GenerateToken
{
    public static final String CLAVE="JohnTenesaca"; //Clave de cifrado dentro del proyecto.Ya que en el caso que se intente modificar por un intruso debe ingresar al servidor y decopilar para tener esta clave.
    public static final long TIEMPO_VIDA=300000; //5 Minutos
    public static final String ENCABEZADO="Authorization";
    public static final String PREFIJO_TOKEN="Bearer";
}
