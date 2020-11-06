import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

ArrayList<Line> priceData;
ArrayList<Stock> stockData;
String[] stockArray;
SimpleDateFormat dateFormat;


void settings(){
    size(SCREENX, SCREENY);
  }
void setup() {
  PFont myFont = loadFont("Serif-14.vlw");
  textFont(myFont);
  fill(0);
  background(255);
  
  priceData = new ArrayList<Line>();
  stockData = new ArrayList<Stock>();
  stockArray = readStockInfo("stocks.csv");
  readPriceData("daily_prices100k.csv");
  println("there are " + priceData.size() + " price points");
  //println(priceData.get(250).getTicker());
  //initStock("ZYME");
  //println(stockData.get(0).getExchange());
  
  //table = loadTable("daily_prices100k.csv");
  //println(table.getRowCount());
}

void draw() {
  
  
}

void mousePressed() {
  
}
String[] readStockInfo(String fileName) {
  String[] lines = loadStrings(fileName);
  //println("there are " + lines.length + " stocks");
  return lines;
}
void initStock(String ticker) {
  for (int i = 0 ; i < stockArray.length; i++) {
    String[] data = split(stockArray[i], ",");
    if(data[0].equals(ticker)) {
      stockData.add(new Stock(stockArray[i]));
      break;
    }
  }
}
void readPriceData(String fileName) {
  Table priceTable = loadTable(fileName);
  int line = 0;
  if(fileName.equals("daily_prices100k.csv")){
    line = 1;
  }
  for(int i = line; i < priceTable.getRowCount(); i++) {
    TableRow row = priceTable.getRow(i);
    priceData.add(new Line(row.getString(0),row.getString(1),row.getString(2),row.getString(3),row.getString(4),row.getString(5),row.getString(6),row.getString(7)));
  }
  /*
  String[] lines = loadStrings(fileName);
  for (int i = 0 ; i < lines.length; i++) {
    priceData.add(new Line(lines[i]));
    //println(lines[i]);
    //text(lines[i], 10, i*15);
  }
  println("there are " + lines.length + " lines");
  */
}
