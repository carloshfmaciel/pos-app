DROP TABLE IF EXISTS COUNTRY;
CREATE TABLE COUNTRY (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  st_name varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS STATE;
CREATE TABLE STATE (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  st_name varchar(255) NOT NULL,
  id_country int NOT NULL,
  foreign key (id_country) references COUNTRY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS CITY;
CREATE TABLE CITY (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  st_name varchar(255) NOT NULL,
  id_state int not null,
  foreign key (id_state) references STATE(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  st_name varchar(255) NOT NULL,
  job_role varchar(255) NOT NULL,
  dt_admission DATE NOT NULL,
  ts_start_period TIME NOT NULL,
  ts_end_period TIME NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ENTITY_TYPE`;
CREATE TABLE `ENTITY_TYPE` (  
  st_entity_type varchar(2) NOT NULL,
  st_desc varchar(255) NOT NULL,
  UNIQUE KEY ENTITY_TYPE(st_entity_type)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ENTITY;
CREATE TABLE ENTITY (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  st_name varchar(255) NOT NULL,
  st_entity_type varchar(2) NOT NULL,
  foreign key (st_entity_type) references ENTITY_TYPE(st_entity_type)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  id_entity int NOT NULL,
  st_address varchar(100) NOT NULL,
  nm_number int NOT NULL,
  st_zipcode varchar(10) NOT NULL,
  id_city int NOT NULL,
  foreign key (id_entity) references ENTITY(id),
  foreign key (id_city) references CITY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ROLE;
CREATE TABLE ROLE (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  st_name varchar(30) NOT NULL,
  UNIQUE KEY ROLE_NAME (st_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ENTITY_ROLE;
CREATE TABLE ENTITY_ROLE (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  id_entity int NOT NULL,
  id_role int NOT NULL,
  foreign key (id_entity) references ENTITY(id),
  foreign key (id_role) references ROLE(id),
  UNIQUE UQ_ROLE_ENTITY (id_entity, id_role)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ORDER`;
CREATE TABLE `ORDER` (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  id_customer int NOT NULL,
  foreign key (id_customer) references ENTITY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `SCHEDULE_DELIVERY`;
CREATE TABLE `SCHEDULE_DELIVERY` (  
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  id_order int NOT NULL,
  id_employee int NOT NULL,
  dt_delivery DATE NOT NULL,
  foreign key (id_order) references `ORDER`(id),
  foreign key (id_employee) references `ENTITY`(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;