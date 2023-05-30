package com.example.proyectoiii;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.example.proyectoiii.Controller.drawGraph;
import static com.example.proyectoiii.Controller.generateGraph;

public class Main extends Application {
    static BufferedReader in;
    static PrintWriter out;
    private Socket client;
    private boolean done;

    @Override
    public void start(Stage primaryStage) throws IOException{
        client = new Socket("localhost",9999);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        InputHandler inHandler = new InputHandler();
        Thread t = new Thread(inHandler);
        t.start();
        Pane root = new Pane();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Random Graph");
        primaryStage.show();
        generateGraph();
        drawGraph(root);
    }
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
}