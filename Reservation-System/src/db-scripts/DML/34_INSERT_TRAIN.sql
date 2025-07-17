
SELECT * FROM TRAIN;

INSERT INTO train(
    train_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    train_code,
    fk_destination_station_id,
    fk_origin_station_id
)
Values(1,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'LHE19_to_FSD19',
       2,
    1
      );

INSERT INTO train(
    train_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    train_code,
    fk_destination_station_id,
    fk_origin_station_id
)
Values(2,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'FSD19_to_LHE19',
       1,
       2
      );


INSERT INTO train(
    train_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    train_code,
    fk_destination_station_id,
    fk_origin_station_id
)
Values(3,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'LHE19_to_SKT100',
        3,
       1
      );

INSERT INTO train(
    train_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    train_code,
    fk_destination_station_id,
    fk_origin_station_id
)
Values(4,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SKT100_to_LHE19',
       3,
       1
      );

INSERT INTO train(
    train_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    train_code,
    fk_destination_station_id,
    fk_origin_station_id
)
Values(5,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'LHE19_to_DGK165',
       4,
       1
      );

INSERT INTO train(
    train_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    train_code,
    fk_destination_station_id,
    fk_origin_station_id
)
Values(6,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'DGK165_to_LHE19',
       1,
       4
      );