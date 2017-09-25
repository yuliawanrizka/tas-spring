/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.1 		*/
/*  Created On : 22-Sep-2017 09:30:27 				*/
/*  DBMS       : SQL Server 2008 						*/
/* ---------------------------------------------------- */

/* Drop Foreign Key Constraints */

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_Classroom_Location]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [Classroom] DROP CONSTRAINT [FK_Classroom_Location]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_Classroom]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_Classroom]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_Course]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_Course]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_TrainingPeriod]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_TrainingPeriod]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_UserRole]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_UserRole]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_UserRole_02]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_UserRole_02]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_UserRole_03]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_UserRole_03]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CoursePeriod_UserRole_04]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CoursePeriod] DROP CONSTRAINT [FK_CoursePeriod_UserRole_04]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_EligibleParticipants_TrainingPeriod]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [EligibleParticipants] DROP CONSTRAINT [FK_EligibleParticipants_TrainingPeriod]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_EligibleParticipants_UserRole]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [EligibleParticipants] DROP CONSTRAINT [FK_EligibleParticipants_UserRole]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_Employee_Grade]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [Employee] DROP CONSTRAINT [FK_Employee_Grade]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_Employee_Location]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [Employee] DROP CONSTRAINT [FK_Employee_Location]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_EnrolledParticipants_CoursePeriod]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [EnrolledParticipants] DROP CONSTRAINT [FK_EnrolledParticipants_CoursePeriod]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_EnrolledParticipants_EligibleParticipants]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [EnrolledParticipants] DROP CONSTRAINT [FK_EnrolledParticipants_EligibleParticipants]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_Schedule_CoursePeriod]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [Schedule] DROP CONSTRAINT [FK_Schedule_CoursePeriod]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_TrainingPeriod_UserRole]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [TrainingPeriod] DROP CONSTRAINT [FK_TrainingPeriod_UserRole]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_TrainingPeriod_UserRole_02]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [TrainingPeriod] DROP CONSTRAINT [FK_TrainingPeriod_UserRole_02]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_UserRole_Employee]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [UserRole] DROP CONSTRAINT [FK_UserRole_Employee]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_UserRole_Roles]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [UserRole] DROP CONSTRAINT [FK_UserRole_Roles]
GO

/* Drop Tables */

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Classroom]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Classroom]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Course]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Course]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CoursePeriod]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CoursePeriod]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[EligibleParticipants]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [EligibleParticipants]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Employee]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Employee]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[EnrolledParticipants]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [EnrolledParticipants]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Grade]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Grade]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Location]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Location]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Roles]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Roles]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[Schedule]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [Schedule]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[TrainingPeriod]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [TrainingPeriod]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[UserRole]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [UserRole]
GO

/* Create Tables */

CREATE TABLE [Classroom]
(
	[classroomId] bigint NOT NULL IDENTITY (1, 1),
	[classroom] varchar(50) NOT NULL,
	[locationId] bigint NULL
)
GO

CREATE TABLE [Course]
(
	[courseId] bigint NOT NULL IDENTITY (1, 1),
	[courseName] varchar(50) NOT NULL,
	[bccRelated] bit NOT NULL DEFAULT 0
)
GO

CREATE TABLE [CoursePeriod]
(
	[coursePeriodId] bigint NOT NULL IDENTITY (1, 1),
	[courseId] bigint NOT NULL,
	[trainingPeriodId] bigint NOT NULL,
	[mainTrainer] bigint NOT NULL,
	[backupTrainer] bigint NULL,
	[classroomId] bigint NOT NULL,
	[creatorId] bigint NOT NULL,
	[createdAt] datetime NOT NULL,
	[updaterId] bigint NULL,
	[updatedAt] datetime NULL,
	[onlineClass] bit NOT NULL DEFAULT 0
)
GO

CREATE TABLE [EligibleParticipants]
(
	[eligibleParticipantsId] bigint NOT NULL IDENTITY (1, 1),
	[trainingPeriodId] bigint NOT NULL,
	[userRoleId] bigint NOT NULL
)
GO

CREATE TABLE [Employee]
(
	[employeeId] bigint NOT NULL IDENTITY (1, 1),
	[accountName] varchar(50) NOT NULL,
	[password] varchar(100) NOT NULL,
	[fullName] varchar(100) NOT NULL,
	[gradeId] bigint NOT NULL,
	[stream] varchar(30) NOT NULL,
	[email] varchar(125) NOT NULL,
	[active] bit NOT NULL DEFAULT 1,
	[locationId] bigint NOT NULL
)
GO

