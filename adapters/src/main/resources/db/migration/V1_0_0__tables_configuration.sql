CREATE TABLE users
(
    id           serial PRIMARY KEY,
    email        varchar(250) NOT NULL,
    first_name   varchar(100) NOT NULL,
    last_name    varchar(100) NOT NULL,
    birthday     date         NOT NULL,
    address      varchar(100),
    phone_number varchar(15)
);

INSERT INTO users ( email, first_name, last_name, birthday, address, phone_number)
VALUES ('user1@example.com', 'John', 'Doe', '1990-05-15', '123 Main St', '123-456-7890'),
       ('user2@example.com', 'Jane', 'Smith', '1985-10-20', '456 Elm St', '987-654-3210'),
       ('user3@example.com', 'Michael', 'Johnson', '1995-03-25', '789 Oak St', '456-789-0123'),
       ('user4@example.com', 'Emily', 'Brown', '1988-12-10', '321 Pine St', '789-012-3456'),
       ('user5@example.com', 'David', 'Wilson', '1992-08-05', '654 Cedar St', '012-345-6789');