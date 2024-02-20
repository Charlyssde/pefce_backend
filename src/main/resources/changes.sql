alter table public.a_proyectos_historico
    add i_file bool default false;

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
