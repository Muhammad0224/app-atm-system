Baza uchun

INSERT INTO public.atm (id, address, bank_code, card_type, max_limit, min_limit, employee_id) VALUES (2, 'Chilonzor', '00180', 'HUMO', 100000000, 100000, 3);
INSERT INTO public.atm (id, address, bank_code, card_type, max_limit, min_limit, employee_id) VALUES (1, 'Sergeli', '00180', 'UZCARD', 100000000, 100000, 1);

INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (1, 1);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (1, 2);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (1, 3);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (1, 4);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (2, 1);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (2, 2);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (2, 3);
INSERT INTO public.atm_commissions (atm_id, commissions_id) VALUES (2, 4);

INSERT INTO public.atm_pennies (atm_id, pennies_id) VALUES (1, 3);
INSERT INTO public.atm_pennies (atm_id, pennies_id) VALUES (1, 1);
INSERT INTO public.atm_pennies (atm_id, pennies_id) VALUES (1, 2);

INSERT INTO public.atmhistory (id, created_at, details, from_employee, operation, atm_id) VALUES (1, '2021-08-18 15:17:19.689000', '{"50000":"20","100000":"10","Currency":"UZS","1000":"100","Sum":"2100000"}', true, 'PUT', 1);
INSERT INTO public.atmhistory (id, created_at, details, from_employee, operation, atm_id) VALUES (2, '2021-08-18 15:51:45.202000', '{"100000":"20","Currency":"UZS","Sum":"2000000"}', false, 'GET', 1);
INSERT INTO public.atmhistory (id, created_at, details, from_employee, operation, atm_id) VALUES (3, '2021-08-18 15:51:45.577000', '{"Currency":"UZS","Card":"9860123412341234","Withdraw money":"2020000.0"}', false, 'WITHDRAW', 1);
INSERT INTO public.atmhistory (id, created_at, details, from_employee, operation, atm_id) VALUES (4, '2021-08-18 15:54:56.758000', '{"50000":"10","100000":"1","Currency":"UZS","Sum":"600000"}', false, 'PUT', 1);
INSERT INTO public.atmhistory (id, created_at, details, from_employee, operation, atm_id) VALUES (5, '2021-08-18 15:54:56.789000', '{"Deposit":"594000.0","Currency":"UZS","Card":"9860123412341234"}', false, 'DEPOSIT', 1);

INSERT INTO public.card (id, cvv, active, attempts, balance, bank_code, blocked, card_type, expiry, number, owner_name, password) VALUES (1, 145, true, 0, 1074000, '00180', false, 'UZCARD', '12/24', '9860123412341234', 'Javlon', '$2a$12$.OAZnwfIbk9R9H75c0RlvuQdaZHqH2jTQ6cUpgCFyWDJN/ZnWVEYe');

INSERT INTO public.commission (id, additional, key, value) VALUES (1, true, 'PUT', '1');
INSERT INTO public.commission (id, additional, key, value) VALUES (2, true, 'GET', '1');
INSERT INTO public.commission (id, additional, key, value) VALUES (3, false, 'PUT', '2');
INSERT INTO public.commission (id, additional, key, value) VALUES (4, false, 'GET', '2');

INSERT INTO public.employee (id, email, full_name, password) VALUES (1, 'test', 'Komilov Sanjar', '$2a$12$ehgK7x1vDD0tVv/Ax/I7UOm9r/sj/KfCFM5gziMVzYptOQMedEQTa');
INSERT INTO public.employee (id, email, full_name, password) VALUES (2, 'testjon', 'Abdullayev Anvar', '$2a$12$ehgK7x1vDD0tVv/Ax/I7UOm9r/sj/KfCFM5gziMVzYptOQMedEQTa');
INSERT INTO public.employee (id, email, full_name, password) VALUES (3, 'testbek', 'Maqsudov Akbar', '$2a$12$ehgK7x1vDD0tVv/Ax/I7UOm9r/sj/KfCFM5gziMVzYptOQMedEQTa');

INSERT INTO public.employee_roles (employee_id, roles_id) VALUES (1, 1);
INSERT INTO public.employee_roles (employee_id, roles_id) VALUES (1, 2);
INSERT INTO public.employee_roles (employee_id, roles_id) VALUES (2, 2);
INSERT INTO public.employee_roles (employee_id, roles_id) VALUES (3, 2);

INSERT INTO public.penny (id, currency, key, value) VALUES (1, 'UZS', '1000', 100);
INSERT INTO public.penny (id, currency, key, value) VALUES (2, 'UZS', '50000', 30);
INSERT INTO public.penny (id, currency, key, value) VALUES (3, 'UZS', '100000', 981);

INSERT INTO public.role (id, name) VALUES (1, 'ROLE_DIRECTOR');
INSERT INTO public.role (id, name) VALUES (2, 'ROLE_EMPLOYEE');