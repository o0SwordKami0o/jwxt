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
    mgr_tel char(11) not null unique
);

/*学院*/
create table tb_colleges(
    college_id int primary key auto_increment,
    college_name varchar(40) not null ,
    college_addr varchar(40) not null ,
    college_tel char(11) not null unique

);

/* 专业*/

create table tb_professions(
    pro_id int primary key auto_increment,
    pro_name varchar(40) not null ,
    pro_desc varchar(200),
    cid int not null ,
    constraint FK_PROFESSION_COLLEGE foreign key (cid) references tb_colleges(college_id)
);


/*班级 */
create table tb_classes(
    class_id int primary key auto_increment,
    class_name varchar(40) not null unique ,
    class_desc varchar(200),
    pro_id int not null ,
    constraint FK_CLASS_PROFESSION foreign key (pro_id) references tb_professions(pro_id)
);

/*学期 */
create table tb_steps(
    step_id int primary key auto_increment,
    step_name varchar(20) not null unique
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
    constraint FK_TEACHER_PROFESSION foreign key (pro_id) references tb_professions(pro_id)
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
    stu_tel char(11) not null ,
    stu_remark varchar(200),
    class_id int not null ,
    constraint FK_STUDENT_CLASS foreign key (class_id) references tb_classes(class_id)
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
    constraint FK_COURSE_TEACHER foreign key (teacher_id) references tb_teachers(teacher_id),
    constraint FK_COURSE_STEP foreign key (step_id) references tb_steps(step_id)

);

/* 课程与班级的关联表*/
create table tb_course_class(
    course_id int not null ,
    class_id int not null ,
    primary key (course_id,class_id),
    constraint FK_COURSE_CLASS1 foreign key (course_id) references tb_courses(course_id),
    constraint FK_COURSE_CLASS2 foreign key (class_id) references tb_classes(class_id)
);

/*成绩*/
create table tb_grades(
    stu_num char(11) not null ,
    course_id int not null ,
    score double,
    primary key (stu_num,course_id),
    constraint FK_GRADE_STUDENT foreign key (stu_num) references tb_students(stu_num),
    constraint FK_GRADE_COURSE foreign key (course_id) references tb_courses(course_id)
);

/* 学生课程表*/
create table tb_stu_course(
    stu_id int,
    course_id int,
    primary key (stu_id,course_id)
);

INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (2, '数学1901', 'desc', 1);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (3, '数学1902', 'desc', 1);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (4, '物理1901', 'desc', 2);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (5, '物理1902', 'desc', 2);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (6, '大数据1901', 'desc', 3);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (7, '大数据1902', 'desc', 3);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (8, '软工2001', 'desc', 4);
INSERT INTO db_tims.tb_classes (class_id, class_name, class_desc, pro_id) VALUES (9, '软工2002', 'desc', 4);


INSERT INTO db_tims.tb_colleges (college_id, college_name, college_addr, college_tel) VALUES (1, '数学与统计学院', '知行楼302', '11122335500');
INSERT INTO db_tims.tb_colleges (college_id, college_name, college_addr, college_tel) VALUES (2, '计信学院', '逸夫楼202', '11122335501');

INSERT INTO db_tims.tb_course_class (course_id, class_id) VALUES (1, 9);
INSERT INTO db_tims.tb_course_class (course_id, class_id) VALUES (2, 9);
INSERT INTO db_tims.tb_course_class (course_id, class_id) VALUES (3, 9);
INSERT INTO db_tims.tb_course_class (course_id, class_id) VALUES (4, 9);
INSERT INTO db_tims.tb_course_class (course_id, class_id) VALUES (5, 9);
INSERT INTO db_tims.tb_course_class (course_id, class_id) VALUES (6, 9);

INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (1, 'java', 'desc', 2, 16, 100, 30, 1, 1, 1);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (2, 'c++', 'desc', 2, 16, 100, 30, 1, 1, 1);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (3, '操作系统', 'desc', 2, 16, 100, 30, 1, 1, 1);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (4, '计算机网络', 'desc', 2, 16, 100, 30, 1, 1, 1);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (5, '软件项目管理', 'desc', 2, 16, 100, 30, 1, 1, 2);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (6, '数据结构', 'desc', 3, 32, 100, 30, 1, 1, 2);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (7, '论语', 'desc', 2, 16, 100, 3, 1, 1, 2);
INSERT INTO db_tims.tb_courses (course_id, course_name, course_desc, course_xf, course_time, course_cap, course_choosed, choose_enable, teacher_id, step_id) VALUES (8, '中国文化遗产', 'desc', 2, 16, 100, 1, 1, 1, 2);

INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101005', 1, 87);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101005', 2, 66);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101005', 3, 66);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101005', 4, 77);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101005', 6, 77);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101006', 1, 80);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101006', 2, 66);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101007', 1, 80);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101008', 1, 99);
INSERT INTO db_tims.tb_grades (stu_num, course_id, score) VALUES ('20190101009', 1, 47);

INSERT INTO db_tims.tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel) VALUES (1, 'm0001', '111111', '王主任', '男', 47, '13030303300');
INSERT INTO db_tims.tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel) VALUES (2, 'm0002', '111111', '李主任', '男', 45, '13030303301');
INSERT INTO db_tims.tb_managers (mgr_id, mgr_num, mgr_pwd, mgr_name, mgr_gender, mgr_age, mgr_tel) VALUES (3, 'm0003', '111111', '张主任', '男', 48, '13030303302');

INSERT INTO db_tims.tb_professions (pro_id, pro_name, pro_desc, cid) VALUES (1, '应用数学', '数学专业描述', 1);
INSERT INTO db_tims.tb_professions (pro_id, pro_name, pro_desc, cid) VALUES (2, '计算机与科学', '计算机专业描述', 1);
INSERT INTO db_tims.tb_professions (pro_id, pro_name, pro_desc, cid) VALUES (3, '大数据', '大数据专业描述', 2);
INSERT INTO db_tims.tb_professions (pro_id, pro_name, pro_desc, cid) VALUES (4, '软件工程', '软件工程专业描述', 2);

INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (1, '2019-2020第一学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (2, '2019-2020第二学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (3, '2020-2021第一学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (4, '2020-2021第二学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (5, '2021-2022第一学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (6, '2021-2022第二学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (7, '2022-2023第一学期');
INSERT INTO db_tims.tb_steps (step_id, step_name) VALUES (8, '2022-2023第二学期');

INSERT INTO db_tims.tb_stu_course (stu_id, course_id) VALUES (1, 7);


INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (1, '20190101001', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 5);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (2, '20190101002', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 6);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (3, '20190101003', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 7);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (4, '20190101004', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 8);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (6, '20190101005', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (7, '20190101006', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (8, '20190101007', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (9, '20190101008', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (10, '20190101009', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (11, '20190101010', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (12, '20190101011', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (13, '20190101012', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (14, '20190101013', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (15, '20190101014', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (16, '20190101015', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (17, '20190101016', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (18, '20190101017', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (19, '20190101018', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (20, '20190101019', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (21, '20190101020', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (22, '20190101021', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (23, '20190101022', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (24, '20190101023', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (25, '20190101024', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (26, '20190101025', '333333', 'Tom', '男', 20, 2019, '湖北武汉', '13232323322', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (27, '20190101026', '333333', 'Lucy', '女', 22, 2020, '湖北武汉', '13232323323', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (28, '20190101027', '333333', 'Jack', '男', 21, 2019, '湖北武汉', '13232323324', '', 9);
INSERT INTO db_tims.tb_students (stu_id, stu_num, stu_pwd, stu_name, stu_gender, stu_age, stu_begin_year, stu_addr, stu_tel, stu_remark, class_id) VALUES (29, '20190101028', '333333', 'LiHua', '男', 20, 2020, '湖北武汉', '13232323325', '', 9);

INSERT INTO db_tims.tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id) VALUES (1, 't0001', '222222', '张老师', '男', 31, '13131313311', '讲师', 3);
INSERT INTO db_tims.tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id) VALUES (2, 't0002', '222222', '李老师', '女', 32, '13131313312', '副教授', 3);
INSERT INTO db_tims.tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id) VALUES (3, 't0003', '222222', '王老师', '男', 34, '13131313313', '讲师', 4);
INSERT INTO db_tims.tb_teachers (teacher_id, teacher_num, teacher_pwd, teacher_name, teacher_gender, teacher_age, teacher_tel, teacher_resume, pro_id) VALUES (4, 't0004', '222222', '陈老师', '男', 33, '13131313314', '副教授', 4);