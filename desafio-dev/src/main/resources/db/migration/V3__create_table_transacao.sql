create table `transacao` (
                         `id_transacao` INT(10) AUTO_INCREMENT PRIMARY KEY ,
                         `id_tipo` INT(10) not null,
                         `data_transacao` date,
                         `valor_transacao` numeric(19,2),
                         `cpf_beneficiario` varchar(11),
                         `cartao` varchar(12),
                         `hora_transacao` time,
                         `nome_dono` varchar(14),
                         `nome_loja` varchar(19)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


alter table transacao add constraint FK_TipoTransacao foreign key (id_tipo) references tipo_transacao(id_tipo);
