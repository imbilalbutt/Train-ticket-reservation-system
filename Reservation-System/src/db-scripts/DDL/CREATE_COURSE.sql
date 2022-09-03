CREATE TABLE IF NOT EXISTS public.course
(
    course_id bigint NOT NULL DEFAULT nextval('course_course_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    locked boolean NOT NULL,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    active_status boolean,
    course_code character varying(255) COLLATE pg_catalog."default",
    course_name character varying(255) COLLATE pg_catalog."default",
    credit_hours bigint,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
)