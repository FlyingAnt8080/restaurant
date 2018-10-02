package com.lzy.liujing.restaurant.entity;

public class Goods extends BaseBean{
    /**
     * 菜品id
     */
    private Integer goodsId;
    /**
     * 菜品名
     */
    private String goodsName;
    /**
     * 菜品类型id
     */
    private Integer categoryId;
    /**
     * 烹饪时长
     */
    private Long cookingTime;
    /**
     * 菜小图片
     */
    private String imgSmallUrl;
    /**
     * 菜大图片
     */
    private String imgUrl;
    /**
     * 菜轮播图
     */
    private String imgCircleUrl;
    /**
     * 菜品描述
     */
    private String description;
    /**
     *制作成本
     */
    private Double cost;
    /**
     * 菜品价格
     */
    private Double price;
    /**
     * 折扣
     */
    private double discount;
    /**
     * 销量
     */
    private Long totalCount;
    /**
     * 菜品上架状态(0下架，1上架)
     */
    private Integer putawayStatus;

    public Integer getGoodsId() {
        return goodsId;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", categoryId=" + categoryId +
                ", cookingTime=" + cookingTime +
                ", imgSmallUrl='" + imgSmallUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCircleUrl='" + imgCircleUrl + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", discount=" + discount +
                ", totalCount=" + totalCount +
                ", putawayStatus=" + putawayStatus +
                '}';
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Long cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getImgSmallUrl() {
        return imgSmallUrl;
    }

    public void setImgSmallUrl(String imgSmallUrl) {
        this.imgSmallUrl = imgSmallUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgCircleUrl() {
        return imgCircleUrl;
    }

    public void setImgCircleUrl(String imgCircleUrl) {
        this.imgCircleUrl = imgCircleUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPutawayStatus() {
        return putawayStatus;
    }

    public void setPutawayStatus(Integer putawayStatus) {
        this.putawayStatus = putawayStatus;
    }
}
