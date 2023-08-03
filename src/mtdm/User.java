package mtdm;

import processing.core.PApplet;
import processing.core.PGraphics;

public class User {
  int ID;
  public User(int ID){
    this.ID  = ID;
  }
  public void turn(Board table, PApplet p, int pixelSize){
    Point location = pickSquare(p, pixelSize);
    Direction side = pickSide(p);
    table.placeLine(location, side, this);
  }
  private Point pickSquare(PApplet p, int pixelSize){
    System.out.println("pick location\r");
    Main.mouseClick = false;
    while(Main.mouseClick);
    int x = p.mouseX;
    int y = p.mouseY;
    return new Point(x/pixelSize, y/pixelSize);
  }
  private Direction pickSide(PApplet p) {
    while(true){
      System.out.println((p.key == PApplet.CODED) + " " + (p.keyCode) + "   \r");
      switch(p.keyCode){
      case PApplet.UP:
        return Direction.top;
      case PApplet.DOWN:
        return Direction.bottom;
      case PApplet.LEFT:
        return Direction.left;
      case PApplet.RIGHT:
        return Direction.rigth;
      }
      try {
        Thread.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
