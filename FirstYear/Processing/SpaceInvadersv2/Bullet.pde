class Bullet {
  float xpos, ypos;
  PImage bulletImage;
  
  Bullet(float x, float y)
  {
    xpos = x;
    ypos = y;
    bulletImage = loadImage("bullet.png");
    bulletImage.resize(0, 60);
  }
  
  void move()
  {
   
    ypos -= BULLET_SPEED;
  }
  
  void draw()
  {
    image(bulletImage, xpos, ypos);
  }
  
  void collide()
  {
    for(int i=0; i<alienArray.length; i++)
    {
      if(xpos > alienArray[i].xpos && xpos < alienArray[i].xpos + ALIEN_WIDTH
        && ypos > alienArray[i].ypos && ypos < alienArray[i].ypos + ALIEN_HEIGHT)
      {
        alienArray[i].exploded = true;
        oof.play();
      }
    }
  }
}
