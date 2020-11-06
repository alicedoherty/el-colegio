// Alice, wrote first version of Line class with basic constructor and getters/setters 
// (setters have since been removed), 17/03/20, 10pm
//
// Each Line object represents one line of data from daily_prices.csv

class Line { 
  String ticker;
  float open_price;
  float close_price;
  float adjusted_close;
  float low;
  float high;
  int volume;
  // Alice, created a date format to make sorting of data easier, 24/03/20, 10am
  Date date;
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  float percentageChange;
    //Sean: created constructor
   Line(String ticker, String open_price, String close_price, String adjusted_close, String low, String high, String volume, String dateS) {
    this.ticker = ticker;
    this.open_price = Float.parseFloat(open_price);
    this.close_price = Float.parseFloat(close_price);
    this.adjusted_close = Float.parseFloat(adjusted_close);
    this.low = Float.parseFloat(low);
    this.high = Float.parseFloat(high);
    this.volume = Integer.parseInt(volume);
    try {
      date = dateFormat.parse(dateS);
    } catch (Exception e) {
      println("Date in wrong format");
    }
  }

// Alice, two comparators below added, 30/03/20, 7pm
//
// dateComparator sorts the passed in ArrayList<Line> by date associated with each Line (earliest -> latest)
// percentageChangeComparator sorts passed in ArrayList<Line> by percentageChange associated with each Line (lowest -> highest)
//
  Comparator<Line> dateComparator = new Comparator<Line>() {
    @Override
    int compare(Line line1, Line line2) {
      return line1.getDate().compareTo(line2.getDate());
    }
  };
  
  Comparator<Line> percentageChangeComparator = new Comparator<Line>() {
    @Override
    int compare(Line line1, Line line2) {
      return Float.compare(line1.getPercentageChange(), line2.getPercentageChange());
    }
  };
  
  String getTicker() {
    return ticker;
  }

  float getOpenPrice() {
    return open_price;
  }

  float getClosePrice() {
    return close_price;
  }

  float getAdjustedClose() {
    return adjusted_close;
  }

  float getLow() {
    return low;
  }

  float getHigh() {
    return high;
  }

  int getVolume() {
    return volume;
  }
  
  Date getDate() {
    return date;
  }
  
  void setPercentageChange(float percentageChange) {
    this.percentageChange = percentageChange;
  }
  
  float getPercentageChange() {
    return percentageChange;
  }

}
