CREATE DATABASE crm_app;

USE crm_app;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

-- Thêm roles
INSERT INTO roles (id, name, description) VALUES
(1, 'Admin', 'Quản trị hệ thống'),
(2, 'Leader', 'Quản lý dự án'),
(3, 'Employee', 'Nhân viên thực hiện');

-- Thêm users
INSERT INTO users (email, password, fullname, role_id, phone) VALUES
('admin@example.com', '123456', 'Nguyễn Văn Admin', 1, '0901111111'),
('manager@example.com', '123456', 'Trần Thị Manager', 2, '0902222222'),
('user1@example.com', '123456', 'Lê Văn User1', 3, '0903333333'),
('user2@example.com', '123456', 'Phạm Thị User2', 3, '0904444444'),
('user3@example.com', '123456', 'Hoàng Văn User3', 3, '0905555555');


-- Thêm jobs
INSERT INTO jobs (name, start_date, end_date) VALUES
('Dự án CRM', '2025-09-01', '2025-12-31'),
('Website Bán Hàng', '2025-09-05', '2025-11-30'),
('Mobile App', '2025-09-10', '2025-12-15'),
('Hệ thống Quản lý Nhân sự', '2025-09-15', '2026-01-15');

-- Thêm status
INSERT INTO status (id, name) VALUES
(1, 'Đang thực hiện'),
(2, 'Hoàn thành'),
(3, 'Chưa bắt đầu');

-- Thêm task
INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id) VALUES
-- CRM Project
('Thiết kế giao diện CRM', '2025-09-02', '2025-09-10', 3, 1, 2),
('Lập trình backend CRM', '2025-09-05', '2025-09-20', 4, 1, 1),
('Test CRM', '2025-09-21', '2025-09-30', 5, 1, 1),

-- Website Bán Hàng
('Tạo database Website', '2025-09-06', '2025-09-12', 2, 2, 2),
('Xây dựng API Website', '2025-09-10', '2025-09-25', 3, 2, 1),
('Frontend Website', '2025-09-12', '2025-09-28', 4, 2, 2),

-- Mobile App
('Thiết kế UI App', '2025-09-11', '2025-09-18', 3, 3, 2),
('Lập trình App', '2025-09-19', '2025-10-15', 5, 3, 1),
('Test App', '2025-10-16', '2025-11-05', 4, 3, 1),

-- HRM System
('Khảo sát yêu cầu HRM', '2025-09-16', '2025-09-25', 2, 4, 2),
('Thiết kế CSDL HRM', '2025-09-26', '2025-10-05', 3, 4, 1),
('Triển khai HRM', '2025-10-10', '2025-12-20', 5, 4, 1);

-- Chạy database đến đây
-- END INFOR ======================================================================================================================================



-- QUERY
-- find User by email and password
SELECT * FROM users u WHERE u.email = ? AND u.password = ? ;

-- SELECT  
SELECT * FROM jobs j;

-- INSERT
INSERT INTO jobs ( name, start_date, end_date) VALUES (? , ?, ?);

-- DELETE
DELETE FROM jobs j WHERE j.id = ?;

-- SELECT BY ID
SELECT * FROM jobs j WHERE j.id = ?;

-- UPDATE
UPDATE jobs j SET name = ?, start_date= ?, end_date = ? WHERE j.id = ?;

-- SELECT ALL TASK ( by id)
SELECT t.id, t.name,t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, j.name as nameJob, u.fullname as nameUser, s.name as nameStatus
FROM tasks t
JOIN jobs j ON t.job_id = j.id
JOIN users u ON u.id = t.user_id
JOIN status s ON s.id = t.status_id;
WHERE t.id = ?;

-- UPDATE TASK 
UPDATE tasks
SET name = ?,
start_date = ?,
end_date = ?,
user_id = ?,
job_id = ?,
status_id = ?
WHERE id = ?;

-- SELECT TASK BY ID USER
SELECT t.id, t.name, t.start_date, t.end_date,t.user_id , u.fullname as nameUser , t.status_id , s.name as nameStatus, t.job_id , j.name as nameJob
FROM tasks t
JOIN jobs j ON t.job_id = j.id
JOIN status s ON s.id = t.status_id
JOIN users u ON u.id = t.user_id
WHERE u.id = ?;

-- get task by id user
SELECT t.id, t.name, t.start_date, t.end_date,t.user_id , u.fullname as nameUser , t.status_id , s.name as nameStatus, t.job_id , j.name as nameJob
FROM tasks t
JOIN jobs j ON t.job_id = j.id
JOIN status s ON s.id = t.status_id
JOIN users u ON u.id = t.user_id
WHERE u.id = ?;

-- get task by id and iduser
SELECT t.id, t.name, t.start_date, t.end_date,t.user_id , u.fullname as nameUser , t.status_id , s.name as nameStatus, t.job_id , j.name as nameJob
FROM tasks t
JOIN jobs j ON t.job_id = j.id
JOIN status s ON s.id = t.status_id
JOIN users u ON u.id = t.user_id
WHERE u.id = ? AND t.id = ?;

	

-- SELECT USER 
SELECT * ROM users u JOIN roles r ON u.role_id = r.id;

-- SELECT tasks with statusid and id user

SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id,u.fullname as nameUser,  t.name as nameStatus 
FROM tasks t
JOIN status s ON t.status_id = s.id
JOIN users u ON t.user_id = u.id
JOIN jobs j ON j.id = t.job_id
WHERE s.id = 2 AND u.id = 3;

-- SELECT USER FROM id job
SELECT u.id, u.email,u.password, u.fullname,u.avatar, u.role_id, u.phone
FROM tasks t 
JOIN users u ON t.user_id = u.id
JOIN jobs j ON j.id = t.job_id
WHERE j.id = 1;

-- SELECT TASKS FROM ID JOB
SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, j.name as nameJob
FROM tasks t 
JOIN jobs j ON j.id = t.job_id
WHERE j.id = 1;

-- SELECT tasks with statusid and id user and id job

SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id,u.fullname as nameUser,  t.name as nameStatus, j.id as idJob, j.name as nameJob 
FROM tasks t
JOIN status s ON t.status_id = s.id
JOIN users u ON t.user_id = u.id
JOIN jobs j ON j.id = t.job_id
WHERE s.id = 1 AND u.id = 5 AND j.id = 1; 

-- SELECT TASKS BY ID STATUS
SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, s.name as nameStatus
FROM tasks t
JOIN status s ON t.status_id = s.id
WHERE u.id = 1;

-- get project by id user
SELECT j.id, j.name, j.start_date, j.end_date
FROM tasks t
JOIN users u ON t.user_id = u.id
JOIN jobs j ON j.id = t.job_id
WHERE u.id = 2;

-- testing
SELECT *
FROM tasks t
JOIN status s ON t.status_id = s.id
WHERE s.id = 1;

SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, t.job_id, t.status_id, j.name as nameJob, u.fullname as nameUser
FROM tasks t
JOIN jobs j ON j.id = t.job_id
JOIN users u ON t.user_id = u.id
WHERE j.id = 4;



-- USE 
USE crm_app;

