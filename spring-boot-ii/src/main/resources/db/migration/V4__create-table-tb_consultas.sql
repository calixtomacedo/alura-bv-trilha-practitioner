create table tb_consultas(
    id bigint not null auto_increment,
    id_medico bigint not null,
    id_paciente bigint not null,
    data_agendamento datetime not null,

    primary key(id),
    constraint fk_consultas_medicos foreign key(id_medico) references tb_medicos(id),
    constraint fk_consultas_pacientes foreign key(id_paciente) references tb_pacientes(id)
);