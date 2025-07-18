CREATE TABLE IF NOT EXISTS course
(
    course_id BIGSERIAL PRIMARY KEY,

    created_by VARCHAR(255),
    created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP WITH TIME ZONE,
    active_status BOOLEAN DEFAULT TRUE,

    course_code VARCHAR(255),
    course_name VARCHAR(255),
    credit_hours INT,

    CONSTRAINT course_pkey PRIMARY KEY (course_id)
)