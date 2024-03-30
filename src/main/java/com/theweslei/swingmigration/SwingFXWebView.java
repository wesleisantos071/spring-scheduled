package com.theweslei.swingmigration;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import javax.swing.*;

public class SwingFXWebView {

    private static void startSwingGUI() {
        JFrame frame = new JFrame("Swing and JavaFX WebView Example");
        final JFXPanel fxPanel = new JFXPanel();

        frame.add(fxPanel);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //this command is important to make sure JFX will run in its correct UI Thread
        Platform.runLater(() -> startJfxGUI(fxPanel));
    }

    private static void startJfxGUI(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Group root = new Group(); //create the
        Scene scene = new Scene(root, Color.ALICEBLUE);
        WebView webView = new WebView();

        webView.getEngine().load("http://www.google.com");
        root.getChildren().add(webView);

        return scene;
    }

    public static void main(String[] args) {
        // this command is important to make sure SWING will run in its correct UI Thread
        SwingUtilities.invokeLater(SwingFXWebView::startSwingGUI);
    }
}

