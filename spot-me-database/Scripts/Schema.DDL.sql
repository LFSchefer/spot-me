DROP TABLE IF EXISTS t_spots;

CREATE TABLE t_spots(
	id_spot int GENERATED ALWAYS AS IDENTITY,
	spot_name VARCHAR(200),
	spot_lat DECIMAL(9,6),
	spot_lng DECIMAL(9,6),
	spot_desc VARCHAR(2000),
	spot_img VARCHAR(41),
	CONSTRAINT t_spot_pkey PRIMARY KEY (id_spot),
	CONSTRAINT t_spot_ukey UNIQUE (spot_name, spot_lat, spot_lng)
);