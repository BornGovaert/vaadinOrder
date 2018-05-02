package com.switchfully.vaadin.ordergui.webapp;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class UpdateItemView extends CustomComponent implements View {

    private EditItemForm form = new EditItemForm();

    //todo add navigator to menu
    protected void createMenu(HorizontalLayout horizontalLayout) {
        MenuBar menu = new MenuBar();
        MenuBar.MenuItem örder = menu.addItem("Örder", null, null);
        MenuBar.MenuItem items = menu.addItem("Item", null, null);
        items.addItem("Items", null);
        items.addItem("New Item", null);
        items.addItem("Update Item", null);
        MenuBar.MenuItem customer = menu.addItem("Customer", null, null);
        MenuBar.MenuItem order = menu.addItem("Order", null, null);
        menu.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        horizontalLayout.addComponent(menu);
    }

    protected void init() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        createMenu(horizontalLayout);
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addComponent(horizontalLayout);
        addTitleLabel(mainLayout);
        mainLayout.addComponent(form);
        setCompositionRoot(mainLayout);
    }

    private void addTitleLabel(Layout mainLayout) {
        Label label = new Label("<b>UPDATE ITEM</b>", ContentMode.HTML);
        mainLayout.addComponent(label);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}


