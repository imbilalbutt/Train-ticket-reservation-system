CREATE TABLE IF NOT EXISTS public.employee_taught_course
(
    fk_employee_id bigint NOT NULL,
    fk_course_id bigint NOT NULL,
    CONSTRAINT fkc8m4xyi45xq35w1n5q2efceuv FOREIGN KEY (fk_employee_id)
    REFERENCES public.employee (employee_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fksgmsqwd6thcjoujlw23rhw4eo FOREIGN KEY (fk_course_id)
    REFERENCES public.course (course_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)