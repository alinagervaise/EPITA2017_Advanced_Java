
-- drop schema iam2017;
-- drop table address;
-- drop table identities;



--create schema iam2017;

-- create table identities
CREATE TABLE iam2017.identities
	(identity_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY
	CONSTRAINT IDENTITY_PK PRIMARY KEY, 
	IDENTITIES_UID  VARCHAR(255),
	display_name VARCHAR(255),
	email VARCHAR(255),
	password VARCHAR(255),
	birthday Date,
	isAdmin INT
	);


-- Create table address
CREATE TABLE iam2017.address
	(address_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY
	CONSTRAINT ADDRESS_PK PRIMARY KEY, 
	identity_id INT NOT NULL,
	street VARCHAR(255),
	city VARCHAR(255),
	zipCode VARCHAR(255),
	constraint IDENTITY_ADDRESS_FK foreign key (identity_id) 
	references identities(identity_id)
	);
