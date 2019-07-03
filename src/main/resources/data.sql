insert into course(id, name, created_date_time, updated_date_time) values (10001, 'JPA in 100 steps', sysdate(), sysdate());
insert into course(id, name, created_date_time, updated_date_time) values (10002, 'Spring in 100 steps', sysdate(), sysdate());
insert into course(id, name, created_date_time, updated_date_time) values (10003, 'Guice in 100 steps', sysdate(), sysdate());
insert into course(id, name, created_date_time, updated_date_time) values (10004, 'CICD in 100 steps', sysdate(), sysdate());

insert into passport(id, number) values (40001,'E40001');
insert into passport(id, number) values (40002,'E40002');
insert into passport(id, number) values (40003,'E40003');

insert into student(id, name, passport_id) values (20001,'Elsa', 40001);
insert into student(id, name, passport_id) values (20002,'Anna', 40002);
insert into student(id, name, passport_id) values (20003,'Olaf', 40003);



insert into review(id, rating, description) values (50001, '5', 'Super');
insert into review(id, rating, description) values (50002, '4', 'Great');
insert into review(id, rating, description) values (50003, '3', 'Good');

