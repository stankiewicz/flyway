set AUTOCOMMIT_DML_MODE='PARTITIONED_NON_ATOMIC';
update MyTable2 set Int64=20 where RowId>0;
-- ddl not allowed in partitioned, should fail this
-- commented below to try flyway.repair()
CREATE TABLE MyTable3 (
                         RowId INT64 NOT NULL,
                         `Int64` INT64
) PRIMARY KEY (RowId);