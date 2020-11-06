// Stephen 19/03/20 4pm Created the Price Over Time (POT) sketch, originally had one big draw method and sketch was in a separate folder
// Stephen 23/03/20 1pm updated layout of class to have one method to draw buttons, one to draw labels etc, and then one method to call all the smaller methods
// Stephen 31/03/20 6:45pm Made homePage and MasterList buttons clickable by utilising Diarmuid's Widget class
// Stephen 02/04/20 7:20pm updating to display stock info (exchange, sector, industry etc.) in the top right corner and to fit on the screen.
// Stephen 09/04/20 5pm worked with Diarmuid to figure out why the masterList and homePage buttons were not "clickable" and got ride of bug.
// Stephen 09/04/20 5:30pm Updated xCoordinate of data drawn on the graph by reinitialising back to original position each time the graph is drawn again. 
// Stephen 09/04/20 6pm Fixed bug where the stock info in the top right corner (ticker, sector, industry etc) was hidden because the background was redrawn over the info.
// Stephen 13/04/20 1am fixed for loop which draws the stock info, before it was the same info drawn every time.

Widget potPageHomePage, potPageMasterList;

int originXPos=100;
int originYPos=SCREEN_Y-100;
int x2=SCREEN_X-150;
int y2=50;
int x = originXPos;
int y = originYPos;

void drawPOT(String ticker) {
  closePriceOverTime = queryPriceOverTime(ticker);
  drawPotStockInfo();    // background of colour teal is drawn in this function aswell
  myFont = createFont("NSimSun",20,true);
  drawPotStockInfo();
  drawPotButtons();
  drawPotAxis();
  drawPotLabels();
  drawPotData(closePriceOverTime);
}

void drawPotAxis(){
  line(originXPos,originYPos,originXPos,y2);  // top of y axis, origin
  stroke(0);
  line(originXPos,originYPos,x2,originYPos);        // origin, end of x-axis
  stroke(0);
}

void drawPotButtons() {
  fill(PINK);
  rect((x2+10),(SCREEN_Y/2),130,50);      // homePage button
  potPageHomePage = new Widget((x2+10),(SCREEN_Y/2),130,50,5);
  rect((x2+10),((SCREEN_Y/2)+55),130,50);     // masterList button  
  potPageMasterList = new Widget((x2+10),((SCREEN_Y/2)+55),130,50,5);
}

void drawPotLabels() {
  fill(0);
  textSize(20);
  text("Home Page",(x2+25),(SCREEN_Y/2+35));
  text("Master List",(x2+25),((SCREEN_Y/2)+35)+50);
  text("Price Over Time (POT)",(SCREEN_X/2)-100,30);  // title of graph
  text("Price",5,(SCREEN_Y/2));    // y-axis label
  text("($)",10,(SCREEN_Y/2)+25);
  text("Year",((SCREEN_X/2)-100),(SCREEN_Y)-10);  // x-axis label
}

void drawPotStockInfo() {  
  fill(0);
  textSize(13);
  // Alice, added for loop to print out relevant company data, 02/04/20, 5pm
  for (Stock stock : stockData) {
    background(TEAL);
    if(stock.getTicker().equals(tickerName)) {      // Stephen, fixed bug in for loop (changed if comparison and added break) 13/04/20, 1am
      text("Name: "+ stock.getStockName() ,x2-260,80);
      text("Ticker: "+ stock.getTicker() ,x2-260,100);
      text("Sector: "+ stock.getSector() ,x2-260,120); 
      text("Industry: "+ stock.getIndustry() ,x2-260,140);    
      text("Exchange: "+ stock.getExchange() ,x2-260,160);
      break; // break when match is found so data is not only drawn once.
    }
  }
}

// xPosIncrementer is used to account for the fact that some stocks will have 10 dataPoints, whereas others may have 100, as an example.
void drawPotData(ArrayList<Float> closePriceOverTime) {
  float xPosIncrementer = (x2-originXPos) / closePriceOverTime.size();
  x = originXPos;
  for(int i=0; i<closePriceOverTime.size()-1; i++) {
       int j =i+1;
       line(x,(originYPos-(closePriceOverTime.get(i))*3),x+xPosIncrementer,(originYPos-(closePriceOverTime.get(j))*3)); // *3 to emphasis the price changes
       stroke(0);   // this colours the line.
       x+=xPosIncrementer;
       //System.out.println(""+closePriceOverTime.get(i));
   }  
}
