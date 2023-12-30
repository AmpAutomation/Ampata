package ca.ampautomation.ampata.view.blank;


import ca.ampautomation.ampata.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "blank-view", layout = MainView.class)
@ViewController("ampata_BlankView")
@ViewDescriptor("blank-view.xml")
public class BlankView extends StandardView {
}