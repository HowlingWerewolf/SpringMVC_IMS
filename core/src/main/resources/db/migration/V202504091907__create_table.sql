CREATE TABLE product
(
    ID serial PRIMARY KEY,
    DESCRIPTION character varying(255),
    PRICE double precision
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE product
    OWNER to springims;
