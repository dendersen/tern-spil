package mtdm;

public class User {
  int ID;
  public User(int ID){
    this.ID  = ID;
  }
  public void turn(Board table, int pixelSize){
    while(true){
      if(HID.mouseClickLeft){
        HID.mouseClickLeft = false;
        HID.locked = true;
        Direction side;
        Point location = new Point((HID.mouseX-pixelSize/2)/pixelSize, (HID.mouseY-pixelSize/2)/pixelSize);
        while(true){
          side = Direction.values()[HID.Arrow(false, null).ordinal()];
          if(side != Direction.none) {
            break;
          }
          if(HID.mouseClickLeft){
            Point temp = new Point((HID.mouseX-pixelSize/2)/pixelSize, (HID.mouseY-pixelSize/2)/pixelSize);
            if(
              temp.getX() < table.getWidth() && 
              temp.getY() < table.getHeigth() &&
              temp.getX() >= 0 && 
              temp.getY() >= 0
            )
            location = temp;
          }
          table.setActive(location);
        }
        if(!table.placeLine(location, side, this)){
          break;
        }
      }else{
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      table.setActive(null);
    }
  }
}
