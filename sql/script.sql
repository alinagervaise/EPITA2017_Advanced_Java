
-- drop schema iam2017;
-- alter table iam2017.identity_address drop CONSTRAINT FK_50UWEKTYAR0QY8J9N08133E5;
-- alter table iam2017.identity_address drop CONSTRAINT FK_5B8IB7REYYY2F02TFF238P4AT;
-- ALTER TABLE iam2017.address DROP CONSTRAINT IDENTITY_ADDRESS_FK;
-- drop table iam2017.address;
-- ALTER TABLE iam2017.identities DROP CONSTRAINT IDENTITY_PK;
-- drop table iam2017.identities;
-- drop table iam2017.identity_address;



--create schema iam2017;

-- create table identities
CREATE TABLE iam2017.identities
	(identity_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY
	CONSTRAINT IDENTITY_PK PRIMARY KEY, 
	IDENTITIES_UID  VARCHAR(255),
	username VARCHAR(255),
	display_name VARCHAR(255),
	email VARCHAR(255),
	password VARCHAR(255),
	birthday Date,
	isAdmin int
	);

insert into "IAM2017"."IDENTITIES" ("IDENTITIES_UID","USERNAME", "DISPLAY_NAME", "EMAIL", "PASSWORD", "BIRTHDAY", "ISADMIN")
values('000', 'admin', 'galina B.', 'admin@gmail.com', 'admin', '1986-02-02', 1);

-- Create table address
CREATE TABLE iam2017.address
	(address_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY
	CONSTRAINT ADDRESS_PK PRIMARY KEY, 
	identity_id INT NOT NULL,
	street VARCHAR(255),
	city VARCHAR(255),
	zipCode VARCHAR(255),
	country VARCHAR(255)
	);
