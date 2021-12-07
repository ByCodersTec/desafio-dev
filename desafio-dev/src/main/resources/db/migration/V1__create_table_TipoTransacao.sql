create table `tipo_transacao` (
  `id_tipo` INT(10) AUTO_INCREMENT PRIMARY KEY ,
  `tipo` bigint not null,
  `descricao` varchar(100),
  `natureza` varchar(100),
  `sinal` varchar(1)
);