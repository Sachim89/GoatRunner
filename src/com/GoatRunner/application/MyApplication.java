package com.GoatRunner.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.GoatRunner.controller.BookingController;
import com.GoatRunner.controller.LoginController;


public class MyApplication extends Application{
	@Override
    public Set<Class<?>> getClasses() {
		System.out.println("Entered");
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(LoginController.class);
        s.add(BookingController.class);
        return s;
    }
}
