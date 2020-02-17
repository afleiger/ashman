/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Villegard
 */
public class AFleigerFinal extends Application {
        
    
    static Label mStatus;
    private Maze mMaze;
    private BorderPane mRoot;
    private Stage mStageRef;
    
    static MenuItem goMenuItem;
    static MenuItem pauseMenuItem;
    static MenuItem saveMenuItem;
    static MenuItem openMenuItem;
    static MenuItem settingsMenuItem;
    
    /*static Paint BACKGROUND_COLOR = Color.BLACK;
    static Paint WALL_COLOR = Color.GRAY;
    static Paint ASH_COLOR = Color.GREEN;
    static Paint CAKE_COLOR = Color.WHITESMOKE;
    static Paint GHOST_COLOR = Color.RED;*/
    
    
    @Override
    public void start(Stage primaryStage) {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        if(prefs.getBoolean("FirstRun", true) == true)
        {
            prefs.putBoolean("FirstRun", false); 
        }
        prefs.addPreferenceChangeListener(prefChangeEvent -> onPrefChanged(prefChangeEvent));
        GameSettings.readPreferences(getClass());
        
        mRoot = new BorderPane();
        
        //add menus
        mRoot.setTop(buildMenus());
        mStatus = new Label("Everything is Copacetic");
        ToolBar toolBar = new ToolBar(mStatus);
        mRoot.setBottom(toolBar);
        
        mMaze = new Maze();
        Scene scene = new Scene(mRoot);
        scene.setOnKeyPressed(keyEvent -> onKey(keyEvent));
        
        mRoot.setCenter(mMaze);
        mStageRef = primaryStage;
        
        primaryStage.setTitle("Ashman");
        primaryStage.setScene(scene);
        primaryStage.setHeight(500);
        primaryStage.setWidth(425);
        
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle("About") ;
        alert.setHeaderText("Andrew H Fleiger, CSCD 370 Final Project, Fall 2017") ;
        alert.showAndWait() ;
    }
    
    private MenuBar buildMenus()
    {
        // build a menu bar
        MenuBar menuBar = new MenuBar() ;
        
        // File menu
        Menu fileMenu = new Menu("_File") ;
        saveMenuItem = new MenuItem("_Save") ;
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S,
                    KeyCombination.CONTROL_DOWN));
        saveMenuItem.setOnAction(actionEvent -> onSave());
        
        openMenuItem = new MenuItem("_Open") ;
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O,
                    KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction(actionEvent -> onOpen());
        
        MenuItem quitMenuItem = new MenuItem("_Quit") ;
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q,
                    KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit()) ;
        
        fileMenu.getItems().addAll(saveMenuItem, openMenuItem, new SeparatorMenuItem(), quitMenuItem) ;
        
        // Game menu
        Menu gameMenu = new Menu("_Game") ;
        MenuItem newMenuItem = new MenuItem("_New Game");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N,
                    KeyCombination.CONTROL_DOWN));
        newMenuItem.setOnAction(actionEvent -> onNew());
        
        goMenuItem = new MenuItem("_Go");
        goMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.G,
                    KeyCombination.CONTROL_DOWN));
        goMenuItem.setOnAction(actionEvent -> onGo());
        
        pauseMenuItem = new MenuItem("_Pause");
        pauseMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.P,
                    KeyCombination.CONTROL_DOWN));
        pauseMenuItem.setOnAction(actionEvent -> onPause());
        pauseMenuItem.disableProperty().set(true);
        
        settingsMenuItem = new MenuItem("S_ettings");
        settingsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E,
                    KeyCombination.CONTROL_DOWN));
        settingsMenuItem.setOnAction(actionEvent -> onSettings(actionEvent));
        
        
        gameMenu.getItems().addAll(newMenuItem, new SeparatorMenuItem(), goMenuItem, pauseMenuItem, new SeparatorMenuItem(), settingsMenuItem);
        
        // Help menu
        Menu helpMenu = new Menu("_Help") ;
        MenuItem aboutMenuItem = new MenuItem("_About") ;
        aboutMenuItem.setOnAction(actionEvent -> onAbout()) ;
        helpMenu.getItems().add(aboutMenuItem) ;
        menuBar.getMenus().addAll(fileMenu,gameMenu, helpMenu) ;
        
        return menuBar ;
    }
    
    private void onNew()
    {
        //stub
        mMaze.newGame();
    }
    private void onGo()
    {
        saveMenuItem.disableProperty().set(true);
        openMenuItem.disableProperty().set(true);
        pauseMenuItem.disableProperty().set(false);
        goMenuItem.disableProperty().set(true);
        mMaze.startGame();
    }
    private void onPause()
    {
        saveMenuItem.disableProperty().set(false);
        openMenuItem.disableProperty().set(false);
        pauseMenuItem.disableProperty().set(true);
        goMenuItem.disableProperty().set(false);
        mMaze.pauseGame();
    }
    
    private void onSettings(ActionEvent event)
    {
        try
        {
            settingsMenuItem.disableProperty().setValue(true);
            SettingsLayoutController olc = new SettingsLayoutController();
            olc.initModality(Modality.WINDOW_MODAL);
            olc.setOnCloseRequest((DialogEvent event1) -> {
                settingsMenuItem.disableProperty().setValue(false);
            });
       
            olc.show();
        }
        catch(Exception e)
        {
            setStatus("Error Occured");
            System.out.println(e.getMessage());
        }
    }
    
    private void onPrefChanged(PreferenceChangeEvent event)
    {
        Platform.runLater(() -> {
            mMaze.updateSettings();
        });
    }
    
    
    private void onSave()
    {
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Line Files", "*.ash"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        file = fileChooser.showSaveDialog(mStageRef);
        
        if (file != null) {
            try 
            {
                FileOutputStream fout = new FileOutputStream(file);
                ObjectOutputStream objout = new ObjectOutputStream(fout);
                objout.writeObject(mMaze);
                objout.close();
                fout.close();
            }
            catch (IOException e) 
            {
                System.out.println(e.getMessage());
            }
            setStatus(file.getName() + " saved successfully");
        }
    }
    
    private void onOpen()
    {
        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Ashman Game File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Line Files", "*.ash")
        );
        
        File file = chooser.showOpenDialog(mStageRef);
        if (file != null) {
            try 
            {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream objin = new ObjectInputStream(fin);
                mMaze = (Maze) objin.readObject();
                mRoot.setCenter(mMaze);
                objin.close();
                fin.close();
            } 
            catch (IOException | ClassNotFoundException e) 
            {
                System.out.println(e.getMessage());
            }
            setStatus(file.getName() + " opened successfully");
        }
        
    }
    
    void setStatus(String status)
    {
        mStatus.setText(status);
    }
    
    private void onKey(KeyEvent event)
    {
        switch(event.getCode())
        {
            case RIGHT:
                mMaze.requestRight();
                break;
            case LEFT:
                mMaze.requestLeft();
                break;
            case UP:
                mMaze.requestUp();
                break;
            case DOWN:
                mMaze.requestDown();
                break;
            case HOME:
                mMaze.cheater();
                break;
                
        }
    }
    
}
