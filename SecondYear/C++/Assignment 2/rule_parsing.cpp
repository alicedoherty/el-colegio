#include <string>
#include <vector>
#include <iostream>
#include <fstream>

using namespace std;

void fancy_print(vector<string> r);
string get_formatted_dtr(string dtr);

main(int argc, char *argv[])
{
    // open indicated file
    // get lines making vectors from rules

    char *fname;
    ifstream file;
    fname = argv[1]; // filename is first command line argument

    file.open(fname);

    string line;
    int nrules = 0;
    vector<string> rule;
    size_t i, start, len; // len is number of characters in a category
    string category;
    vector<vector<string>> the_rules;

    while (getline(file, line))
    {

        i = line.find(" --> ");         // i is first after mother
        start = i + 5;                  // start is first of daughter
        len = i;                        // length of mother is i
        category = line.substr(0, len); // make string from mother

        rule.push_back(category); // mother of rule gets pushed

        /* push all daughters up to last comma */
        while ((i = line.find(",", start)) != string::npos)
        {
            len = i - start; // length of current daughter
            category = line.substr(start, len);
            rule.push_back(category);
            start = i + 1;
        }

        /* push last dtr */
        category = line.substr(start);
        rule.push_back(category);
        the_rules.push_back(rule);
        rule.clear();
    }

    for(int i = 0; i < the_rules.size(); i++) {
        fancy_print(the_rules[i]);
    }
    
    cout << "Number of rules was " << the_rules.size() << endl;
    cout << "Now going to show rules with a specific number of daughters\n";

    int dtr_number;             // number of dtrs user requests
    bool finished = false;

    while(!finished) {
        cout << "Rules with how many daughters do you want? (Enter 0 to quit)\n";
        cin >> dtr_number;
        if(dtr_number == 0) {
            finished = true;
        }
        else {
            for(int i = 0; i < the_rules.size(); i++) {
                if(the_rules[i].size() == dtr_number + 1) {
                    fancy_print(the_rules[i]);
                }
            }
        }
    }
}

void fancy_print(vector<string> r)
{
    // Original print out format
    //
    // cout << r[0];
    // cout << " --> " << endl;
    // for (unsigned int i = 1; i < r.size(); i++)
    // {
    //     cout << "     " << r[i] << endl;
    // }

    string formatted_dtr;
    for (unsigned int i = 1; i < r.size(); i++)
    {
        // checks if dtr is in [] and if it is formats it as word(dtr)
        formatted_dtr = get_formatted_dtr(r[i]);
        
        // makes sure comma isn't printed after last dtr
        if(i < r.size() - 1) {
           cout << formatted_dtr << ", ";
        }
        else {
            cout << formatted_dtr;
        }
    }
    cout << " --> " << r[0] << "\n";
}

// Check if daughter is a word in [] and reformats it
string get_formatted_dtr(string dtr) {
    size_t pos;
    string word_no_brackets;
    if(dtr.find('[') == 0) {
        pos = dtr.find(']');
        if(pos == dtr.size()-1) {
            word_no_brackets = dtr.substr(1,dtr.size()-2);
	        return "word(" + word_no_brackets + ")";
      }
    }
    return dtr;
}
