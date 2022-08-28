CREATE SCHEMA vacunacion;

INSERT INTO vacunacion.vac_ttipe_vaccine (id, name_vaccine, status)
VALUES ('ddf115c6-4e16-4455-bfd6-9ba328e95180', 'Sputnik', true),
       ('ddf115c6-4e16-4455-bfd6-9ba328e95181', 'AstraZeneca', true),
       ('ddf115c6-4e16-4455-bfd6-9ba328e95183', 'Pfizer', true),
       ('ddf115c6-4e16-4455-bfd6-09ba328e9518', 'Jhonson&Jhonson', true);

INSERT INTO vacunacion.vac_trole (id, "name", status)
VALUES ('4f418f99-268c-4df3-afb4-676275b280fa', 'ADMIN', true),
       ('4f418f99-268c-4df3-afb4-676275b280ff', 'EMPLOYEE', true);
