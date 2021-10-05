package com.example.eshop5.databaseStuff;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
        {
        @ForeignKey(entity = Customers.class, parentColumns = "id", childColumns = "customerId", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Products.class, parentColumns = "id", childColumns = "productId", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
        }
)
public class Sales {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int customerId;

    private int productId;

    private String name;

    private int quantity;

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }
}
