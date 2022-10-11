-- INSERT COUNTRY
INSERT INTO COUNTRY(st_name) VALUES('Brazil');

-- INSERT STATE
INSERT INTO STATE(st_name, id_country) VALUES('Sao Paulo', 1);

-- INSERT CITY
INSERT INTO CITY(st_name, id_state) VALUES('Sao Paulo', 1);

-- INSERT ROLE
--INSERT INTO ROLE(st_name) VALUES('ROLE_ADMIN');
--INSERT INTO ROLE(st_name) VALUES('ROLE_CLIENT');

-- INSERT ENTITY_TYPE
INSERT INTO ENTITY_TYPE(st_entity_type, st_desc) VALUES('E', 'Employee');
INSERT INTO ENTITY_TYPE(st_entity_type, st_desc) VALUES('C', 'Customer');