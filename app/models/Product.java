package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;
    private BigDecimal itemCost;
    private int unitsInStock;
    private int userId;
    private String description;
    private byte[] picture;
    private int categoryId;


    public int getProductId()
    {
        return productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public BigDecimal getItemCost()
    {
        return itemCost;
    }

    public int getUnitsInStock()
    {
        return unitsInStock;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getDescription()
    {
        return description;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setItemCost(BigDecimal itemCost)
    {
        this.itemCost = itemCost;
    }

    public void setUnitsInStock(int unitsInStock)
    {
        this.unitsInStock = unitsInStock;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }
}
