--CREATE TABLE Phone (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  customer_id VARCHAR(50) NOT NULL,
--  phone_number VARCHAR(20) NOT NULL,
--  active CHAR(1) DEFAULT 'N'
--);

insert into Phone(id, customer_id, phone_number) values(SEQ_ID.nextval, 'cust001', '046310000001');
insert into Phone(id, customer_id, phone_number) values(SEQ_ID.nextval, 'cust001', '046310000002');
insert into Phone(id, customer_id, phone_number) values(SEQ_ID.nextval, 'cust001', '046310000003');
insert into Phone(id, customer_id, phone_number) values(SEQ_ID.nextval, 'cust002', '046310000004');
insert into Phone(id, customer_id, phone_number) values(SEQ_ID.nextval, 'cust002', '046310000005');
