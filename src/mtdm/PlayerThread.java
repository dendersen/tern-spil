package mtdm;

import processing.core.PGraphics;

public class PlayerThread extends Thread{
  Main main;
  Board table;
  User[] users;
  int userID = 0;
  public void start(Main main, int userCount, int width, int height, int pixelSize){
    setDaemon(true);
    this.main = main;
    table = new Board(width, height);
    users = new User[userCount];
    for (int i = 0; i < users.length; i++) {
      users[i] = new User(i);
    }
    super.start();
  }
  public void run(){
    while(true){
      users[userID%users.length].turn(table, main.getPixelSize());
      System.out.println("understood");
      userID++;
    }
  }
  public void draw(PGraphics g, int pixelSize){
    table.draw(g, pixelSize,userID);
  }

}
