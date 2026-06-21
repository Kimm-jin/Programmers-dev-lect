use java_basic;

CREATE TABLE member
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    grade VARCHAR(10)  NOT NULL, -- "일반" 또는 "VIP"
    name  VARCHAR(50)  NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20)
);