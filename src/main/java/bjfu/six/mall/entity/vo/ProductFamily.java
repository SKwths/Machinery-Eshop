package bjfu.six.mall.entity.vo;

public class ProductFamily {
    private int id;
    private int parentId;
    private String name;
    private ProductFamily[] children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductFamily[] getChildren() {
        return children;
    }

    public void setChildren(ProductFamily[] children) {
        this.children = children;
    }
}
