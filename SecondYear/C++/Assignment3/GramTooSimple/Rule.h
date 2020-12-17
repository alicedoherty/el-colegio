#include <string>
#include <vector>
using namespace std;

class Rule {
 public:
  Rule();
  Rule(string input);
  string mother;
  vector<string> daughters;
  void show();
  //string get_formatted_dtr(string dtr);
};
