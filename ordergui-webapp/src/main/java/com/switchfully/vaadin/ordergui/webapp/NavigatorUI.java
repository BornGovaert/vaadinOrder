package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class NavigatorUI extends UI {

    private Navigator navigator;
    private ItemResource itemResource;

    public static final String VIEW_HOME = "";
    public static final String VIEW_NEWITEM = "newItem";
    public static final String VIEW_UPDATE_ITEM_VIEW = "updateItem";

    @Autowired
    public NavigatorUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        // Create and register the views
        navigator.addView(VIEW_HOME, new MainView(itemResource));
        navigator.addView(VIEW_NEWITEM, new NewItemView());
        navigator.addView(VIEW_UPDATE_ITEM_VIEW, new UpdateItemView());
        // todo Placeholder - what is this?
        // setContent(new CssLayout());
    }
}
