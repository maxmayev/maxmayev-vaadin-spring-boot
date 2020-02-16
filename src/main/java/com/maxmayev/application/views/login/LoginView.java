package com.maxmayev.application.views.login;

import com.maxmayev.application.backend.dto.Registration;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


//@Tag("sa-login-view")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
public class LoginView extends VerticalLayout {
    public static final String ROUTE = "login";

    private LoginForm login = new LoginForm(); //


    private Button register = new Button("Register", e->{
        UI.getCurrent().navigate(RegistrationView.ROUTE);
    });
    public LoginView(){
        this.setDefaultHorizontalComponentAlignment(FlexLayout.Alignment.CENTER);
        login.setForgotPasswordButtonVisible(false);
        login.setAction("/login"); //
        getElement().appendChild(login.getElement()); //
        add(register);

    }
}
