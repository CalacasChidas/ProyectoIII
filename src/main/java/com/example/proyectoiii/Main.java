package com.example.proyectoiii;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.proyectoiii.Controller.drawGraph;
import static com.example.proyectoiii.Controller.generateGraph;

public class Main extends Application {
    static BufferedReader in;
    static PrintWriter out;
    private Socket client;
    private boolean done;
    private Timer timer;
    public static Pane root;

    SerialPort puerto = new SerialPort("COM3");

    @Override
    public void start(Stage primaryStage) throws IOException{
        client = new Socket("localhost",9999);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        InputHandler inHandler = new InputHandler();
        Thread t = new Thread(inHandler);
        t.start();

        root = new Pane();


        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("AirWar");

        generateGraph();
        drawGraph(root);


        /*
        Button button = new Button("Agregar avión");
        button.setLayoutX(20);
        button.setLayoutY(20);
        button.setOnAction(e -> {
            try {
                agregar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        root.getChildren().add(button);

         */

        primaryStage.show();

        startTimer();
    }
    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> notificacionAgregar(null));
            }
        }, 0, 60000);
    }

    private void notificacionAgregar(Stage primaryStage) {
        Alert notification = new Alert(Alert.AlertType.INFORMATION);
        notification.setTitle("Notificación");
        notification.setHeaderText(null);
        notification.setContentText("Ya es momento de construir un nuevo avión :)");

        ButtonType closeButton = new ButtonType("Agregar avión");
        notification.getButtonTypes().setAll(closeButton);

        Optional<ButtonType> result = notification.showAndWait();
        if (result.isPresent() && result.get() == closeButton) {
            openAgregar(primaryStage);
        }
    }
    private void openAgregar(Stage primaryStage) {
        try {
            comm();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarView.fxml"));
            Parent root = loader.load();

            agregarController controller = loader.getController();
            controller.loadItems();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar aviones");

            if (primaryStage != null) {
                stage.initOwner(primaryStage);
                stage.initModality(Modality.WINDOW_MODAL);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }


    /*
    private void agregar () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarView.fxml"));
        Parent root = loader.load();

        agregarController controller = loader.getController();
        controller.loadItems();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Agregar aviones");
        stage.show();
    }

     */
    public void shutdown(){
        done = true;
        try{
            out.close();
            in.close();
            if(!client.isClosed()){
                client.close();
            }
        }catch (IOException e){
            //Ignore
        }
    }
    class InputHandler implements Runnable{

        @Override
        public void run() {
            try{
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while(!done){
                    String message = inReader.readLine();
                    if(message.equals("/quit")){
                        inReader.close();
                        shutdown();
                    }else{
                        out.write(message);
                    }
                }
            }catch(IOException e){
                shutdown();
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Comunicacion Arduino-Java
     */
    public void comm() {
        try {
            puerto.openPort();
            System.out.println("Arduino conectado.");
            puerto.setParams(SerialPort.BAUDRATE_19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            puerto.addEventListener((SerialPortEvent event) -> {
                if (event.isRXCHAR()) {
                    System.out.println("Se ha realizado un disparo!");
                    try {
                        send("B");
                    } catch (SerialPortException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Comunicacion Java-Arduino
     * @param msg mensage que se envia de java a arduino
     */
    private void send(String msg) throws SerialPortException {
        puerto.writeString(msg);
    }
}