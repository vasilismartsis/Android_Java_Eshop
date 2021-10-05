package com.example.eshop5.databaseStuff;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Customers.class, Products.class, Sales.class, Cart.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DataAccessObject DataAccessObject();
}
