package com.example.eshop5.ui.showData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eshop5.MainActivity;
import com.example.eshop5.R;
import com.example.eshop5.databaseStuff.Customers;
import com.example.eshop5.databaseStuff.Products;
import com.example.eshop5.databaseStuff.Sales;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShowDataFragment extends Fragment {
    View root;
    TextView cText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_show_data, container, false);

        //Οι OnClick Listeners κάθε κουμποιού
        Button showCustomersButton = root.findViewById(R.id.showCustomersButton);
        showCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomers();
            }
        });

        Button showProductsButton = root.findViewById(R.id.showProductsButton);
        showProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProducts();
            }
        });

        Button showSalesCountButton = root.findViewById(R.id.showSalesCountButton);
        showSalesCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSalesCount();
            }
        });

        Button showSalesButton = root.findViewById(R.id.showSalesButton);
        showSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSales();
            }
        });

        return root;
    }

    //Εμφανίζει κάθε πελάτη στον πίνακα Customers
    void showCustomers(){
        cText = root.findViewById(R.id.text_showData);

        //Δημοιουργία αντικειμένου τύπου Customers
        List<Customers> customers = MainActivity.database.DataAccessObject().getCustomers();

        String info = "";

        for(Customers customer : customers)
        {
            int id = customer.getId();
            String username = customer.getUsername();
            String password = customer.getPassword();
            info = info + "\n\n" + "Id: " + id + "\n Username: " + username + "\n Password: " + password;
        }

        cText.setText(info);
    }

    //Εμφανίζει κάθε προϊόν στον πίνακα Products
    void showProducts(){
        cText = root.findViewById(R.id.text_showData);

        //Δημοιουργία αντικειμένου τύπου Products
        List<Products> products = MainActivity.database.DataAccessObject().getProducts();

        String info = "";

        for(Products product : products)
        {
            int id = product.getId();
            String name = product.getName();
            int quantity = product.getQuantity();
            info = info + "\n\n" + "Id: " + id + "\n Name: " + name + "\n Quantity: " + quantity;
        }

        cText.setText(info);
    }

    //Εμφανίζει το σύνολο των πωλήσεων της βάσης
    void showSalesCount(){
        cText = root.findViewById(R.id.text_showData);

        //Δημοιουργία αντικειμένου τύπου Sales
        List<Sales> sales = MainActivity.database.DataAccessObject().getSales();

        String info =  "The total number of sales is: " + String.valueOf(sales.size());

        cText.setText(info);
    }


    //Εμφανίζει κάθε πώληση στον πίνακα Sales
    void showSales(){
        cText = root.findViewById(R.id.text_showData);

        //Δημοιουργία αντικειμένου τύπου Sales
        List<Sales> sales = MainActivity.database.DataAccessObject().getSales();

        String info = "";

        for(Sales sale : sales)
        {
            int id = sale.getId();
            String name = sale.getName();
            int quantity = sale.getQuantity();
            int customerId = sale.getCustomerId();
            int productId = sale.getProductId();

            info = info + "\n\n" + "Id: " + id + "\n Name: " + name + "\n Quantity: " + quantity + "\n Product Id: " + productId + "\n Customer Id: " + customerId;
        }

        cText.setText(info);
    }
}
