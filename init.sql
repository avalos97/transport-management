-- public.clients definition

-- Drop table

-- DROP TABLE public.clients;

CREATE TABLE public.clients (
	id serial NOT NULL,
	"name" varchar(100) NOT NULL,
	country varchar(100) NOT NULL,
	address varchar(200) NULL,
	email varchar(100) NULL,
	is_active bool NULL DEFAULT true,
	phone varchar(25) NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (id)
);


-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
	id serial NOT NULL,
	product_type varchar(100) NULL,
	description varchar(200) NULL,
	is_active bool NULL DEFAULT true,
	CONSTRAINT products_pkey PRIMARY KEY (id)
);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id serial NOT NULL,
	"name" varchar(100) NOT NULL,
	description varchar(255) NULL,
	CONSTRAINT roles_name_key UNIQUE (name),
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- public.shipping_type definition

-- Drop table

-- DROP TABLE public.shipping_type;

CREATE TABLE public.shipping_type (
	id serial NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT shippingtype_pkey PRIMARY KEY (id)
);


-- public.storage_type definition

-- Drop table

-- DROP TABLE public.storage_type;

CREATE TABLE public.storage_type (
	id serial NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT storagetype_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id serial NOT NULL,
	"name" varchar(100) NOT NULL,
	username varchar(25) NOT NULL,
	"password" varchar(255) NOT NULL,
	is_active bool NOT NULL DEFAULT true,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);


-- public."storage" definition

-- Drop table

-- DROP TABLE public."storage";

CREATE TABLE public."storage" (
	id serial NOT NULL,
	storage_type_id int4 NULL,
	"name" varchar(100) NULL,
	"location" varchar(100) NULL,
	is_active bool NULL DEFAULT true,
	CONSTRAINT storage_pkey PRIMARY KEY (id)
);


-- public."storage" foreign keys

ALTER TABLE public."storage" ADD CONSTRAINT storage_type_id_fkey FOREIGN KEY (storage_type_id) REFERENCES public.storage_type(id);


-- public.users_roles definition

-- Drop table

-- DROP TABLE public.users_roles;

CREATE TABLE public.users_roles (
	user_id int4 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id)
);


-- public.users_roles foreign keys

ALTER TABLE public.users_roles ADD CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);
ALTER TABLE public.users_roles ADD CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- public.shipments definition

-- Drop table

-- DROP TABLE public.shipments;

CREATE TABLE public.shipments (
	id serial NOT NULL,
	product_id int4 NULL,
	client_id int4 NULL,
	storage_id int4 NULL,
	shipping_type_id int4 NULL,
	quantity int4 NULL,
	registration_date timestamp NULL DEFAULT now(),
	delivery_date timestamp NULL,
	shipping_price numeric(10, 2) NULL,
	discount numeric(10, 2) NULL,
	fleet_vehicle_number varchar(8) NULL,
	guide_number varchar(10) NULL,
	is_active bool NULL DEFAULT true,
	CONSTRAINT shipments_pkey PRIMARY KEY (id)
);


-- public.shipments foreign keys

ALTER TABLE public.shipments ADD CONSTRAINT client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);
ALTER TABLE public.shipments ADD CONSTRAINT product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);
ALTER TABLE public.shipments ADD CONSTRAINT shipping_type_id_fkey FOREIGN KEY (shipping_type_id) REFERENCES public.shipping_type(id);
ALTER TABLE public.shipments ADD CONSTRAINT storage_id_fkey FOREIGN KEY (storage_id) REFERENCES public."storage"(id);


INSERT INTO public.clients
(id, "name", country, address, email, is_active, phone)
VALUES(4, 'Cliente B', 'Colombia', 'Calle Chiltiupán y 17 Av. Norte', 'algo@prueba.com', true, NULL);
INSERT INTO public.clients
(id, "name", country, address, email, is_active, phone)
VALUES(1, 'Cliente A', 'El Salvador', 'Calle Chiltiupán y 17 Av. Norte', 'empresaF@ejemplo.com', true, NULL);

INSERT INTO public.products
(id, product_type, description, is_active)
VALUES(1, 'Electrodomésticos', 'Ejemplo Descripcion.', true);

INSERT INTO public.roles
(id, "name", description)
VALUES(1, 'ADMIN', 'Administrator role');
INSERT INTO public.roles
(id, "name", description)
VALUES(2, 'USER', NULL);

INSERT INTO public.shipping_type
(id, "name")
VALUES(1, 'Terrestre');
INSERT INTO public.shipping_type
(id, "name")
VALUES(2, 'Maritimo');

INSERT INTO public.storage_type
(id, "name")
VALUES(1, 'Bodega');
INSERT INTO public.storage_type
(id, "name")
VALUES(2, 'Puerto');

INSERT INTO public.users
(id, "name", username, "password", is_active)
VALUES(4, 'admin', 'admin', '$2a$10$fw.qK704tcYYgSXh936lh.Bly1rYTvaO5QAWPOtDX7H7nse91DJHe', true);
INSERT INTO public.users
(id, "name", username, "password", is_active)
VALUES(1, 'user', 'user', '$2a$10$vUojJCjY.I8tBhmU8Xd4GekNYGiCvOH4jKV1bxz2kCv6WoBuMQoPO', true);

INSERT INTO public."storage"
(id, storage_type_id, "name", "location", is_active)
VALUES(2, 1, 'Bodega B', 'una locacion.', true);
INSERT INTO public."storage"
(id, storage_type_id, "name", "location", is_active)
VALUES(1, 1, 'Bodega A', 'Santa Ana, Metapan.', true);

INSERT INTO public.users_roles
(user_id, role_id)
VALUES(4, 1);
INSERT INTO public.users_roles
(user_id, role_id)
VALUES(1, 2);

INSERT INTO public.shipments
(id, product_id, client_id, storage_id, shipping_type_id, quantity, registration_date, delivery_date, shipping_price, discount, fleet_vehicle_number, guide_number, is_active)
VALUES(3, 1, 4, 2, 1, 100, NULL, '2023-06-30 00:00:00.000', 100.50, 5.03, 'dfg654', '1234567898', true);


