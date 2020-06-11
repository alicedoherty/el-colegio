class Bullet {
  float xpos, ypos;
//  color bulletColor = color(200, 100, 50);
//  int radius;
  PImage bulletImage;
  
  Bullet(float x, float y)
  {
    xpos = x;
    ypos = y;
    //radius = 15;
    bulletImage = loadImage("bullet.png");
    bulletImage.resize(0, 60);
  }
  
  void move()
  {
   
    ypos -= BULLET_SPEED;
  }
  
  void draw()
  {
    //fill(bulletColor);
    //ellipse(xpos, ypos, radius, radius);
    image(bulletImage, xpos, ypos);
  }
  
  void collide()
  {
    for(int i=0; i<alienArray.length; i++)
    {
      /*if(yposBull - radius <= alienArray[i].ypos + ALIEN_HEIGHT && yposBull - radius >= alienArray[i].ypos
        && xposBull >= alienArray[i].xpos && xposBull <= alienArray[i].xpos + ALIEN_WIDTH)*/
      /*if(ypos <= alienArray[i].ypos + ALIEN_HEIGHT && ypos >= alienArray[i].ypos
       && xpos >= alienArray[i].xpos && xpos <= alienArray[i].xpos + ALIEN_WIDTH)*/
      if(xpos > alienArray[i].xpos && xpos < alienArray[i].xpos + ALIEN_WIDTH
        && ypos > alienArray[i].ypos && ypos < alienArray[i].ypos + ALIEN_HEIGHT)
      {
        alienArray[i].exploded = true;
        //println("collided!");
      }
    }
  }
  
  void removeBullet()
  {
   /* for (Bullet bullet : bulletArray)
    {
      int index = bulletArray.indexOf(bullet);
      if(yposBull <= 0)
      {
        bulletArray.remove(index);
      }
    }*/
  }
}
