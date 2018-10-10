package com.lzy.liujing.restaurant.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysMenu extends BaseBean{
    /**
     * 功能菜单id
     */
    private Long menuId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 父节点id
     */
    private Long parentId;
    /**
     * 父节点id集
     */
    private Long parentIds;
    /**
     * 是否显示
     */
    private Integer isShow;
    /**
     * 权限
     */
    private String permission;
    /**
     * 菜单链接
     */
    private String menuHref;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     *菜单权重
     */
    private Integer weight;
    /**
     * 菜单描述
     */
    private String description;
    /**
     * 查询条件
     */
    private Map<String,Object> condition;
    /**
     * 子菜单
     */
    private List<SysMenu> childMenus;
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentIds() {
        return parentIds;
    }

    public void setParentIds(Long parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getCondition() {
        if(condition==null){
            condition = new HashMap<String,Object>();
        }
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", parentIds=" + parentIds +
                ", isShow=" + isShow +
                ", permission='" + permission + '\'' +
                ", menuHref='" + menuHref + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", condition=" + condition +
                '}';
    }
}
