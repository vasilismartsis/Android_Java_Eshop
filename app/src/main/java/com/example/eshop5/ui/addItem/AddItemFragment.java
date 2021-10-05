package com.example.eshop5.ui.addItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eshop5.MainActivity;
import com.example.eshop5.R;
import com.example.eshop5.databaseStuff.Products;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddItemFragment extends Fragment {
    View root;
    TextView cText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_item, container, false);

        //Προσθέτει ένα προϊόν στον πίνακα Products
        Button nameButton = root.findViewById(R.id.loginButton);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView itemNameInput = root.findViewById(R.id.itemNameInput);
                TextView itemQuantityInput = root.findViewById(R.id.itemQuantityInput);

                String name = itemNameInput.getText().toString();
                int quantity = Integer.parseInt(itemQuantityInput.getText().toString());

                Products product = new Products();

                product.setName(name);
                product.setQuantity(quantity);

                MainActivity.database.DataAccessObject().addProduct(product);

                Toast.makeText(getActivity(), "Product added successfully", Toast.LENGTH_LONG).show();

                itemNameInput.setText("");
                itemQuantityInput.setText("");
            }
        });

        return root;
    }
}
