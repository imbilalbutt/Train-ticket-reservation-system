CREATE TABLE IF NOT EXISTS public.station
(
    station_id bigint NOT NULL DEFAULT nextval('station_station_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    locked boolean NOT NULL,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    active_status boolean,
    city character varying(255) COLLATE pg_catalog."default",
    station_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT station_pkey PRIMARY KEY (station_id)
)