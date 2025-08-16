package com.zhisheng.books.intrduction_to_java_programming_10th.chapter30;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * 30-2
 */
public class FlashText extends Application {
    private String text = "";

    @Override
    public void start(Stage primaryStage) {
        StackPane stackPane = new StackPane();
        Label label = new Label("Programming is fun");
        stackPane.getChildren().add(label);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (label.getText().trim().length() == 0) {
                            text = "Welcome";
                        } else {
                            text = "";
                        }

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                label.setText(text);
                            }
                        });

                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {

                }
            }
        }).start();

        Scene scene = new Scene(stackPane, 200, 50);
        primaryStage.setTitle("Flash Text");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
