CREATE SCHEMA vacunacion;


CREATE TABLE IF NOT EXISTS vacunacion.product
(
    product_id
    INT
    NOT
    NULL,
    name
    varchar
(
    250
) NOT NULL,
    PRIMARY KEY
(
    product_id
)
    );
