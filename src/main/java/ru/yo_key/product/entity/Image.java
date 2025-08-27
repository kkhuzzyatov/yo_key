package ru.yo_key.product.entity;

public class Image {
    private Integer id;
    private String imageLink;
    private boolean isMain;

    public Image(Integer id, String imageLink, boolean isMain) {
        this.id = id;
        this.imageLink = imageLink;
        this.isMain = isMain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
