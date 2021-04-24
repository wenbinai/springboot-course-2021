-- 全部角色用户的公共表
-- ID/role需经常使用的信息加密置于token
create table if not exists user
(
    id bigint(19) not null primary key ,
    name varchar(8) not null ,
    number varchar(12) not null ,
    password varchar(65) not null ,
    role int not null default 1,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    unique (number),
    index (number)
);
-- 与user一对一，因此使用共用主键。非组合关系，利于维护
-- 即，添加一个教师时，先提取出user数据加入user表，并获取返回的id；再将id和其他信息存教师表
create table if not exists teacher
(
    id bigint(19) not null primary key ,
    title varchar(8),
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);
-- 与user为共用主键
create table if not exists student
(
    id bigint(19) not null primary key ,
    clazz varchar(8),
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);
-- 每门可以有1位授课教师，索引非外键
create table if not exists course
(
    id bigint(19) not null primary key ,
    name varchar(45) not null ,
    teacher_id bigint(19) not null ,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    index (teacher_id)
);
-- 学生课程双向多对多。中间表
create table if not exists student_course
(
    id bigint(19) not null primary key ,
    student_id bigint(19) not null ,
    course_id bigint(19) not null ,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    index (course_id),
    index (student_id)
);
