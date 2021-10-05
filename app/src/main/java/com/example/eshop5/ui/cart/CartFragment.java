package com.example.eshop5.ui.cart;

import android.os.Build;
import android.os.Bundle;
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
import com.example.eshop5.databaseStuff.Products;
import com.example.eshop5.databaseStuff.Sales;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class CartFragment extends Fragment {
    View root;
    TextView cText;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cart, container, false);

        //the layout on which you are working
        final LinearLayout cartButtonsLayout = root.findViewById(R.id.cartButtonsLayout);

        List<Products> products = MainActivity.database.DataAccessObject().getProducts();
        List<Cart> cartProducts = MainActivity.database.DataAccessObject().getCartProducts();

        for (final Cart cartProduct : cartProducts) {
            System.out.println("HAHAHHAHA");
            Toast.makeText(getActivity(), "HELOOOOOO", Toast.LENGTH_SHORT).show();
            final LinearLayout parentLayout = new LinearLayout(getActivity());

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.width = 200;
            lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
            lp.topMargin = 20;
            lp.bottomMargin = 10;
            lp.gravity = Gravity.CENTER;

            //set the properties for button
            final TextView productName = new TextView(getActivity());
            productName.setText(cartProduct.getName());
            productName.setTextAlignment(getView().TEXT_ALIGNMENT_CENTER);
            productName.setLayoutParams(lp);
            //add button to the layout
            parentLayout.addView(productName);


            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp2.width = 200;
            lp2.height = 50;
            lp2.topMargin = 20;
            lp2.bottomMargin = 10;

            //set the properties for button
            final com.cepheuen.elegantnumberbutton.view.ElegantNumberButton productQuantity = new com.cepheuen.elegantnumberbutton.view.ElegantNumberButton(getActivity());
            productQuantity.setRange(0, cartProduct.getQuantity());
            productQuantity.setNumber(String.valueOf(cartProduct.getQuantity()));
            productQuantity.setLayoutParams(lp2);
            //add button to the layout
            parentLayout.addView(productQuantity);


            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp3.height = 100;
            lp3.bottomMargin = 10;
            lp3.gravity = Gravity.CENTER;

            //set the properties for button
            final Button buyButton = new Button(getActivity());
            buyButton.setText("Remove from cart");
            buyButton.setLayoutParams(lp3);

            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Product successfully removed from cart", Toast.LENGTH_SHORT).show();
                    Cart productToRemove = new Cart();

                    productToRemove.setId(cartProduct.getId());

                    MainActivity.database.DataAccessObject().removeFromCart(productToRemove);

                    parentLayout.setVisibility(View.GONE);
                }
            });

            //add button to the layout
            parentLayout.addView(buyButton);

            cartButtonsLayout.addView(parentLayout);
        }

        //Κουμπί αγοράς των προϊόντων του καλαθιού
        root.findViewById(R.id.purchaseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Αν έχει συνδεθεί ο χρήστης στο σύστημα αγόρασε τα προϊόντα
                if(MainActivity.customerId != 0)
                {
                    //Δημοιουργία αντικειμένου τύπου Cart
                    List<Cart> cartProducts = MainActivity.database.DataAccessObject().getCartProducts();

                    //Ενημερώνει τα Quantities των αντίστηχων προϊόντων του πίνακα Products και προσθέτει κάθε προϊόν του καλαθιού στον πίνακα Sales
                    for (final Cart cartProduct : cartProducts) {
                        Products productToUpdate = new Products();

                        productToUpdate.setId(cartProduct.getId());
                        int newQuantity = MainActivity.database.DataAccessObject().getProduct(cartProduct.getId()).getQuantity() - cartProduct.getQuantity();
                        productToUpdate.setQuantity(newQuantity);
                        productToUpdate.setName(cartProduct.getName());

                        MainActivity.database.DataAccessObject().updateProduct(productToUpdate);


                        Sales sale = new Sales();

                        sale.setCustomerId(MainActivity.customerId);
                        sale.setProductId(cartProduct.getId());
                        sale.setName(cartProduct.getName());
                        sale.setQuantity(cartProduct.getQuantity());

                        MainActivity.database.DataAccessObject().addSale(sale);
                    }

                    //Αφερεί κάθε εγγραφή του πίνακα Cart
                    MainActivity.database.DataAccessObject().deleteCart();

                    root.findViewById(R.id.cartButtonsLayout).setVisibility(View.GONE);
                }
                else
                {
                    //Εναλλάσει το current fragment σε login σε περίπτωση που δεν ένει συνδεθεί ο χρήστης στο σύστημα
                    MainActivity.navController.navigate(R.id.nav_login);
                }
            }
        });

        return root;
    }
}
