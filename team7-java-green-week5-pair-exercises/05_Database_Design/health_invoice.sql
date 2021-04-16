START TRANSACTION;



DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS pet_procedure;
DROP TABLE IF EXISTS vet_procedure;
DROP TABLE IF EXISTS pet_owner;


CREATE TABLE pet_owner (
        owner_id SERIAL,
        first_name varchar(20),
        last_name varchar(20),
        street_address varchar(50),
        city varchar(20),
        state_name varchar(2),
        zipCode varchar(10),
        
        CONSTRAINT pk_owner_owner_id PRIMARY KEY (owner_id)

);

CREATE TABLE vet_procedure (
        procedure_id SERIAL,
        procedure_name varchar(20),
        cost_of_procedure decimal(10,2),
        
        CONSTRAINT pk_vet_procedure_procedure_id PRIMARY KEY (procedure_id)

);

CREATE TABLE pet_procedure (
        transaction_id SERIAL,
        procedure_id int,
        pet_name varchar(20),
        visit_date date,
        
        CONSTRAINT pk_pet_procedure_transaction_id PRIMARY KEY (transaction_id),
        CONSTRAINT fk_procedure_id FOREIGN KEY (procedure_id) REFERENCES vet_procedure(procedure_id)

);

CREATE TABLE invoices (
        invoice_id SERIAL,
        hospital_name varchar(50),
        invoice_date date,
        transaction_id int,
        owner_id int,
        
        CONSTRAINT pk_invoices_invoice_id PRIMARY KEY (invoice_id),
        CONSTRAINT fk_transaction_id FOREIGN KEY (transaction_id) REFERENCES pet_procedure(transaction_id),
        CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) references pet_owner(owner_id)
        

);










COMMIT;