-- =====================================================================
-- Backfill config_opsdata_columns for Overflying Routes (opsdata_table_id = 5)
-- Target : Nesma UAT (ne-uat-altradoc)   Source : RB UAT (15 rows / 9 active)
-- opsdata_column_id + audit columns use table defaults; idempotent per row.
-- Review the SELECT result before COMMIT.
-- =====================================================================
BEGIN;

-- seq 1: overflying_country (Overflying Country)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'overflying_country', 1, 'Overflying Country', 'Y', 'N', 'Y', NULL, NULL, 'ALPHANUMERIC', '(case when length(overflying_country) = 2 then overflying_country else (select mc.cntry_code from mst_country mc where upper(mc.cntry_name) = upper(overflying_country)) end)', 'COUNTRY', 'Y', true, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'overflying_country');

-- seq 2: overflying_fir (Overflying Fir)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'overflying_fir', 2, 'Overflying Fir', 'Y', 'N', 'Y', NULL, NULL, 'ALPHANUMERIC', 'overflying_fir', 'FIR', 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'overflying_fir');

-- seq 3: sequence (Sequence)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'sequence', 3, 'Sequence', 'Y', 'N', 'N', NULL, NULL, 'INTEGER', 'ROW_NUMBER() OVER (
    PARTITION BY
        m.flight_id,
        (
            CASE
                WHEN length(overflying_country) = 2
                THEN overflying_country
                ELSE (
                    SELECT mc.cntry_code
                    FROM mst_country mc
                    WHERE upper(mc.cntry_name) = upper(overflying_country)
                )
            END
        ),
        overflying_fir
    ORDER BY entry_date_time
)', NULL, 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'sequence');

-- seq 4: entry_date_time (Entry Date Time)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'entry_date_time', 4, 'Entry Date Time', 'Y', 'N', 'N', NULL, NULL, 'DATETIME', 'coalesce(entry_date_time,stg.flight_date)', NULL, 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'entry_date_time');

-- seq 5: exit_date_time (Exit Date Time)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'exit_date_time', 5, 'Exit Date Time', 'Y', 'N', 'N', NULL, NULL, 'DATETIME', 'coalesce(exit_date_time,stg.flight_date)', NULL, 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'exit_date_time');

-- seq 6: entry_latitude (Entry Latitude)  active=N
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'entry_latitude', 6, 'Entry Latitude', 'N', 'N', 'N', NULL, NULL, 'ALPHANUMERIC', 'entry_latitude', NULL, 'Y', false, NULL, 'N', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'entry_latitude');

-- seq 7: entry_longitude (Entry Longitude)  active=N
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'entry_longitude', 7, 'Entry Longitude', 'N', 'N', 'N', NULL, NULL, 'ALPHANUMERIC', 'entry_longitude', NULL, 'Y', false, NULL, 'N', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'entry_longitude');

-- seq 8: exit_latitude (Exit Latitude)  active=N
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'exit_latitude', 8, 'Exit Latitude', 'N', 'N', 'N', NULL, NULL, 'ALPHANUMERIC', 'exit_latitude', NULL, 'Y', false, NULL, 'N', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'exit_latitude');

-- seq 9: exit_longitude (Exit Longitude)  active=N
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'exit_longitude', 9, 'Exit Longitude', 'N', 'N', 'N', NULL, NULL, 'ALPHANUMERIC', 'exit_longitude', NULL, 'Y', false, NULL, 'N', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'exit_longitude');

-- seq 10: distance_travelled (Distance Travelled)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'distance_travelled', 10, 'Distance Travelled', 'Y', 'N', 'N', NULL, NULL, 'INTEGER', 'CAST(distance_travelled AS INTEGER)', NULL, 'Y', true, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'distance_travelled');

-- seq 11: distance_unit_of_measure (Distance Unit Of Measure)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'distance_unit_of_measure', 11, 'Distance Unit Of Measure', 'Y', 'N', 'N', NULL, NULL, 'ALPHANUMERIC', '''NM''', NULL, 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'distance_unit_of_measure');

-- seq 12: gcd (GCD)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'gcd', 12, 'GCD', 'Y', 'N', 'N', NULL, NULL, 'INTEGER', 'CAST(gcd AS INTEGER)', NULL, 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'gcd');

-- seq 13: gcd_uom (GCD Unit Of Measure)  active=Y
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'gcd_uom', 13, 'GCD Unit Of Measure', 'Y', 'N', 'N', NULL, NULL, 'INTEGER', '''NM''', NULL, 'Y', false, NULL, 'Y', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'gcd_uom');

-- seq 14: overflying_amount (Estimated Amount)  active=N
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'overflying_amount', 14, 'Estimated Amount', 'N', 'N', 'N', NULL, NULL, 'INTEGER', 'overflying_amount', NULL, 'Y', false, NULL, 'N', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'overflying_amount');

-- seq 15: overflying_amount_currency (Currency)  active=N
INSERT INTO config_opsdata_columns (opsdata_table_id, opsdata_column_nm, column_sequence, opsdata_column_lbl, show_column_on_ui, freeze_column, search_criteria, search_criteria_seq, ui_group_label, column_datatype, opsdata_stg_column_nm, lov_source, is_editable, is_mandatory, max_length, is_active, validation_level, validation_condition, validation_message)
SELECT 5, 'overflying_amount_currency', 15, 'Currency', 'N', 'N', 'N', NULL, NULL, 'INTEGER', 'overflying_amount_currency', NULL, 'Y', false, NULL, 'N', 'O', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM config_opsdata_columns WHERE opsdata_table_id = 5 AND opsdata_column_nm = 'overflying_amount_currency');

-- Expect: total=15, active=9
SELECT count(*) AS total, sum((is_active='Y')::int) AS active FROM config_opsdata_columns WHERE opsdata_table_id = 5;

COMMIT;   -- use ROLLBACK; if counts look wrong