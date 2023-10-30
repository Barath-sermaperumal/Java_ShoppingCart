# Java_ShoppingCart
TechStack
Java
->Class
->Interfaces
->Object
->Data Types
->File Handling
->BufferedWritter
->FileWriter
->Scanner
->Parameterised and Non-parameterised constructor

Credentials->credentials.csv
-----------
Admin[register as a admin using the followed email ]
-----
Email-> admin@gmail.com

There are two roles 
->Admin
->User
They both have their own menu privellages,
Once logged in their id will be stored at the loggedInUserId, which can be used across the application for validating their own carts & orders.

User
---
Category:
This section provides information about the categories or groups that products or items are organized into. 
Categories help users navigate and find products more easily based on that particular category.
The categories were stored in the file named category.csv

Products:
The Products section contains detailed information about individual items available for purchase with its price. 
user can add the product to their cart using by entering their choices of the respective product.
product file name: products.csv

Cart:
The Cart section represents a shopping cart where users can temporarily store items they intend to purchase before checkout. 
It has the count and total price of the product and it is unique for each and every users
cart file name: carts.csv

Order:
The Order section represents completed transactions or purchases made by users. It contains information about what was bought.

Logout:
The user id will be removed the loggedInUserID.

Admin
-----
Options
-------
->Category
*Add, Edit, Delete, View
->Products
*Add, Edit, Delete, View
->Cart
*View
->Orders
*View


Challenges Faced:
-> Adding product to the cart
-> replacing the particular line to the cart
-> Deleting the redundunt datas from the cart file
