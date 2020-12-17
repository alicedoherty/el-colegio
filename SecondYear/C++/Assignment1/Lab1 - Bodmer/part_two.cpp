/* 
 * this code labels the vowels in an interaction like this
 * enter a word
 * potato
 * p :
 * o :V
 * t :
 * a :V
 * t :
 * o :V
*/

#include <string>
#include <iostream>
using namespace std;

// declaration before use, implementation will actually be later in the file
bool is_vowel(char c);
bool is_cons(char c);

int main() {

  string s;
  cout << "Enter a word: \n";
  cin >> s;

  for(int i=0; i < s.size(); i++) {
    char c;
    c = s[i];
    cout << c << ": ";
    if(is_vowel(c))
      cout << "V";
    else if(is_cons(c))
      cout << "C";
    else
      cout << "Character is not a letter :(";
    cout << endl;
  }
}

bool is_vowel(char c) {
  if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
    return true;
  else
    return false;
}

bool is_cons(char c) {
  if(c > 97 && c <= 122 && !is_vowel(c))
    return true;
  else
    return false;
}
