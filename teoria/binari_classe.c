#include <stdio.h>
#include <stdlib.h>

int main(void){
    int x, i, j, bin[100];

    printf("exercici numeros binaris, escriu el numero que vols convertir de decimal a binari:");

        while(scanf("%d", &x) != EOF){  //EOF és equivalent a -1
            fwrite( &i, sizeof(i), 1, stdout);  //size_t fwrite(const void *ptr, size_t size, size_t nmemb, FILE *stream)
                                                //ptr − This is the pointer to the array of elements to be written.
        }                                       //size − This is the size in bytes of each element to be written.
        return EXIT_SUCCESS;                     //nmemb − This is the number of elements, each one with a size of size bytes.
}                                           //stream − This is the pointer to a FILE object that specifies an output stream.
