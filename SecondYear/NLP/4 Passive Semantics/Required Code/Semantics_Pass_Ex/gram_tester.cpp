#include "Relation.h"
#include "eval.h"
#include "util.h"
#include <fstream>
#include "BUPARSE_BT.h"
#include "BUPARSE_BT_TREE.h"

#include <iostream>
using namespace std;

/*

intended grammar: university_gram_with_pass 

some additional active sentences with kissed, froze

Hermione kissed Ron          true
Ron kissed Hermione          true
MrsDursley kissed Dudley     true
Dudley kissed MrsDursley     false
Hermione froze Neville       true
Neville froze Hermione       false

these should both parse and get correct truth values

additional passive sentences with kissed, frozen

Ron was kissed by Hermione	true
Hermione was kissed by Ron	true
Dudley was kissed by MrsDursley	true
MrsDursley was kissed by Dudley	false
Neville was frozen by Hermione  true
Hermione was frozen by Neville  false

these will parse, but will not currently get handled semantically correctly
and on screen will see a sem_error message

*/

int main(int argc, char *argv[]) {
  string gname,s;
  if(argc != 2) {
    cout << "usage: gram_tester GRAMMAR\n";
    cout << "where GRAMMAR is the filename of a grammar\n";
  }

  // this set up some global 'Rel' or relation objects
  // which evaluation functions will refer to
  likes = Rel(2,"likes_rel");
  //likes.show();

  student = Rel(1,"student_rel");
  //student.show();

  lecturer = Rel(1,"lecturer_rel");
  //lecturer.show();
  
  subject = Rel(1,"subject_rel");
  //subject.show();

  unicorn = Rel(1);

  kissed = Rel(2,"kissed_rel");

  froze = Rel(2,"froze_rel");

  gname = string(argv[1]);


  BUPARSE_BT_TREE parser(gname);

  parser.g.print();

  vector<string> words;

  while(cout << "enter the string to be checked "
 	&& getline(cin,s)) {
    words = tokenize(s); 
    cout << "input is " << s << endl;

    //parser.use_dot = 1;
    if(parser.parse_all(words,0)) {   
      cout << "yep\n";
      int v; 
      v = compute_s_value(parser.tree_ptr);
      cout << "value is: " << v << endl;
    }
    else {
      cout << "nope\n";
    }
  }

}

