**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:      |   4  |
| -------------- | --- |
| Student Names: |  Rayyan Khalil   |
|                |  Ammar Elzeftawy   |
|                |  David San Kim   |
|                |  Eduardo Benetti   |

# Introduction

The primary goal of this lab is provide a complete understanding about two essential techniques for software testing: Mutation and GUI Testing. Firslty, this report will cover how mutation was initially covered on the material that the team created in assignment 3, followed by an analysis about 10 functions that the team selected to describe. After analyzing the current coverage the program had, the team tried in many way to increase its value by creating different JUnit tests to "kill" the mutants, using the PiTest library. On the other hand, the second portion of this lab report, we conducted a GUI analysis using Selenium, a very well knonw web interface tool to test and compare different outcomes of web pages. Finally, we described how this process went, and describbing the series of challenges and knowledge the team acquired through out the process. 

# Analysis of 10 Mutants of the Range class 

RANGE CLASS

1) **Negated double local variable number 1 → SURVIVED** --> intersects() (161)           
This function is currently surviving the mutation testing because it is negating the double b1 argument. With the second function input negated, negative numbers will become positive and positive will become negative. When reacing line 161, where it compares b1 >= b0 it will not make any sense given that the upper bound will always be a number smaller than the lower bound. Since no test case was built for types of input, it is not being covered, leading to the mutation survival. 
![image](https://user-images.githubusercontent.com/30624408/225460194-b1fa02b8-4571-4023-a036-22ed2a4b03af.png)

2) **Incremented (a++) double local variable number 1 → SURVIVED** --> Range() (95)          
This mutation was created inside the Range constructor, where input information is being validates before the creation of the object. At the very end of the constructor, it has the following line of code: _this.lower = lower;_ where it is intended to set the this object into the argument lower. However, the implemented mutation change into the following line: _this.lower = lower++;_. In this case, it survives all the test since none of them verifies if the input variable is the same as the object's lower bound
![image](https://user-images.githubusercontent.com/30624408/225460277-d48c1e5c-ce6b-45d7-83f6-a54f3d3e5cdf.png)

3) **Decremented (a--) double local variable number 1 → SURVIVED** --> Range() (95)           
Similar to the previous, inside the Range constructor, the mutation is now decreasing the value of the lower bound object. SImilarly, there is no JUnit test checking if the information that was passed by argument (as the Range's lower bound) is actually the one being created ( _this.lower = lower--;_ ). With that said, the mutation is surving even with the creation of a Range with a value lower than the one passed as argument of the constructor. 
![image](https://user-images.githubusercontent.com/30624408/225460301-da2ce2eb-e8e5-44dc-b1f9-c59c97b4e688.png)

4)  **Incremented (a++) integer local variable number 3 → SURVIVED** --> hashCode() (464)        
Similar to the Range constructor, variable _temp_ was increased by the value of 1. Yet, it differs from the constructor case since this variable was only used locally by that function and not returned, making a harder to write JUnit tests to kill that mutant. The variable was then used to create the result variable (afterwards returned), but since it goes through calculation and then casted back into integer format, the small increase in temp might not be enough to change the final value of _result_. As a consequence of that, no written JUnit was able to kill that mutant, make it survive during the testing process. 
![image](https://user-images.githubusercontent.com/30624408/225469538-b15e2701-1e1c-4820-8ef2-fe6f21c7355e.png)

5) **Decremented (a--) integer local variable number 3 → SURVIVED** --> hashCode() (464)             
Once again, this mutant was created on the temp variable used in the the hashCode function, yet since it was not returned and only used locally, it made it complex to test for mutants. Since it being changed within the function with the following line of code: _temp = Double.doubleToLongBits(this.lower);_ , reducing its value by 1, was not enough to change the value of results (being returned by the function). With that said, none of the written JUnit tests were able to spot that mutant, therefore surviving.
![image](https://user-images.githubusercontent.com/30624408/225461497-ace38494-99d0-4aef-876e-2227ef287709.png)

6) **Incremented (++a) double local variable number 3 → KILLED** --> Range() (96)
This mutation also took place in the Range constructors, yet since its doing the calculation before the variable being called, it generates different bahavior which was caught by the JUnits tests. On line 96: _this.upper = upper;_ it was created a mutant, changing it to: _this.upper = ++upper_. But on the designed JUnits tests, we asserted the correct range was created by testing the upper and lower bounds of the Range object. As a consequence of that, the tests are able to spot the mutant and kill it.        
![image](https://user-images.githubusercontent.com/30624408/225461638-59994605-7b4b-49e7-a616-e1cf3162dda8.png)

7) **Replaced double subtraction with division → KILLED** --> getLength() (123)                  
In the getLength function, the program should return the subtraction between the upper and lower bounds of the Range object. However, the mutant created is changing _return this.upper - this.lower;_ into a division: _return this.upper/this.lower;_ . This logic will certainly not return the same value of most of the cases. We wrote JUnit tests to test out the length of smaller, big and 0 size Ranges, therefore the test suite was able to spot the mutant and kill it accordingly.           
![image](https://user-images.githubusercontent.com/30624408/225466272-48a92005-6b61-4ae8-af63-c0f856adb8d1.png)

8) **Replaced return value with null for org/jfree/data/Range::expandToInclude → KILLED** --> expandToInclude() (312)        
In the line 312, in function expandToInclude, the program is returning the Range object that was passed as an argument. Yet with the creation of the mutant, that same line went from: _return range;_ to _return null_ . In order to spot that mutant, the team created during assignment 3 a JUnit test to check for null returns, since that should not happen given no Range object should be passed as an argument for that function. As a result, the function JUnit was marked as a Killed, since we were able to spot the mutant                   
![image](https://user-images.githubusercontent.com/30624408/225467183-da2bbc7c-ce23-4aad-ab4a-e0321014c966.png)

9) **Incremented (++a) double field upper → KILLED** --> getUpperBound() (114)      
Inside the getUpperBound (which should serve as a simples getter function), the function should return the value of the upper bound. On line 114, the function is simply returning it, with the following command: _return this.upper;_ , yet with the addition of the mutant, it transforms into the following line: _return ++this.upper;_ . In this second case, we are firstly incrementing the variable, then retuning it to where it was called. With that said, the mutant will return a upper bound greater than its actual value, which was checked and tested on the test suit by checking if the create Range had exactly the same value for both bounds. With that said, the test suit was able to spot the mutant, and kill it      
![image](https://user-images.githubusercontent.com/30624408/225469868-4156e69d-71db-4d86-8c30-0a952032d8da.png)         

10) **Substituted 2.0 with 1.0 → KILLED** --> getCentralValue() (132)        
On function getCentralValue, it should return the value between the two bounds of the Range object. For that to work, the class is dividing both bounds by 2.0 ( _return this.lower / 2.0 + this.upper / 2.0;_ ). Yet with the creation of the mutant, it is now dividing it by 1.0 (  _return this.lower / 1.0 + this.upper / 1.0;_ ), therefore returning the value of the bounds, rather than its midpoint. The team created in assignment 3 a JUnit test to spot this mutant, given it would only require to check if the values passed to the function is actually the midpoint for any Range where lower bound is not equal to upper bound. In that case, there should always be a midpoint. As a result, the JUnit test suite was able to spot the mutant and kill it accordingly.    
![image](https://user-images.githubusercontent.com/30624408/225478565-d5627d97-f6c6-4fcf-9c99-16510dfdfc6c.png)


# Report all the statistics and the mutation score for each test class

# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

# A discussion of what could have been done to improve the mutation score of the test suites

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

# Explain your SELENUIM test case design process

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix

# How the team work/effort was divided and managed


# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
