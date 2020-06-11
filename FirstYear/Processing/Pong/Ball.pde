class Ball {
  float x; float y;
  float dx; float dy;
  float time;
  int radius;
  float velocity;
  color ballColor = color(200, 100, 50);
  
  Ball() {
    x = random(SCREENX/4, SCREENX/2);
    y = random(SCREENX/4, SCREENX/2);
    dx = random(1, 2); dy = random(1, 2);
    radius = 5;
    time = millis();
  }
  
  void move() {
    x = x + dx; 
    y = y + dy;
    
    if (millis() > time + 200)
    {
      dx *= 1.01;
      dy *= 1.01;
      time = millis();
    }
    velocity = dx;
  }
  
  void draw() {
    fill(ballColor);
    ellipse(int(x), int(y), radius, radius);
  }
  
  void collideWall() {
    if(x - radius <= 0)
      dx = -dx;
    else if(x + radius >= SCREENX)
      dx = -dx;
  }
  
  
  void collidePlayer(Player tp) {  
    if(y + radius >= tp.ypos && y - radius < tp.ypos + PADDLEHEIGHT && x >= tp.xpos && x <= tp.xpos + PADDLEWIDTH)
    {
      println("collided!");
      dy = -dy;
      dx += 0.2*tp.velocityPaddle;
    }
  }
  
  void collideComp(Player cp) {
    if(y - radius <= cp.ypos + PADDLEHEIGHT && y - radius >= cp.ypos && x >= cp.xpos && x <= cp.xpos + PADDLEWIDTH)
    {
      println("collided!");
      dy = -dy;
    }
  }
  
}
