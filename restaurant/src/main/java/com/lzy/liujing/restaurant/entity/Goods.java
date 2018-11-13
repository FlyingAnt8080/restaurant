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
     * 菜品类型
     */
    private GoodsCategory goodsCategory;

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
    private Double discount;

    /**
     * 菜品上架状态(0下架，1上架)
     */
    private Integer putawayStatus;
    /**
     * 销售量
     */
    private Long soldCount;
    /**
     * 库存量
     */
    private Long storeCount;
    /**
     * 销售状态(0 在售，1销售完)
     */
    private Integer soldState;
    /**
     * 类型状态(1普通菜，2新菜，3热菜）
     */
    private Integer typeState;

    public Integer getGoodsId() {
        return goodsId;
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

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getPutawayStatus() {
        return putawayStatus;
    }

    public void setPutawayStatus(Integer putawayStatus) {
        this.putawayStatus = putawayStatus;
    }

    public Long getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Long soldCount) {
        this.soldCount = soldCount;
    }

    public Long getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Long storeCount) {
        this.storeCount = storeCount;
    }

    public Integer getSoldState() {
        return soldState;
    }

    public void setSoldState(Integer soldState) {
        this.soldState = soldState;
    }

    public Integer getTypeState() {
        return typeState;
    }

    public void setTypeState(Integer typeState) {
        this.typeState = typeState;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", imgSmallUrl='" + imgSmallUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCircleUrl='" + imgCircleUrl + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", discount=" + discount +
                ", putawayStatus=" + putawayStatus +
                ", soldCount=" + soldCount +
                ", storeCount=" + storeCount +
                ", soldState=" + soldState +
                ", typeState=" + typeState +
                '}';
    }
}
