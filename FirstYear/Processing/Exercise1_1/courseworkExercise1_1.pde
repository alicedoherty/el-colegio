int i;
void setup() {
  size (400, 400);
  noStroke(); 
  i=175;
}

void draw(){
  background(255);
  
  rect(375-i, 200, 50, 50);
  fill(255, 204, 0);

  rect(i, 175, 50, 50);
  fill(203, 70, 60);
  
  rect(175, i, 50, 50);
  fill(80, 180, 100);
  
  if(i++>=399) i=200;
}
