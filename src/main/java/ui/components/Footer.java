package ui.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Footer extends HorizontalLayout {
    public Footer(){
        setWidth("100%");
        setHeight("50px");

        Label footerLabel = new Label("Universal Yazılım A.Ş © 2023 - Tüm Hakları Saklıdır.");
        footerLabel.setWidthUndefined();

        addComponent(footerLabel);
        setComponentAlignment(footerLabel, Alignment.MIDDLE_CENTER);
    }
}
