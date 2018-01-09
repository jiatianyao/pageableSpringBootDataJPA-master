package guru.springframework.domain;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Excel(name = "订单ID", orderNum = "1", mergeVertical = true, isImportField = "orderId")
    private Integer id;//id

    @Version
    @Excel(name = "版本", orderNum = "2", mergeVertical = false, isImportField = "version")
    private Integer version;//版本
    @Excel(name = "productId", orderNum = "3", mergeVertical = true, isImportField = "productId")
    private String productId;//productId
    @Excel(name = "描述", orderNum = "4", mergeVertical = true, isImportField = "description")
    private String description;
    @Column(name = "imageUrl")
    @Excel(name = "imageUrl", orderNum = "5", mergeVertical = true, isImportField = "imageUrl")
    private String imageUrl;
    @Excel(name = "价格", orderNum = "6", mergeVertical = false, isImportField = "price")
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
