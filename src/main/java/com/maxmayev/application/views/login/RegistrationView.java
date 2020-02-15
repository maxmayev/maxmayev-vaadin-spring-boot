package com.maxmayev.application.views.login;

import com.maxmayev.application.backend.dto.Registration;
import com.maxmayev.application.backend.service.RegistrationService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Tag("sa-register-view")
@Route(value = RegistrationView.ROUTE)
@PageTitle("Registration")
public class RegistrationView extends VerticalLayout {
    public static final String ROUTE = "registration";

    private RegistrationService service;

    private Binder<Registration> binder = new BeanValidationBinder<>(Registration.class);

    private TextField username = new TextField("User Name");
    private PasswordField password = new PasswordField("Password");

    @Autowired
    public RegistrationView(RegistrationService service) {
        this.service = service;
        // Build the layout
        H1 heading = new H1("Registration new user");
        Button submit = new Button("Register");
        add(
                heading,
                username,
                password,
                submit
        );

        submit.addClickListener(e -> {
            submitUser();
            String msg = String.format(
                    "Thank you, your registration was submitted!");
            Notification.show(msg, 3000, Notification.Position.MIDDLE);
            init();
            UI.getCurrent().navigate(LoginView.class);
        });


        // Add keyboard shortcut
        submit.addClickShortcut(Key.ENTER);

        // Bind fields from this UI class to domain object using naming convention
        binder.bindInstanceFields(this);
        // enable save button only if the bean is valid
        binder.addStatusChangeListener(e -> submit.setEnabled(binder.isValid()));

        init();
    }

    private void submitUser() {
        service.register(binder.getBean());
    }

    private void init() {
        binder.setBean(new Registration());
    }

}

