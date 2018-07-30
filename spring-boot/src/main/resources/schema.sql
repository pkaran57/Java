-- Thanks to Spring's Auto-Configuration, it enables execution of schema.sql and data.sql in resources folder on app startup

create table ZIP_DATA
(
    ZIP integer not null,
    LOCATION_INFO varchar(255),
    primary key(ZIP)
);