-- Roles table init --
DELETE FROM [Roles];
SET IDENTITY_INSERT[Roles] ON;

INSERT INTO [Roles](roleId, roleName) VALUES (1, 'Administrator');
INSERT INTO [Roles](roleId, roleName) VALUES (2, 'Trainer');
INSERT INTO [Roles](roleId, roleName) VALUES (3, 'Manager');
INSERT INTO [Roles](roleId, roleName) VALUES (4, 'Staff');

SET IDENTITY_INSERT [Roles] OFF;

-- Grade table init --
DELETE FROM [Grade];
SET IDENTITY_INSERT[Grade] ON;

INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (1, 'SE', 'JP');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (2, 'SE', 'PG');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (3, 'SE', 'AP');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (4, 'SE', 'AN');
INSERT INTO [Grade](gradeId, jobFamily, grade) VALUES (5, 'ADM', 'ADM1');

SET IDENTITY_INSERT [Grade] OFF;

-- Location table init --
DELETE FROM [Location];
SET IDENTITY_INSERT[Location] ON;

INSERT INTO [Location](locationId, location) VALUES ( 1, 'Bali');
INSERT INTO [Location](locationId, location) VALUES ( 2, 'Yogyakarta');
INSERT INTO [Location](locationId, location) VALUES ( 3, 'Bandung');
INSERT INTO [Location](locationId, location) VALUES ( 4, 'Jakarta');

SET IDENTITY_INSERT [Location] OFF;

-- Course table init --
DELETE FROM [Course];
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
DELETE FROM [Employee];
SET IDENTITY_INSERT[Employee] ON;

INSERT INTO [EMPLOYEE](employeeId, accountName, password, fullName, gradeId, stream, email, active, locationId) VALUES (1, 'mitrais\admin', '$2a$10$fo87Tigk947EXQM9ya6BiO9HOk62x2ez8fpZ17jLqaOERveT0ivq2', 'Administrator', 5, '-', 'admin@example.com', 1, 1);

SET IDENTITY_INSERT [Employee] OFF;

-- UserRole table init --
DELETE FROM [UserRole];
SET IDENTITY_INSERT[UserRole] ON;

INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (1, 1, 1);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (2, 1, 2);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (3, 1, 3);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (4, 1, 4);

SET IDENTITY_INSERT [UserRole] OFF;