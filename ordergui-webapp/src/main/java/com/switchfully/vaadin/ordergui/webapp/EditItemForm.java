package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class EditItemForm extends FormLayout {

    private TextField name = new TextField("Name");
    private TextArea description = new TextArea("Description");
    private TextField price = new TextField("Price");
    private TextField amountOfStock = new TextField("AmountOfStock");
    private Label euro = new Label("\u20ac");

    private Button create = new Button("Create");
    private Button cancel = new Button("Cancel");

    private ItemResource itemResource;

    private Item item;

    public EditItemForm() {

        //todo styling of form
        //        create.setStyleName(ValoTheme.BUTTON_PRIMARY);
        //        create.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        //todo try catch block in case the string value entered for price isn't a number
        create.addClickListener(e -> create());
        cancel.addClickListener(e -> cancel());

        VerticalLayout nameAndDescription = new VerticalLayout(name, description);
        //todo figure out what 'em' is and what the difference is with %
        name.setWidth("25em");
        description.setHeight("10em");
        description.setWidth("25em");
        nameAndDescription.setSpacing(true);
        addComponent(nameAndDescription);

        HorizontalLayout priceAndStock = new HorizontalLayout(euro, price, amountOfStock);
        priceAndStock.setSpacing(true);
        addComponent(priceAndStock);
        priceAndStock.setComponentAlignment(euro, Alignment.MIDDLE_RIGHT);
        priceAndStock.setStyleName(".tester");

        HorizontalLayout buttons = new HorizontalLayout(create, cancel);
        create.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        buttons.setSpacing(true);
        addComponent(buttons);

    }

    //todo create button
    private void create() {
        item.setName(name.getValue());
        item.setDescription(description.getValue());
        int priceNumber = Integer.parseInt(price.getValue());
        item.setPrice(priceNumber);
        int amountOfStockNumber = Integer.parseInt(price.getValue());
        item.setAmountOfStock(amountOfStockNumber);
        itemResource.createItem(item);
    }

    private void cancel() {
        name.setValue("");
        description.setValue("");
        price.setValue("0");
        amountOfStock.setValue("0");
    }
}
