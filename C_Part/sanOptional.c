
#include "sanOptional.h"


// #1. Float divide by zero:
void float_division_by_zero(){
   float sum =10;
   for (float i=0; i<=2; i++){
       sum /= i;  // Error: Division by zero on the first iteration
                  // Solution: Modify the logic to check for and avoid division when the divisor could be equal to zero
   }
}


// #2. LeakSanitizer (LSan):
void memory_leak(){
    void *p2 = malloc(5);
    //free(p2);  // Solution: Freeing the pointer p2.
    p2 = 0;      // The memory is leaked here (no-deleting of a heap-allocated object p2).
}


// #3. Shift (UndefinedBehaviorSanitizer (UBSsan)):
void undefined_behavior_shift(){
    int num1 = 2048;
    int num2 = -1;
    num1 <<= 28;  // Error: The number is shifted more than the size of integer --> the behaviour is undefined
    num2 <<= 1;   // Error: Left (or right) shift operator is a negative number --> the behaviour is undefined
}


// #4. Variable-Length Array Bounds (UBSsan):
void vlarray_bounds(){
    int index = -1;
    int arr[index];  // Error: Invalid array length
                     // Solution: One way to fix the issue is by checking array bounds before constructing arrays
}


int main(){
    memory_leak();
    undefined_behavior_shift();
    vlarray_bounds();
    float_division_by_zero();
    return 0;
}
