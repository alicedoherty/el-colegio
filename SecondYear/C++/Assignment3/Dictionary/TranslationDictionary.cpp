  /************* TranslationDictionary.cpp **********************/
  #include <string>
  #include <iostream>
  #include <fstream>

  #include "TranslationDictionary.h"

  using namespace std;

  Trans_Dict::Trans_Dict() {}

  /*************************************************************************/
  /* builds dictionary from file with translation pairs source/translation */
  /* on single lines                                                       */
  /*************************************************************************/
  Trans_Dict::Trans_Dict(string file_name) {
    string line, translation, word;
    ifstream f;
    Transpair p;
    size_t trans_offset;


    f.open(file_name);
    if(f) {
      opened_ok = true;
    }
    else {
      opened_ok = false;
    }
      
    if(opened_ok) {

      while(f >> line) {
        trans_offset = line.find('/'); /* trans_offset is location of / */

        if(trans_offset == string::npos) {
          cout << "there was an entry with no /\n";
          continue;
        }
        else {
          word = line.substr(0,trans_offset); // up to / 
          translation = line.substr(trans_offset+1); // after / 
          // make transpair with word and translation
          p.french = word;
          p.english = translation;
          thewords.push_back(p); // add transpair to thewords
        }
      }
    }
  }


  /*****************************************************************/
  /* looks up word in dictionary, returning true or false          */
  /* setting 'trans' to contain the translation if found 
  /*****************************************************************/
  bool Trans_Dict::lookup(string word, string &trans) {
    for (int i = 0; i < thewords.size(); i++) {
      // if word is French - will look for match
      if (thewords[i].french == word) {
        trans = thewords[i].english;
        return true;
      }
      // else if word is English - will go into this statement and look for it
      else if (thewords[i].english == word) {
        trans = thewords[i].french;
        return true;
      }
    }
    return false;
  }

  void Trans_Dict::update(string french, string english) {
    for (int i = 0; i < thewords.size(); i++) {
      // if French/English pair already in dictionary - do nothing
      if(french == thewords[i].french && english == thewords[i].english) {
        return;
      }
      // if French word is in dictionary but English is different - update English
      else if(french == thewords[i].french) {
        thewords[i].english = english;
        return;
      }
    }
    // if French word doesn't match any in dictionary - create new French/English entry
    Transpair new_pair;
    new_pair.french = french;
    new_pair.english = english;
    thewords.push_back(new_pair);
  }

  void Trans_Dict::export_to_file(string filename) {
    ofstream dictionary_file(filename);
    for(int i = 0; i < thewords.size(); i++) {
      dictionary_file << thewords[i].french << "/" << thewords[i].english << "\n";
    }
  }
