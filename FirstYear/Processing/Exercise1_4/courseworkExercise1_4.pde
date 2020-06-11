int i=0;
int j=400;
int width = 50;
int height = 50;

void setup(){
  size (400, 400);
  noStroke(); background(255,255,255);
  frameRate(150);
}

void draw(){
  background(255,255,255);
 
  fill(140, 155, 215);
  rect(i++, 200, width, height);
 
  fill(210, 140, 215);
  rect(j--, 100, width, height);
  
// square moving right
  
  if(i == 400) i = 0;
  else if(i + width > 400){
    fill(140, 155, 215);
    rect(0, 200, (i + width)-400, height);
  } 
  
// square moving left
  
  if(j == 0 - width) j = 400 - width;
  else if(j < 0){
    fill(210, 140, 215);
    rect(0, 100, (j + width), height); // end of square on left side
    rect(j + 400, 100, width, height); // start of square on right
  } 
}
