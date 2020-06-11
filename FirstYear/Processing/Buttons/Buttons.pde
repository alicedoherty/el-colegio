ArrayList widgetList;
PFont stdFont;
int squareColor;
final int EVENT_BUTTON1 = 1;
final int EVENT_BUTTON2 = 2;
final int EVENT_BUTTON3 = 3;
final int EVENT_NULL = 0;

void setup() { 
  Widget widget1, widget2, widget3;
  size(400, 400);
  stdFont=loadFont("ComicSansMS-26.vlw"); 
  textFont(stdFont);
  
  squareColor = #FFFFFF;

  widget1 = new Widget(100, 100, 100, 40, "red", 
    color(255, 0, 0), stdFont, EVENT_BUTTON1);
  widget2 = new Widget(100, 200, 100, 40, "green", 
    color(0, 255, 0), stdFont, EVENT_BUTTON2); 
  widget3 = new Widget(100, 300, 100, 40, "blue", 
    color(0, 0, 255), stdFont, EVENT_BUTTON3);


  widgetList = new ArrayList(); 
  widgetList.add(widget1); 
  widgetList.add(widget2);
  widgetList.add(widget3);
}

void draw() { 
  for (int i = 0; i<widgetList.size(); i++) { 
    Widget aWidget = (Widget) widgetList.get(i); 
    aWidget.border(mouseX, mouseY);
    aWidget.draw();
  }
  
  stroke(#000000);
  fill(squareColor);
  rect(20, 20, 50, 50);
}

void mousePressed() {
  int event; 

  for (int i = 0; i<widgetList.size(); i++) { 
    Widget aWidget = (Widget) widgetList.get(i); 
    event = aWidget.getEvent(mouseX, mouseY); 
    switch(event) { 
    case EVENT_BUTTON1:  
      squareColor = #FF0000;
      break; 
    case EVENT_BUTTON2: 
      squareColor = #00FF00;
      break;
    case EVENT_BUTTON3:
      squareColor = #0000FF;
      break;
    }
  }
}
