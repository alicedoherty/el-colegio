class Player {
  float xpos, ypos; 
  color paddlecolor = color(#C61C1C);
  PImage spaceshipImage;
  
  Player(int screen_y)
  {
    xpos = SCREENX/2;
    ypos = screen_y;
    spaceshipImage = loadImage("spaceship2.png");
  }
  
  void move(int x) {
    if (x > SCREENX - SPACESHIP_WIDTH) 
      xpos = SCREENX - SPACESHIP_WIDTH;
    else
      xpos = x;
  }
  
  void draw()
  {
    fill(paddlecolor);
    //rect(xpos, ypos, PADDLEWIDTH, PADDLEHEIGHT);
    image(spaceshipImage, xpos, ypos);
  } 
}
