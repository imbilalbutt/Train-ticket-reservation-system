CREATE TABLE IF NOT EXISTS public.train
(
    train_id bigint NOT NULL DEFAULT nextval('train_train_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    locked boolean NOT NULL,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    active_status boolean,
    train_code character varying(255) COLLATE pg_catalog."default",
    fk_destination_station_id bigint,
    fk_origin_station_id bigint,
    CONSTRAINT train_pkey PRIMARY KEY (train_id),
    CONSTRAINT fk4dpir5t68x3uke4t6t75yarbp FOREIGN KEY (fk_destination_station_id)
    REFERENCES public.station (station_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION,
    CONSTRAINT fkkqb99ktl08rcx5cjtfly1nwe5 FOREIGN KEY (fk_origin_station_id)
    REFERENCES public.station (station_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )
