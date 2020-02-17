/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import static AFleigerFinal.AFleigerFinal.openMenuItem;
import static AFleigerFinal.AFleigerFinal.saveMenuItem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Villegard
 */
public class Maze extends Region implements Serializable
{
    private static final long TIMER_MILSEC = 150;//150
    private static final long SUB_TIMER_MILSEC = 30;//30
    protected static final double ASPECT_RATIO = 1;
    
    
    protected static final int[][] KEY1 ={
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0},
        {0,1,0,0,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0},
        {0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0},
        {0,1,0,0,1,0,0,0,1,1,1,1,0,0,0,1,0,0,1,0},
        {0,1,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,1,0},
        {0,1,1,1,0,1,1,1,1,0,0,1,1,1,1,0,1,1,1,0},
        {0,1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,0},
        {0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0},
        {0,1,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,1,0},
        {0,1,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,1,0},
        {0,1,0,1,0,0,0,1,1,1,1,1,1,0,0,0,1,0,1,0},
        {0,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0},
        {0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
        {0,1,1,1,0,1,1,1,1,0,0,1,1,1,1,0,1,1,1,0},
        {0,1,0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0},
        {0,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,0},
        {0,1,1,1,1,0,1,0,1,1,1,1,0,1,0,1,1,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    
    protected static final int[][] KEY2={
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0},
        {0,0,1,0,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,0},
        {0,0,1,0,0,1,0,1,1,1,1,1,1,0,0,0,1,1,0,0},
        {0,0,1,1,1,1,0,0,0,1,0,0,1,1,1,0,1,0,0,0},
        {0,0,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,0},
        {0,0,1,0,0,0,0,1,0,0,1,1,1,1,1,1,0,1,0,0},
        {0,0,1,1,0,1,1,1,0,0,1,0,0,0,0,1,0,1,0,0},
        {0,0,0,1,1,1,0,1,0,1,1,1,1,0,1,1,1,1,0,0},
        {0,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,0,0},
        {0,0,1,0,1,0,0,0,0,1,0,0,1,1,1,0,1,1,0,0},
        {0,0,1,0,1,1,1,1,1,1,0,0,1,0,0,0,0,1,0,0},
        {0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,0},
        {0,0,0,1,0,1,1,1,0,0,1,0,0,0,1,1,1,1,0,0},
        {0,0,1,1,0,0,0,1,1,1,1,1,1,0,1,0,0,1,0,0},
        {0,0,1,0,1,1,1,0,0,0,0,0,1,0,1,0,0,1,0,0},
        {0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    
    protected Tile[][] mTiles;
    protected transient Canvas mCanvas;
    protected transient Canvas mCharLayer;
    protected transient StackPane mPane;
    protected Ashman mPlayer;
    protected transient AnimationTimer mTimer;
    protected transient AnimationTimer mSubTimer;
    private long mPreviousTime;
    private long mSubPreviousTime;
    private Direction mDesiredDirection;
    private Direction mDirection;
    private ArrayList<Ghost> mGhosts;
    private transient Label mStatus;
    private int mScore;
    private double mScoreMod;
    private int mGhostCount;
    private int mCurrentLevel;
    
    private transient AudioClip eat;
    private transient AudioClip captured;
    private transient AudioClip levelwin;
    private transient AudioClip victoryMusic;
    
    public Maze()
    {
        new AudioClip(getClass().getResource("sounds/intro.wav").toString()).play(.3, 0, 1, 0, 1);
        mDesiredDirection = Direction.NONE;
        mDirection = Direction.NONE;
        mGhosts = new ArrayList<>();
        mStatus = AFleigerFinal.mStatus;
        mScore = 0;
        mScoreMod = 100000;
        mGhostCount = GameSettings.initialGhosts;
        mCurrentLevel = 1;
        
        eat = new AudioClip(getClass().getResource("sounds/eat.wav").toString());
        captured = new AudioClip(getClass().getResource("sounds/captured.wav").toString());
        levelwin = new AudioClip(getClass().getResource("sounds/levelwin.wav").toString());
        victoryMusic = new AudioClip(getClass().getResource("sounds/victorymusic.wav").toString());
        
        mCanvas = new Canvas();
        mCharLayer = new Canvas();
        
        mPane = new StackPane();
        mPane.getChildren().addAll(mCanvas, mCharLayer);
        this.getChildren().add(mPane);
        this.setPrefSize(2000000, 2000000);
        setLevel(KEY1);
        mPlayer = new Ashman(randomPosition());
        for(int x = 0; x < mGhostCount; x++)
        {
            mGhosts.add(new Ghost(randomPosition(), randomDirection()));
        }
        
        mPreviousTime = System.currentTimeMillis();
        mSubPreviousTime = System.currentTimeMillis();
        mTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                onTimer(now) ;
            }
        };
        mSubTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                onSubTimer(now) ;
            }
        };
        updateStatus();
    }
    
    private void readObject(ObjectInputStream i)
    {
        try 
        {
            i.defaultReadObject();
            mCanvas = new Canvas();
            mCharLayer = new Canvas();
            mPane = new StackPane();
            mPane.getChildren().addAll(mCanvas, mCharLayer);
            this.getChildren().add(mPane);
            
            mTimer = new AnimationTimer()
            {
                @Override
                public void handle(long now)
                {
                    onTimer(now) ;
                }
            };
            mSubTimer = new AnimationTimer()
            {
                @Override
                public void handle(long now)
                {
                    onSubTimer(now) ;
                }
            };
            eat = new AudioClip(getClass().getResource("sounds/eat.wav").toString());
            captured = new AudioClip(getClass().getResource("sounds/captured.wav").toString());
            levelwin = new AudioClip(getClass().getResource("sounds/levelwin.wav").toString());
            victoryMusic = new AudioClip(getClass().getResource("sounds/victorymusic.wav").toString());
            
            mStatus = AFleigerFinal.mStatus;
            
        }
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void drawCharLayer()
    {
        GraphicsContext gc = mCharLayer.getGraphicsContext2D();
        gc.save();
        gc.clearRect(0, 0, mCharLayer.getWidth(), mCharLayer.getHeight());
        double scaleX = mCharLayer.getWidth() / 200;
        double scaleY = mCharLayer.getHeight() / 200;
        gc.scale(scaleX, scaleY);
        
        mPlayer.draw(gc);
        for(Ghost g: mGhosts)
        {
            g.draw(gc);
        }
        
        gc.restore();
    }
    
    private void setLevel(int[][] ara)
    {
        mTiles = new Tile[20][];
        Tile t;
        for(int x = 0; x < 20; x++)
        {
            mTiles[x] = new Tile[20];
            for(int j = 0; j < 20; j++)
            {
                t = new WallTile();
                if(ara[x][j] == 1)
                {
                    t = new CakeTile();
                }
                mTiles[x][j] = t;
            }
        }
    }
    
    public void drawLevel()
    {
        GraphicsContext gc = mCanvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(GameSettings.palette.getBackGroundColor());
        gc.fillRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        double scaleX = mCanvas.getWidth() / 200;
        double scaleY = mCanvas.getHeight() / 200;
        
        gc.scale(scaleX, scaleY);

        
        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                mTiles[x][y].draw(gc);
                gc.translate(10,0);
            }
            gc.translate(-200, 10);
        }
        gc.restore();
        drawCharLayer();
    }
    
    public void requestUp()
    {
        mDesiredDirection = Direction.UP;
    }
    public void requestDown()
    {
        mDesiredDirection = Direction.DOWN;
    }
    public void requestLeft()
    {
        mDesiredDirection = Direction.LEFT;
    }
    public void requestRight()
    {
        mDesiredDirection = Direction.RIGHT;
    }
    
    public void startGame()
    {
        for(Ghost g : mGhosts)
        {
            g.setDirection(randomDirection());
        }
        mTimer.start();
    }
    
    @Override
    public void layoutChildren()
    {
        double w = this.getWidth();
        double h = this.getHeight();
        
        double maxHeight =  w / ASPECT_RATIO;
        double maxWidth = h * ASPECT_RATIO;
        
        double scaledWidth, scaledHeight;
        
        if(maxHeight > h)
        {
            scaledHeight = h;
            scaledWidth = maxWidth;
        }
        else
        {
            scaledHeight = maxHeight;
            scaledWidth = w;
        }
        
        mCanvas.setWidth(scaledWidth);
        mCanvas.setHeight(scaledHeight);
        mCharLayer.setHeight(scaledHeight);
        mCharLayer.setWidth(scaledWidth);
        
        this.layoutInArea(mPane, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
        this.drawLevel();
    }
    
    private void onTimer(long now)
    {
        now = System.currentTimeMillis();
        long elapsed = (now-mPreviousTime);
        if(elapsed > TIMER_MILSEC)
        {
            nextStep();
            mPreviousTime = now;
        }
    }
    private void onSubTimer(long now)
    {
        now = System.currentTimeMillis();
        long elapsed = (now-mSubPreviousTime);
        if(elapsed > SUB_TIMER_MILSEC)
        {
            nextSubStep();
            mSubPreviousTime = now;
        }
    }
    
    private void playerStep()
    {
        //eat.stop();
        int x = mPlayer.getArrayX();
        int y = mPlayer.getArrayY();
        mPlayer.setPosition(new Point2D(y*10, x*10));
        Direction dir = mDesiredDirection;
        if(mTiles[x][y].isCake())
        {
            mTiles[x][y] = new EmptyTile();
            mScore += (5+ ((int)(1000/mScoreMod)));
            eat.play(.1, 0, 1, 0, 1);

        }
        
        switch(dir)
        {
            case UP:
                if(mTiles[x-1][y].isWall())
                {
                    dir = Direction.NONE;
                }
                break;
            case DOWN:
                if(mTiles[x+1][y].isWall())
                {
                    dir = Direction.NONE;
                }
                break;
            case LEFT:
                if(mTiles[x][y-1].isWall())
                {
                    dir = Direction.NONE;
                }
                break;
            case RIGHT:
                if(mTiles[x][y+1].isWall())
                {
                    dir = Direction.NONE;
                }
                break;
        }
        
        mDirection = dir;
    }
    private void playerSubStep()
    {
        updateStatus();
        switch(mDirection)
        {
            case UP:
                mPlayer.moveUP();
                break;
            case DOWN:
                mPlayer.moveDown();
                break;
            case LEFT:
                mPlayer.moveLeft();
                break;
            case RIGHT:
                mPlayer.moveRight();
                break;
            case NONE:
                mPlayer.moveStop();
                break;
        }   
    }
    private void ghostStep()
    {

        for(Ghost g: mGhosts)
        {
            int x = g.getArrayX();
            int y = g.getArrayY();
            Point2D gPos = new Point2D(y*10, x*10);
            g.setPosition(gPos);
            
            
            Direction dir = g.getDirection();
            boolean isValid = false;
            while(!isValid)
            {
                isValid = true;
                switch(dir)
                {
                    case UP:
                        if(mTiles[x-1][y].isWall())
                        {
                            dir = randomDirection();
                            isValid = false;
                        }
                        break;
                    case DOWN:
                        if(mTiles[x+1][y].isWall())
                        {
                            dir = randomDirection();
                            isValid = false;
                        }
                        break;
                    case LEFT:
                        if(mTiles[x][y-1].isWall())
                        {
                            dir = randomDirection();
                            isValid = false;
                        }
                        break;
                    case RIGHT:
                        if(mTiles[x][y+1].isWall())
                        {
                            dir = randomDirection();
                            isValid = false;
                        }
                        break;
                    case NONE:
                        dir = randomDirection();
                        isValid = false;
                        break;
                }
            }
            g.setDirection(dir);
        }
    }
    private void ghostSubStep()
    {
        Point2D pPos = new Point2D(mPlayer.getArrayY()*10, mPlayer.getArrayX()*10);
        mScoreMod = 1;
        for(Ghost g : mGhosts)
        {
            Point2D gPos = new Point2D(g.getArrayY()*10, g.getArrayX()*10);
            double distance = gPos.distance(pPos);
            mScoreMod += distance;
            if(distance < 10)
            {
                endGame();
                return;
            }
            
            
            switch(g.getDirection())
            {
                case UP:
                    g.moveUP();
                    break;
                case DOWN:
                    g.moveDown();
                    break;
                case RIGHT:
                    g.moveRight();
                    break;
                case LEFT:
                    g.moveLeft();
                    break;
            }
            
            gPos = new Point2D(g.getArrayY()*10, g.getArrayX()*10);
            distance = gPos.distance(pPos);
            if(distance < 10)
            {
                endGame();
                return;
            }
        }
    }
    private void nextStep()
    {
        mSubTimer.stop();
        playerStep();
        ghostStep();
        mSubTimer.start();
    }
    private void nextSubStep()
    {
        playerSubStep();
        ghostSubStep();
        drawLevel();

    }
    
    public void newGame()
    {
        pauseGame();
        mDesiredDirection = Direction.NONE;
        mDirection = Direction.NONE;
        mGhosts = new ArrayList<>();
        mScore = 0;
        mScoreMod = 100000;
        
        setLevel(KEY1);
        mCurrentLevel = 1;
        mPlayer = new Ashman(randomPosition());
        for(int x = 0; x < mGhostCount; x++)
        {
            mGhosts.add(new Ghost(randomPosition(), randomDirection()));
        }
        
        AFleigerFinal.pauseMenuItem.disableProperty().set(true);
        AFleigerFinal.goMenuItem.disableProperty().set(false);
        saveMenuItem.disableProperty().set(false);
        openMenuItem.disableProperty().set(false);
        
        drawLevel();
        updateStatus();
    }
    
    public void advanceLevel()
    {
        pauseGame();
        mDesiredDirection = Direction.NONE;
        mDirection = Direction.NONE;
        mGhosts = new ArrayList<>();
        setLevel(KEY2);
        mCurrentLevel = 2;
        mPlayer = new Ashman(randomPosition());
        for(int x = 0; x < mGhostCount+GameSettings.addedGhosts; x++)
        {
            mGhosts.add(new Ghost(randomPosition(), randomDirection()));
        }
        AFleigerFinal.pauseMenuItem.disableProperty().set(true);
        AFleigerFinal.goMenuItem.disableProperty().set(false);
        saveMenuItem.disableProperty().set(false);
        openMenuItem.disableProperty().set(false);
        
        drawLevel();
        updateStatus();
        
    }
    
    public void endGame()
    {
        captured.play(.4, 0, 1, 0, 1);
        pauseGame();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("You have been captured!");
        alert.setOnHidden(actionEvent -> newGame());

        alert.show();
    }
    
    private void updateStatus()
    {
        int cakes = getCakeCount();
        setStatus("   Score: "+mScore+"      Cakes Left: "+cakes+"      Level: "+mCurrentLevel);
        if(cakes == 0)
        {
            victory();
        }
    }
    
    private void victory()
    {
        pauseGame();
        Alert alert = new Alert(AlertType.INFORMATION);
        if(mCurrentLevel == 1)
        {
            levelwin.play(.4, 0, 1, 0, 1);
            alert.setTitle("Victory");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations, advancing to Level 2. Current Score: "+mScore);
            alert.setOnHidden(actionEvent -> advanceLevel());
        }
        else
        {
            victoryMusic.play(.3, 0, 1, 0, 1);
            alert.setTitle("Total Victory");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations, you have won. Final Score: "+mScore);
            alert.setOnHidden(actionEvent -> newGame());
        }

        alert.show();
    }
    
    public void pauseGame()
    {
        for(Ghost g : mGhosts)
        {
            g.setDirection(Direction.NONE);
        }
        mPlayer.setDirection(Direction.NONE);
        mDirection = Direction.NONE;
        mSubTimer.stop();
        mTimer.stop();
    }
    private Direction randomDirection()
    {
        Direction[] dirs = new Direction[]{Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
        return dirs[new Random().nextInt(4)];
    }
    
    private Point2D randomPosition()
    {
        int cakeCount = getCakeCount();
        Random rand = new Random();
        int pick = rand.nextInt(cakeCount);
        
        cakeCount = 0;
        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                if(mTiles[x][y].isCake())
                {
                    if(pick == cakeCount)
                    {
                        return new Point2D(y*10,x*10);
                    }
                    cakeCount++;
                }
            }
        }
        return null;
    }
    
    public void cheater()
    {
        int cakeCount = getCakeCount();
        Random rand = new Random();
        if(cakeCount == 0)
        {
            return;
        }
        int pick = rand.nextInt(cakeCount);
        
        cakeCount = 0;
        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                if(mTiles[x][y].isCake())
                {
                    if(!(pick == cakeCount))
                    {
                        mTiles[x][y] = new EmptyTile();
                    }
                    cakeCount++;
                }
            }
        }
        drawLevel();
        updateStatus();
    }
    private int getCakeCount()
    {
        int cakeCount = 0;
        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                if(mTiles[x][y].isCake())
                {
                    cakeCount++;
                }
            }
        }
        return cakeCount;
    }
    
    public void updateSettings()
    {
        int ghostCount;
        if(mCurrentLevel == 1)
        {
            ghostCount = GameSettings.initialGhosts;
        }
        else
        {
            ghostCount = GameSettings.initialGhosts + GameSettings.addedGhosts;
        }
        
        while(mGhosts.size() > ghostCount)
        {
            mGhosts.remove(0);
        }
        while(mGhosts.size() < ghostCount)
        {
            mGhosts.add(new Ghost(randomPosition(), Direction.NONE));
        }
        mGhostCount = GameSettings.initialGhosts;
        drawLevel();
    }
    
    private void setStatus(String str)
    {
        mStatus.setText(str);
    }
}
