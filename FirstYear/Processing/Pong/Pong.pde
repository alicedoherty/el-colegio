Player thePlayer;
Player theComputer;
Ball theBall;
int playerLives;
int computerLives;
int time;

void settings() {
  size(SCREENX, SCREENY);
}

void setup() {
  thePlayer = new Player(SCREENY - MARGIN - PADDLEHEIGHT);
  theComputer = new Player(MARGIN);
  theBall = new Ball();
  
  playerLives = LIVES;
  computerLives = LIVES;
  time = millis();
  
  ellipseMode(RADIUS);
  textAlign(CENTER);
  frameRate(80);
  
  PFont myFont = loadFont("BIZ-UDPGothic-30.vlw");
  textFont(myFont);
}

void mousePressed() {
  computerLives = LIVES;
  playerLives = LIVES;
  reset();
}

void reset() {
   thePlayer = new Player(SCREENY - MARGIN - PADDLEHEIGHT);
   theComputer = new Player(MARGIN);
   theBall = new Ball();
   
   theComputer.velocity = 2; 
   background(255, 255, 255);
   
   thePlayer.draw();
   theComputer.draw();
   theBall.draw();
}

void scores() {
  if(theBall.y < theBall.radius)
  {
    if(computerLives > 0)
    {
      computerLives--;
      reset();
    }
    else 
      text("YOU WIN :)", SCREENX/2, SCREENY/2);
  }
  
  else if(theBall.y > SCREENY - theBall.radius)
  {
    if(playerLives > 0)
    {
      playerLives--;
      reset();
    }
    else
      text("YOU LOSE ;(", SCREENX/2, SCREEN/2);
  }
}

void draw() {
  background(255, 255, 255);
  
  textSize(30);
  fill(#346BB7);
  text(computerLives, 20, 40);
  text(playerLives, 20, SCREENY - 20);
  text(abs(theBall.velocity), SCREENX - 70, SCREENY - 10);
  
  thePlayer.move(mouseX);
  theBall.move();
  theComputer.moveComputer();
  
  theBall.collidePlayer(thePlayer);
  theBall.collideComp(theComputer);
  theBall.collideWall();
 
  thePlayer.draw();
  theBall.draw();
  theComputer.draw();
  
  scores();
}
