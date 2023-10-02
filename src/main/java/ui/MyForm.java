package ui;

import com.vaadin.ui.*;
import core.entity.Product;
import core.service.ProductService;

public class MyForm extends FormLayout {

    private final TextField productName = new TextField("Ürün adı :");
    private final ComboBox categoryName = new ComboBox("Kategori :");
    private final Button saveButton = new Button("Kaydet");

    public MyForm() {
        categoryName.addItem("Kategori 1");
        categoryName.addItem("Kategori 2");
        categoryName.addItem("Kategori 3");
        addComponents(productName, categoryName, saveButton);

        saveButton.addClickListener(event -> {
            String product = productName.getValue();
            String category = categoryName.getValue().toString();

            Notification.show("Veriler kaydedildi: " + product + " " + category);
            Product tempProduct = new Product();
            tempProduct.setName(product);
            ProductService productService = new ProductService();
            productService.addProduct(tempProduct);

            productName.clear();
            categoryName.clear();
            productName.focus();
        });
    }
}
