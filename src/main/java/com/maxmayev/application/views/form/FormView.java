package com.maxmayev.application.views.form;

import com.maxmayev.application.backend.domain.Employee;
import com.maxmayev.application.backend.dto.EmployeeDTO;
import com.maxmayev.application.backend.dto.Registration;
import com.maxmayev.application.backend.service.ProcessingEmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.maxmayev.application.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "form", layout = MainView.class)
//@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Form")
@CssImport("styles/views/form/form-view.css")
public class FormView extends Div {

    private ProcessingEmployeeService employeeService;

    private Binder<EmployeeDTO> binder = new BeanValidationBinder<>(EmployeeDTO.class);

    private TextField firstname = new TextField("First name");
    private TextField lastname = new TextField("Last Name");
    private TextField email = new TextField("Email");
    private TextArea title = new TextArea("Title");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    public FormView(ProcessingEmployeeService processingEmployeeService) {
        this.employeeService = processingEmployeeService;
        setId("form-view");
        VerticalLayout wrapper = createWrapper();

        createTitle(wrapper);
        createFormLayout(wrapper);
        createButtonLayout(wrapper);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> binder.readBean(null));
        save.addClickListener(e -> {
            submitEmployee();
            String msg = String.format(
                    "Thank you, employee was submitted!");
            Notification.show(msg, 3000, Notification.Position.MIDDLE);
            init();

        });
        // Bind fields from this UI class to domain object using naming convention
        binder.bindInstanceFields(this);
        // enable save button only if the bean is valid
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        init();
        add(wrapper);

    }

    private void submitEmployee() {
        employeeService.add(binder.getBean().toEmployee());
    }

    private void init() {
        binder.setBean(new EmployeeDTO());
    }

    private void createTitle(VerticalLayout wrapper) {
        H1 h1 = new H1("Form");
        wrapper.add(h1);
    }

    private VerticalLayout createWrapper() {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setId("wrapper");
        wrapper.setSpacing(false);
        return wrapper;
    }

    private void createFormLayout(VerticalLayout wrapper) {
        FormLayout formLayout = new FormLayout();
        firstname.setId("firstname");
        addFormItem(wrapper, formLayout, firstname, "First name");
        addFormItem(wrapper, formLayout, lastname, "Last name");
        FormLayout.FormItem emailFormItem = addFormItem(wrapper, formLayout,
                email, "Email");
        formLayout.setColspan(emailFormItem, 2);
        FormLayout.FormItem titleFormItem = addFormItem(wrapper, formLayout,
                title, "Title");
        formLayout.setColspan(titleFormItem, 2);
    }

    private void createButtonLayout(VerticalLayout wrapper) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(cancel);
        buttonLayout.add(save);
        wrapper.add(buttonLayout);
    }

    private FormLayout.FormItem addFormItem(VerticalLayout wrapper,
            FormLayout formLayout, Component field, String fieldName) {
        FormLayout.FormItem formItem = formLayout.addFormItem(field, fieldName);
        wrapper.add(formLayout);
        field.getElement().getClassList().add("full-width");
        return formItem;
    }

}
