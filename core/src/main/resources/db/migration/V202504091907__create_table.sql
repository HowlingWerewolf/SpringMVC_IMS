CREATE TABLE public."PRODUCTS"
(
    "ID" integer NOT NULL,
    "DESCRIPTION" character varying(255),
    "PRICE" double precision,
    PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."PRODUCTS"
    OWNER to springims;

--TODO: use sequence in inserts
CREATE SEQUENCE public."PRODUCT_SEQ"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999999999
    CACHE 1;

ALTER SEQUENCE public."PRODUCT_SEQ"
    OWNER TO springims;

INSERT INTO public."PRODUCTS"(
	"ID", "DESCRIPTION", "PRICE")
	VALUES (1, 'Lamp', 5.78);
INSERT INTO public."PRODUCTS"(
	"ID", "DESCRIPTION", "PRICE")
	VALUES (2, 'Table', 75.29);
INSERT INTO public."PRODUCTS"(
	"ID", "DESCRIPTION", "PRICE")
	VALUES (3, 'Chair', 22.81);
	
