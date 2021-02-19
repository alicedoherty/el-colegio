#include "Rule.h"
#include <iostream>

Rule::Rule() {}

Rule::Rule(string input) {
  size_t i, start, len;
  string daughter;

  // set Rule.mother
  i = input.find(" --> ");         
  start = i + 5;                  
  len = i;                        
  mother = input.substr(0, len);

  // set Rule.daughters
  while ((i = input.find(",", start)) != string::npos) {
    len = i - start;
    daughter = input.substr(start, len);
    daughters.push_back(daughter);
    start = i + 1;
  }
  daughter = input.substr(start);
  daughters.push_back(daughter);
}

void Rule::show() {
  cout << mother;
  cout << " --> " << endl;
  for (unsigned int i = 0; i < daughters.size(); i++)
  {
    cout << "     " << daughters[i] << endl;
  }

  // Below is for the alternative formatting of dtrs from [example] to word(example)

  // string formatted_dtr;
  // for (unsigned int i = 0; i < daughters.size(); i++)
  // {
  //   // checks if dtr is in [] and if it is formats it as word(dtr)
  //   formatted_dtr = get_formatted_dtr(daughters[i]);
  // 
  //   // makes sure comma isn't printed after last dtr
  //   if(i < daughters.size() - 1) {
  //       cout << formatted_dtr << ", ";
  //   }
  //   else {
  //       cout << formatted_dtr << "\n";
  //   }
  // }
}

// // Check if daughter is a word in [] and reformats it
// string Rule::get_formatted_dtr(string dtr) {
//     size_t pos;
//     string word_no_brackets;
//     if(dtr.find('[') == 0) {
//         pos = dtr.find(']');
//         if(pos == dtr.size()-1) {
//             word_no_brackets = dtr.substr(1,dtr.size()-2);
// 	          return "word(" + word_no_brackets + ")";
//       }
//     }
//     return dtr;
// }

  
