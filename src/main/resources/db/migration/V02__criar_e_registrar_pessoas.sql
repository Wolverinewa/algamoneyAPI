CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo TINYINT NOT NULL,
	logradouro VARCHAR(200) NULL,
	numero VARCHAR(10) NULL,
	complemento VARCHAR(100) NULL,
	bairro VARCHAR(100) NULL,
	cep VARCHAR(9) NULL,
	cidade VARCHAR(100) NULL,
	estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	values ('Willian Adão', true, 'R. Visconde de Inhomerim', '59', 
			'casa - Próximo ao Shopping Dom Pedro', 'Jd. Santa Genebra', 
			'13080-590', 'Campinas', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	values ('Marcia Adão', true, 'R. Visconde de Inhomerim', '59', 
			'casa - Próximo ao Shopping Dom Pedro', 'Jd. Santa Genebra', 
			'13080-590', 'Campinas', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	values ('Danilo Adão', true, 'R. Visconde de Inhomerim', '59', 
			'casa - Próximo ao Shopping Dom Pedro', 'Jd. Santa Genebra', 
			'13080-590', 'Campinas', 'SP');
			