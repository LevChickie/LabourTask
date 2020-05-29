/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class AvoidTooLongMethodsCheck {

  public void methodTest()// Noncompliant {{A Method should not have more than 40 lines}}
  {
      int a;
      String b;
      int c;
      c=1;
      b="two";
      a =1;
      a=c;
      c= 5;
      a= 3*c;
      b = "3";
      c= 3;
      a = 2;
      b= "four";
      a = c+3;
      b="five";
      c = a*03+1;
      a = 3;
      b= "six";
      a = 1;
      b= "seven";
      c= 5;
      a= 3*c;
      b = "eight";
      c= 3;
      a = 2;
      a= 3*c;
      b = "two";
      c= 3;
      a = 2;
      b= "six";
      a = 1;
      b= "seven";
      c= 5;
      a= 3*c;
      b = "eight";
      c= 3;
      a = 2;
      a= 3*c;
      b = "two";
      b="five";
      c = a*03+1;
      a = 3;
      b= "six";
      a = 1;
      b= "seven";
  }

}
