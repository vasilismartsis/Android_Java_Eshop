package com.example.eshop5.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eshop5.MainActivity;
import com.example.eshop5.R;
import com.example.eshop5.databaseStuff.Customers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    View root;
    TextView cText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        Button registerButton = root.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView usernameInput = root.findViewById(R.id.itemNameInput);
                TextView passwordInput = root.findViewById(R.id.itemQuantityInput);

                //Συλλογή στοιχείων χρήστη από τα inputs
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                //Δημοιουργία αντικειμένου τύπου Customers
                Customers customer = new Customers();

                customer.setUsername(username);
                customer.setPassword(password);

                //Εισαγωγή αντικειμένου στη βάση
                MainActivity.database.DataAccessObject().addCustomer(customer);

                //Εμφάνηση μυνήματος επιτυχείας
                Toast toast = Toast.makeText(getActivity(), "Customer added successfully", Toast.LENGTH_LONG);
                toast.show();

                //Καθαρισμός πεδίων
                usernameInput.setText("");
                passwordInput.setText("");

                //Ορισμός του παιδίου customerId το οποίο προσομοιώνει την δημιουργία προσορινού session
                MainActivity.customerId = MainActivity.database.DataAccessObject().getCustomer(username, password).getId();

                //Χρήση του navController της Main για εναλλαγή του Fragment στο fragment_shop
                MainActivity.navController.navigate(R.id.nav_shop);
            }
        });

        Button loginButton = root.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView usernameInput = root.findViewById(R.id.itemNameInput);
                TextView passwordInput = root.findViewById(R.id.itemQuantityInput);

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                //Έλεγχος ύπαρξης του χρήστη στη βάση
                if(MainActivity.database.DataAccessObject().getCustomer(username, password) != null) {
                    MainActivity.navController.navigate(R.id.nav_shop);
                    Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_LONG).show();
                    //Ορισμός του παιδίου customerId το οποίο προσομοιώνει την δημιουργία προσορινού session
                    MainActivity.customerId = MainActivity.database.DataAccessObject().getCustomer(username, password).getId();
                }
                else
                {
                    Toast.makeText(getActivity(), "You need to register first", Toast.LENGTH_LONG).show();
                }
            }
        });

        return  root;
    }
}
