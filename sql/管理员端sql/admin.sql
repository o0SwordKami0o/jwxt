drop database if exists db_tims;
create database db_tims;

use db_tims;

/*管理员 */
create table tb_managers(
    mgr_id int primary key auto_increment,
    mgr_num char(5) not null unique ,  /* 'm0001' */
    mgr_pwd varchar(20) not null ,
    mgr_name varchar(20) not null ,
    mgr_gender char(2) not null ,
    mgr_age int not null ,
    mgr_tel char(11) not null unique,
    is_delete int default 0
);

/*学院*/
create table tb_colleges(
    college_id int primary key auto_increment,
    college_name varchar(40) not null ,
    college_addr varchar(40) not null ,
    college_tel char(11) not null unique,
    is_delete int default 0
);

/* 专业*/

create table tb_professions(
    pro_id int primary key auto_increment,
    pro_name varchar(40) not null ,
    pro_desc varchar(200),
    cid int not null ,
    is_delete int default 0
   
);


/*班级 */
create table tb_classes(
    class_id int primary key auto_increment,
    class_name varchar(40) not null unique ,
    class_desc varchar(200),
    pro_id int not null ,
    is_delete int default 0
    
);

/*学期 */
create table tb_steps(
    step_id int primary key auto_increment,
    step_name varchar(20) not null unique,
    is_delete int default 0
);

/* 教师*/
create table tb_teachers(
    teacher_id int primary key auto_increment,
    teacher_num char(5) not null unique ,  /* 't0001' */
    teacher_pwd varchar(20) not null ,
    teacher_name varchar(20) not null ,
    teacher_gender char(2) not null ,
    teacher_age int not null ,
    teacher_tel char(11) not null unique,
    teacher_resume varchar(400),
    pro_id int not null ,
    is_delete int default 0
   
);


/*学生 */
create table tb_students(
    stu_id int primary key auto_increment,
    stu_num char(11) not null unique ,  /* '20200101033' */
    stu_pwd varchar(20) not null ,
    stu_name varchar(20) not null ,
    stu_gender char(2) not null ,
    stu_age int not null ,
    stu_begin_year int not null ,
    stu_addr varchar(200) not null ,
    stu_tel char(11) not null unique,
    stu_remark varchar(200),
    class_id int not null ,
    is_delete int default 0
   
);


/* 课程*/
create table tb_courses(
    course_id int primary key auto_increment,
    course_name varchar(50) not null ,
    course_desc varchar(400) not null ,
    course_xf int not null ,
    course_time int not null ,
    course_cap int not null ,
    course_choosed int not null ,
    choose_enable int not null ,  /* 0不可选，1可选  */
    teacher_id int,
    step_id int not null ,
    is_delete int default 0
  
);

/* 课程与班级的关联表*/
create table tb_course_class(
    course_id int not null ,
    class_id int not null ,
    primary key (course_id,class_id)
);

/*成绩*/
create table tb_grades(
    stu_num char(11) not null ,
    course_id int not null ,
    score int,
    primary key (stu_num,course_id),
    is_delete int default 0
);

INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (1, '数学1901', 'desc', 1, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (2, '数学1902', 'desc', 1, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (3, '物理1901', 'desc', 1, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (4, '物理1902', 'desc', 1, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (5, '大数据1901', 'desc', 2, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (6, '大数据1902', 'desc', 2, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (7, '软工2001', 'desc', 2, 0);
INSERT INTO tb_classes (class_id, class_name, class_desc, pro_id, is_delete) VALUES (8, '软工2002', 'desc', 2, 0);



INSERT INTO tb_colleges (college_id, college_name, college_addr, college_tel, is_delete) VALUES (1, '数学与统计学院', '知行楼302', '11122335500', 0);
INSERT INTO tb_colleges (college_id, college_name, college_addr, college_tel, is_delete) VALUES (2, '计信学院', '逸夫楼202', '11122335501', 0);



INSERT INTO tb_course_class (course_id, class_id) VALUES (1, 1);
INSERT INTO tb_course_class (course_id, class_id) VALUES (2, 1);
INSERT INTO tb_course_class (course_id, class_id) VALUES (1, 2);
INSERT INTO tb_course_class (course_id, class_id) VALUES (3, 2);
INSERT INTO tb_course_class (course_id, class_id) VALUES (4, 2);
INSERT INTO tb_course_class (course_id, class_id) VALUES (1, 3);
INSERT INTO tb_course_class (course_id, class_id) VALUES (2, 3);
INSERT INTO tb_course_class (course_id, class_id) VALUES (5, 3);
INSERT INTO tb_course_class (course_id, class_id) VALUES (1, 4);
INSERT INTO tb_course_class (course_id, class_id) VALUES (3, 5);

INSERT INTO tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id, is_delete) VALUES (1, '软件工程', '无', 2, 32, 100, 56, 0, 1, 1, 0);
INSERT INTO tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id, is_delete) VALUES (2, 'JavaWeb', '无', 2, 32, 100, 65, 1, 1, 2, 0);
INSERT INTO tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id, is_delete) VALUES (3, '软件项目管理', '无', 2, 32, 100, 54, 1, 2, 3, 0);
INSERT INTO tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id, is_delete) VALUES (4, '人工智能', '无', 4, 34, 100, 34, 1, 2, 4, 0);
INSERT INTO tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id, is_delete) VALUES (5, '大数据应用分析', '无', 2, 23, 100, 64, 1, 1, 5, 0);
INSERT INTO tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id, is_delete) VALUES (6, '高数', '', 23, 32, 32, 32, 1, 1, 1, 1);


