package ui.view;
import com.vaadin.ui.*;
import core.entity.Category;
import core.service.CategoryService;

public class AddNewCategory extends VerticalLayout {
    CategoryService categoryService = new CategoryService();
    Window confirmationWindow = new Window("Ürün ekle");

    public AddNewCategory(){
        Button addCategoryButton = new Button("Yeni kategori ekle");
        addComponent(addCategoryButton);
        addCategoryButton.addClickListener(clickEvent -> addNewCategoryWindow());
    }

    public void addNewCategoryWindow(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        confirmationWindow.setModal(true);
        confirmationWindow.setContent(layout);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        TextField categoryName = new TextField("Kategori");

        Button saveButton = new Button("Ekle");
        Button cancelButton = new Button("Vazgeç");
        buttonsLayout.addComponents(saveButton, cancelButton);

        saveButton.addClickListener(clickEvent -> {
            if (!categoryName.isEmpty()){
                addCategory(categoryName.getValue());
            }else{
                Notification.show("Kategori adı boş olamaz");
            }
        });
        cancelButton.addClickListener(clickEvent -> confirmationWindow.close());

        layout.addComponents(categoryName, buttonsLayout);
        confirmationWindow.setContent(layout);

        getUI().addWindow(confirmationWindow);
    }

    private void addCategory(String categoryName){
        try {
            Category category = new Category();
            category.setCategoryName(categoryName);
            categoryService.add(category);
            Notification.show("Kategori eklendi : " + categoryName);
            confirmationWindow.close();
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
