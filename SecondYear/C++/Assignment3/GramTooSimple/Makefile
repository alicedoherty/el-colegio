Rule.o: Rule.cpp
	g++ $(CPPFLAGS) -c Rule.cpp

Grammar.o: Grammar.cpp
	g++ $(CPPFLAGS) -c Grammar.cpp

rule_tester: Rule.o rule_tester.cpp
	g++ $(CPPFLAGS) Rule.o rule_tester.cpp -o rule_tester

grammar_tester: Rule.o Grammar.o grammar_tester.cpp
	g++ $(CPPFLAGS) Rule.o Grammar.o grammar_tester.cpp -o grammar_tester

clean:
	rm *~ *.o rule_tester grammar_tester

