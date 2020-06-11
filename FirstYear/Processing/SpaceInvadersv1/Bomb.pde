class Bomb {
  float xpos, ypos;
 // color bombColor = color(200, 100, 50); 
 // int radius;
  PImage bombImage;
  boolean offScreen;
  boolean collided;
  
  Bomb(float x, float y) {
    xpos = x;
    ypos = y;
  //  radius = 15;
    bombImage = loadImage("bomb.png");
    collided = false;
  }
  
  void move() {
    ypos += BOMB_SPEED;
  }
  
  void draw() {
   // fill(bombColor);
   // ellipse(xpos, ypos, radius, radius);
   image(bombImage, xpos, ypos);
  }
  
  void offScreen() {
    if(ypos > SCREENY)
      offScreen = true;
    else
      offScreen = false;
      System.out.println(offScreen);
  }
  
  void collide(Player position) {
    if(ypos + BOMB_HEIGHT >= position.ypos && ypos - BOMB_HEIGHT < position.ypos + SPACESHIP_HEIGHT 
      && xpos >= position.xpos && xpos <= position.xpos + SPACESHIP_WIDTH)
      collided = true; 
      
      // fix collision
  }
}
