CREATE SCHEMA vacunacion;

CREATE TABLE vacunacion.vac_trole (
	id uuid NOT NULL,
	nome_rol varchar(20) NOT NULL,
	status bool NOT NULL,
	CONSTRAINT vac_trole_pkey PRIMARY KEY (id)
);

INSERT INTO vacunacion.vac_trole (id,nome_rol,status) VALUES
	 ('4f418f99-268c-4df3-afb4-676275b280fa','ADMIN',true);


CREATE TABLE vacunacion.vac_tauthenticator (
	id uuid NOT NULL,
	nickname varchar(20) NOT NULL,
	"password" varchar(20) NOT NULL,
	status bool NULL,
	id_employee uuid NULL,
	id_role uuid NULL,
	CONSTRAINT uk_gx3x9qkpmav7297dd99yopaqm UNIQUE (nickname),
	CONSTRAINT vac_tauthenticator_pkey PRIMARY KEY (id)
);

INSERT INTO vacunacion.vac_tauthenticator (id,nickname,"password",status,id_employee,id_role) VALUES
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95180','ADMIN','123456789',true,'9f38ea39-a48a-46f4-9fca-06d170584b16','4f418f99-268c-4df3-afb4-676275b280fa');



CREATE TABLE vacunacion.vac_temployee (
	id uuid NOT NULL,
	date_birth date NULL,
	email varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	first_surname varchar(50) NOT NULL,
	gender varchar(1) NULL,
	identification_card varchar(10) NOT NULL,
	second_name varchar(50) NOT NULL,
	second_surname varchar(50) NOT NULL,
	status bool NULL DEFAULT true,
	status_descripction varchar(20) NULL,
	status_vaccinated bool NULL,
	vaccinated_descripction varchar(20) NULL,
	CONSTRAINT uk_so273jv2p5vfhil0xn5hfbwks UNIQUE (identification_card),
	CONSTRAINT vac_temployee_pkey PRIMARY KEY (id)
);

INSERT INTO vacunacion.vac_temployee (id,date_birth,email,first_name,first_surname,gender,identification_card,second_name,second_surname,status,status_descripction,status_vaccinated,vaccinated_descripction) VALUES
	 ('9f38ea39-a48a-46f4-9fca-06d170584b16',NULL,'johnecu15@gmail.com','john','tenesaca',NULL,'0105598254','henry','criollo',true,NULL,NULL,NULL);
