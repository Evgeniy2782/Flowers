package com.example.evgen.flowersstories.ViewPagerItem;

public class ProductModel {

//    @SerializedName("invNumberFlowers")
//    @Expose
    private String invNumberFlowers;

//    @SerializedName("images")
//    @Expose
    private String images;

//    @SerializedName("name")
//    @Expose
    private String name;

//    @SerializedName("description")
//    @Expose
    private String description;

//    @SerializedName("priceID")
//    @Expose
    private Integer priceID;

//    @SerializedName("quantity")
//    @Expose
    private Integer quantity;

//    @SerializedName("in_Stock")
//    @Expose
    private String in_Stock;

    private boolean isSelectCheckBox;

    public ProductModel(String invNumberFlowers, String images, String name, String description, Integer priceID, Integer quantity,
                        String in_Stock, boolean isSelectCheckBox) {

        this.invNumberFlowers = invNumberFlowers;
        this.images = images;
        this.name = name;
        this.description = description;
        this.priceID = priceID;
        this.quantity = quantity;
        this.in_Stock = in_Stock;
        this.isSelectCheckBox = isSelectCheckBox;
    }

    public boolean isSelectCheckBox() {
        return isSelectCheckBox;
    }

    public String getImages() {
        return images;
    }

    public String getInvNumberFlowers() {
        return invNumberFlowers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriceID() {
        return priceID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getIn_Stock() {
        return in_Stock;
    }

}
