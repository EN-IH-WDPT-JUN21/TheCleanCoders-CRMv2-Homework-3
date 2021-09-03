--DROP TABLE IF EXISTS opportunities_table;
--DROP TABLE IF EXISTS leads_table;
--DROP TABLE IF EXISTS sales_rep_table;
--DROP TABLE IF EXISTS contacts_table;
--DROP TABLE IF EXISTS accounts_table;
--
--
--CREATE TABLE accounts_table (
--                              id INT AUTO_INCREMENT PRIMARY KEY,
--                              company_name VARCHAR(250) NOT NULL,
--                              industry VARCHAR(250) NOT NULL,
--                              employees INT NOT NULL,
--                              city VARCHAR(250) NOT NULL,
--                              country VARCHAR(250) NOT NULL
--);
--
--CREATE TABLE contacts_table (
--                              id INT AUTO_INCREMENT PRIMARY KEY,
--                              name VARCHAR(250) NOT NULL,
--                              phone_number VARCHAR(250) NOT NULL,
--                              email VARCHAR(250) NOT NULL,
--                              company_name VARCHAR(250) NOT NULL
----                              opportunity INT NOT NULL,
----                              FOREIGN KEY (opportunity) REFERENCES opportunities_table(id)
--
--);
--
--CREATE TABLE sales_rep_table (
--                              id INT AUTO_INCREMENT PRIMARY KEY,
--                              sales_rep_name VARCHAR(250) NOT NULL
--);
--
--CREATE TABLE leads_table (
--                              id INT AUTO_INCREMENT PRIMARY KEY,
--                              name VARCHAR(250) NOT NULL,
--                              phone_number VARCHAR(250) NOT NULL,
--                              email VARCHAR(250) NOT NULL,
--                              company_name VARCHAR(250) NOT NULL,
--                              sales_rep INT NOT NULL,
--                              FOREIGN KEY (sales_rep) REFERENCES sales_rep_table(id)
--);
--
--CREATE TABLE opportunities_table (
--                              id INT AUTO_INCREMENT PRIMARY KEY,
--                              product VARCHAR(250) NOT NULL,
--                              quantity INT NOT NULL,
--                              decision_maker INT NOT NULL,
--                              opportunity_status VARCHAR(250) NOT NULL,
--                              sales_rep INT NOT NULL,
--                              account INT NOT NULL,
--                              FOREIGN KEY (decision_maker) REFERENCES contacts_table(id),
--                              FOREIGN KEY (sales_rep) REFERENCES sales_rep_table(id),
--                              FOREIGN KEY (account) REFERENCES accounts_table(id)
--);

INSERT INTO accounts_table (id, company_name, industry, employees, city, country) VALUES
(default, 'FirstSight', 'MANUFACTURING', 100, 'Basel', 'Switzerland'),
(default, 'TwoCompany', 'ECOMMERCE', 74, 'Vigo', 'Spain'),
(default, 'ThirdRound', 'MEDICAL', 250, 'Gdansk', 'Poland'),
(default, 'FourSeasons', 'OTHER', 12, 'Faro', 'Portugal');

INSERT INTO contacts_table (id, name, phone_number, email, company_name) VALUES
(default, 'Maravillas', '135791113', 'mara@ironhack.es', 'theCleanCoders Inc'),
(default, 'Katarzyna', '2468101214', 'catKat@ironhack.pl', 'theCleanCodersPoland'),
(default, 'Natalia', '314152878', 'natalia@ironhack.pl', 'theCleanCoders Ltd'),
(default, 'Vitaliano', '2112345678', 'vitaliano@ironhack.pt', 'theCleanCoders Lda');

INSERT INTO sales_rep_table (id, sales_rep_name) VALUES
(default, 'Jaime Jordan'),
(default, 'Marian Garcia'),
(default, 'Julia Dusterdieck'),
(default, 'Steve McDuck'),
(default, 'Mariana Garcia');

INSERT INTO leads_table (id, name, phone_number, email, company_name, sales_rep) VALUES
(default, 'Harry Potter','0044123456', 'harryp@hogwarts.wiz', 'Gryffindor Ltd', 2),
(default, 'Raistlin', '1234564879', 'wizkid@majere.org', 'TheCompanyOfDragons', 5),
(default, 'Verissimo', '666332211', 'rambodeolhao@malucos.pt', 'A Doca de Pesca', 4),
(default, 'Joao Lopes','351962458752', 'meumail@meuservidor.pt', 'cleanDevelopers', 1),
(default, 'Miguel Naves', '5643218563', 'miguel@nevermind.com', 'Os fortesnight', 4);

INSERT INTO opportunities_table (id, product, quantity, opportunity_status, decision_maker, sales_rep, account) VALUES
(default, 'BOX', 80, 'OPEN', 1, 1, 1),
(default, 'FLATBED', 7, 'CLOSED_WON', 1, 2, 2),
(default, 'HYBRID', 100, 'OPEN', 2, 1, 3),
(default, 'HYBRID', 5, 'CLOSED_LOST', 2, 3, 4),
(default, 'BOX', 12, 'CLOSED_WON', 2, 3, 1);