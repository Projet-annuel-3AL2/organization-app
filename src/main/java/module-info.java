module com.agirpourtous {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.cli;
    requires spring.webflux;
    requires spring.core;
    requires spring.web;
    requires reactor.core;
    requires org.reactivestreams;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens com.agirpourtous.gui;
    exports com.agirpourtous.core.models;
    exports com.agirpourtous.core.api;
    exports com.agirpourtous.core.api.requests;
    exports com.agirpourtous.gui;
    exports com.agirpourtous.gui.controllers;
    exports com.agirpourtous.gui.controllers.elements;
    exports com.agirpourtous;
}