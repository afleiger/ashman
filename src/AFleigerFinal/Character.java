/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Villegard
 */
public abstract class Character implements Serializable
{
    protected transient Point2D mPos;
    protected Direction mDir;
    
    public Character(Point2D pos)
    {
        mPos = pos;
        mDir = Direction.NONE;
    }
    
    private void writeObject(ObjectOutputStream o) 
    {
        try {
            o.defaultWriteObject();
            o.writeDouble(mPos.getX());
            o.writeDouble(mPos.getY());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void readObject(ObjectInputStream i)
    {
        try {
            i.defaultReadObject();
            mPos = new Point2D(i.readDouble(), i.readDouble());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public double getX()
    {
        return mPos.getX();
    }
    public double getY()
    {
        return mPos.getY();
    }
    public int getArrayX()
    {
        return (int)mPos.getY() /10;
    }
    public int getArrayY()
    {
        return (int)mPos.getX() /10;
    }
    public void setPosition(Point2D pos)
    {
        mPos = pos;
    }
    public Direction getDirection()
    {
        return mDir;
    }
    public void setDirection(Direction dir)
    {
        mDir = dir;
    }
    
    public void moveRight()
    {
        mPos = mPos.add(2, 0);
        mDir = Direction.RIGHT;
    }
    public void moveLeft()
    {
        mPos = mPos.add(-2, 0);
        mDir = Direction.LEFT;
    }
    public void moveUP()
    {
        mPos = mPos.add(0, -2);
        mDir = Direction.UP;
    }
    public void moveDown()
    {
        mPos = mPos.add(0, 2);
        mDir = Direction.DOWN;
    }
    public void moveStop()
    {
        mDir = Direction.NONE;
    }
    
    public abstract void draw(GraphicsContext gc);
}

