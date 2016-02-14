/**
 * Licensed under the Apache License,Version2.0(the"License");you may not
 * use this file except in compliance with the License.You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an"AS IS"BASIS,WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.datenhahn.vaadin.componentrenderer.demo;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.datenhahn.vaadin.componentrenderer.grid.ComponentGrid;

/**
 * Demonstrates the use of the typed ComponentGrid
 *
 * @author Jonas Hahn (jonas.hahn@datenhahn.de)
 */
public class ComponentGridTab extends VerticalLayout {

    private static final String GENERATED_FOOD_ICON = "foodIcon";
    private static final String GENERATED_RATING = "rating";
    private static final String GENERATED_DELETE = "delete";
    private static final String GENERATED_DETAILS_ICONS = "detailsIcons";

    public ComponentGridTab() {
        init();
    }

    private void init() {

        setSizeFull();
        setMargin(true);
        setSpacing(true);

        addComponent(new Label("Look at the sourcecode to see the difference between the typed ComponentGrid and using" +
                " the classic grid"));
        ComponentGrid<Customer> grid = new ComponentGrid<>(Customer.class);
        addComponent(createEnableDisableCheckBox(grid));

        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(100, Unit.PERCENTAGE);

        grid.setRows(CustomerProvider.createDummyData());

        grid.setDetailsGenerator(new CustomerDetailsGenerator());

        grid.addComponentColumn(Customer.FOOD, cust -> ViewComponents.createFoodSelector(grid, null, cust));
        grid.addComponentColumn(GENERATED_FOOD_ICON, cust -> ViewComponents.createFoodIcon(cust));
        grid.addComponentColumn(GENERATED_RATING, cust -> ViewComponents.createRating(cust));
        grid.addComponentColumn(GENERATED_DELETE, cust -> ViewComponents.createComponentDeleteButton(grid, cust));
        grid.addComponentColumn(GENERATED_DETAILS_ICONS, cust -> ViewComponents.createDetailsIcons(grid, cust));
        grid.setFrozenColumnCount(1);

        grid.setColumns(GENERATED_DETAILS_ICONS, Customer.ID, Customer.FIRST_NAME, Customer.LAST_NAME, Customer.FOOD, GENERATED_FOOD_ICON, GENERATED_RATING, GENERATED_DELETE);

        addComponent(grid);
        setExpandRatio(grid, 1.0f);

    }

    private CheckBox createEnableDisableCheckBox(final Grid myGrid) {
        CheckBox checkBox = new CheckBox("enable/disable");
        checkBox.setValue(myGrid.isEnabled());
        checkBox.addValueChangeListener(event -> myGrid.setEnabled(!myGrid.isEnabled()));
        return checkBox;
    }

}