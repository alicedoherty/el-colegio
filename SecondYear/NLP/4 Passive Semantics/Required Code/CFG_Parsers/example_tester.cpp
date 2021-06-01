#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <algorithm>

#include "BUPARSE_BT.h"
#include "BUPARSE_BT_TREE.h"

using namespace std;

vector<string> words;

void tokenize(string line) {
  /* empty the words vector */
  for(int i = words.size(); i > 0; i--) {
    words.pop_back();
  }

  /* update the words vector from line */
  string::iterator word_itr, space_itr;

  word_itr = line.begin();             /* word_itr is beginning of line */
  space_itr = find(word_itr,line.end(),' '); /* find space */
 
  while(space_itr != line.end()) {
    words.push_back(string(word_itr,space_itr));

    word_itr = space_itr+1;
    space_itr = find(word_itr,line.end(),' '); /* find space */

  }

  words.push_back(string(word_itr,space_itr)); 

}

struct Test {
  string s;
  char type;
};
  
int main(int argc, char *argv[]) {

  string gname,testname;
  if(argc != 3) {
    cout << "usage: example_tester GRAMMAR TESTS\n";
    cout << "where GRAMMAR is the filename of a grammar\n";
    cout << "where TESTS is the filename of a test file\n";
    exit(0);
  }

  gname = string(argv[1]);
  //Grammar g(gname);
  //g.print();

  testname = string(argv[2]);
  ifstream test_stream;
  test_stream.open(testname);
  if(!test_stream) { cout << "prob opening " << testname << endl; return 1; }

  vector<Test> tests;
  string s;    
  while(getline(test_stream,s)) {
    if((s[0] == '%') || (s=="")) { continue; }
    Test t;

    if(s[0] != '*') {
      t.s = s;
      t.type = '+';
    }
    else {
      t.s = s.substr(1);
      t.type = '-';
    }
      
    tests.push_back(t);
    
  }
  
  BUPARSE_BT_TREE parser(gname);

 
  parser.g.print();
  
  for(size_t i=0; i < tests.size(); i++) {
    Test t;
    t = tests[i]; 
    tokenize(t.s); 

    cout << "*************************************\n";
    if(t.type == '+') { cout << "pos "; }
    else { cout << "neg ";}
    cout << "test " << i << " " << t.s << endl;

    int verdict = parser.parse_all(words,false);
	
    if(t.type == '+') {
      if(verdict == 1) { cout << "YEP pos\n"; }
      else { cout << "NOPE pos\n"; }
    }
    else if(t.type == '-') {
      if(verdict == 0) { cout << "YEP neg\n"; }
      else { cout << "NOPE neg\n";
      }
    }
    else {
      cout << "unknown test type\n";
    }
  }

  return 0;

}




