

SELECT * FROM EMPLOYEE;


INSERT INTO employee(
    employee_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    office_address,
    office_number,
    office_start_date,
    fk_user_id

)
Values(1,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'Staffelweg',
       3,
       '2020-10-01T09:15:30.000',
       null
      );

INSERT INTO employee(
    employee_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    office_address,
    office_number,
    office_start_date,
    fk_user_id

)
Values(2,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'Carl Thierstrasse',
       125,
       '2020-10-01T09:15:30.000',
       null
      );
