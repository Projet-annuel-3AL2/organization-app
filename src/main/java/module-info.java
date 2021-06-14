module com.agirpourtous {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.cli;
    requires spring.webflux;
    requires spring.core;
    requires spring.web;
    requires reactor.core;
    requires com.fasterxml.jackson.annotation;
    requires reactor.extra;
    requires reactor.netty.http;
    requires slf4j.api;
    requires org.eclipse.jetty.client;
    requires org.reactivestreams;
    requires com.fasterxml.jackson.core;
    requires org.controlsfx.controls;
    requires org.pf4j;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens com.agirpourtous.core.models;
    opens com.agirpourtous.core.api.requests;
    opens com.agirpourtous.gui;
    opens com.agirpourtous.gui.plugin;
    exports com.agirpourtous.core.models;
    exports com.agirpourtous.core.api;
    exports com.agirpourtous.core.api.requests;
    exports com.agirpourtous.core.api.services;
    exports com.agirpourtous.gui;
    exports com.agirpourtous.gui.plugin;
    exports com.agirpourtous.gui.controllers;
    exports com.agirpourtous.gui.controllers.elements;
    exports com.agirpourtous.gui.controllers.popups;
    exports com.agirpourtous;
}