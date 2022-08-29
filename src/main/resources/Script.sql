CREATE SCHEMA vacunacion;


-- vacunacion.vac_temployee definition

-- Drop table

-- DROP TABLE vacunacion.vac_temployee;

CREATE TABLE vacunacion.vac_temployee (
	id uuid NOT NULL,
	date_birth date NULL,
	email varchar(50) NOT NULL,
	first_surname varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
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


-- vacunacion.vac_trole definition

-- Drop table

-- DROP TABLE vacunacion.vac_trole;

CREATE TABLE vacunacion.vac_trole (
	id uuid NOT NULL,
	"name" varchar(20) NOT NULL,
	status bool NOT NULL,
	CONSTRAINT vac_trole_pkey PRIMARY KEY (id)
);


-- vacunacion.vac_ttipe_vaccine definition

-- Drop table

-- DROP TABLE vacunacion.vac_ttipe_vaccine;

CREATE TABLE vacunacion.vac_ttipe_vaccine (
	id uuid NOT NULL,
	name_vaccine varchar(50) NOT NULL,
	status bool NULL DEFAULT true,
	CONSTRAINT vac_ttipe_vaccine_pkey PRIMARY KEY (id)
);


-- vacunacion.vac_tauthenticator definition

-- Drop table

-- DROP TABLE vacunacion.vac_tauthenticator;

CREATE TABLE vacunacion.vac_tauthenticator (
	id uuid NOT NULL,
	nickname varchar(20) NOT NULL,
	"password" varchar(20) NOT NULL,
	status bool NULL,
	id_employee uuid NULL,
	id_role uuid NULL,
	CONSTRAINT uk_gx3x9qkpmav7297dd99yopaqm UNIQUE (nickname),
	CONSTRAINT vac_tauthenticator_pkey PRIMARY KEY (id),
	CONSTRAINT fk99eaq6mj5uqum34vgc01talvr FOREIGN KEY (id_role) REFERENCES vacunacion.vac_trole(id),
	CONSTRAINT fknl96rn3xesf3qmp1j2nryivs2 FOREIGN KEY (id_employee) REFERENCES vacunacion.vac_temployee(id)
);


-- vacunacion.vac_tdirection definition

-- Drop table

-- DROP TABLE vacunacion.vac_tdirection;

CREATE TABLE vacunacion.vac_tdirection (
	id uuid NOT NULL,
	city varchar(20) NOT NULL,
	country varchar(30) NOT NULL,
	postal_code varchar(20) NULL DEFAULT 'N/A'::character varying,
	primary_street varchar(20) NULL DEFAULT 'N/A'::character varying,
	province varchar(20) NOT NULL,
	secondary_street varchar(20) NULL DEFAULT 'N/A'::character varying,
	status bool NOT NULL,
	id_employe uuid NOT NULL,
	CONSTRAINT vac_tdirection_pkey PRIMARY KEY (id),
	CONSTRAINT fkdwgbfe8yaxpw0lyvmjl86xc9c FOREIGN KEY (id_employe) REFERENCES vacunacion.vac_temployee(id)
);


-- vacunacion.vac_tphone definition

-- Drop table

-- DROP TABLE vacunacion.vac_tphone;

CREATE TABLE vacunacion.vac_tphone (
	id uuid NOT NULL,
	phone_home varchar(15) NULL,
	phone_priority varchar(15) NOT NULL,
	phone_priority_operator varchar(20) NULL DEFAULT 'N/A'::character varying,
	status bool NOT NULL,
	id_employee uuid NOT NULL,
	CONSTRAINT vac_tphone_pkey PRIMARY KEY (id),
	CONSTRAINT fkok554hjsqi737m2erq3c0cxlc FOREIGN KEY (id_employee) REFERENCES vacunacion.vac_temployee(id)
);


-- vacunacion.vac_tvaccine definition

-- Drop table

-- DROP TABLE vacunacion.vac_tvaccine;

CREATE TABLE vacunacion.vac_tvaccine (
	id uuid NOT NULL,
	date_vaccine date NULL,
	number_doses numeric(1) NULL,
	id_employee uuid NULL,
	id_tipe_vaccine uuid NULL,
	CONSTRAINT vac_tvaccine_pkey PRIMARY KEY (id),
	CONSTRAINT fkp0234mwk041y9u5x3g2wlsjxw FOREIGN KEY (id_employee) REFERENCES vacunacion.vac_temployee(id),
	CONSTRAINT fksye2fvrjc9e8i69sgs6jcw99n FOREIGN KEY (id_tipe_vaccine) REFERENCES vacunacion.vac_ttipe_vaccine(id)
);


INSERT INTO vacunacion.vac_ttipe_vaccine (id,name_vaccine,status) VALUES
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95180','Sputnik',true),
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95181','AstraZeneca',true),
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95183','Pfizer',true),
	 ('ddf115c6-4e16-4455-bfd6-09ba328e9518','Jhonson&Jhonson',true);

INSERT INTO vacunacion.vac_trole (id,"name",status) VALUES
	 ('4f418f99-268c-4df3-afb4-676275b280fa','ADMIN',true),
	 ('4f418f99-268c-4df3-afb4-676275b280ff','EMPLOYEE',true);


INSERT INTO vacunacion.vac_temployee (id,date_birth,email,first_surname,first_name,gender,identification_card,second_name,second_surname,status,status_descripction,status_vaccinated,vaccinated_descripction) VALUES
	 ('9f38ea39-a48a-46f4-9fca-06d170584b16','1999-01-04','johnecu15@gmail.com','tenesaca','john','M','0105598254','henry','criollo',true,NULL,true,NULL),
	 ('9f38ea39-a48a-46f4-9fca-06d170584b26','2015-06-12','juanandres@gmail.com','tenesaca','Juan','M','0105598270','Andres','criollo',true,NULL,true,NULL),
	 ('9f38ea39-a48a-46f4-9fca-06d170584b70','2098-06-12','silviaPatricia@gmail.com','criollo','silvia','F','0105598272','Patricia','Orellana',true,NULL,true,NULL),
	 ('9f38ea39-a48a-46f4-9fca-06d170584b71','2015-06-12','mariacastillo@gmail.com','guadalupe','Maria','F','0105598274','Castillo','Garnica',true,NULL,true,NULL),
	 ('9f38ea39-a48a-46f4-9fca-06d170584b72','2015-06-12','prueba1@gmail.com','Iniguez','Jorge','M','0105598275','Andres','Castro',true,NULL,true,NULL);
	 
	
INSERT INTO vacunacion.vac_tauthenticator (id,nickname,"password",status,id_employee,id_role) VALUES
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95180','ADMIN','123456789',true,'9f38ea39-a48a-46f4-9fca-06d170584b16','4f418f99-268c-4df3-afb4-676275b280fa'),
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95181','empleado1','987654321',true,'9f38ea39-a48a-46f4-9fca-06d170584b26','4f418f99-268c-4df3-afb4-676275b280ff'),
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95182','empleado2','987654321',true,'9f38ea39-a48a-46f4-9fca-06d170584b70','4f418f99-268c-4df3-afb4-676275b280ff'),
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95183','empleado3','987654321',true,'9f38ea39-a48a-46f4-9fca-06d170584b71','4f418f99-268c-4df3-afb4-676275b280ff'),
	 ('ddf115c6-4e16-4455-bfd6-9ba328e95184','ADMIN1','123456789',true,'9f38ea39-a48a-46f4-9fca-06d170584b72','4f418f99-268c-4df3-afb4-676275b280fa');
