CREATE TABLE public."PRODUCTS"
(
    "ID" serial PRIMARY KEY,
    "DESCRIPTION" character varying(255),
    "PRICE" double precision
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."PRODUCTS"
    OWNER to springims;

--CREATE SEQUENCE public."PRODUCT_SEQ"
--    INCREMENT 1
--    START 1
--    MINVALUE 1
--    MAXVALUE 99999999999999
--    CACHE 1;
--
--ALTER SEQUENCE public."PRODUCT_SEQ"
--    OWNER TO springims;
