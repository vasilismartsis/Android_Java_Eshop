package com.example.eshop5.databaseStuff;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DataAccessObject {
    @Insert
    public void addCustomer(Customers customer);

    @Query("select * from Customers")
    public List<Customers> getCustomers();

    @Query("SELECT * FROM Customers WHERE username == :username AND password == :password")
    public Customers getCustomer(String username, String password);

    @Delete
    public void removeCustomer(Customers customer);

    @Update
    public void updateCustomer(Customers customer);




    @Insert
    public void addProduct(Products product);

    @Query("select * from Products")
    public List<Products> getProducts();

    @Query("SELECT * FROM Products WHERE id == :product")
    public Products getProduct(int product);

    @Update
    public void updateProduct(Products product);

    @Delete
    public void removeProduct(Products product);




    @Insert
    public void addSale(Sales sale);

    @Delete
    public void removeSale(Sales sale);

    @Update
    public void updateSale(Sales sale);

    @Query("select * from Sales")
    public List<Sales> getSales();



    @Query("select * from Cart")
    public List<Cart> getCartProducts();

    @Query("SELECT * FROM Cart WHERE id == :product")
    public Cart getCartProduct(int product);

    @Insert
    public void addToCart(Cart product);

    @Delete
    public void removeFromCart(Cart product);

    @Query("DELETE FROM Cart")
    public void deleteCart();

    @Update
    public void updateCartProduct(Cart product);
}
