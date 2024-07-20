CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE systemUser (
    _id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    account VARCHAR(128) NOT NULL,
    password VARCHAR(128) ,
    name VARCHAR(128),
    CONSTRAINT unique_account UNIQUE (account)
);

COMMENT ON COLUMN systemUser._id IS 'UUID';
COMMENT ON COLUMN systemUser.account IS '帳號';
COMMENT ON COLUMN systemUser.password IS '密碼';
COMMENT ON COLUMN systemUser.name IS '名稱';


INSERT INTO systemUser (account, password, name)
VALUES 
    ('user1@example.com', 'password123', 'John Doe'),
    ('user2@example.com', 'secret456', 'Jane Smith'),
    ('admin@example.com', 'adminpass', 'Admin User');