INSERT INTO tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel, is_delete) VALUES (1, 'm0000', 'admin', 'admin', '女', 21, '12203948392', 0);
INSERT INTO tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel, is_delete) VALUES (2, 'm0001', '111111', '王主任', '女', 47, '13030303300', 0);
INSERT INTO tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel, is_delete) VALUES (3, 'm0002', '111111', '李主任', '男', 45, '13030303301', 0);
INSERT INTO tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel, is_delete) VALUES (4, 'm0003', '111111', '张主任', '男', 48, '13030303302', 0);
INSERT INTO tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel, is_delete) VALUES (6, 'm0100', '2121211', 'ggkt', '男', 22, '18293847367', 1);
INSERT INTO tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel, is_delete) VALUES (20, 'm1111', '2121211', 'ggkt', '男', 22, '18293847357', 0);


INSERT INTO tb_professions (pro_id, pro_name, pro_desc, cid, is_delete) VALUES (1, '应用数学', '数学专业描述', 1, 0);
INSERT INTO tb_professions (pro_id, pro_name, pro_desc, cid, is_delete) VALUES (2, '计算机与科学', '计算机专业描述', 2, 0);
INSERT INTO tb_professions (pro_id, pro_name, pro_desc, cid, is_delete) VALUES (3, '大数据', '大数据专业描述', 2, 0);
INSERT INTO tb_professions (pro_id, pro_name, pro_desc, cid, is_delete) VALUES (4, '软件工程', '软件工程专业描述', 2, 0);



INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (1, '2019-2020第一学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (2, '2019-2020第二学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (3, '2020-2021第一学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (4, '2020-2021第二学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (5, '2021-2022第一学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (6, '2021-2022第二学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (7, '2022-2023第一学期', 0);
INSERT INTO tb_steps (step_id, step_name, is_delete) VALUES (8, '2022-2023第二学期', 0);




INSERT INTO tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id, is_delete) VALUES (1, '20190101001', '333333', 'Tom', '男', 20, 2020, '湖北武汉', '13232323322', '', 1, 0);
INSERT INTO tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id, is_delete) VALUES (2, '20190101002', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 8, 0);
INSERT INTO tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id, is_delete) VALUES (3, '20190101003', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 5, 0);
INSERT INTO tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id, is_delete) VALUES (4, '20190101004', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 6, 0);
INSERT INTO tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id, is_delete) VALUES (5, '20190101111', 'adadad', 'hanhan yimiwu', '男', 21, 2020, 'dada', '12345678987', '', 1, 1);


INSERT INTO tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id, is_delete) VALUES (1, 't0001', '222222', '张老师', '男', 31, '13131313311', '', 2, 0);
INSERT INTO tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id, is_delete) VALUES (2, 't0002', '222222', '李老师', '女', 32, '13131313312', '副教授', 3, 0);
INSERT INTO tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id, is_delete) VALUES (3, 't0003', '222222', '王老师', '男', 34, '13131313313', '讲师', 4, 0);
INSERT INTO tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id, is_delete) VALUES (4, 't0004', '222222', '陈老师', '男', 33, '13131313314', '副教授', 4, 0);
INSERT INTO tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id, is_delete) VALUES (5, 't1007', 'adada', 'hanhan yimiwu', '男', 30, '10293847598', '', 1, 1);

