MSC Project - E Commerce Application written entirely using Java EE technologies:

Synopsis<br />
This rep contains the source code for the Woodys Mazda E Commerce Application designed and developed by Lalin Pethiyagoda, as part of 
the MSc in Computer Science course.
The e-commerce application is implemented so that the client can sell second hand car parts to the interested parties. 
The usnderlying database is Postgres 9.4. This holds 5 tables
tblCustomer,order,rderitem,product and category. 
The customer has to register before (and subsequently login ) before they7 can purchase the items.The customer is first verified to see if they exist in the database, if not they are directed to regsister first, then login
Once they are logged in, they can browse the main categories page, where they can choose items related a category.
The client is then taken to the items for that category page and thye can add items in different quantities to the cart. The acrt maintains the state of the transaction and displays a running total of the products. 

