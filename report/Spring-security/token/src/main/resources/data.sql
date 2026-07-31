use java_basic;
CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(20),
                      email VARCHAR(50),
                      user_id VARCHAR(50),
                      password VARCHAR(100),                -- BCrypt 해시는 60자 → 넉넉하게
                      role ENUM('ROLE_USER', 'ROLE_ADMIN') DEFAULT 'ROLE_USER',
                      PRIMARY KEY (id)
);