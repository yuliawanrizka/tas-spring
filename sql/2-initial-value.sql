-- Delete Data First --
DELETE FROM [UserRole];
DELETE FROM [Employee];
DELETE FROM [Course];
DELETE FROM [Classroom];
DELETE FROM [Location];
DELETE FROM [Grade];
DELETE FROM [Roles];

-- Reseed Primary Key --
DBCC CHECKIDENT ('[UserRole]', RESEED, 0);
GO
DBCC CHECKIDENT ('[Employee]', RESEED, 0);
GO
DBCC CHECKIDENT ('[Course]', RESEED, 0);
GO
DBCC CHECKIDENT ('[Classroom]', RESEED, 0);
GO
DBCC CHECKIDENT ('[Location]', RESEED, 0);
GO
DBCC CHECKIDENT ('[Grade]', RESEED, 0);
GO
DBCC CHECKIDENT ('[Roles]', RESEED, 0);
GO


-- Roles table init --
SET IDENTITY_INSERT[Roles] ON;

INSERT INTO [Roles](roleId, roleName) VALUES (1, 'Administrator');
INSERT INTO [Roles](roleId, roleName) VALUES (2, 'Trainer');
INSERT INTO [Roles](roleId, roleName) VALUES (3, 'Manager');
INSERT INTO [Roles](roleId, roleName) VALUES (4, 'Staff');

SET IDENTITY_INSERT [Roles] OFF;

-- Grade table init --
SET IDENTITY_INSERT[Grade] ON;

INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (1, 'ADM', 'ADM1');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (2, 'ADM', 'ADM2');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (3, 'ADM', 'ADM3');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (4, 'ADM', 'ADM4');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (5, 'IT', 'IT1');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (6, 'IT', 'IT2');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (7, 'IT', 'IT3');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (8, 'IT', 'IT4');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (9, 'MJF', 'MG1');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (10, 'MJF', 'MG2');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (11, 'MJF', 'SM');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (12, 'MJF', 'VP');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (13, 'FA', 'FA1');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (14, 'FA', 'FA2');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (15, 'FA', 'FA3');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (16, 'FA', 'FA4');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (17, 'TR', 'TR1');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (18, 'TR', 'TR2');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (19, 'TR', 'TR3');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (20, 'TR', 'TR4');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (21, 'SE', 'JP');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (22, 'SE', 'PG');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (23, 'SE', 'AP');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (24, 'SE', 'AN');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (25, 'SQ', 'JT');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (26, 'SQ', 'TS');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (27, 'SQ', 'ST');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (28, 'SQ', 'TA');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (29, 'CON', 'JC');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (30, 'CON', 'CON');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (31, 'CON', 'SC');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (32, 'CON', 'LC');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (33, 'DSG', 'JD');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (34, 'DSG', 'DS');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (35, 'DSG', 'SD');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (36, 'DSG', 'DD');

SET IDENTITY_INSERT [Grade] OFF;

-- Location table init --
SET IDENTITY_INSERT[Location] ON;

INSERT INTO [Location](locationId, location) VALUES ( 1, 'Bali');
INSERT INTO [Location](locationId, location) VALUES ( 2, 'Yogyakarta');
INSERT INTO [Location](locationId, location) VALUES ( 3, 'Bandung');
INSERT INTO [Location](locationId, location) VALUES ( 4, 'Jakarta');

SET IDENTITY_INSERT [Location] OFF;

-- Classroom table init --
SET IDENTITY_INSERT[Classroom] ON;

INSERT INTO [Classroom](classroomId, classroom, locationId) VALUES ( 1, 'Saraswati Meeting Room', 1);
INSERT INTO [Classroom](classroomId, classroom, locationId) VALUES ( 2, 'Bandung A', 3);
INSERT INTO [Classroom](classroomId, classroom, locationId) VALUES ( 3, 'Borobudur Meeting Room', 2);
INSERT INTO [Classroom](classroomId, classroom, locationId) VALUES ( 4, 'Jakarta A', 4);

SET IDENTITY_INSERT [Classroom] OFF;

-- Course table init --
SET IDENTITY_INSERT[Course] ON;

INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(1, 'Beginner', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(2, 'Low Intermediate 1', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(3, 'Low Intermediate 2', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(4, 'Intermediate 1', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(5, 'Intermediate 2', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(6, 'Business Writing 1', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(7, 'Communicating Effectively 1', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(8, 'Business Writing 2', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(9, 'Communicating Effectively 2', 1);
INSERT INTO [Course](courseId, courseName, bccRelated) VALUES(10, 'Presentation Skill 2', 1);

SET IDENTITY_INSERT [Course] OFF;

-- Employee table init --
SET IDENTITY_INSERT[Employee] ON;

INSERT INTO [EMPLOYEE](employeeId, accountName, password, fullName, gradeId, stream, email, active, locationId) VALUES (1, 'mitrais\admin', '$2a$10$fo87Tigk947EXQM9ya6BiO9HOk62x2ez8fpZ17jLqaOERveT0ivq2', 'Administrator', 1, null, 'admin@example.com', 1, 1);

SET IDENTITY_INSERT [Employee] OFF;

-- UserRole table init --
SET IDENTITY_INSERT[UserRole] ON;

INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (1, 1, 1);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (2, 1, 2);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (3, 1, 3);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (4, 1, 4);

SET IDENTITY_INSERT [UserRole] OFF;