CREATE TABLE trabalhos (
  id BIGINT AUTO_INCREMENT NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  orientador VARCHAR(255) NOT NULL,
  coorientador VARCHAR(255),
  curso_id BIGINT(20) NOT NULL, 
  resumo VARCHAR(3000) NOT NULL,
  file_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE curso (
  id BIGINT AUTO_INCREMENT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE autor (
  id BIGINT AUTO_INCREMENT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE palavra (
  id BIGINT AUTO_INCREMENT NOT NULL,
  palavra VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE trabalhos
  ADD KEY FK_CURSO_ID (curso_id),
  ADD KEY FK_FILE_ID (file_id);

ALTER TABLE trabalhos
  ADD CONSTRAINT FK_CURSO_ID FOREIGN KEY (curso_id) REFERENCES curso (id),
  ADD CONSTRAINT FK_FILE_ID FOREIGN KEY (file_id) REFERENCES files (id);

CREATE TABLE trabalhos_tem_autores (
  trabalho_id bigint(20) NOT NULL,
  autor_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
CREATE TABLE trabalhos_tem_palavras (
  trabalho_id bigint(20) NOT NULL,
  palavra_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE trabalhos_tem_autores
  ADD KEY FK_TRABALHOS_TEM_AUTORES_ID (trabalho_id),
  ADD KEY FK_AUTORES_TEM_TRABALHOS_ID (autor_id);
  
ALTER TABLE trabalhos_tem_autores
  ADD CONSTRAINT FK_TRABALHOS_TEM_AUTORES_ID FOREIGN KEY (trabalho_id) REFERENCES trabalhos (id),
  ADD CONSTRAINT FK_AUTORES_TEM_TRABALHOS_ID FOREIGN KEY (autor_id) REFERENCES autor (id);
  
ALTER TABLE trabalhos_tem_palavras
  ADD KEY FK_TRABALHOS_TEM_PALAVRAS_ID (trabalho_id),
  ADD KEY FK_PALAVRAS_TEM_TRABALHOS_ID (palavra_id);
  
ALTER TABLE trabalhos_tem_palavras
  ADD CONSTRAINT FK_TRABALHOS_TEM_PALAVRAS_ID FOREIGN KEY (trabalho_id) REFERENCES trabalhos (id),
  ADD CONSTRAINT FK_PALAVRAS_TEM_TRABALHOS_ID FOREIGN KEY (palavra_id) REFERENCES palavra (id);
  
COMMIT;


INSERT INTO curso VALUES (1, 'Integrado em Informática');
INSERT INTO curso VALUES (2, 'Integrado em Guia de Turismo');
INSERT INTO curso VALUES (3, 'Subsequente em Agropecuária');
INSERT INTO curso VALUES (4, 'Subsequente em Alimentos');
INSERT INTO curso VALUES (5, 'Subsequente em Agrimensura');
INSERT INTO curso VALUES (6, 'Superior em Agroecologia');
INSERT INTO curso VALUES (7, 'Superior em Gestão em Turismo');
INSERT INTO curso VALUES (8, 'Superior em Engenharia de Alimentos');
INSERT INTO curso VALUES (9, 'EAD em Vendas');

COMMIT;