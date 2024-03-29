alter table public.a_proyectos_historico
    add is_file bool default false;

--MODIFICACION A TABLA a_minuta__a_tarea
ALTER TABLE public.a_minuta__a_tarea ADD id int8 NULL;
----MODIFICACION A TABLA a_agenda__a_tarea
ALTER TABLE public.a_agenda__a_tarea ADD id int8 NULL;

-----MODIFICACION TABLA a_minuta__a_archivo
ALTER TABLE public.a_minuta__a_archivo ADD url varchar NULL;
ALTER TABLE public.a_minuta__a_archivo ADD tipo varchar NULL;
ALTER TABLE public.a_minuta__a_archivo ADD tamaño varchar NULL;
ALTER TABLE public.a_minuta__a_archivo ALTER COLUMN tamaño TYPE int8 USING tamaño::int8;


-- MODIFICACION A TABLA  a_empresas
ALTER TABLE public.a_empresas
ADD autorizado boolean DEFAULT false NULL;


--Tabla eventos_participantes--
alter table a_evento__a_participantes
    add activo boolean;

alter table a_evento__a_participantes
    add males integer;

alter table a_evento__a_participantes
    add females integer;

alter table a_evento__a_participantes
    add id_municipio integer;

alter table a_evento__a_participantes
    add constraint a_evento__a_participantes_a_municipios_id_fk
        foreign key (id_municipio) references a_municipios;

alter table a_evento__a_participantes
    add id integer generated always as identity;

alter table a_evento__a_participantes
    add estatus varchar(255);

alter table a_evento__a_participantes
    drop constraint a_evento__a_participantes_pk;

alter table a_evento__a_participantes
    add constraint a_evento__a_participantes_pk
        primary key (id);

alter table a_evento__a_participantes
    add created_at timestamp default now();

alter table a_evento__a_participantes
    add updated_at timestamp default now();

