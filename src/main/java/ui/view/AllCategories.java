package ui.view;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import core.entity.Category;
import core.service.CategoryService;

import java.util.List;

public class AllCategories extends FormLayout {
    public AllCategories(){
        setMargin(true);
        setSpacing(true);

        Table table = new Table();
        table.addContainerProperty("ID", Integer.class, null);
        table.addContainerProperty("Kategori Adı", String.class, null);
        table.addContainerProperty("Sil", Button.class, null);
        table.addContainerProperty("Güncelle", Button.class, null);

        table.addStyleName(ValoTheme.LABEL_H1);

        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.findAll();
        categories.forEach(category -> {


            Button deleteButton = new Button("Sil");
            deleteButton.setId("deleteButton_"+category.getId());
            deleteButton.addClickListener(clickEvent -> showConfirmationDialog(category.getId()));

            Button updateButton = new Button("Güncelle");
            updateButton.setId("updateButton_"+category.getId());
            updateButton.addClickListener(clickEvent -> showWindowForUpdate(category));

            table.addItem(new Object[]{category.getId(), category.getCategoryName(),
                    deleteButton, updateButton}, category.getId());
        });

        table.setPageLength(table.size());
        table.setSelectable(true);
        table.setImmediate(true);

        AddNewCategory newCategory = new AddNewCategory();
        addComponents(newCategory, table);
    }

    private void showConfirmationDialog(int categoryId){
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        Window confirmationWindow = new Window("Silme İşlemi Onayı");
        confirmationWindow.setModal(true);
        confirmationWindow.setContent(layout);


        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        Label confirmationLabel = new Label("Silmek istediğinize emin misiniz?");
        Button confirmButton = new Button("Evet");
        Button cancelButton = new Button("Hayır");

        confirmButton.addClickListener(clickEvent -> {
            CategoryService categoryService = new CategoryService();
            categoryService.deleteById(categoryId);
            Notification.show("Kategori silindi : " + categoryId, Notification.Type.WARNING_MESSAGE);
            confirmationWindow.close();
        });

        cancelButton.addClickListener(clickEvent -> confirmationWindow.close());

        layout.addComponent(confirmationLabel);
        buttonsLayout.addComponents(confirmButton, cancelButton);
        layout.addComponent(buttonsLayout);
        confirmationWindow.setContent(layout);
        getUI().addWindow(confirmationWindow);
    }

    private void showWindowForUpdate(Category category){
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        Window confirmationWindow = new Window("Kategori Güncelle");
        confirmationWindow.setModal(true);
        confirmationWindow.setContent(layout);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        TextField categoryName = new TextField("Kategori Adı");
        Button confirmButton = new Button("Güncelle");
        Button cancelButton = new Button("Vazgeç");

        categoryName.setValue(category.getCategoryName());

        confirmButton.addClickListener(clickEvent -> {
            if (categoryName.isEmpty()){
                Notification.show("Kategori adı boş olamaz!", Notification.Type.WARNING_MESSAGE);
            }
            else {
                CategoryService categoryService = new CategoryService();
                category.setCategoryName(categoryName.getValue());
                categoryService.update(category);
                Notification.show("Kategori güncellendi : "+ category.getCategoryName());
                confirmationWindow.close();
            }
        });

        cancelButton.addClickListener(clickEvent -> confirmationWindow.close());

        buttonsLayout.addComponents(confirmButton, cancelButton);
        layout.addComponents(categoryName ,buttonsLayout);
        confirmationWindow.setContent(layout);

        getUI().addWindow(confirmationWindow);
    }


}
