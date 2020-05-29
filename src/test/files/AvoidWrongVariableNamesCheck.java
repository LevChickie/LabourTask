/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class AvoidWrongVariableNamesCheck {

  int a; // Noncompliant {{Use names that reference to its purpose}}
  int myint;// Noncompliant {{Use names that reference to its purpose}}
  int counter;// Compliant
 }
