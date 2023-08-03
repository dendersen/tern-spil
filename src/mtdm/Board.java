package mtdm;

import processing.core.PGraphics;

public class Board {
  private boolean[][] vertical,horizontal;
  private User[][] closed;
  public Board(int width, int height){
    vertical = new boolean[width+1][height];
    horizontal = new boolean[width][height+1];
  }
  public boolean placeLine(Point square, Direction side, User user){
    int i,j;
    switch (side) {
      case top:
      case bottom:
        i = square.getX();
        j = square.getY() + (side == Direction.top ? 0 : 1);
        horizontal[i][j] = true;
        break;
      case rigth:
      case left:
        j = square.getY();
        i = square.getX() + (side == Direction.left ? 0 : 1);
        vertical[i][j] = true;
        break;
    }
    return assign(square, user);
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
  public void draw(PGraphics g, int pixelSize){
    for (int x = 0; x < vertical.length; x++) {
      for (int y = 0; y < vertical[x].length; y++) {
        if(vertical[x][y]){
          g.strokeWeight(1);
        }else{
          g.strokeWeight(2);
        }
        g.line((pixelSize / 2) + (x * pixelSize), (pixelSize / 2) + (y * pixelSize), (pixelSize / 2) + (x * pixelSize), (pixelSize / 2) + ((y + 1) * pixelSize));
      }
    }
    for (int x = 0; x < horizontal.length; x++) {
      for (int y = 0; y < horizontal[x].length; y++) {
        if(horizontal[x][y]){
          g.strokeWeight(1);
        }else{
          g.strokeWeight(2);
        }
        g.line((pixelSize / 2) + (x * pixelSize), (pixelSize / 2) + (y * pixelSize), (pixelSize / 2) + ((x + 1) * pixelSize), (pixelSize / 2) + (y * pixelSize));
      }
    }
  }
  public int getWidth(){
    return horizontal.length;
  }
  public int getHeigth(){
    return vertical[0].length;
  }
}
