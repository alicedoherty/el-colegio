#include "eval.h"
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <algorithm>



Rel likes;
Rel student;
Rel lecturer;
Rel subject;
Rel unicorn;
Rel kissed;
Rel froze;

// these decs in eval.h
// bool compute_s_value(Tree *t);
// Thing compute_np_value(Tree *t);
// Rel compute_vp_value(Tree *t);
// Rel compute_n_value(Tree *t);
// Rel compute_tv_value(Tree *t);


// bool match(Tree *t, string s);

void sem_error(Tree *t);

bool compute_s_value(Tree *t) {
  bool v;

  // s --> np,vp  where np is a name
  if(match(t,Rule("s --> np,vp"))
     && match(t->dtrs[0],Rule("np --> name"))) {

    Thing v1;
    Rel v2;
    v1 = compute_np_value(t->dtrs[0]);
    v2 = compute_vp_value(t->dtrs[1]);
    v = true_of(v2,v1);
    return v;
  }
  // s --> np,vp where np is det,n
  else if(match(t,Rule("s --> np,vp"))
	  && match(t->dtrs[0],Rule("np --> det,n"))) {
    Rel v1;
    Rel v2;
    string gq;
    Tree *np = t->dtrs[0];
    
    gq = np->dtrs[0]->dtrs[0]->mother.cat; // gets the word in det position

    v1 = compute_n_value(np->dtrs[1]); // get value from n dtr of np
    
    v2 = compute_vp_value(t->dtrs[1]); // get value from vp dtr of s
    
    v = gen_quant(gq,v1,v2);
    
    return v;
  }
  else {
    sem_error(t);
    return false;
  }
}

// np --> name  
Thing compute_np_value(Tree *t) {
  Thing v;

  if(match(t,Rule("np --> name"))) {
    // short cut
    return (t->dtrs[0]->dtrs[0]->mother.cat);
  }
  else {
    sem_error(t);
    return "nothing";
  }

}

// tv --> [w]
Rel compute_tv_value(Tree *t) {
  Rel r(2);
  if(t->dtrs[0]->mother.cat == "likes") {
    return likes;
  }
  else if(t->dtrs[0]->mother.cat == "kissed") {
    return kissed;
  }
  else if(t->dtrs[0]->mother.cat == "froze") {
    return froze;
  }
  else {
    sem_error(t);
    return r;
  }
}

// tv_pass --> [was],[w]
// see assign description
// if its tree for 'was kissed' should return a relation
// derived from the 'kissed' relation by flipping the order
// in each of its contained tuples
// sim in case of 'was frozen'
Rel compute_tv_pass_value(Tree *t) {
  Rel r(2);

  // Create new variable "rel_to_flip" that is a copy of kissed/froze relation so that 
  // when it gets flipped it doesn't affect the original kissed/froze relation
  Rel rel_to_flip;

  if (match(t,Rule("tv_pass --> [was],[kissed]"))) {
    rel_to_flip = kissed;
    return (flip(rel_to_flip));
  }
  else if(match(t,Rule("tv_pass --> [was],[frozen]"))) {
    rel_to_flip = froze;
    return (flip(rel_to_flip));
  }
  else {
    sem_error(t);
    return r;
  }
}

Rel flip(Rel rel) {
  for(int i = 0; i < rel.tuples.size(); i++) {
     reverse(rel.tuples[i].begin(), rel.tuples[i].end());
   }
   return rel;
}


Rel compute_vp_value(Tree *t) {
  Rel r_unary(1);

  if(match(t,Rule("vp --> tv,np"))
     && match(t->dtrs[1], Rule("np --> name"))) {
    
    Tree *tv = t->dtrs[0];
    Rel r_binary(2);
    r_binary = compute_tv_value(tv);
    
    Tree *np = t->dtrs[1];
    Thing o;
    o = compute_np_value(np);
    r_unary = reduce(r_binary,1,o);
    return r_unary;
  } // end vp --> tv,np

  else if(match(t,Rule("vp --> [is],[a],n"))) {
    r_unary = compute_n_value(t->dtrs[2]);
    return r_unary;
  }
  //vp --> tv_pass,[by],np
  else if(match(t,Rule("vp --> tv_pass,[by],np"))
    && match(t->dtrs[2], Rule("np --> name"))) {
      
    Tree *tv_pass = t->dtrs[0];
    Rel r_binary(2);
    r_binary = compute_tv_pass_value(tv_pass);

    Tree *np = t->dtrs[2];
    Thing o;
    o = compute_np_value(np);
    r_unary = reduce(r_binary,1,o);
    return r_unary;
  }

  else {
    sem_error(t);
    return r_unary;
  }
  
}




Rel compute_n_value(Tree *t) {
  //n --> [subject] etc
  Rel r(1);
  if(t->mother.cat == "n" && t->dtrs.size() == 1) {
  
    if(t->dtrs[0]->mother.cat == "subject") {
      return subject;
    }
    else if(t->dtrs[0]->mother.cat == "lecturer") {
      return lecturer;
    }
    else if(t->dtrs[0]->mother.cat == "student") {
      return student;
    }
    else if(t->dtrs[0]->mother.cat == "unicorn") {
      return unicorn;
    }
    else {
      sem_error(t->dtrs[0]);
      return r;
    }
  }
  else if(match(t,Rule("n --> n,[who],vp"))) {
    Rel p(1);
    Rel q(1);
    p = compute_n_value(t->dtrs[0]);
    q = compute_vp_value(t->dtrs[2]);
    r = meet(p,q);
    return r;
  }
  else {
    sem_error(t);
    return r;
  }

}

void sem_error(Tree *t) {
  cout << "unhandled tree type\n";
  t->drawtree();

}

bool match(Tree *t, Rule r) {

  if(!are_equal(t->mother, r.mother)) { return false; }

  if(t->dtrs.size() != r.dtrs.size()) { return false; }

  for(int i=0; i < t->dtrs.size(); i++) {
    if(!are_equal(t->dtrs[i]->mother, r.dtrs[i])) { return false; }
  }

  return true;

}