INSERT INTO Category
            (categoryId ,name)
      VALUES(1          ,'Pottery');

INSERT INTO Category
            (categoryId ,name)
      VALUES(2          ,'Painting');

INSERT INTO Category
            (categoryId ,name)
      VALUES(3         ,'Drawing & Illustration');

INSERT INTO Category
             (categoryId ,name)
       VALUES(4          ,'Photography');






INSERT INTO UserAccount
            (userId, firstName, lastName, email , state , city , bio , picture)
      VALUES(1    , 'Jennifer'  , 'Joy'  , 'jjoy@artist.com', 'Oregon', 'Portland', 'I love clay, so I shape it and sell it.', 'C:\\Program Files\\MariaDB 10.3\\pictures\\tower.jpg');


INSERT INTO UserAccount
            (userId, firstName, lastName, email , state , city , bio , picture)
      VALUES(2     , 'David'  , 'Reed'  , 'guywhobuysstuff@customer.com', 'Arkansas', 'Little Rock', null, null);


INSERT INTO UserAccount
            (userId, firstName, lastName, email , state , city , bio , picture)
      VALUES(3     , 'Lisa'  , 'McKenny'  , 'lisabuysart@customer.com', 'New York', 'New York', null, null);






INSERT INTO Product
            (productId, productName, itemCost, unitsInStock, userId, picture, description, categoryId)
      VALUES(1        , 'Happy Mugs', 19.99  , 15           , 1     ,  'C:\\Program Files\\MariaDB 10.3\\pictures\\tower.jpg', 'Hand crafted mugs that are happy.', 1);


INSERT INTO Product
            (productId, productName, itemCost, unitsInStock, userId, picture, description, categoryId)
      VALUES(2        , 'Dinner Plates', 14.99  , 9        , 1     , 'C:\\Program Files\\MariaDB 10.3\\pictures\\tower.jpg', 'Hand crafted dinner plates.', 1`);


INSERT INTO Product
            (productId, productName, itemCost, unitsInStock, userId, picture, description, categoryId)
      VALUES(3        , 'Nice Picture', 59.99  , 3        , 1     , 'C:\\Program Files\\MariaDB 10.3\\pictures\\tower.jpg', 'A nice photo!.', 4);


INSERT INTO Product
            (productId, productName, itemCost, unitsInStock, userId, picture, description, categoryId)
      VALUES(4        , 'Very Detailed Painting', 39.99   , 6        , 1     , 'C:\\Program Files\\MariaDB 10.3\\pictures\\tower.jpg', 'A painting with great detail.', 2);


INSERT INTO Product
            (productId, productName, itemCost, unitsInStock, userId, picture, description, categoryId)
      VALUES(5        , 'People Throwing Confetti', 9.99  , 3        , 1     , 'C:\\Program Files\\MariaDB 10.3\\pictures\\tower.jpg', 'Hand crafted dinner plates.', 3);






INSERT INTO Review
            (reviewId, content              , reviewDate, productId, userId, stars, title)
      VALUES(1       , 'This is what it is!', '2018-12-09', 1         ,       2,    4, 'It is great!');

INSERT INTO Review
            (reviewId, content              , reviewDate, productId, userId, stars, title)
      VALUES(2       , 'Bloam dfkl dsjie mk oidk msdoj!', '2019-1-04', 1         ,       2,    5, 'Glerb glerb glerb glerb!');

INSERT INTO Review
            (reviewId, content              , reviewDate, productId, userId, stars, title)
      VALUES(3       , 'This has my friends and family getting very happy!', '2018-12-10', 2         ,       2,    2, 'SHOI GUHBOI NOI NOI!');

INSERT INTO Review
            (reviewId, content              , reviewDate, productId, userId, stars, title)
      VALUES(4       , 'I am shouting about this product!', '2018-11-19', 3         ,       2,    5, 'Hamps Is the lamps!');

INSERT INTO Review
            (reviewId, content              , reviewDate, productId, userId, stars, title)
      VALUES(5       , 'Funk is da dkj eijkd eij n!', '2019-02-14', 3         ,       2,    4, 'Pampkin gon gon!');

INSERT INTO Review
            (reviewId, content              , reviewDate, productId, userId, stars, title)
      VALUES(6       , 'Fekj ido wlke idopwe eni pq dmjkj!', '2019-03-01', 4         ,       2,    3, 'It is nomkes!');




