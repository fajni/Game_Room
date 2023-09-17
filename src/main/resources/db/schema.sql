CREATE TABLE IF NOT EXISTS players(
    player_number NUMERIC(5) primary key,
    name VARCHAR(25) not null,
    lastname VARCHAR(35) not null
);

CREATE TABLE IF NOT EXISTS pcs(
    pc_number NUMERIC(5) primary key,
    title VARCHAR(10) not null,
    status VARCHAR(5)
);

ALTER TABLE players ADD pc_number NUMERIC(5) not null;
ALTER TABLE players ADD CONSTRAINT fk_players FOREIGN KEY(pc_number) REFERENCES pcs(pc_number);

ALTER TABLE pcs ADD player_number NUMERIC(5) null;
ALTER TABLE pcs ADD CONSTRAINT fk_PCS FOREIGN KEY(player_number) REFERENCES PLAYERS(player_number);