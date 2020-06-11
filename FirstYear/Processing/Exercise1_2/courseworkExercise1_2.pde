int i;
void setup() {
  size (400, 400);
  noStroke(); 
  i=0;
}

void draw(){
  background(255);
  
  rect(i, 200, 50, 50);
  fill(255, 204, 0);
  
  if(i++>=399) i=0;
}
