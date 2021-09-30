#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
    int i;

    while(fread(&i, sizeof(i), 1, stdin) >0){
        printf("%d\n", i);             
    }
    return 0;
}
