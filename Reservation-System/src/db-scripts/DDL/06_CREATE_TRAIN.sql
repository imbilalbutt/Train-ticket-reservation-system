CREATE TABLE IF NOT EXISTS train
(
    train_id BIGSERIAL PRIMARY KEY,

    created_by VARCHAR(255),
    created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP WITH TIME ZONE,
    active_status BOOLEAN DEFAULT TRUE,

    train_code VARCHAR(255),

    fk_destination_station_id bigint,
    fk_origin_station_id bigint,
    CONSTRAINT train_primary_key PRIMARY KEY (train_id),
    CONSTRAINT foreign_key_station_destination FOREIGN KEY (fk_destination_station_id)
    REFERENCES station (station_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION,
    CONSTRAINT foreign_key_station_origin FOREIGN KEY (fk_origin_station_id)
    REFERENCES station (station_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )
