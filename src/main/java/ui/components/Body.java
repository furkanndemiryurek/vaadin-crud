package ui.components;

import com.vaadin.ui.*;
import ui.view.AllCategories;
import ui.view.AllProducts;

public class Body extends VerticalLayout {
    private final Button productsButton = new Button("Ürünler");
    private final Button categoriesButton = new Button("Kategoriler");
    private final VerticalLayout productsLayout = new VerticalLayout();
    private final VerticalLayout categoriesLayout = new VerticalLayout();

    public Body(){
        setSizeFull();
        buildBodyLayout();

        productsButton.addClickListener(clickEvent -> {
            productsLayout.setVisible(true);
            categoriesLayout.setVisible(false);
        });

        categoriesButton.addClickListener(clickEvent -> {
            productsLayout.setVisible(false);
            categoriesLayout.setVisible(true);
        });

    }

    public void buildBodyLayout(){
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(productsButton, categoriesButton);
        buttons.setSpacing(true);

        AllProducts allProducts = new AllProducts();
        productsLayout.addComponents(allProducts);

        AllCategories allCategories = new AllCategories();
        categoriesLayout.addComponents(allCategories);

        addComponents(buttons, productsLayout, categoriesLayout);

        categoriesLayout.setVisible(false);

        setSpacing(true);
    }
}
