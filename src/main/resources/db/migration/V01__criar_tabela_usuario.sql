CREATE TABLE usuarios (
  id BIGINT AUTO_INCREMENT NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  perfil VARCHAR(10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
INSERT INTO usuarios VALUES (1, 'admin@admin.com','$2a$10$VHRLcO99ddKCnDmYPx7EJOFzqoysDt0pXpflg.WUEH8IOkkCZX1g.','ADMIN')