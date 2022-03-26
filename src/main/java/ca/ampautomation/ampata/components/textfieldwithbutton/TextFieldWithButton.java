package ca.ampautomation.ampata.components.textfieldwithbutton;

import io.jmix.core.common.event.Subscription;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.ValueSource;
import io.jmix.ui.component.validation.Validator;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Consumer;


@CompositeDescriptor("text-field-with-button.xml")
public class TextFieldWithButton extends CompositeComponent<CssLayout> implements
        Field, 
        Button,
        CompositeWithCaption,
        CompositeWithHtmlCaption,
        CompositeWithHtmlDescription,
        CompositeWithIcon,
        CompositeWithContextHelp {

    public static final String NAME = "textFieldWithButton";

    private TextField valueField;
    private Button button1;

    public TextFieldWithButton() {
        addCreateListener(this::onCreate);
    }

    private void onCreate(CreateEvent createEvent) {
        valueField = getInnerComponent("textFieldWithButton_valueField");
        button1 = getInnerComponent("textFieldWithButton_button1");

 //       button1.addClickListener(clickEvent -> updateValue(step));
    }

    @Override
    public boolean isEditable() {
        return valueField.isEditable();
    }

    @Override
    public void setEditable(boolean editable) {
        valueField.setEditable(editable);
//        button1.setEnabled(editable);
    }

    @Override
    public void addValidator(Validator validator) {
        valueField.addValidator(validator);
    }

    @Override
    public void removeValidator(Validator validator) {
        valueField.removeValidator(validator);
    }

    @Override
    public Collection<Validator> getValidators() {
        return valueField.getValidators();
    }

    @Nullable
    @Override
    public Object getValue() {
        return valueField.getValue();
    }

    @Override
    public void setValue(@Nullable Object value) {
        valueField.setValue(value);
    }

    @Override
    public Subscription addValueChangeListener(Consumer listener) {
        return valueField.addValueChangeListener(listener);
    }

    @Override
    public boolean isRequired() {
        return valueField.isRequired();

    }

    @Override
    public void setRequired(boolean required) {
        valueField.setRequired(required);
        getComposition().setRequiredIndicatorVisible(required);
    }

    @Nullable
    @Override
    public String getRequiredMessage() {
        return valueField.getRequiredMessage();
    }

    @Override
    public void setRequiredMessage(@Nullable String msg) {
        valueField.setRequiredMessage(msg);
    }

    @Override
    public boolean isValid() {
        return valueField.isValid();
    }

    @Override
    public void validate() throws ValidationException {
        valueField.validate();
    }

    @Override
    public void setValueSource(@Nullable ValueSource valueSource) {
        valueField.setValueSource(valueSource);
        getComposition().setRequiredIndicatorVisible(valueField.isRequired());
    }

    @Nullable
    @Override
    public ValueSource getValueSource() {
        return valueField.getValueSource();
    }




    @Override
    public void setDisableOnClick(boolean disableOnClick) {
        button1.setDisableOnClick(disableOnClick);
    }

    @Override
    public boolean isDisableOnClick() {
        return button1.isDisableOnClick();
    }

    @Nullable
    @Override
    public KeyCombination getShortcutCombination() {
        return button1.getShortcutCombination();
    }

    @Override
    public void setShortcutCombination(@Nullable KeyCombination shortcut) {
        button1.setShortcutCombination(shortcut);
    }

    @Override
    public void setShortcut(@Nullable String shortcut) {
        button1.setShortcut(shortcut);
    }

    @Override
    public void click() {
        button1.click();
    }

    @Override
    public Subscription addClickListener(Consumer<ClickEvent> listener) {
        return button1.addClickListener(listener);
    }

    @Nullable
    @Override
    public Action getAction() {
        return button1.getAction();
    }

    @Override
    public void setAction(@Nullable Action action, boolean overrideOwnerProperties) {
        button1.setAction(action, overrideOwnerProperties);
    }

    @Override
    public void focus() {
        button1.focus();
    }

    @Override
    public int getTabIndex() {
        return button1.getTabIndex();
    }

    @Override
    public void setTabIndex(int tabIndex) {
        button1.setTabIndex(tabIndex);

    }
}