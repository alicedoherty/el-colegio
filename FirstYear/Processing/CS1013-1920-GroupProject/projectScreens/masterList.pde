// This tab is the entire screen for the Master list page.
// it is a series of draw functions that is broken up by page section, then centralized in the drawMasterList() section 
// created by Diarmuid McGonagle 
Widget masterPageHome, masterPageWinners, masterPageLosers, masterPageChangeExchange;
ArrayList<Widget> masterListStockButtons;
import java.text.DecimalFormat;
private static DecimalFormat df = new DecimalFormat("0.00");

// this function adds up all the other drawMasterList function and adds a background color
// it also includes  added functionality for the scrollbar (push/pop matrix)
// created by Diarmuid McGonagle 
void drawMasterList(ArrayList<Line> exchange){
  // background color
 
  pushMatrix();
  background(242,242,242);
  scrollBar.display();
  scrollBar.update();
  float newYValue = scrollBar.scrollValue(); 
  drawGainersButtonMaster();
  drawLosersButtonMaster();
  drawHomePageButtonMaster();
  drawChangeExchangeButton();
  drawTitle();
  drawStockButtons(exchange, newYValue);
  translate (0, newYValue);
  drawStockNames(exchange, newYValue);
  
  drawOpenPrice(exchange);
  drawClosePrice(exchange);
  drawChangeOverTime(exchange);
  
  popMatrix();
}
// this function draws the master list Title 
// created by Diarmuid McGonagle 
void drawTitle() {
  // title
  strokeWeight(5);
  stroke(64);
  fill(255);
  rect(295, 35, 427, 75);
  fill(0);
  textFont(font1);
  textSize(36);
  text("" + exchangeName, 460, 85);
}
// this function draws the names of the Stocks
// it includes a for loop that iterates through the stocks and prints them out as well as a button to that stock
// created by Diarmuid McGonagle 
void drawStockNames(ArrayList<Line> exchange, float yVal) {
  // names of stonks
  for (int i = 0; i < exchange.size(); i++) {
    // use yPlace to choose where to put names of stonks
    int yPlace = (i * 50) + 200;
    strokeWeight(1);
    stroke(255);
    fill(137, 141, 50);
    rect(50, yPlace, 200, 50);
    fill(0);
    textFont(font2);
    textSize(18);
    text(exchange.get(i).getTicker(), 120, yPlace + 30);
    System.out.println(""+yVal);
    //Widget stockButton = new Widget(50, yPlace, 200, 50, 5, exchange.get(i).getTicker());
    //masterListStockButtons.add(stockButton);
  }
}
void drawStockButtons(ArrayList<Line> exchange, float yVal) {
  // names of stonks
  for (int i = 0; i < exchange.size(); i++) {
    int yPlace = (i * 50) + 200;
    
    //fill(0, 141, 50);
    //rect(50, yPlace, 200, 50);
    //fill(0);
    //textFont(font2);
    //textSize(18);
    //text(exchange.get(i).getTicker(), 120, yPlace + 30);
    // use yPlace to choose where to put names of stonks
    Widget stockButton = new Widget(50, yPlace + (int)yVal, 200, 50, 5, exchange.get(i).getTicker());
    masterListStockButtons.add(stockButton);
  }
}
// this function draws the Open Prices included 
// created by Diarmuid McGonagle 
void drawOpenPrice(ArrayList<Line> exchange) {
    strokeWeight(1);
    stroke(255);
    fill(197,207,210);
    rect(295, 145, 200, 50);
    fill(255);
    textFont(font2);
    textSize(18);
    text("Open Price", 345, 175);
    // repetitve
    for (int i = 0; i < exchange.size(); i++) {
      // use yPlace to choose where to put names of stonks
      int yPlace = (i * 50) + 200;
      strokeWeight(1);
      stroke(255);
      fill(197,207,210);
      rect(295, yPlace, 200, 50);
      fill(255);
      textFont(font2);
      textSize(18);
      text(exchange.get(i).getOpenPrice(), 365, yPlace + 30);
    }
}
// this function draws the Close Prices included 
// created by Diarmuid McGonagle 
void drawClosePrice(ArrayList<Line> exchange) {
    strokeWeight(1);
    stroke(255);
    fill(197,207,210);
    rect(495, 145, 200, 50);
    fill(255);
    textFont(font2);
    textSize(18);
    text("Close Price", 545, 175);
    // repetitve
    for (int i = 0; i < exchange.size(); i++) {
      // use yPlace to choose where to put names of stonks
      int yPlace = (i * 50) + 200;
      strokeWeight(1);
      stroke(255);
      fill(197,207,210);
      rect(495, yPlace, 200, 50);
      fill(255);
      textFont(font2);
      textSize(18);
      text(exchange.get(i).getClosePrice(), 565, yPlace + 30);
    }
}
// this function calculates and draws the Change Over Time
// created by Diarmuid McGonagle 
void drawChangeOverTime(ArrayList<Line> exchange) {
  // Open Price
    strokeWeight(1);
    stroke(255);
    fill(197,207,210);
    rect(695, 145, 200, 50);
    fill(255);
    textFont(font2);
    textSize(18);
    text("Change Over Time", 715, 175);
    // repetitve
    for (int i = 0; i < exchange.size(); i++) {
      // use yPlace to choose where to put names of stonks
      int yPlace = (i * 50) + 200;
      strokeWeight(1);
      stroke(255);
      fill(197,207,210);
      rect(695, yPlace, 200, 50);
      fill(255);
      textFont(font2);
      textSize(18);
      float openPrice = exchange.get(i).getOpenPrice();
      float closePrice = exchange.get(i).getClosePrice();
      float changeOverTime = (closePrice / openPrice * 100)-100;
      text(df.format(changeOverTime) + "%", 765, yPlace + 30);
    }
  
}
// this function draws the button for the Gainers list and includes the functionality 
// created by Diarmuid McGonagle 
void drawGainersButtonMaster() {
  // gainers
  strokeWeight(1);
  stroke(255);
  fill(78, 172, 91);
  ellipse(85, 77, 87, 37);
  fill(255);
  textSize(12);
  text("Winners", 62, 82);
  masterPageWinners = new Widget(42, 58, 87, 37, 2);
}
// this function draws the button for the Losers list and includes the functionality 
// created by Diarmuid McGonagle 
void drawLosersButtonMaster() {
  // losers
  strokeWeight(1);
  stroke(255);
  fill(235, 50, 34);
  ellipse(200, 77, 87, 37);
  fill(255);
  textFont(font2);
  textSize(12);
  text("Losers", 180, 82);
  masterPageLosers = new Widget(155, 58, 87, 37, 2);
}
// this function draws the button for the HomePage and includes the functionality 
// created by Diarmuid McGonagle 
void drawHomePageButtonMaster() {
  // link to master list
  strokeWeight(1);
  stroke(255);
  fill(0,60,113);
  rect(765, 45, 200, 50);
  fill(255);
  textFont(font1);
  textSize(18);
  text("HedgeOverFlow", 795, 78);
  masterPageHome = new Widget(765, 45, 200, 50, 2);
}
// this function draws the button for the stock exchange and includes the functionality 
// created by Diarmuid McGonagle 
void drawChangeExchangeButton() {
  // link to master list
  strokeWeight(1);
  stroke(255);
  fill(0,60,113);
  rect(50, 125, 200, 50);
  fill(255);
  textFont(font1);
  textSize(18);
  text("" + previousExchangeName, 100,160);
  masterPageChangeExchange = new Widget(50, 125, 200, 50, 2);
}
