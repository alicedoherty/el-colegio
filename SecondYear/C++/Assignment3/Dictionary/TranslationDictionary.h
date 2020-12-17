/******** TranslationDictionary.h ************************/
#include <string>
#include <vector>
using namespace std;

struct Transpair {
  string french;
  string english;
};

class Trans_Dict {
  private:
    vector<Transpair> thewords;

  public:
    Trans_Dict();
    Trans_Dict(string file_name);
    bool opened_ok; 
    bool lookup(string word, string& trans);
    void update(string french, string english);
    void export_to_file(string filename);
};




