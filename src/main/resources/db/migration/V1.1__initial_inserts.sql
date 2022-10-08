-- INSERT COUNTRY
INSERT INTO COUNTRY(st_name) VALUES('Brazil');

-- INSERT STATE
INSERT INTO STATE(st_name, id_country) VALUES('Sao Paulo', 1);

-- INSERT CITY
INSERT INTO CITY(st_name, id_state) VALUES('Sao Paulo', 1);

-- INSERT ROLE
INSERT INTO ROLE(st_name) VALUES('Administrator');

-- INSERT ENTITY_TYPE
INSERT INTO ENTITY_TYPE(st_entity_type, st_desc) VALUES('E', 'Employee');
INSERT INTO ENTITY_TYPE(st_entity_type, st_desc) VALUES('C', 'Customer');

-- INSERT ADM ADDRESS
INSERT INTO ADDRESS(st_address, nm_number, st_zipcode, id_city) 
VALUES('Rua Xpto', 70, '04824100', 1);

-- INSERT ADM USER
INSERT INTO ENTITY(st_name, st_entity_type, id_address) VALUES('Carlos Maciel', 'E', 1);

-- GIVEN ADM ROLE
INSERT INTO ENTITY_ROLE(id_entity, id_role) VALUES(1,1);