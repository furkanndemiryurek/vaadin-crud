package core.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
