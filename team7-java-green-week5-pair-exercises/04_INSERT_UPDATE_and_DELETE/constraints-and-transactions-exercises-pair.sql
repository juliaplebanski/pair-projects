-- Write queries to return the following:
-- Make the following changes in the "world" database.

-- 1. Add Superman's hometown, Smallville, Kansas to the city table. The 
-- countrycode is 'USA', and population of 45001. (Yes, I looked it up on 
-- Wikipedia.)

SELECT * FROM city
ORDER BY id DESC;

START TRANSACTION;

INSERT INTO city 
VALUES(4090, 'Smallville', 'USA', 'Kansas', 45001);

COMMIT;

ROLLBACK;

-- 2. Add Kryptonese to the countrylanguage table. Kryptonese is spoken by 0.0001
-- percentage of the 'USA' population.

SELECT * FROM countrylanguage
WHERE language = 'Kryptonese';

START TRANSACTION;

INSERT INTO countrylanguage
VALUES('USA', 'Kryptonese', false, 0.0001);

COMMIT;

ROLLBACK;


-- 3. After heated debate, "Kryptonese" was renamed to "Krypto-babble", change 
-- the appropriate record accordingly.

SELECT * FROM countrylanguage
WHERE countrycode = 'USA';


START TRANSACTION;

UPDATE countrylanguage SET language = 'Krypto-babble' WHERE  language = 'Kryptonese';

COMMIT;
ROLLBACK;


-- 4. Set the US captial to Smallville, Kansas in the country table.

SELECT * FROM country
WHERE code = 'USA';

START TRANSACTION;

UPDATE country 
SET capital = (SELECT city.id FROM city WHERE city.name = 'Smallville') WHERE country.code = 'USA';

COMMIT;

ROLLBACK;

-- 5. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)
START TRANSACTION;

DELETE FROM city
WHERE name = 'Smallville';

COMMIT;
ROLLBACK;

--did not succeed because of foreign key constraint

-- 6. Return the US captial to Washington.

SELECT * FROM country
WHERE code = 'USA';

START TRANSACTION;

UPDATE country 
SET capital = (SELECT city.id FROM city WHERE city.name = 'Washington') WHERE country.code = 'USA';

COMMIT;

ROLLBACK;



-- 7. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)

START TRANSACTION;

DELETE FROM city
WHERE name = 'Smallville';

COMMIT;
ROLLBACK;

-- yes it succeeded because we took out this primary key at it's lowest level which didn't affect any other rows 

-- 8. Reverse the "is the official language" setting for all languages where the
-- country's year of independence is within the range of 1800 and 1972 
-- (exclusive). 
-- (590 rows affected)

SELECT * FROM countrylanguage;


START TRANSACTION;

UPDATE countrylanguage
SET isofficial = NOT isofficial
WHERE countrycode IN (SELECT code FROM country WHERE country.indepyear > 1800 AND country.indepyear < 1972);

COMMIT;

ROLLBACK;


-- 9. Convert population so it is expressed in 1,000s for all cities. (Round to
-- the nearest integer value greater than 0.)
-- (4079 rows affected)


START TRANSACTION;

UPDATE city
SET population = ROUND(population / 1000);

COMMIT;

ROLLBACK;


-- 10. Assuming a country's surfacearea is expressed in square miles, convert it to 
-- square meters for all countries where French is spoken by more than 20% of the 
-- population.
-- (7 rows affected)


START TRANSACTION;

UPDATE country
SET surfacearea = (surfacearea / 0.00000038610)
WHERE code IN (SELECT countrycode FROM countrylanguage WHERE language = 'French' AND percentage > 20);


COMMIT;
ROLLBACK;
