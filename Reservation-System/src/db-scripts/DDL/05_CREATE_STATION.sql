CREATE TABLE IF NOT EXISTS station
(
    station_id BIGSERIAL PRIMARY KEY,

    created_by VARCHAR(255),
    created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP WITH TIME ZONE,
    active_status BOOLEAN DEFAULT TRUE,

    city VARCHAR(100),
    station_code VARCHAR(100)

--     CONSTRAINT station_primary_key PRIMARY KEY (station_id)
)