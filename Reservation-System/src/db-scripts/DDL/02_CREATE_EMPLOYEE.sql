CREATE TABLE IF NOT EXISTS employee
(
    employee_id  BIGSERIAL PRIMARY KEY,

    created_by VARCHAR(255),
    created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP WITH TIME ZONE,
    active_status BOOLEAN DEFAULT TRUE,


    office_address VARCHAR(255),
    office_number VARCHAR(255),
    office_start_date timestamp without time zone,

    fk_user_id bigint,

--     CONSTRAINT employee_primary_key PRIMARY KEY (employee_id),

    CONSTRAINT foreign_key_user FOREIGN KEY (fk_user_id)
    REFERENCES users (user_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )
