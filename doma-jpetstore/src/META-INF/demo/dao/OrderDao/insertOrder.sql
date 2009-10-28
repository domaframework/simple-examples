INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,
  SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,
  COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,
  CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)
VALUES(/*order.orderId*/1, /*order.username*/'username', /*order.orderDate*/date '2001-01-01', /*order.shipAddress1*/'shipAddress1', /*order.shipAddress2*/'shipAddress2', /*order.shipCity*/'shipCity',
  /*order.shipState*/'shipState', /*order.shipZip*/'shipZip', /*order.shipCountry*/'shipCountry', /*order.billAddress1*/'billAddress1', /*order.billAddress2*/'billAddress2', /*order.billCity*/'billCity',
  /*order.billState*/'billState', /*order.billZip*/'billZip', /*order.billCountry*/'billCountry', /*order.courier*/'courier', /*order.totalPrice*/1000.12, /*order.billToFirstName*/'billToFirstName', /*order.billToLastName*/'billToLastName',
  /*order.shipToFirstName*/'shipToFirstName', /*order.shipToLastName*/'shipToLastName', /*order.creditCard*/'creditCard', /*order.expiryDate*/'2007', /*order.cardType*/'cardType', /*order.locale*/'locale')
