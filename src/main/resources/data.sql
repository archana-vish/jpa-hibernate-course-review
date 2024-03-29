insert into course(id, name, created_date_time, updated_date_time, is_deleted) values (10001, 'JPA in 100 steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, updated_date_time, is_deleted) values (10002, 'Spring in 100 steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, updated_date_time, is_deleted) values (10003, 'Guice in 100 steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, updated_date_time, is_deleted) values (10004, 'CICD in 100 steps', sysdate(), sysdate(), false);

insert into passport(id, number) values (40001,'E40001');
insert into passport(id, number) values (40002,'E40002');
insert into passport(id, number) values (40003,'E40003');

insert into student(id, name, passport_id) values (20001,'Elsa', 40001);
insert into student(id, name, passport_id) values (20002,'Anna', 40002);
insert into student(id, name, passport_id) values (20003,'Olaf', 40003);

insert into review(id, rating, description, course_id) values (50001, 'FIVE', 'Super', 10001);
insert into review(id, rating, description, course_id) values (50002, 'FOUR', 'Great', 10001);
insert into review(id, rating, description, course_id) values (50003, 'THREE', 'Good', 10003);

insert into student_course(student_id, course_id) values (20001, 10001);
insert into student_course(student_id, course_id) values (20001, 10003);
insert into student_course(student_id, course_id) values (20002, 10001);
insert into student_course(student_id, course_id) values (20003, 10001);



