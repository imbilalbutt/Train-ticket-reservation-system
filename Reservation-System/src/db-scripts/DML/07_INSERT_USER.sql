
INSERT INTO users(
    user_id,
      created_by,
      created_date,
      is_non_locked,
      modified_by,
      modified_date,
      active_status,
      cell_number,
      cnic_number,
      email,
      first_name,
      home_address,
      last_name,
      password,
      reconfirm_password,
      roles,
      username)
Values(1,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       '+13040000100',
       '800007000001',
       'email@yahoo.com',
       'Bilal Ahmad',
       'Home Address Road',
       'Butt',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',

--     password@123
    '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       'user',
       'imbilalbutt');



INSERT INTO users(
    user_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    cell_number,
    cnic_number,
    email,
    first_name,
    home_address,
    last_name,
    password,
    reconfirm_password,
    roles,
    username)
Values(2,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       '+13040000100',
       '800007000001',
       'email2@yahoo.com',
       'Bilal Ahmad',
       'Home Address Road',
       'Butt',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',

--     password@123
       '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       'user',
       'imbilalbutt2');