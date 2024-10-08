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


--CREACION DE LAS TABLAS PARA ENCUESTA
CREATE TABLE encuestas (
	id serial4 NOT NULL,
	nombre varchar NULL,
	descripcion varchar NULL,
	fecha_creacion date NULL,
	creado_por int8 NULL,
	CONSTRAINT encuestas_pkey PRIMARY KEY (id),
	CONSTRAINT encuestas_creado_por_fkey FOREIGN KEY (creado_por) REFERENCES a_usuarios(id)
);



CREATE TABLE empresas_encuestas (
	id serial4 NOT NULL,
	id_empresa int8 NULL,
	id_encuesta int8 NULL,
	CONSTRAINT empresas_encuestas_pkey PRIMARY KEY (id),
	CONSTRAINT empresas_encuestas_id_empresa_fkey FOREIGN KEY (id_empresa) REFERENCES a_empresas(id),
	CONSTRAINT empresas_encuestas_id_encuesta_fkey FOREIGN KEY (id_encuesta) REFERENCES encuestas(id)
);



CREATE TABLE preguntas (
	id serial4 NOT NULL,
	pregunta varchar NULL,
	fecha_creacion date NULL,
	id_encuesta int8 NULL,
	creado_por int8 NULL,
	CONSTRAINT preguntas_pkey1 PRIMARY KEY (id),
	CONSTRAINT preguntas_creado_por_fkey FOREIGN KEY (creado_por) REFERENCES a_usuarios(id),
	CONSTRAINT preguntas_id_encuesta_fkey FOREIGN KEY (id_encuesta) REFERENCES encuestas(id)
);

CREATE TABLE respuestas (
    id serial4 NOT NULL,
    respuesta int8 NULL,
    id_pregunta int8 NULL,
    id_empresa int8 NULL,
    id_encuesta int8 NULL,
    creado_por int8 NULL,
    CONSTRAINT respuestas_pkey PRIMARY KEY (id),
    CONSTRAINT respuestas_id_pregunta_fkey FOREIGN KEY (id_pregunta) REFERENCES preguntas(id),
    CONSTRAINT respuestas_id_empresa_fkey FOREIGN KEY (id_empresa) REFERENCES a_empresas(id),
    CONSTRAINT respuestas_id_encuesta_fkey FOREIGN KEY (id_encuesta) REFERENCES encuestas(id),
    CONSTRAINT respuestas_creado_por_fkey FOREIGN KEY (creado_por) REFERENCES a_usuarios(id)
);

--CREACION DE LAS TABLAS PARA MINUTAS
CREATE TABLE a_usuario__a_minuta (
     id serial4 NOT NULL,
     minuta_id int8 NULL,
     usuario_id int8 NULL,
     CONSTRAINT a_usuario__a_minuta_pkey PRIMARY KEY (id),
     CONSTRAINT a_usuario__a_minuta_minuta_id_fkey FOREIGN KEY (minuta_id) REFERENCES a_minutas(id),
     CONSTRAINT a_usuario__a_minuta_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES a_usuarios(id)
);

CREATE TABLE eventos_encuestas (
    id SERIAL PRIMARY KEY,
    id_evento int8,
    id_encuesta int8,
    FOREIGN KEY (id_evento) REFERENCES a_eventos(id),
    FOREIGN KEY (id_encuesta) REFERENCES encuestas(id)
);


--MODIFICACION A TABLA a_solicitudes
alter table a_solicitudes
    add perfil_id integer;

alter table a_solicitudes
    add constraint a_solicitudes_perfil_id_fk
        foreign key (perfil_id) references a_perfiles;

/************************ABRIL 2024***********************************/

CREATE TABLE public.a_dsp (
	id int GENERATED ALWAYS AS IDENTITY NOT NULL,
	solicitud_sefiplan timestamp DEFAULT now() NULL,
	numero_dsp varchar NULL,
	autorizacion timestamp DEFAULT now() NULL,
	recepcion timestamp DEFAULT now() NULL,
	importe float4 NULL,
	descripcion varchar NULL,
	concepto varchar NULL,
	codigo_presupuestal varchar NULL,
	vigencia timestamp DEFAULT now() NULL,
	solicitud timestamp DEFAULT null NULL,
	solicitud_prorroga timestamp DEFAULT null NULL,
	oficio_prorroga timestamp DEFAULT null NULL,
	autorizacion_prorroga timestamp DEFAULT null NULL,
	recepcion_prorroga timestamp DEFAULT null NULL,
	file varchar NULL,
	CONSTRAINT a_dsp_pk PRIMARY KEY (id)
);

ALTER TABLE public.a_dsp ALTER COLUMN file TYPE int4 USING file::int4;
ALTER TABLE public.a_dsp ADD CONSTRAINT a_dsp_a_archivos_fk FOREIGN KEY (file) REFERENCES public.a_archivos(id);

/**********************11/07/2024*****************/
ALTER TABLE public.a_archivos ALTER COLUMN url DROP NOT NULL;
ALTER TABLE public.a_archivos ALTER COLUMN mime DROP NOT NULL;
ALTER TABLE public.a_archivos ALTER COLUMN created_at DROP NOT NULL;
ALTER TABLE public.a_archivos ALTER COLUMN estatus DROP NOT NULL;

