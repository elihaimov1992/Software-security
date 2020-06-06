// I.D. 308019306

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>


// Exp.:
// #1. Float divide by zero: Detect floating-point division by zero (Unlike other similar options,  is not enabled by 
//                           -fsanitize=undefined, since floating-point division by zero can be a legitimate way of obtaining 
//                           infinities and NaNs.).
// #2. LeakSanitizer (LSan): Detects memory leaks (run-time tool that does not require compiler instrumentation.).
//
// ****UndefinedBehaviorSanitizer (UBSsan): Detect undefined behavior (runtime checker for undefined behavior, which is a result 
//                                            of any operation with unspecified semantics, such as dividing by zero, null pointer 
//                                            dereference, or usage of an uninitialized non-static variable.).
// #3. Shift (UndefinedBehaviorSanitizer (UBSsan)): Checking that the result of a shift operation is not undefined (This option has two
//                                                 suboptions, -fsanitize=shift-base and -fsanitize=shift-exponent.).
// #4. Variable-Length Array Bounds (UBSsan): This option instructs the compiler to check that the size of a variable 
//                                            length array is positive.


// #1. Float divide by zero: runtime ERROR: (-fsanitize=float-divide-by-zero)
void float_division_by_zero();

// #2. LeakSanitizer (LSan): detected memory leaks ERROR: (-fsanitize=leak)
void memory_leak();

// #3. Shift (UndefinedBehaviorSanitizer (UBSsan)): runtime ERROR: (-fsanitize=shift)
void undefined_behavior_shift();

// #4. Variable-Length Array Bounds (UBSsan): runtime error: (-fsanitize=vla-bound)
void vlarray_bounds();
