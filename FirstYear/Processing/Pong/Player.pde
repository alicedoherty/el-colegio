20, 20class Player {
  float xpos; float ypos; 
  float velocity; float oldxpos;
  float velocityPaddle;
  float time;
  color paddlecolor = color(50);
  
  Player(int screen_y)
  {
    xpos = SCREENX/2;
    ypos = screen_y;
    velocity = 1;
    time = millis();
  }
  
  void move(int x) {
    if (x > SCREENX - PADDLEWIDTH) 
      xpos = SCREENX - PADDLEWIDTH;
    else
      xpos = x;
      
    velocityPaddle = oldxpos - xpos;
    oldxpos = xpos;
  }
   
  void moveComputer() {
    if(theComputer.xpos + PADDLEWIDTH/2 < theBall.x)
    {
      theComputer.xpos = theComputer.xpos + velocity;
    }
    else if(theComputer.xpos + PADDLEWIDTH/2 > theBall.x)
    {
      theComputer.xpos = theComputer.xpos - velocity;
    }
    
    if (millis() > time + 400)
    {
      velocity *= 1.0025;
      time = millis();
    }
  }
  
  void draw()
  {
    fill(paddlecolor);
    rect(xpos, ypos, PADDLEWIDTH, PADDLEHEIGHT);
  }
  
}
