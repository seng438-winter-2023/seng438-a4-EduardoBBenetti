**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:      |   4  |
| -------------- | --- |
| Student Names: |  Rayyan Khalil   |
|                |  Ammar Elzeftawy   |
|                |  David San Kim   |
|                |  Eduardo Benetti   |

# Introduction


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
![image](https://user-images.githubusercontent.com/30624408/225460347-a977735e-ffee-4b81-aff2-f0214a439c8a.png)

5) **Decremented (a--) integer local variable number 3 → SURVIVED** --> hashCode() (464)             
Once again, this mutant was created on the temp variable used in the the hashCode function, yet since it was not returned and only used locally, it made it complex to test for mutants. Since it being changed within the function with the following line of code: _temp = Double.doubleToLongBits(this.lower);_ , reducing its value by 1, was not enough to change the value of results (being returned by the function). With that said, none of the written JUnit tests were able to spot that mutant, therefore surviving.
![image](https://user-images.githubusercontent.com/30624408/225461497-ace38494-99d0-4aef-876e-2227ef287709.png)

6) **Incremented (++a) double local variable number 3 → KILLED** --> Range() (96)
This mutation also took place in the Range constructors, yet since its doing the calculation before the variable being called, it generates different bahavior which was caught by the JUnits tests. On line 96: _this.upper = upper;_ it was created a mutant, changing it to: _this.upper = ++upper_. But on the designed JUnits tests, we asserted the correct range was created by testing the upper and lower bounds of the Range object. As a consequence of that, the tests are able to spot the mutant and kill it.        
![image](https://user-images.githubusercontent.com/30624408/225461638-59994605-7b4b-49e7-a616-e1cf3162dda8.png)

7) **Replaced double subtraction with division → KILLED** --> getLength() (123)                  
In the getLength function, the program should return the subtraction between the upper and lower bounds of the Range object. However, the mutant created is changing _return this.upper - this.lower;_ into a division: _return this.upper/this.lower;_ . This logic will certainly not return the same value of most of the cases. We wrote JUnit tests to test out the length of smaller, big and 0 size Ranges, therefore the test suite was able to spot the mutant and kill it accordingly.           
![image](https://user-images.githubusercontent.com/30624408/225466272-48a92005-6b61-4ae8-af63-c0f856adb8d1.png)


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
