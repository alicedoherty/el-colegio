class Stock {
  String ticker;
  String exchange;
  String name;
  String sector;
  String industry;
  ArrayList<Line> pricePoints;
  
  Stock(String rawData) {
    String[] data = split(rawData, ",");
    ticker = data[0];
    exchange = data[1];
    name = data[2];
    sector = data[3];
    industry = data[4];
    pricePoints = new ArrayList<Line>();
  }
  
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
