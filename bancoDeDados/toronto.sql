CREATE DATABASE IF NOT EXISTS dataPix;
USE dataPix;

CREATE TABLE pessoas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    cep CHAR(8),
    email VARCHAR(100),
    chave_pix VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(20) DEFAULT 'Ativo'
);

CREATE TABLE transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    remetente_id INT NOT NULL,
    destinatario_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Concluida',
    FOREIGN KEY (remetente_id) REFERENCES pessoas(id),
    FOREIGN KEY (destinatario_id) REFERENCES pessoas(id)
);

CREATE TABLE denuncias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    denunciante_id INT NOT NULL,
    denunciado_id INT NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (denunciante_id) REFERENCES pessoas(id),
    FOREIGN KEY (denunciado_id) REFERENCES pessoas(id)
);

CREATE TABLE vendedores_verificados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    data_verificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES pessoas(id)
);

CREATE TABLE logs_alertas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    mensagem VARCHAR(255) NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES pessoas(id)
);

INSERT INTO pessoas (nome, cpf, telefone, cep, email, chave_pix, status) VALUES
('Marcos Silva', '241.413.000-00', '12000000000', '00004567', 'marcos@email.com', 'marcos@pix', 'Confiavel'),
('Juliana Castro', '039.581.000-00', '21988900000', '02104000', 'juliana@email.com', 'juliana@pix', 'Ativo'),
('Carlos Alberto', '572.890.000-00', '31977800000', '03014000', 'carlos@email.com', 'carlos@pix', 'Suspeito'),
('Fernanda Lopes', '103.298.000-00', '47966700000', '08921000', 'fernanda@email.com', 'fernanda@pix', 'Ativo'),
('Ricardo Gomes', '123.456.000-00', '11944400000', '00005879', 'ricardo@email.com', 'ricardo@pix', 'Confiavel'),
('Patrícia Lima', '321.654.000-00', '81933300000', '05003000', 'patricia@email.com', 'patricia@pix', 'Suspeito'),
('Tiago Nogueira', '456.789.000-00', '64922200000', '06990000', 'tiago@email.com', 'tiago@pix', 'Bloqueado'),
('Camila Mendes', '741.852.000-00', '71911100000', '04015000', 'camila@email.com', 'camila@pix', 'Confiavel'),
('Luciano Pires', '852.963.000-00', '11900000000', '00001020', 'luciano@email.com', 'luciano@pix', 'Ativo'),
('Letícia Santos', '951.357.000-00', '21922200000', '02105000', 'leticia@email.com', 'leticia@pix', 'Confiavel');

INSERT INTO transacoes (remetente_id, destinatario_id, valor, status) VALUES
(1, 2, 500.00, 'Concluida'),
(2, 3, 1200.00, 'Concluida'),
(3, 4, 100.00, 'Suspeito'),
(5, 6, 250.00, 'Concluida'),
(7, 8, 600.00, 'Cancelado'),
(9, 10, 700.00, 'Concluida'),
(1, 3, 1500.00, 'Suspeito');

INSERT INTO denuncias (denunciante_id, denunciado_id, motivo) VALUES
(2, 3, 'Produto não entregue'),
(4, 6, 'Atividade suspeita'),
(8, 7, 'Golpe Pix');

INSERT INTO vendedores_verificados (usuario_id) VALUES
(1),
(5),
(8),
(10);

INSERT INTO logs_alertas (usuario_id, mensagem) VALUES
(3, 'Transação suspeita detectada'),
(6, 'Conta denunciada múltiplas vezes'),
(7, 'Conta temporariamente bloqueada');
