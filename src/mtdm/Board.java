package mtdm;

import processing.core.PGraphics;

public class Board {
  private boolean[][] vertical,horizontal;
  private User[][] closed;
  private Point active;
  public Board(int width, int height){
    vertical = new boolean[width+1][height];
    horizontal = new boolean[width][height+1];
    closed = new User[width][height];
  }
  public boolean placeLine(Point square, Direction side, User user){
    int i,j;
    switch (side) {
      case top:
      case bottom:
        i = square.getX();
        j = square.getY() + (side == Direction.top ? 0 : 1);
        if(!horizontal[i][j]){
          horizontal[i][j] = true;
          return assign(square, user);
        }
        break;
      case rigth:
      case left:
        j = square.getY();
        i = square.getX() + (side == Direction.left ? 0 : 1);
        if(!vertical[i][j]){
          vertical[i][j] = true;
          return 
          assign(square, user) ||
          assign(square.add(1,0), user) ||
          assign(square.add(0,1), user) ||
          assign(square.add(-1,0), user) ||
          assign(square.add(0,-1), user);
        }
        break;
      default:
        break;
    }
    System.out.println("his line is already chosen");
    return true;
  }
  private boolean assign(Point square, User user) {
    if(
      closed[square.getX()][square.getY()] == null &&
      isComplete(square)
    ){
      closed[square.getX()][square.getY()] = user;
      return true;
    }
    return false;
  }
  private boolean isComplete(Point square) {
    return 
    horizontal[square.getX()][square.getY()] && 
    horizontal[square.getX()][square.getY()+1] && 
    vertical[square.getX()][square.getY()] && 
    vertical[square.getX()+1][square.getY()];
  }
  public void draw(PGraphics g, int pixelSize, int userID){
    g.background(220);
    g.strokeWeight(1);
    g.fill(0);
    g.text(userID,pixelSize/4,pixelSize/4);
    for (int x = 0; x < vertical.length; x++) {
      for (int y = 0; y < vertical[x].length; y++) {
        if(vertical[x][y]){
          g.strokeWeight(4);
        }else{
          g.strokeWeight(1);
        }
        g.line((pixelSize / 2) + (x * pixelSize), (pixelSize / 2) + (y * pixelSize), (pixelSize / 2) + (x * pixelSize), (pixelSize / 2) + ((y + 1) * pixelSize));
      }
    }
    for (int x = 0; x < horizontal.length; x++) {
      for (int y = 0; y < horizontal[x].length; y++) {
        if(horizontal[x][y]){
          g.strokeWeight(4);
        }else{
          g.strokeWeight(1);
        }
        g.line((pixelSize / 2) + (x * pixelSize), (pixelSize / 2) + (y * pixelSize), (pixelSize / 2) + ((x + 1) * pixelSize), (pixelSize / 2) + (y * pixelSize));
      }
    }
    for (int x = 0; x < closed.length; x++) {
      for (int y = 0; y < closed[x].length; y++) {
        if(closed[x][y] != null){
          g.text(closed[x][y].ID, x * pixelSize + pixelSize, y * pixelSize + pixelSize);
        }
      }
    }
    if(active != null){
      g.fill(255, 0, 0);
      g.strokeWeight(0);
      g.square(
        active.getX() * pixelSize + pixelSize * 0.6f, 
        active.getY() * pixelSize + pixelSize * 0.6f, 
        pixelSize / 10 * 8
      );
    }
  }
  public void setActive(Point square){
    active = square;
  }
  public int getWidth(){
    return horizontal.length;
  }
  public int getHeigth(){
    return vertical[0].length;
  }
}
