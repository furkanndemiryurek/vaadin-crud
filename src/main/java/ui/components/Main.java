package ui.components;

import com.vaadin.ui.VerticalLayout;

    public class Main extends VerticalLayout {
        public Main(){
            setSizeFull();
            setHeight(null);
            buildMainLayout();
        }

        public void buildMainLayout(){
            Header header = new Header();
            Body body = new Body();
            Footer footer = new Footer();

            addComponents(header, body, footer);

            setExpandRatio(header, .4f);
            setExpandRatio(body, 7.4f);
            setExpandRatio(footer, 0.4f);
        }
    }
