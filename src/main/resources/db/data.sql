BEGIN
    INSERT INTO pcs(pc_number, title, status) VALUES(1, "PC1", "OFF");
    INSERT INTO pcs(pc_number) VALUES(2);
    INSERT INTO pcs VALUES(3);
END;

BEGIN
    INSERT INTO users(user_number, name, lastname, pc_number) VALUES(1, "Curtis James", "Jackson III", 1);
    INSERT INTO users VALUES(2, "Austin", "Post", 2);
END;