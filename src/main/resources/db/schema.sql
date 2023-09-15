CREATE TABLE IF NOT EXISTS users(
    user_number NUMERIC(5) primary key,
    name VARCHAR(25) not null,
    lastname VARCHAR(35) not null
);

CREATE TABLE IF NOT EXISTS pcs(
    pc_number NUMERIC(5) primary key,
    title VARCHAR(10) not null,
    status VARCHAR(5)
);

ALTER TABLE users ADD pc_number NUMERIC(5) not null;
ALTER TABLE users ADD CONSTRAINT fk_users FOREIGN KEY(pc_number) REFERENCES pcs(pc_number);

ALTER TABLE pcs ADD user_number NUMERIC(5) null;
ALTER TABLE pcs ADD CONSTRAINT fk_PCS FOREIGN KEY(user_number) REFERENCES USERS(user_number);