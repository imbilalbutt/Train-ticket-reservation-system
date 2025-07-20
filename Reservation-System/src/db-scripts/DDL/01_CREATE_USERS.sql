
CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,

    -- Audit fields
    created_by VARCHAR(255),
    created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP WITH TIME ZONE,
    active_status BOOLEAN DEFAULT TRUE,

    -- User information
    cell_number VARCHAR(15),
    cnic_number VARCHAR(15),
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    home_address TEXT,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    reconfirm_password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    username VARCHAR(10) NOT NULL,

    -- Explicitly named constraints
    CONSTRAINT uk_users_cell_number UNIQUE (cell_number),
    CONSTRAINT uk_users_cnic_number UNIQUE (cnic_number),
    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT uk_users_username UNIQUE (username)
);