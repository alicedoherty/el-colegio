class Alien { //<>//
  int xpos, ypos, direction, status, count, index;
  float random;
  boolean exploded;
  PImage alienImage, explodingImage;
  
  Bomb aBomb;
  boolean hasBomb;

  Alien(int x, int y) {
    xpos = x;
    ypos = y;
    exploded = false;
    status = ALIEN_ALIVE;
    direction = 1;
   // alienImage = loadImage("spacer.gif");
    alienImage = loadImage("invader.png");
   // explodingImage = loadImage("exploding.gif");
    explodingImage = loadImage("bonk2.png");
    
    aBomb = null;
    hasBomb = false; 
  }

  void move() {
    if (xpos == SCREENX - ALIEN_WIDTH || xpos < 0)
    {
      if (count < ALIEN_HEIGHT) 
      {
        ypos++;
        count++;
      } 
      else 
      {
        direction *= -1;
        xpos+=direction;
        count=0;
      }
    } 
    else 
    {
      xpos+=direction;
    }
  }

  void draw() {
    if (status==ALIEN_ALIVE)
    {
      image(alienImage, xpos, ypos);
      if(!hasBomb)
      {
        random = random(1000);
        if(random > 999.6)
        {
          aBomb = new Bomb(xpos, ypos);
          hasBomb = true;
        }  
      }
    }
    else if (status != ALIEN_DEAD)
    {
      image(explodingImage, xpos - 80, ypos - 40);
      status++;
    }
  }

  void die() {
    if (status==ALIEN_ALIVE)
    {
      status++;
    }
  }
}
