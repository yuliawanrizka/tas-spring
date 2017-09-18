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

-- Employee table init --
DELETE FROM [Employee];
SET IDENTITY_INSERT[Employee] ON;

INSERT INTO [EMPLOYEE](employeeId, accountName, password, fullName, gradeId, stream, email, active) VALUES (1, 'mitrais\admin', '$2a$10$fo87Tigk947EXQM9ya6BiO9HOk62x2ez8fpZ17jLqaOERveT0ivq2', 'Administrator', 5, '-', 'admin@example.com', 1);

SET IDENTITY_INSERT [Employee] OFF;

-- UserRole table init --
DELETE FROM [UserRole];
SET IDENTITY_INSERT[UserRole] ON;

INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (1, 1, 1);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (2, 1, 2);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (3, 1, 3);
INSERT INTO [UserRole](userRoleId, employeeId, roleId) VALUES (4, 1, 4);

SET IDENTITY_INSERT [UserRole] OFF;
