import processing.sound.*;
SoundFile shootingSound;
SoundFile oof;
SinOsc sineOsc;
Reverb reverb;

Alien[] alienArray;
Player thePlayer;
ArrayList<Bullet> bulletArray;
PImage background;

void settings() {
  size(SCREENX, SCREENY);
}

void setup() {
  frameRate(90);
  PFont myFont = loadFont("MicrosoftSansSerif-100.vlw");
  textFont(myFont);
  
  shootingSound = new SoundFile(this, "laser.wav");
  oof = new SoundFile(this, "oof.wav");
  oof.cue(0.5);
  
  sineOsc = new SinOsc(this);
  
  reverb = new Reverb(this);
  
  background = loadImage("backgroundfinal.png");

  alienArray = new Alien[NUMBER_OF_ALIENS];
  init_aliens(alienArray);

  thePlayer = new Player(SCREENY - 130);

  bulletArray = new ArrayList<Bullet>();
}

void draw() {
  println(alienArray.length);
  background(background); 

  thePlayer.move(mouseX);
  thePlayer.draw();
  

  for (Bullet bullet : bulletArray)
  {
    bullet.draw();
    bullet.move();
    bullet.collide();
  }
  
  int deathCount = 0;
  for (int i=0; i<alienArray.length; i++)
  { 
    alienArray[i].move();
    alienArray[i].draw();
    
    if (alienArray[i].exploded == true)
    {
      alienArray[i].die();
    }
    if(alienArray[i].aBomb != null && alienArray[i].hasBomb)
    {
      alienArray[i].aBomb.move();
      alienArray[i].aBomb.draw();
      alienArray[i].aBomb.offScreen();
      alienArray[i].aBomb.collide(thePlayer);

      if(alienArray[i].aBomb.collided == true)
      {
        fill(255, 255, 255);
        text("GAME OVER", SCREENX/4 - 60, SCREENY/2);  
        noLoop(); 
      }
    }
    if(alienArray[i].status == ALIEN_DEAD)
    {
      deathCount++;
      println(deathCount);
    }
    if(deathCount == NUMBER_OF_ALIENS)
    {
      fill(255, 255, 255);
      text("YOU WIN", SCREENX/4 - 10, SCREENY/2);
      sineOsc.play();
    }
  } 
}

void init_aliens (Alien alienArray[]) {
  for (int i=0; i<alienArray.length; i++)
  {
    alienArray[i] = new Alien((i*(ALIEN_WIDTH + 20)), MARGIN);
  }
}

void mousePressed() {
  bulletArray.add(new Bullet(thePlayer.xpos + 50, thePlayer.ypos - 60));
  shootingSound.play();
  reverb.process(shootingSound);
}
