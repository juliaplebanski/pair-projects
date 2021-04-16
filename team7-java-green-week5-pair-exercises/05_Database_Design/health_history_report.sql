
DROP TABLE IF EXISTS pet_procedure;
DROP TABLE IF EXISTS vet_procedure;
DROP TABLE IF EXISTS pet;

START TRANSACTION;

CREATE TABLE pet (

        pet_id SERIAL,
        pet_name VARCHAR(20),
        owner_name VARCHAR (50),
        pet_type VARCHAR(20),
        pet_age INT,
        
        CONSTRAINT pk_pet_pet_id PRIMARY KEY (pet_id)
        
);

CREATE TABLE vet_procedure (

        procedure_id SERIAL,
        procedure_name VARCHAR(50),
        
        CONSTRAINT pk_vet_procedure_procedure_id PRIMARY KEY (procedure_id)

);

CREATE TABLE pet_procedure (

        transaction_id SERIAL,
        procedure_id INT,
        pet_id INT,
        visit_date DATE,
        
        CONSTRAINT pk_pet_procedure_transaction_id PRIMARY KEY (transaction_id),
        CONSTRAINT fk_pet_procedure_vet_procedure FOREIGN KEY (procedure_id) REFERENCES vet_procedure(procedure_id),
        CONSTRAINT fk_pet_procedure_vet FOREIGN KEY (pet_id) REFERENCES pet(pet_id)

);

COMMIT;
