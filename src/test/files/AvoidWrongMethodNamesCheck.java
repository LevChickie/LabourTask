/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class AvoidWrongMethodNamesCheck {

  public void a() // Noncompliant {{Use names that reference to its purpose}}
  {

  }
  public void counter()// Compliant
  {

  }
 }
