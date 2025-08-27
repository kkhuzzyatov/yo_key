package ru.yo_key.product.entity;

import java.util.List;

public class Product {
    private Integer id;
    private String nameText;
    private Double standardPriceValue;
    private Integer discountPercent;
    private Integer segmentId;
    private List<Integer> sizeIds;
    private List<String> sizeValues;
    private List<Image> images;

    public Product(Integer id, String nameText, Double standardPriceValue, Integer discountPercent, Integer segmentId, List<Integer> sizeIds, List<String> sizeValues, List<Image> images) {
        this.id = id;
        this.nameText = nameText;
        this.standardPriceValue = standardPriceValue;
        this.discountPercent = discountPercent;
        this.segmentId = segmentId;
        this.sizeIds = sizeIds;
        this.sizeValues = sizeValues;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public Double getStandardPriceValue() {
        return standardPriceValue;
    }

    public void setStandardPriceValue(Double standardPriceValue) {
        this.standardPriceValue = standardPriceValue;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public List<Integer> getSizeIds() {
        return sizeIds;
    }

    public void setSizeIds(List<Integer> sizeIds) {
        this.sizeIds = sizeIds;
    }

    public List<String> getSizeValues() {
        return sizeValues;
    }

    public void setSizeValues(List<String> sizeValues) {
        this.sizeValues = sizeValues;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}