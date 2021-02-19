#include "Grammar.h"
#include <iostream>
#include <fstream>
#include <vector>

Grammar::Grammar(){}

Grammar::Grammar(string fname) {
    ifstream file;
    file.open(fname);

    string line;

    while (getline(file, line)) {
        Rule r(line);
        rules.push_back(r);
    }
}

void Grammar::show() {
    cout << "Number of rules was " << rules.size() << "\n";
    for(int i = 0; i < rules.size(); i++) {
        rules[i].show();
    }
}