CREATE TABLE [EnrolledParticipants]
(
	[enrolledParticipantsId] bigint NOT NULL IDENTITY (1, 1),
	[eligibleParticipantsId] bigint NOT NULL,
	[coursePeriodId] bigint NOT NULL
)
GO

CREATE TABLE [Grade]
(
	[gradeId] bigint NOT NULL IDENTITY (1, 1),
	[jobFamily] varchar(50) NOT NULL,
	[grade] varchar(50) NOT NULL
)
GO

CREATE TABLE [Location]
(
	[locationId] bigint NOT NULL IDENTITY (1, 1),
	[location] varchar(50) NULL
)
GO

CREATE TABLE [Roles]
(
	[roleId] bigint NOT NULL IDENTITY (1, 1),
	[roleName] varchar(50) NOT NULL
)
GO

CREATE TABLE [Schedule]
(
	[scheduleId] bigint NOT NULL IDENTITY (1, 1),
	[coursePeriodId] bigint NOT NULL,
	[date] date NOT NULL
)
GO

CREATE TABLE [TrainingPeriod]
(
	[trainingPeriodId] bigint NOT NULL IDENTITY (1, 1),
	[trainingName] varchar(50) NOT NULL,
	[active] bit NOT NULL,
	[startDate] date NOT NULL,
	[endDate] date NOT NULL,
	[creatorId] bigint NOT NULL,
	[createdAt] datetime NOT NULL,
	[updaterId] bigint NULL,
	[updatedAt] datetime NULL
)
GO

