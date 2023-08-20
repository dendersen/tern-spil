package mtdm;

import processing.core.PApplet;

public class Main extends PApplet{
  PlayerThread GUI;
  final private int pixelSize = 100;
  final private int width = 10;
  final private int height = 10;
  final private int userCount = 5;

  public int getPixelSize() {
    return pixelSize;
  }
  public static boolean mouseClick = false;
  public static void main(String[] args) {
    Main sketch = new Main();
    PApplet.runSketch(new String[] {"Main"},sketch);
  }
  @Override
  public void settings(){
    GUI = new PlayerThread();
    GUI.start(this, userCount, width, height, pixelSize);
    size(pixelSize + width*pixelSize, pixelSize + height*pixelSize);
  }
  @Override
  public  void setup(){
    GUI.draw(getGraphics(),pixelSize);
    g.fill(0);
    g.stroke(0);
  }
  @Override
  public void draw(){
    HID.update(this);
    GUI.draw(getGraphics(),pixelSize);
    System.out.print("        \r");
  }
  @Override
  public void mousePressed(){
    if(HID.locked) return;
    if(mouseButton == RIGHT){
      HID.mouseClickRigth = true;
    }else{
      HID.mouseClickLeft = true;
    }
    System.out.print(mouseButton);
  }
  @Override
  public void mouseReleased(){
    HID.locked = false;
    if(mouseButton == RIGHT){
      HID.mouseClickRigth = false;
    }else{
      HID.mouseClickLeft = false;
    }
    System.out.print(mouseButton);
  }
}
