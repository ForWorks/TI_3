import java.util.Scanner;

public class Main {

    public static int closeExp(int a, int b) {
        int d0 = a; int d1 = b;
        int x0 = 1; int x1 = 0;
        int y0 = 0; int y1 = 1;
        while(d1 > 1) {
            int q = d0 / d1;
            int d2 = d0 % d1;
            int x2 = x0 - q * x1;
            int y2 = y0 - q * y1;
            d0 = d1; d1 = d2;
            x0 = x1; x1 = x2;
            y0 = y1; y1 = y2;
        }
        return y1;
    }

    public static int pow(int base, int power, long mod) {
        int a1 = base;
        int z1 = power;
        int x = 1;
        while(z1 != 0) {
            while(z1 % 2 == 0) {
                z1 = z1 / 2;
                a1 = (int)((a1 * a1) % mod);
            }
            z1 = z1 - 1;
            x = (int)((x * a1) % mod);
        }
        return x;
    }

    public static int hash(char[] str, int multiply) {
        int result = 100;
        for(int i = 0; i < str.length; i++) {
            result = (int)Math.pow(result + str[i] - 64, 2) % multiply;
        }
        return result;
    }

    public static void signatureCreation(String string) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите первое простое число:");
        int first = scan.nextInt();
        System.out.println("Введите второе простое число:");
        int second = scan.nextInt();
        int multiply = first * second;
        int function = (first - 1) * (second - 1);
        System.out.println("Введите открытую экспоненту:");
        int exp = scan.nextInt();
        int closeExp = closeExp(function, exp);
        if(closeExp < 0)
            closeExp += function;
        int hash = hash(string.toCharArray(), multiply);
        int signature = pow(hash, closeExp, multiply);
        System.out.println("Цифровая подпись:");
        System.out.println(signature);
        System.out.println("Открытый ключ:");
        System.out.println(exp + " " + multiply);
    }

    public static void signatureVerification(String string) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите значение цифровой подписи:");
        int signature = scan.nextInt();
        System.out.println("Введите значение открытого ключа:");
        int exp = scan.nextInt();
        int multiply = scan.nextInt();
        int hash = hash(string.toCharArray(), multiply);
        if(hash != (pow(signature, exp, multiply)))
            System.out.println("Подпись недействительная.");
        else
            System.out.println("Подпись действительная.");
    }

    public static void mainMenu() {
        System.out.println("Создать цифровую подпись(1)");
        System.out.println("Проверить цифровую подпись(2)");
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        scan.nextLine();
        String str;
        switch (value) {
            case 1: {
                System.out.println("Введите строку для создания цифровой подписи:");
                str = scan.nextLine();
                signatureCreation(str);
            } break;
            case 2: {
                System.out.println("Введите строку для проверки цифровой подписи:");
                str = scan.nextLine();
                signatureVerification(str);
            } break;
            default:
                System.out.println("Проверьте введенные данные.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            mainMenu();
        }
    }

}
