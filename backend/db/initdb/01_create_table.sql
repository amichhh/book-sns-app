DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS record CASCADE;
DROP TABLE IF EXISTS like_bind CASCADE;

CREATE TABLE book (
    id SERIAL NOT NULL, 
    title VARCHAR(100) NOT NULL, 
    author VARCHAR(60) NOT NULL, 
    publisher VARCHAR(30) NOT NULL, 
    outline VARCHAR(400),
    genre VARCHAR(30)[] NOT NULL,
    isbn VARCHAR(13),
    image_path VARCHAR(400),
    product_path VARCHAR(400),
    record_count INT NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE account (
    account_id VARCHAR(30) NOT NULL, 
    user_name VARCHAR(30) NOT NULL, 
    password VARCHAR(64) NOT NULL, 
    email VARCHAR(256) NOT NULL, 
    authority VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    PRIMARY KEY (account_id)
);
CREATE TABLE record (
    id SERIAL NOT NULL, 
    account_id VARCHAR(30) NOT NULL, 
    book_id INT NOT NULL, 
    evaluation INT,
    impression VARCHAR(400) NOT NULL, 
    like_count INT NOT NULL,
    is_public BOOLEAN NOT NULL,
    register_date DATE NOT NULL, 
    update_date DATE NOT NULL, 
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account (account_id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);
CREATE TABLE like_bind (
    id SERIAL NOT NULL, 
    record_id INT NOT NULL,
    account_id VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account (account_id),
    FOREIGN KEY (record_id) REFERENCES book (id)
);