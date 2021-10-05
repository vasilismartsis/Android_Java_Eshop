package com.example.eshop5.ui.shop;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eshop5.MainActivity;
import com.example.eshop5.R;
import com.example.eshop5.databaseStuff.Cart;
import com.example.eshop5.databaseStuff.Customers;
import com.example.eshop5.databaseStuff.Products;
import com.example.eshop5.ui.cart.CartFragment;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class ShopFragment extends Fragment {
    View root;
    TextView cText;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_shop, container, false);

        //the layout on which you are working
        LinearLayout productButtonsLayout = root.findViewById(R.id.productButtonsLayout);

        //Δημοιουργία αντικειμένου τύπου Πroducts
        List<Products> products = MainActivity.database.DataAccessObject().getProducts();

        //Για κάθε product στον πίνακα Products δημιούργισε ένα LinearLayout το οποίο περιέχει ένα TextView για το όνομα και ένα Elegant Number Button για το Quantity του κάθε προϊόντος
        for (final Products product : products) {
            LinearLayout parentLayout = new LinearLayout(getActivity());

            //set layout parameters
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.width = 200;
            lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
            lp.topMargin = 20;
            lp.bottomMargin = 10;
            lp.gravity = Gravity.CENTER;

            //set the properties for button
            TextView productName = new TextView(getActivity());
            productName.setText(product.getName());
            productName.setTextAlignment(getView().TEXT_ALIGNMENT_CENTER);
            productName.setLayoutParams(lp);
            //add button to the layout
            parentLayout.addView(productName);

            //set layout parameters
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp2.width = 200;
            lp2.height = 50;
            lp2.topMargin = 20;
            lp2.bottomMargin = 10;

            //set the properties for button
            final com.cepheuen.elegantnumberbutton.view.ElegantNumberButton productQuantity = new com.cepheuen.elegantnumberbutton.view.ElegantNumberButton(getActivity());
            if(MainActivity.database.DataAccessObject().getCartProduct(product.getId()) == null) {
                productQuantity.setRange(0, product.getQuantity());
            }
            else {
                productQuantity.setRange(0, product.getQuantity() - MainActivity.database.DataAccessObject().getCartProduct(product.getId()).getQuantity());
            }
            productQuantity.setNumber("0");
            productQuantity.setLayoutParams(lp2);
            //add button to the layout
            parentLayout.addView(productQuantity);

            //set layout parameters
            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp3.height = 100;
            lp3.bottomMargin = 10;
            lp3.gravity = Gravity.CENTER;

            //set the properties for button
            Button buyButton = new Button(getActivity());
            buyButton.setText("Add to cart");
            buyButton.setLayoutParams(lp3);

            //Προσθήκη της επιλεγμένης ποσότητας προιόντος στο πίνακα Cart
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Product successfully added to cart", Toast.LENGTH_SHORT).show();

                    //Δημοιουργία αντικειμένου τύπου Cart
                    Cart productToAdd = new Cart();
                    productToAdd.setId(product.getId());
                    productToAdd.setName(product.getName());

                    //Άν το προιών δεν έχει προσθεθεί στο καλάθι ακόμα τότε πρόσθεσέ το αλιώς απλά ενημέρωσε το Quantity
                    if(MainActivity.database.DataAccessObject().getCartProduct(product.getId()) == null)
                    {
                        productToAdd.setQuantity(Integer.parseInt(productQuantity.getNumber()));

                        MainActivity.database.DataAccessObject().addToCart(productToAdd);
                    }
                    else
                    {
                        productToAdd.setQuantity(Integer.parseInt(productQuantity.getNumber()) + MainActivity.database.DataAccessObject().getCartProduct(product.getId()).getQuantity());

                        MainActivity.database.DataAccessObject().updateCartProduct(productToAdd);
                    }

                    //Επαναπροσδιορισμός του Range και του αριθμού του Elegant Number Button
                    productQuantity.setRange(0, product.getQuantity() - MainActivity.database.DataAccessObject().getCartProduct(product.getId()).getQuantity());
                    productQuantity.setNumber("0");

                }
            });

            //add button to the layout
            parentLayout.addView(buyButton);

            productButtonsLayout.addView(parentLayout);
        }


        return root;
    }
}
