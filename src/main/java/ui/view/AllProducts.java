package ui.view;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import core.entity.Category;
import core.entity.Product;
import core.service.CategoryService;
import core.service.ProductService;

import java.util.List;

public class AllProducts extends FormLayout {
    public AllProducts(){
        setMargin(true);
        setSpacing(true);

        Table table = new Table();
        table.addContainerProperty("ID", Integer.class, null);
        table.addContainerProperty("Ürün Adı", String.class, null);
        table.addContainerProperty("Kategori Adı", String.class, null);
        table.addContainerProperty("Sil", Button.class, null);
        table.addContainerProperty("Güncelle", Button.class, null);

        table.addStyleName(ValoTheme.LABEL_H1);

        ProductService productService = new ProductService();
        List<Product> products = productService.findAll();
        products.forEach(product -> {
            Button deleteButton = new Button("Sil");
            Button updateButton = new Button("Güncelle");

            deleteButton.setId("deleteButton_" + product.getId());
            updateButton.setId("updateButton_"+ product.getId());

            deleteButton.addClickListener(event -> showConfirmationDialogForDelete(product.getId()));

            updateButton.addClickListener(event -> showWindowForUpdate(product));

            table.addItem(new Object[]{product.getId(), product.getName(),
                    product.getCategory().getCategoryName(), deleteButton, updateButton
            }, product.getId());
        });

        table.setPageLength(table.size());
        table.setSelectable(true);
        table.setImmediate(true);

        AddNewProduct newProduct = new AddNewProduct();
        addComponents(newProduct, table);
    }

    private void showConfirmationDialogForDelete(int productId) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        Window confirmationWindow = new Window("Silme İşlemi Onayı");
        confirmationWindow.setModal(true);
        confirmationWindow.setContent(layout);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        Label confirmationLabel = new Label("Silmek istediginize emin misiniz?");
        Button confirmButton = new Button("Evet");
        Button cancelButton = new Button("Hayır");

        confirmButton.addClickListener(event -> {
            ProductService productService = new ProductService();
            productService.deleteById(productId);
            Notification.show("Ürün silindi: " + productId, Notification.Type.WARNING_MESSAGE);
            confirmationWindow.close();
        });

        cancelButton.addClickListener(event -> confirmationWindow.close());

        layout.addComponent(confirmationLabel);
        buttonsLayout.addComponents(confirmButton, cancelButton);
        layout.addComponent(buttonsLayout);
        confirmationWindow.setContent(layout);
        getUI().addWindow(confirmationWindow);
    }

    public void showWindowForUpdate(Product product){
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        Window confirmationWindow = new Window("Ürün Güncelle");
        confirmationWindow.setModal(true);
        confirmationWindow.setContent(layout);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        TextField productName = new TextField("Ürün adı");
        ComboBox categoryName = new ComboBox("Kategori adı");
        Button confirmButton = new Button("Güncelle");
        Button cancelButton = new Button("Vazgeç");

        productName.setValue(product.getName());
        String firstCategoryName = product.getCategory().getCategoryName();

        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.findAll();

        categories.forEach(category -> categoryName.addItem(category.getCategoryName()));

        categoryName.setValue(firstCategoryName);

        confirmButton.addClickListener(event ->{
            String name = productName.getValue();
            product.setName(name);
            if (categoryName.isEmpty()){
                Notification.show("Lütfen geçerli bir kategori seçin!", Notification.Type.WARNING_MESSAGE);
            }else if(productName.isEmpty()){
                Notification.show("Ürün adı boş olamaz!", Notification.Type.WARNING_MESSAGE);
            }else{
                String category = categoryName.getValue().toString();
                product.getCategory().setCategoryName(category);
                ProductService productService = new ProductService();
                productService.update(product);
                Notification.show("Ürün güncellendi : "+ product.getName());
                confirmationWindow.close();
            }
        });

        cancelButton.addClickListener(event -> confirmationWindow.close());

        buttonsLayout.addComponents(confirmButton, cancelButton);
        layout.addComponents(productName, categoryName, buttonsLayout);

        confirmationWindow.setContent(layout);
        getUI().addWindow(confirmationWindow);
    }
}
