CREATE TABLE tb_filmes
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)          NOT NULL,
    categoria VARCHAR(255)          NOT NULL,
    nota      DOUBLE                NOT NULL,
    CONSTRAINT pk_tb_filmes PRIMARY KEY (id)
);