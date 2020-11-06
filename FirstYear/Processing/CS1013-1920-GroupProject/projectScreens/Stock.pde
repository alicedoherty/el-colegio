// Stock class stores and returns data loaded from stocks.csv and is used to organize all Line data with the same ticker.
//Created by Sean Gaffney
class Stock {
  String ticker;
  String exchange;
  String name;
  String sector;
  String industry;
  ArrayList<Line> pricePoints;
  // take each like from stock.csv and splits into variables
  Stock(String rawData) {
    String[] data = split(rawData, ",");
    ticker = data[0];
    exchange = data[1];
    name = data[2];
    sector = data[3];
    industry = data[4];
    pricePoints = new ArrayList<Line>();
  }
  // adds all corresponding Lines to pricePoints arrayList
  void getStockData() {
    for(Line i : priceData) {
      if(ticker.equals(i.getTicker())) {
        pricePoints.add(i);
      }
    }
  }
  ArrayList getPricePoints() {
   return pricePoints;
  }
   
  String getTicker() {
    return ticker;
  }
  String getExchange() {
    return exchange;
  }
  //getName is a default function
  String getStockName() {
    return name;
  }
  String getSector() {
    return sector;
  }
  String getIndustry() {
    return industry;
  }
}