CREATE TABLE [UserRole]
(
	[userRoleId] bigint NOT NULL IDENTITY (1, 1),
	[employeeId] bigint NOT NULL,
	[roleId] bigint NOT NULL
)
GO

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE [Classroom] 
 ADD CONSTRAINT [PK_Classroom]
	PRIMARY KEY CLUSTERED ([classroomId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_Classroom_Location] 
 ON [Classroom] ([locationId] ASC)
GO

ALTER TABLE [Course] 
 ADD CONSTRAINT [PK_Course]
	PRIMARY KEY CLUSTERED ([courseId] ASC)
GO

ALTER TABLE [CoursePeriod] 
 ADD CONSTRAINT [PK_CoursePeriod]
	PRIMARY KEY CLUSTERED ([coursePeriodId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_Classroom] 
 ON [CoursePeriod] ([classroomId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_Course] 
 ON [CoursePeriod] ([courseId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_TrainingPeriod] 
 ON [CoursePeriod] ([trainingPeriodId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_UserRole] 
 ON [CoursePeriod] ([mainTrainer] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_UserRole_02] 
 ON [CoursePeriod] ([backupTrainer] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_UserRole_03] 
 ON [CoursePeriod] ([creatorId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_CoursePeriod_UserRole_04] 
 ON [CoursePeriod] ([updaterId] ASC)
GO

ALTER TABLE [EligibleParticipants] 
 ADD CONSTRAINT [PK_EligibleParticipants]
	PRIMARY KEY CLUSTERED ([eligibleParticipantsId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_EligibleParticipants_TrainingPeriod] 
 ON [EligibleParticipants] ([trainingPeriodId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_EligibleParticipants_UserRole] 
 ON [EligibleParticipants] ([userRoleId] ASC)
GO

ALTER TABLE [Employee] 
 ADD CONSTRAINT [PK_Employee]
	PRIMARY KEY CLUSTERED ([employeeId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_Employee_Grade] 
 ON [Employee] ([gradeId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_Employee_Location] 
 ON [Employee] ([locationId] ASC)
GO

ALTER TABLE [EnrolledParticipants] 
 ADD CONSTRAINT [PK_EnrolledParticipants]
	PRIMARY KEY CLUSTERED ([enrolledParticipantsId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_EnrolledParticipants_CoursePeriod] 
 ON [EnrolledParticipants] ([coursePeriodId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_EnrolledParticipants_EligibleParticipants] 
 ON [EnrolledParticipants] ([eligibleParticipantsId] ASC)
GO

ALTER TABLE [Grade] 
 ADD CONSTRAINT [PK_Grade]
	PRIMARY KEY CLUSTERED ([gradeId] ASC)
GO

ALTER TABLE [Location] 
 ADD CONSTRAINT [PK_Location]
	PRIMARY KEY CLUSTERED ([locationId] ASC)
GO

ALTER TABLE [Roles] 
 ADD CONSTRAINT [PK_Roles]
	PRIMARY KEY CLUSTERED ([roleId] ASC)
GO

ALTER TABLE [Schedule] 
 ADD CONSTRAINT [PK_Schedule]
	PRIMARY KEY CLUSTERED ([scheduleId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_Schedule_CoursePeriod] 
 ON [Schedule] ([coursePeriodId] ASC)
GO

ALTER TABLE [TrainingPeriod] 
 ADD CONSTRAINT [PK_TrainingPeriod]
	PRIMARY KEY CLUSTERED ([trainingPeriodId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_TrainingPeriod_UserRole] 
 ON [TrainingPeriod] ([creatorId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_TrainingPeriod_UserRole_02] 
 ON [TrainingPeriod] ([updaterId] ASC)
GO

ALTER TABLE [UserRole] 
 ADD CONSTRAINT [PK_UserRole]
	PRIMARY KEY CLUSTERED ([userRoleId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_UserRole_Employee] 
 ON [UserRole] ([employeeId] ASC)
GO

CREATE NONCLUSTERED INDEX [IXFK_UserRole_Roles] 
 ON [UserRole] ([roleId] ASC)
GO

/* Create Foreign Key Constraints */

ALTER TABLE [Classroom] ADD CONSTRAINT [FK_Classroom_Location]
	FOREIGN KEY ([locationId]) REFERENCES [Location] ([locationId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_Classroom]
	FOREIGN KEY ([classroomId]) REFERENCES [Classroom] ([classroomId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_Course]
	FOREIGN KEY ([courseId]) REFERENCES [Course] ([courseId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_TrainingPeriod]
	FOREIGN KEY ([trainingPeriodId]) REFERENCES [TrainingPeriod] ([trainingPeriodId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_UserRole]
	FOREIGN KEY ([mainTrainer]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_UserRole_02]
	FOREIGN KEY ([backupTrainer]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_UserRole_03]
	FOREIGN KEY ([creatorId]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CoursePeriod] ADD CONSTRAINT [FK_CoursePeriod_UserRole_04]
	FOREIGN KEY ([updaterId]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [EligibleParticipants] ADD CONSTRAINT [FK_EligibleParticipants_TrainingPeriod]
	FOREIGN KEY ([trainingPeriodId]) REFERENCES [TrainingPeriod] ([trainingPeriodId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [EligibleParticipants] ADD CONSTRAINT [FK_EligibleParticipants_UserRole]
	FOREIGN KEY ([userRoleId]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [Employee] ADD CONSTRAINT [FK_Employee_Grade]
	FOREIGN KEY ([gradeId]) REFERENCES [Grade] ([gradeId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [Employee] ADD CONSTRAINT [FK_Employee_Location]
	FOREIGN KEY ([locationId]) REFERENCES [Location] ([locationId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [EnrolledParticipants] ADD CONSTRAINT [FK_EnrolledParticipants_CoursePeriod]
	FOREIGN KEY ([coursePeriodId]) REFERENCES [CoursePeriod] ([coursePeriodId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [EnrolledParticipants] ADD CONSTRAINT [FK_EnrolledParticipants_EligibleParticipants]
	FOREIGN KEY ([eligibleParticipantsId]) REFERENCES [EligibleParticipants] ([eligibleParticipantsId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [Schedule] ADD CONSTRAINT [FK_Schedule_CoursePeriod]
	FOREIGN KEY ([coursePeriodId]) REFERENCES [CoursePeriod] ([coursePeriodId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [TrainingPeriod] ADD CONSTRAINT [FK_TrainingPeriod_UserRole]
	FOREIGN KEY ([creatorId]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [TrainingPeriod] ADD CONSTRAINT [FK_TrainingPeriod_UserRole_02]
	FOREIGN KEY ([updaterId]) REFERENCES [UserRole] ([userRoleId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [UserRole] ADD CONSTRAINT [FK_UserRole_Employee]
	FOREIGN KEY ([employeeId]) REFERENCES [Employee] ([employeeId]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [UserRole] ADD CONSTRAINT [FK_UserRole_Roles]
	FOREIGN KEY ([roleId]) REFERENCES [Roles] ([roleId]) ON DELETE No Action ON UPDATE No Action
GO