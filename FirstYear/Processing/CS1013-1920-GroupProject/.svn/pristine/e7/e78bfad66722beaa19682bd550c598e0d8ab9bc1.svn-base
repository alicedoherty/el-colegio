// This tab is the entire screen for the home page.
// it is a series of draw functions that is broken up by page section, then centralized in the drawHomePage() section 
// created by Diarmuid McGonagle 
Widget homePageMasterList, homePageWinners, homePageLosers, homePageApple, homePageTesla, homePageIBM, homePageFord, homePageNetflix;

// this function adds up all the other drawHomePage function and adds a background color
// created by Diarmuid McGonagle 
void drawHomePage(){
  // background color
  background(155, 175, 181);
  drawHomeTitle();
  drawMasterListBox();
  drawLosersButtonHome();
  drawGainersButtonHome();
  drawLines();
  drawCreatedBy();
  drawFeaturedStocks();
}
// this function draws the homepage Title 
// created by Diarmuid McGonagle 
void drawHomeTitle(){
  // title
  strokeWeight(5);
  stroke(64);
  fill(255);
  rect(295, 65, 427, 75);
  fill(0);
  textFont(font1);
  textSize(36);
  text("HEDGEOVERFLOW", 350, 115);
}
// this function draws the button to the Master list and includes the button functionality 
// created by Diarmuid McGonagle 
void drawMasterListBox() {
  // link to master list
  strokeWeight(1);
  stroke(255);
  fill(137, 141, 50);
  rect(295, 395, 427, 105);
  fill(255);
  textFont(font1);
  textSize(36);
  text("Discover More Stocks!", 320, 460);
  homePageMasterList = new Widget(295, 395, 427, 105, 1);
}
// this function draws the button to the Losers list and includes the button functionality 
// created by Diarmuid McGonagle 
void drawLosersButtonHome() {
  // losers
  strokeWeight(1);
  stroke(255);
  fill(235, 50, 34);
  ellipse(335, 260, 175, 75);
  fill(255);
  textFont(font2);
  textSize(18);
  text("Lacklustre Losers", 261, 265);
  homePageLosers = new Widget(248, 230, 175, 75, 1);
}
// this function draws the button to the Gainers list and includes the button functionality 
// created by Diarmuid McGonagle 
void drawGainersButtonHome() {
  // gainers
  strokeWeight(1);
  stroke(255);
  fill(78, 172, 91);
  ellipse(690, 260, 175, 75);
  fill(255);
  textSize(18);
  text("Great Gainers", 632, 265);
  homePageWinners = new Widget(603, 230, 175, 75, 1);
}

// this function draws the two parallel lines seen on the homepage 
// created by Diarmuid McGonagle 
void drawLines() {
  // lines
  stroke(235, 164, 65);
  line(820, 0, 820,580);
  
  line(200, 0, 200,580);
}

// this function draws our names on the left handside 
// created by Diarmuid McGonagle 
void drawCreatedBy() {
 // Created by
  fill(255);
  textSize(18);
  text("Created by:", 45, 105);
    // text
    fill(0);
    textSize(12);
    text("Stephen Davis", 15, 165);
    text("Alice Doherty", 15, 265);
    text("Sean Gaffney", 15, 365);
    text("Diarmuid McGonagle", 15, 465);  
}
// this function draws the buttons to the stocks on the side and includes the button functionality 
// created by Diarmuid McGonagle 
void drawFeaturedStocks() {
  // Featured Stocks
  fill(255);
  textSize(18);
  text("Featured Stocks:", 858, 105);
    // buttons
    stroke(255);
    fill(0);
    ellipse(930, 160, 105, 50);
    ellipse(930, 240, 105, 50);
    ellipse(930, 320, 105, 50);
    ellipse(930, 400, 105, 50);
    ellipse(930, 480, 105, 50);
    // text
    fill(255);
    textSize(18);
    text("Apple", 905, 165);
    homePageApple = new Widget(870, 135, 105, 50, 1);
    text("AMSWA", 907, 245);
    homePageTesla = new Widget(870, 215, 105, 50, 1);
    text("EGHT", 913, 327);
    homePageIBM = new Widget(870, 295, 105, 50, 1);
    text("Flowers", 909, 407);
    homePageFord = new Widget(870, 375, 105, 50, 1);
    text("Raven", 903, 485);
    homePageNetflix = new Widget(870, 455, 105, 50, 1);
}
