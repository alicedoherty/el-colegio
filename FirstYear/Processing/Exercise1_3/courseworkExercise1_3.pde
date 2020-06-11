int i=0;
int width = 50;
int height = 50;

void setup(){
  size (400, 400);
  noStroke(); 
}

void draw(){
  background(255);
  frameRate(100);
  rect(i++, 200, width, height);
  fill(255, 204, 0);
  
  if(i == 400) i = 0;
  if(i + width > 400){
    rect(0, 200, (i+width)-400, height);
  }
}
