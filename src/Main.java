public class Main {

    public static void main(String[] args) {
        char[] tab1 = {'9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        char[] tab2 = {'1'};
        calc(tab1, tab2, '+', 10);
    }

    public static void calc(char[] number1, char[] number2, char operator, int system) {
        char[] bigger = findIfBigger(number1, number2, 'b');
        char[] smaller = findIfBigger(number1, number2, 's');
        int[] sum = new int[bigger.length];
        if (operator == '+') {
            for (int i = 0; i < smaller.length; i++) {
                sum[sum.length - 1 - i] =
                        addBinary(charToInt(number1[number1.length - 1 - i])
                                , charToInt(number2[number2.length - 1 - i]));
            }
            if (bigger.length > smaller.length) {
                for (int y = smaller.length; y < bigger.length; y++) {
                    sum[sum.length - 1 - y] = charToInt(bigger[bigger.length - 1 - y]);
                }
            }
            convert(sum);
        } else if (operator == '-') {
            boolean lessThan0 = false;
            for (int i = 0; i < smaller.length; i++) {
                if (number1.length < number2.length
                        || (number1.length == number2.length && charToInt(number1[0]) < charToInt(number2[0]))) {
                    lessThan0 = true;
                    sum[sum.length - 1 - i] =
                            removeBinary(charToInt(number2[number2.length - 1 - i])
                                    , charToInt(number1[number1.length - 1 - i]));
                } else
                    sum[sum.length - 1 - i] =
                            removeBinary(number1[number1.length - 1 - i], number2[number2.length - 1 - i]);
            }
            if (bigger.length > smaller.length) {
                for (int y = smaller.length; y < bigger.length; y++) {
                    sum[sum.length - 1 - y] = charToInt(bigger[bigger.length - 1 - y]);
                }
            }

            convert(sum);
            if (lessThan0)
                sum[0] = removeBinary(sum[0], 2 * sum[0]);
        } else {
            throw new IllegalArgumentException("I can not find this operation.");
        }
        showBinary(sum, system);
    }

    private static void convert(int[] sum) {
        for (int i = sum.length - 1; i > 0; i--) {
            if (sum[i] >= 10) {
                sum[i] = removeBinary(sum[i], 10);
                sum[i - 1] = addBinary(sum[i - 1], 1);
            }
            if (sum[i] < 0) {
                sum[i] = addBinary(sum[i], 10);
                sum[i - 1] = removeBinary(sum[i - 1], 1);
            }
        }
    }

    private static int addBinary(int a, int b) {
        int c = a ^ b;
        int d = a & b;
        d = d << 1;
        while (d != 0) {
            a = c;
            b = d;
            c = a ^ b;
            d = a & b;
            d = d << 1;
        }
        return c;
    }

    private static int removeBinary(int a, int b) {
        while (b != 0) {
            int d = (~a) & b;
            a = a ^ b;
            b = d << 1;
        }
        return a;
    }

    private static char[] findIfBigger(char[] number1, char[] number2, char whatToFind) {
        if (whatToFind == 's') {
            if (number1.length < number2.length)
                return number1;
            return number2;
        } else if (whatToFind == 'b') {
            if (number1.length > number2.length)
                return number1;
            return number2;
        }
        throw new IllegalArgumentException();
    }

    private static int charToInt(char a) {
        return switch (a) {
            case '1' -> 1;
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            default -> 0;
        };
    }

    private static void showBinary(int[] tab, int system) {
        if (system == 10) {
            for (int e : tab)
                System.out.print(e);
        } else {
            int[] num = new int[tab.length * 1000];
            int y = 0, x = tab.length, f = 0, xx = tab.length;
            long reszta = 0, liczba;
            int[] tab2;
            do {
                tab2 = new int[tab.length];
                for (int i = 0; i < x; i++) {
                    liczba = ((reszta * 10 + tab[i]) / system);
                    reszta = (reszta * 10 + tab[i]) % system;
                    if (y == 0 && liczba == 0) {
                        xx--;
                    }
                    if (y != 0 || liczba != 0) {
                        tab2[y] = (int) liczba;
                        y++;
                    }
                }
                x = xx;
                y = 0;
                {
                    num[f] = (int) reszta;
                    f++;
                    reszta = 0;
                }
                for (int i = 0; i < tab.length; i++)
                    tab[i] = tab2[i];
            } while (x > 0);
            char[] showResult = convertToBase(num);
            for (int j = f - 1; j >= 0; j--)
                System.out.print(showResult[j]);
        }
    }

    public static char[] convertToBase(int[] tab1) {
        char[] tab2 = new char[tab1.length];
        for (int i = 0; i < tab1.length; i++) {
            switch (tab1[i]) {
                case 0 -> tab2[i] = '0';
                case 1 -> tab2[i] = '1';
                case 2 -> tab2[i] = '2';
                case 3 -> tab2[i] = '3';
                case 4 -> tab2[i] = '4';
                case 5 -> tab2[i] = '5';
                case 6 -> tab2[i] = '6';
                case 7 -> tab2[i] = '7';
                case 8 -> tab2[i] = '8';
                case 9 -> tab2[i] = '9';
                case 10 -> tab2[i] = 'A';
                case 11 -> tab2[i] = 'B';
                case 12 -> tab2[i] = 'C';
                case 13 -> tab2[i] = 'D';
                case 14 -> tab2[i] = 'E';
                case 15 -> tab2[i] = 'F';
            }
        }
        return tab2;
    }
}

