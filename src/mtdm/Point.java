package mtdm;

public class Point {
  private int x,y;
  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }
  public int getY() {
    return y;
  }
  public int getX() {
    return x;
  }
  public Point add(int x, int y) {
    return new Point(this.x + x, this.y + y);
  }
}
