all:sanOptional gcc


sanOptional:sanOptional.c
	gcc -fsanitize=float-divide-by-zero -fsanitize=leak -fsanitize=shift -fsanitize=vla-bound sanOptional.c -o sanOptional


gcc:sanOptional.c
	gcc sanOptional.c -o gcc


clean:
	rm -f gcc sanOptional	