#include <stdio.h>

int main(){
    int x, i, j, bin[100];

    printf("exercici numeros binaris");

        while(scanf("%d", &x) != EOF){  //EOF és equivalent a -1
            i=0;
            while (x>0)
            {
                bin[i] = x % 2;
                x = x/2;
                i++;
            }          

        }
        for(j=i; j>=0; j--){
            printf("%d", bin[j]);
        }
}

//funcio decabin(x, &resto_div)