/*********************************/
/* tests the Trans_Dict class    */
/*********************************/
#include <iostream>
#include "TranslationDictionary.h"
#include <stdlib.h>

using namespace std;

void tester_function(Trans_Dict& d, string name, vector<string> fr_words);


int main() {
    Trans_Dict d1("translist");  
    Trans_Dict d2("translist2");  

    if(!d1.opened_ok) {
      cerr << "dictionary to translist did not open ok\n";
      exit(0);
    }

    if(!d2.opened_ok) {
      cerr << "dictionary to translist2 did not open ok\n";
      exit(0);
    }

    vector<string> fr_words;
    fr_words.push_back("maison");
    fr_words.push_back("chien");
    fr_words.push_back("garcon");
    tester_function(d1, "translist", fr_words);

    cout << "------------\n";

    fr_words.clear();
    fr_words.push_back("garcon");
    fr_words.push_back("sante");
    fr_words.push_back("maison");
    tester_function(d2, "translist2", fr_words);

    cout << "------------\n";

    vector<string> en_words;
    en_words.push_back("house");
    en_words.push_back("hat");
    tester_function(d1, "translist", en_words);

    cout << "------------\n";

    d1.update("chapeau","hat");
    cout << "testing d1 after adding chapeau/hat\n";
    tester_function(d1, "translist", en_words);

    cout << "------------\n";

    fr_words.clear();
    fr_words.push_back("maison");
    d1.update("maison","flat");
    cout << "testing d1 after adding maison/flat\n";
    tester_function(d1, "translist", fr_words);

    cout << "------------\n";

    d1.export_to_file("d1_dictionary");
    d2.export_to_file("d2_dictionary");
}

void tester_function(Trans_Dict& d, string name, vector<string> words) {
  string trans;
  bool found;
  for(unsigned int i=0; i < words.size(); i++) {
    if(d.lookup(words[i],trans)) {
      cout << name << " trans of " << words[i] << " is " << trans << endl;
    }
    else {
      cout << "no " << name << " trans of " << words[i] << endl;
    }
  }
}
