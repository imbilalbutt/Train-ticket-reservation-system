CREATE TABLE IF NOT EXISTS employee_taught_course
(
    fk_employee_id BIGINT NOT NULL,
    fk_course_id BIGINT NOT NULL,

    CONSTRAINT foreign_key_employee FOREIGN KEY (fk_employee_id)
    REFERENCES employee (employee_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,

    CONSTRAINT foreign_key_course FOREIGN KEY (fk_course_id)
    REFERENCES course (course_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)