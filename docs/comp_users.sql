CREATE TABLE comp_users (
	username VARCHAR(50) NOT NULL,
	password VARCHAR(300) NOT NULL,
	enabled INT NOT NULL,
	PRIMARY KEY (username)
);

-- 계정
INSERT INTO comp_users (username, password, enabled) VALUES ('user', '$2a$10$x04djNV2e9rpcPPRyXoLk.rMm6iZe2/vYdzpqHQcLeNSYdt7kc30O', 1);
INSERT INTO comp_users (username, password, enabled) VALUES ('admin', '$2a$10$QUddY3O/6ZgkYCR6MFlv9.nqA501Fm0cc/ZxQHX5pwb1o0CYCTiIS', 1);