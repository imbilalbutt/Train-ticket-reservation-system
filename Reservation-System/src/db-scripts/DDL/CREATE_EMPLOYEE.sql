CREATE TABLE IF NOT EXISTS public.employee
(
    employee_id bigint NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    locked boolean NOT NULL,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    active_status boolean,
    office_address character varying(255) COLLATE pg_catalog."default",
    office_number character varying(255) COLLATE pg_catalog."default",
    office_start_date timestamp without time zone,
    fk_user_id bigint,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id),
    CONSTRAINT fkrhl5bwwaum53r69dem9ce036h FOREIGN KEY (fk_user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )
