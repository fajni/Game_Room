SELECT * FROM game_room.users;
SELECT * FROM game_room.roles;

DELETE FROM game_room.roles WHERE user_id = 10;
DELETE FROM game_room.users WHERE user_id = 10;

SELECT * FROM game_room.pcs;

INSERT INTO game_room.pcs (pc_number, active, title) VALUES 
	(1, 0, "PC1"),
	(2, 0, "PC2"),
    (3, 0, "PC3"),
    (4, 0, "PC4"),
    (5, 0, "PC5"),
    (6, 0, "PC6"),
    (7, 0, "PC7"),
    (8, 0, "PC8"),
    (9, 0, "PC9"),
	(10, 0, "PC10"),
    (11, 0, "PC11"),
    (12, 0, "PC12"),
    (13, 0, "PC13"),
    (14, 0, "PC14"),
    (15, 0, "PC15"),
    (16, 0, "PC16"),
    (17, 0, "PC17");