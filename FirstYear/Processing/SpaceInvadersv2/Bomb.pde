class Bomb {
  float xpos, ypos;
  PImage bombImage;
  boolean offScreen, collided;
  
  Bomb(float x, float y) {
    xpos = x;
    ypos = y;
    bombImage = loadImage("bomb.png");
    collided = false;
  }
  
  void move() {
    ypos += BOMB_SPEED;
  }
  
  void draw() {
   image(bombImage, xpos, ypos);
  }
  
  void offScreen() {
    if(ypos > SCREENY)
      offScreen = true;
    else
      offScreen = false;
      //System.out.println(offScreen);
  }
  
  void collide(Player position) {
    if(ypos + BOMB_HEIGHT >= position.ypos && ypos - BOMB_HEIGHT < position.ypos + SPACESHIP_HEIGHT 
      && xpos >= position.xpos && xpos <= position.xpos + SPACESHIP_WIDTH)
      collided = true; 
  }
}
