package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

import static com.switchfully.vaadin.ordergui.webapp.NavigatorUI.VIEW_NEWITEM;
import static com.switchfully.vaadin.ordergui.webapp.NavigatorUI.VIEW_UPDATE_ITEM_VIEW;

public class MainView extends CustomComponent implements View {

    private ItemResource itemResource;
    private Grid grid = new Grid();
    private BeanItemContainer<Item> container;
    private VerticalLayout mainLayout;

    public MainView(ItemResource itemResource) {
        this.itemResource = itemResource;
        mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
    }

    //todo add navigator part
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
        HorizontalLayout menubar = new HorizontalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        createMenu(menubar);
        horizontalLayout.setWidth("90%");
        addTitleLabel(horizontalLayout);
        filterByName(horizontalLayout);
        mainLayout.addComponents(menubar, horizontalLayout);
        renderItems(mainLayout);
        setCompositionRoot(mainLayout);

    }

    private void renderItems(VerticalLayout mainLayout) {
        mainLayout.addComponent(grid);
        grid.setWidth("90%");
        container = new BeanItemContainer<>(Item.class, itemResource.getItems());
        grid.setColumns("name", "description", "price", "amountOfStock", "edit");
        grid.getColumn("edit").setRenderer(new ButtonRenderer(e -> getUI().getNavigator().navigateTo(VIEW_UPDATE_ITEM_VIEW), "Edit"));
        grid.setContainerDataSource(container);
    }

    private void addTitleLabel(HorizontalLayout mainLayout) {
        Label label = new Label("<b>ITEMS:</b>", ContentMode.HTML);
        mainLayout.addComponent(label);
    }

    private void filterByName(HorizontalLayout mainLayout) {
        //FILTER FIELD
        TextField filter = new TextField();
        filter.setInputPrompt("Filter by name...");
        //FILTER BUTTON
        Button FilterTextBtn = new Button("Filter");
        FilterTextBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        FilterTextBtn.setDescription("Filter");
        FilterTextBtn.addClickListener(e -> {
            container.removeAllContainerFilters();
            container.addContainerFilter(new SimpleStringFilter("name", filter.getValue(), true, false));
        });
        //NEW ITEM BUTTON
        Button newItemBtn =
                new Button("New Item",
                        e -> getUI().getNavigator().navigateTo(VIEW_NEWITEM));
        newItemBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        FilterTextBtn.setDescription("new item");
        FilterTextBtn.addClickListener(e -> {
        });
        // SETTING THE LAYOUT OF THE THREE COMPONENTS
        HorizontalLayout filtering = new HorizontalLayout();
        filtering.addComponents(filter, FilterTextBtn, newItemBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        filtering.setSpacing(true);
        mainLayout.addComponent(filtering);
        mainLayout.setComponentAlignment(filtering, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
