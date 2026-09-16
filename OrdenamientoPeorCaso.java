public class OrdenamientoPeorCaso {

    // 1. INSERTION SORT (Peor caso: Array ordenado en orden inverso)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int actual = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > actual) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = actual;
        }
    }

    // 2. MERGE SORT (Peor caso: O(n log n) siempre, divide y vencerás)
    public static void mergeSort(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            mergeSort(arr, izquierda, medio);
            mergeSort(arr, medio + 1, derecha);
            merge(arr, izquierda, medio, derecha);
        }
    }

    private static void merge(int[] arr, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        int[] izq = new int[n1];
        int[] der = new int[n2];

        for (int i = 0; i < n1; i++) izq[i] = arr[izquierda + i];
        for (int j = 0; j < n2; j++) der[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = izquierda;
        while (i < n1 && j < n2) {
            if (izq[i] <= der[j]) {
                arr[k++] = izq[i++];
            } else {
                arr[k++] = der[j++];
            }
        }
        while (i < n1) arr[k++] = izq[i++];
        while (j < n2) arr[k++] = der[j++];
    }

    // 3. QUICKSORT (Peor caso con pivote mal elegido, ej: el último elemento en array ya ordenado)
    public static void quickSort(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(arr, bajo, alto);
            quickSort(arr, bajo, pi - 1);
            quickSort(arr, pi + 1, alto);
        }
    }

    private static int particion(int[] arr, int bajo, int alto) {
        int pivote = arr[alto]; // Peor caso si el array ya está ordenado
        int i = (bajo - 1);
        for (int j = bajo; j < alto; j++) {
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        return i + 1;
    }

    // Método auxiliar para imprimir el array
    public static void imprimir(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // Método principal
    public static void main(String[] args) {
        // Ejemplo del peor caso para Insertion Sort (orden inverso)
        int[] arrInsertion = {5, 4, 3, 2, 1};
        System.out.println("Insertion Sort (Peor caso inicial):");
        imprimir(arrInsertion);
        insertionSort(arrInsertion);
        System.out.println("Ordenado:");
        imprimir(arrInsertion);

        // Ejemplo para Merge Sort
        int[] arrMerge = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nMerge Sort inicial:");
        imprimir(arrMerge);
        mergeSort(arrMerge, 0, arrMerge.length - 1);
        System.out.println("Ordenado:");
        imprimir(arrMerge);

        // Ejemplo del peor caso para Quicksort (array ya ordenado con pivote al final)
        int[] arrQuick = {1, 2, 3, 4, 5};
        System.out.println("\nQuicksort (Peor caso inicial - ya ordenado):");
        imprimir(arrQuick);
        quickSort(arrQuick, 0, arrQuick.length - 1);
        System.out.println("Ordenado:");
        imprimir(arrQuick);
    }
}

