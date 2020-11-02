INSERT INTO User(name, email, password) VALUES('Aluno', 'aluno@email.com', '$2a$10$Jlaffv1lrjIReA3IzIRxUOJLOkYqWUZ59OPL5Gfv0vGDdbUGEAYhS');

INSERT INTO Course(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO Course(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO Topic(title, message, creation_Date, status, author_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NOT_REPLIED', 1, 1);
INSERT INTO Topic(title, message, creation_Date, status, author_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NOT_REPLIED', 1, 1);
INSERT INTO Topic(title, message, creation_Date, status, author_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NOT_REPLIED', 1, 2);