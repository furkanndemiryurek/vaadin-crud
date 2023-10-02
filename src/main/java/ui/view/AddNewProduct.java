package ui.view;

import com.vaadin.ui.*;
import core.entity.Category;
import core.entity.Product;
import core.service.CategoryService;
import core.service.ProductService;

import java.util.List;

public class AddNewProduct extends VerticalLayout {
    Window confirmationWindow = new Window("Ürün ekle");
    public AddNewProduct(){
        Button addProductButton = new Button("Yeni ürün ekle");
        addComponent(addProductButton);
        addProductButton.addClickListener(clickEvent -> addNewProductWindow());
    }

    public void addNewProductWindow(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        confirmationWindow.setModal(true);
        confirmationWindow.setContent(layout);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        TextField productName = new TextField("Ürün Adı");
        ComboBox categoryName = new ComboBox("Kategori");

        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.findAll();
        categories.forEach(category -> categoryName.addItem(category.getCategoryName()));

        Button saveButton = new Button("Ekle");
        Button cancelButton = new Button("Vazgeç");
        buttonsLayout.addComponents(saveButton, cancelButton);

        saveButton.addClickListener(clickEvent -> {
            if (productName.isEmpty() || categoryName.isEmpty()){
                Notification.show("Boş alan bırakmayın");
            }else{
                addProduct(productName.getValue(), categoryName.getValue().toString());
            }
        });

        cancelButton.addClickListener(clickEvent -> confirmationWindow.close());

        layout.addComponents(productName, categoryName, buttonsLayout);
        confirmationWindow.setContent(layout);

        getUI().addWindow(confirmationWindow);
    }

    private void addProduct(String productName, String categoryName){
        try{
            ProductService productService = new ProductService();
            CategoryService categoryService = new CategoryService();
            Product tempProduct = new Product();
            Category tempCategory = categoryService.findByName(categoryName);
            tempProduct.setName(productName);
            tempProduct.setCategory(tempCategory);
            productService.addProduct(tempProduct);
            confirmationWindow.close();
            Notification.show("Ürün eklendi : "+ productName);
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
