import java.util.Random;
import java.util.Scanner;
// с ИИ  не спраился
public class Main {
    public final static int SIZE = 10;
    public final static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X))   {
                 System.out.println("Вы  выиграли! ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил робот Федор!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Это  Конец");
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);

        } while (!isCellValid(x, y));
        System.out.println("Федор  сделал ход " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ур прямой   из  http://mathportal.net/
    public static boolean checkLine(int start_x, int start_y, int dx, int dy, char symb){
        for (int i = 0; i < map.length - (SIZE - DOTS_TO_WIN); i++){
            if ((map[start_x + i * dx][start_y + i * dy] != symb) )
                return false;
        }
        return true;
    }

      public static boolean checkWin(char symb) {
          for (int i = 0; i < map.length; i++){
              if (checkLine(i, 0, 0, 1, symb))  return true; //строки
              if (checkLine(0, i, 1, 0, symb))  return true; // столбцы
          }
          // проверяем диагонали
          if (checkLine(0, 0, 1, 1, symb))  return true;
          if (checkLine(0, map.length - 1, 1, -1, symb)) return true;
          return false;



//        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb && map[0][3] == symb) return true;
//        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb && map[1][3] == symb) return true;
//        if(map[2][0] == symb && map[2][1] == symb && map[2][2] == symb && map[3][3] == symb) return true;
//        if(map[0][0] == symb && map[1][0] == symb && map[2][0] == symb && map[3][0] == symb) return true;
//        if(map[0][1] == symb && map[1][1] == symb && map[2][1] == symb && map[3][1] == symb) return true;
//        if(map[0][2] == symb && map[1][2] == symb && map[2][2] == symb && map[3][3] == symb) return true;
//        if(map[0][0] == symb && map[1][1] == symb && map[2][2] == symb && map[3][3] == symb) return true;
//        if(map[2][0] == symb && map[1][1] == symb && map[0][2] == symb && map[0][3] == symb) return true;
//        return false;
    }
}