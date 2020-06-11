float i=0.0;
float j=400.0;
float width = 50.0;
float height = 50.0;
int colourCode;
float x;
float sinFunc;

void setup(){
  size (400, 400);
  noStroke(); background(255,255,255);
  colorMode(RGB,100);
  colourCode = 0;
  x = PI;
  sinFunc = sin(x);
}

void draw(){
  background(255,255,255);
 
  fill(colourCode++);
  x++;
  rect(i++, sinFunc, width, height);
  if (colourCode == 255) colourCode--;
  
  if(i == 400) i = 0;
  else if(i + width > 400){
    fill(colourCode++);
    rect(0, sinFunc, (i + width)-400, height);
  } 
  
}
