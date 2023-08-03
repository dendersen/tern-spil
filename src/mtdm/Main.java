package mtdm;

import processing.core.PApplet;

public class Main extends PApplet{
  User[] users;
  int userID = 0;
  Board table;
  int pixelSize;
  public static boolean mouseClick = false;
  public static void main(String[] args) {
    Main sketch = new Main(2, 10, 10,100);
    PApplet.runSketch(new String[] {"Main"},sketch);
  }
  public Main(int userCount, int width, int height, int pixelSize){
    table = new Board(width, height);
    users = new User[userCount];
    for (int i = 0; i < users.length; i++) {
      users[i] = new User(i);
    }
    this.pixelSize = pixelSize;
  }
  @Override
  public void settings(){
    size(pixelSize + table.getWidth()*pixelSize, pixelSize + table.getHeigth()*pixelSize);
  }
  @Override
  public  void setup(){
    table.draw(getGraphics(),pixelSize);
  }
  @Override
  public void draw(){
    table.draw(getGraphics(),pixelSize);
    users[userID%users.length].turn(table, this, pixelSize);
    System.out.println("understood");
  }
  @Override
  public void mousePressed(){
    mouseClick = true;
  }
  @Override
  public void mouseReleased(){
    mouseClick = false;
  }
}
