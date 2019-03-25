
CREATE TABLE Category (
                categoryId INT AUTO_INCREMENT NOT NULL,
                name VARCHAR(30) NOT NULL,
                PRIMARY KEY (categoryId)
);


CREATE TABLE UserAccount (
                userId INT AUTO_INCREMENT NOT NULL,
                firstName VARCHAR(20) NOT NULL,
                lastName VARCHAR(20) NOT NULL,
                email VARCHAR(30) NOT NULL,
                state VARCHAR(20) NOT NULL,
                city VARCHAR(30) NOT NULL,
                bio TEXT,
                picture LONGBLOB,
                PRIMARY KEY (userId)
);


CREATE TABLE Product (
                productId INT AUTO_INCREMENT NOT NULL,
                productName VARCHAR(30) NOT NULL,
                itemCost DECIMAL NOT NULL,
                unitsInStock INT NOT NULL,
                userId INT NOT NULL,
                picture LONGBLOB NOT NULL,
                description TEXT NOT NULL,
                PRIMARY KEY (productId)
);


CREATE TABLE Review (
                reviewId INT NOT NULL,
                content TEXT NOT NULL,
                date DATE NOT NULL,
                productId INT NOT NULL,
                userId INT NOT NULL,
                stars INT NOT NULL,
                title VARCHAR(20) NOT NULL,
                PRIMARY KEY (reviewId)
);


CREATE TABLE ProductCategory (
                productCategoryId INT NOT NULL,
                productId INT NOT NULL,
                categoryId INT NOT NULL,
                PRIMARY KEY (productCategoryId)
);


ALTER TABLE ProductCategory ADD CONSTRAINT category_productcategory_fk
FOREIGN KEY (categoryId)
REFERENCES Category (categoryId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Product ADD CONSTRAINT user_product_fk
FOREIGN KEY (userId)
REFERENCES UserAccount (userId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Review ADD CONSTRAINT useraccount_review_fk
FOREIGN KEY (userId)
REFERENCES UserAccount (userId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ProductCategory ADD CONSTRAINT product_productcategory_fk
FOREIGN KEY (productId)
REFERENCES Product (productId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Review ADD CONSTRAINT product_review_fk
FOREIGN KEY (productId)
REFERENCES Product (productId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
