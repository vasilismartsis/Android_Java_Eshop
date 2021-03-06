_\* This project was created for education purposes for a class of International Hellenic University_

_\* This is a partial implementation of an Eshop application on Android made with Java._

**Period:** Spring Semester 2019-2020

**Course:** Advanced Interaction Topics

**Author:** Martsis Vasileios

**Subject:** Eshop

# Android\_Java\_Eshop

### **1\. The structure of the work:**

#### **1.1. The structure of the work includes:**

The MainActivity which is the primary activity that initializes the DrawerLayoutMenu  
The Navigation Component named "mobile\_navigation" which is a container with information for each fragment  
The Layout named “activity\_main” which includes a NavigationView which is the DrawerMenu and includes the layout “app\_bar\_main”  
The Layout named "app\_bar\_main" which includes an AppBarLayout which is the Header includes the layout "content\_main"  
The Layout named "content\_main" is the main layout of the application which using "mobile\_navigation.xml" hosts the following Fragments:

"Fragment\_home": contains just a Welcome message  
"Fragment\_shop": contains the products for sale  
"Fragment\_login": contains a login system  
"Fragment\_show\_data": contains buttons which, when pressed, display the database data  
"Fragment\_cart": contains the shopping cart  
"Fragment\_add\_item": contains a form for inserting products into the database (this feature should normally only be available to the eshop owner)

#### **1.2. The following were needed for the construction of the base:**

The class named "Database" which extends the RoomDatabase class and implements the "DataAccessObject" Interface  
The Customers, Products, Sales, Cart classes which are base tables  
The Interface named "DataAccessObject" which contains all the methods of editing tables (insert, delete, update, etc ...). The sales table also sets appropriate restrictions for properly checking the integrity of the database such as ForeignKeys and onDeleteCascade and on UpdateCuscade.

### **2\. The labor code:**

#### 2.1. MainActivity:

In this class, the Activity.Main layout is initialized with a series of commands that were added automatically during the creation of the DrawerLayout Project, and then the Database object is created.

(\* There are comments in the Code so only a general reference will be made to the functionality of each Script.

In all the following fragments the code for the inflation of the fragment is considered self-evident and is not analyzed)

#### **2.2. LoginFragment:**

A login system for entering data into the database.

#### **2.3. ShopFragment:**

Creates a list of TextViews and Elegant Number Buttons for each product in the database, and a button that adds the product to the Cart table.

#### **2.4. CartFragment:**

The function of CartFragment is similar to that of ShopFragment except that it displays the products in the Cart table, and the button removes the products from the cart instead of adding them.

There is also the Purchase button which adds all the products in the cart to the Sales panel along with the customer details. In order to purchase the products, the user must have previously logged in to the system. If it has not logged in the button redirects it to fragment\_login.

#### **2.5. ShowDataFragment:**

Displays the data of the bases according to the button that has been pressed.

#### **2.6. AddItemFragment:**

Adds a product to the Products panel

### **3\. Build.grandle:**

**Two changes had to be made to this file:**

1.  def room\_version = "2.2.5"

      implementation "androidx.room:room-runtime:$room\_version"

      annotationProcessor "androidx.room:room-compiler:$room\_version"

  2. implementation 'com.cepheuen.elegant-number-button: lib: 1.0.2'

The first adds the Room Api to the Project while the second adds the Elegant Number Button to the Project.
