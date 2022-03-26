package ca.ampautomation.ampata.components.textfieldwithbutton;

import com.google.common.base.Strings;
import io.jmix.ui.xml.layout.loader.AbstractFieldLoader;

public class TextFieldWithButtonLoader extends AbstractFieldLoader<TextFieldWithButton> {
    @Override
    public void createComponent() {
        resultComponent = factory.create(TextFieldWithButton.NAME);
        loadId(resultComponent, element);
    }

    @Override
    public void loadComponent() {
        super.loadComponent();
/*
        String incrementStr = element.attributeValue("step");
        if (!Strings.isNullOrEmpty(incrementStr)) {
            resultComponent.setStep(Integer.parseInt(incrementStr));
        }
*/
    }
}