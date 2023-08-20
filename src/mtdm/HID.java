package mtdm;

import processing.core.PApplet;
import processing.core.PGraphics;

public class HID {
  public static boolean mouseClickRigth;
  public static boolean mouseClickLeft;
  public static int mouseX;
  public static int mouseY;
  public static Direction arrow;
  public static synchronized Direction Arrow(boolean write, Direction side) {
    if(write){
      arrow = side;
    }
    return arrow;
  }
  public static boolean locked = false;
  public static void update(PApplet p) {
    mouseX = p.mouseX;
    mouseY = p.mouseY;
    if(p.key == PApplet.CODED && p.keyPressed){
      switch (p.keyCode) {
        case PApplet.UP:
          Arrow(true, Direction.top);
          break;
        case PApplet.RIGHT:
          Arrow(true, Direction.rigth);
          break;
        case PApplet.DOWN:
          Arrow(true, Direction.bottom);
          break;
        case PApplet.LEFT:
          Arrow(true, Direction.left);
          break;
      }
    }else{
      Arrow(true, Direction.none);
    }
    System.out.print(arrow);
  }
}
