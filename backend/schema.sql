-- 创建数据库
CREATE DATABASE IF NOT EXISTS academic_system
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE academic_system;

-- 用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    status BOOLEAN DEFAULT true,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 课程表
CREATE TABLE IF NOT EXISTS course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    course_code VARCHAR(20) NOT NULL UNIQUE,
    teacher_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    capacity INT DEFAULT 50,
    selected_count INT DEFAULT 0,
    classroom VARCHAR(50),
    description TEXT,
    FOREIGN KEY (teacher_id) REFERENCES user(id),
    INDEX idx_course_code (course_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 考勤表
CREATE TABLE IF NOT EXISTS attendance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    checkin_time DATETIME NOT NULL,
    status ENUM('正常', '迟到', '缺勤') NOT NULL,
    location VARCHAR(255),
    device_info VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    INDEX idx_checkin_time (checkin_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 选课记录表


-- 成绩表
CREATE TABLE IF NOT EXISTS grade (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    score DECIMAL(5,2) CHECK (score BETWEEN 0 AND 100),
    remark TEXT,
    evaluation_date DATE,
    FOREIGN KEY (student_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    INDEX idx_evaluation_date (evaluation_date),
    UNIQUE INDEX uk_student_course_grade (student_id, course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 选课表


-- 创建数据库用户并授权
CREATE USER 'academic_user'@'%' IDENTIFIED BY 'secure_password';
GRANT ALL PRIVILEGES ON academic_system.* TO 'academic_user'@'%';
FLUSH PRIVILEGES;