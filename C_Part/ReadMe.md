

# C-Assignment: GCC Sanitize Options

A small C-language program including four functions each demonstrating a different issue of your choice flagged by GCC-sanitizer.

## Introduction

#### Program Instrumentation Options

GCC supports a number of command-line options that control adding run-time instrumentation to the code it normally generates. For example, one purpose of instrumentation is collect profiling statistics for use in finding program hot spots, code coverage analysis, or profile-guided optimizations. Another class of program instrumentation is adding run-time checking to detect programming errors like invalid pointer dereferences or out-of-bounds array accesses, as well as deliberately hostile attacks such as stack smashing or C++ vtable hijacking. There is also a general hook which can be used to implement other forms of tracing or function-level instrumentation for debug or program analysis purposes.

## Table of contents

> * [C-Assignment: GCC Sanitize Options](#c-assignment-gcc-sanitize-options)
> * [Introduction](#introduction)
>   * [Program Instrumentation Options](#program-instrumentation-options)
> * [Table of contents](#table-of-contents)
> * [Installation](#installation)
> * [Assignment's Sanitize Options Flags](#assignments-sanitize-options-flags)
>   * [LeakSanitizer (LSan)](#leaksanitizer-lsan)
>   * [UndefinedBehaviorSanitizer (UBSsan)](#undefinedbehaviorsanitizer-ubssan)
>     * [Float divide by zero](#float-divide-by-zero)
>     * [Shift (UndefinedBehaviorSanitizer (UBSsan))](#shift-undefinedbehaviorsanitizer-ubssan)
>     * [Variable-Length Array Bounds (UndefinedBehaviorSanitizer (UBSsan))](#variable-length-array-bounds-undefinedbehaviorsanitizer-ubssan)
> * [Getting GCC Sanitize Options](#getting-gcc-sanitize-options)
> * [Using Sanitize Options Flags (Code)](#using-sanitize-options-flags-code)
> * [GCC Version](#gcc-version)
> * [Author](#author)
> * [License](#license)
> * [Acknowledgments](#acknowledgments)



## Installation

* To get started developing in WSL, you need to:
 * Install the Windows Subsystem for Linux [Windows Subsystem for Linux Installation Guide for Windows 10
](https://docs.microsoft.com/en-us/windows/wsl/install-win10).
 * Install Visual Studio Code on the Windows side (not in WSL)[Visual Studio Code on the Windows 
](https://code.visualstudio.com/).
 * Install the [Remote Development extension pack](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.vscode-remote-extensionpack)
 * For further instructions on [Developing in WSL](https://code.visualstudio.com/docs/remote/wsl)


## Assignment's Sanitize Options Flags

#### LeakSanitizer (LSan):

* LeakSanitizer is a memory leak detector which is integrated into AddressSanitizer. The tool is supported on x86_64 Linux and OS X.
* This option only matters for linking of executables and the executable is linked against a library that overrides malloc and other allocator functions.
* The run-time behavior can be influenced using the LSAN_OPTIONS environment variable.
* The option cannot be combined with -fsanitize=thread.
* Syntax: "-fsanitize=leak".

###### UndefinedBehaviorSanitizer (UBSsan):

* Enable UndefinedBehaviorSanitizer, a fast undefined behavior detector.
* Detect undefined behavior (runtime checker for undefined behavior, which is a result of any operation with unspecified semantics, such as dividing by zero, null pointer dereference, or usage of an uninitialized non-static variable.).
* Syntax: "fsanitize=undefined"
* Current suboptions are:

#### Float divide by zero:

* Detect floating-point division by zero.
* Unlike other similar options, is not enabled by fsanitize=undefined, since floating-point division by zero can be a legitimate way of obtaining infinities and NaNs.
* Syntax: "-fsanitize=float-divide-by-zero"

#### Shift (UndefinedBehaviorSanitizer (UBSsan)):

* Checking that the result of a shift operation is not undefined.
* This option has two suboptions, -fsanitize=shift-base and -fsanitize=shift-exponent.
* Syntax: "-fsanitize=shift"

#### Variable-Length Array Bounds (UndefinedBehaviorSanitizer (UBSsan)):

* This option instructs the compiler to check that the size of a variable length array is positive.
* Syntax: "-fsanitize=vla-bound"

## Getting GCC Sanitize Options

* LeakSanitizer and UndefinedBehaviorSanitizer is a part of LLVM starting with version 3.1 and a part of GCC starting with version 4.8 If you prefer to build from source.
* LeakSanitizer and UndefinedBehaviorSanitizer has been tested only on Linux Ubuntu 12.04, 64-bit (it can run both 64- and 32-bit programs), Mac 10.6, 10.7 and 10.8, and AddressSanitizerOnAndroid 4.2+.

## Using Sanitize Options Flags (Code)

1. LeakSanitizer flag:
* In order to use this flag you will need to compile and link your program using GCC with the `-fsanitize=leak` switch.
```
// LeakSanitizer (LSan):
void memory_leak(){
    void *p2 = malloc(5);
    //free(p2);  // Solution: Freeing the pointer p2.
    p2 = 0;      // Error: The memory is leaked here (no-deleting of a heap-allocated object p2).
}
```

2. UndefinedBehaviorSanitizer flag:
* In order to use this flag you will need to compile and link your program using GCC with the `-fsanitize=float-divide-by-zero` switch.
```
// Float divide by zero:
void float_division_by_zero(){
   float sum =10;
   for (float i=0; i<=2; i++){
       sum /= i;  // Error: Division by zero on the first iteration
                  // Solution: Modify the logic to check for and avoid division when the divisor could be equal to zero
   }
}
```

3. Shift flag:
* In order to use this flag you will need to compile and link your program using GCC with the `-fsanitize=shift` switch.
```
// Shift (UndefinedBehaviorSanitizer (UBSsan)):
void undefined_behavior_shift(){
    int num1 = 2048;
    int num2 = -1;
    num1 <<= 28;  // Error: The number is shifted more than the size of integer --> the behaviour is undefined
    num2 <<= 1;   // Error: Left (or right) shift operator is a negative number --> the behaviour is undefined
}
```

4. Variable-Length Array Bounds flag:
* In order to use this flag you will need to compile and link your program using GCC with the `-fsanitize=vla-bound` switch.
```
// Variable-Length Array Bounds (UBSsan):
void vlarray_bounds(){
    int index = -1;
    int arr[index];  // Error: Invalid array length
                     // Solution: One way to fix the issue is by checking array bounds before constructing arrays
}
```

Now, compile the Makefile (with all flags):
```
all:sanOptional gcc


sanOptional:sanOptional.c
	gcc -fsanitize=float-divide-by-zero -fsanitize=leak -fsanitize=shift -fsanitize=vla-bound sanOptional.c -o sanOptional


gcc:sanOptional.c
	gcc sanOptional.c -o gcc


clean:
	rm -f gcc sanOptional	
```

And run the executable.
```
// ./sanOptional
sanOptional.c:28:10: runtime error: left shift of 2048 by 28 places cannot be represented in type 'int'
sanOptional.c:29:10: runtime error: left shift of negative value -1
sanOptional.c:36:9: runtime error: variable length array bound evaluates to non-positive value -1
sanOptional.c:10:12: runtime error: division by zero

=================================================================
==220==ERROR: LeakSanitizer: detected memory leaks

Direct leak of 5 byte(s) in 1 object(s) allocated from:
    #0 0x7f31d28ef9d1 in malloc (/lib/x86_64-linux-gnu/liblsan.so.0+0xf9d1)
    #1 0x7f31d321f27a in memory_leak (/root/sanOptional+0x127a)
    #2 0x7f31d321f43e in main (/root/sanOptional+0x143e)
    #3 0x7f31d1d970b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x270b2)

SUMMARY: LeakSanitizer: 5 byte(s) leaked in 1 allocation(s).
```
The result is output with all errors detected with the flags we used.

**Note: If we do not use these flags, the program will not warn us of errors that will create, compile and run the program as usual with no warnings and crashes, when in fact, behind the scenes, we create vulnerabilities that could be hacking and attacks in the future.

## GCC Version

The version of gcc compiler that we used:
```
// gcc -v
Using built-in specs.
COLLECT_GCC=gcc
COLLECT_LTO_WRAPPER=/usr/lib/gcc/x86_64-linux-gnu/9/lto-wrapper
OFFLOAD_TARGET_NAMES=nvptx-none:hsa
OFFLOAD_TARGET_DEFAULT=1
Target: x86_64-linux-gnu
Configured with: ../src/configure -v --with-pkgversion='Ubuntu 9.3.0-10ubuntu2' --with-bugurl=file:///usr/share/doc/gcc-9/README.Bugs --enable-languages=c,ada,c++,go,brig,d,fortran,objc,obj-c++,gm2 --prefix=/usr --with-gcc-major-version-only --program-suffix=-9 --program-prefix=x86_64-linux-gnu- --enable-shared --enable-linker-build-id --libexecdir=/usr/lib --without-included-gettext --enable-threads=posix --libdir=/usr/lib --enable-nls --enable-clocale=gnu --enable-libstdcxx-debug --enable-libstdcxx-time=yes --with-default-libstdcxx-abi=new --enable-gnu-unique-object --disable-vtable-verify --enable-plugin --enable-default-pie --with-system-zlib --with-target-system-zlib=auto --enable-objc-gc=auto --enable-multiarch --disable-werror --with-arch-32=i686 --with-abi=m64 --with-multilib-list=m32,m64,mx32 --enable-multilib --with-tune=generic --enable-offload-targets=nvptx-none,hsa --without-cuda-driver --enable-checking=release --build=x86_64-linux-gnu --host=x86_64-linux-gnu --target=x86_64-linux-gnu
Thread model: posix
gcc version 9.3.0 (Ubuntu 9.3.0-10ubuntu2)
```

## Author

* **Eli Haimov (ID. 308019306)** - *C-Assignment: GCC Sanitize Options* 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Explanations about all sanitizers [Program Instrumentation Options](https://gcc.gnu.org/onlinedocs/gcc/Instrumentation-Options.html)
* More information about options of sanitizers 
  * [google/sanitizers](https://github.com/google/sanitizers)
  * [Code Diagnostics](https://developer.apple.com/documentation/code_diagnostics)
