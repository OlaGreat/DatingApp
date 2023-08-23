SET FOREIGN_KEY_CHECKS=0;

truncate table address;
truncate table users;
truncate table notification;
truncate table media;
truncate table media_reactions;


insert into address(id, house_number, street, state , country)
values
    (100, '4', 'mcCuley', 'lagos', 'Nigeria'),
    (101, '4','mcCuley', 'lagos', 'Nigeria'),
    (102, '4','mcCuley', 'lagos', 'Nigeria'),
    (103, '4','mcCuley', 'lagos', 'Nigeria'),
    (104, '4','mcCuley', 'lagos', 'Nigeria');

insert into users(id, email, password, is_active, address_id)
values
(100, 'test@gmail.com', 'password', 0,100),
(101, 'test1@gmail.com', 'password', 0,101),
(103, 'test2@gmail.com', 'password', 0,102),
(104, 'test3@gmail.com', 'password', 0,103),
(105, 'test4@gmail.com', 'password', 0,104);
