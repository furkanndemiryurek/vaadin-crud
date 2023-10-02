package ui.components;

import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends VerticalLayout {
    public Header() {
        setSizeFull();
        buildHeaderLayout();
    }

    private void buildHeaderLayout() {
        Label headerLabel= new Label("Universal Yazılım Ürün Yönetim Paneli ");
        headerLabel.setSizeUndefined();
        addComponent(headerLabel);

        this.setSpacing(true);
        headerLabel.addStyleName(ValoTheme.LABEL_H1);
        setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
    }
}
