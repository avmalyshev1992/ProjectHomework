import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;
    private static FantasyCharacter player = null;
    private static BattleScene battleScene = null;
    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!", player.getName()));
            printNavigation();
        }


        switch (string) {
            case "1": {
                System.out.println("Торговец уволился, нового ещё не нашли");
                command(br.readLine());

            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());

            }
        }
        command(br.readLine());
    }


    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }
    interface FightCallback {
        void fightWin();
        void fightLost();
    }
    private static FantasyCharacter createMonster() {
        int random = (int) ( Math.random() * 20 );

        //С вероятностью 50% встретится Скелет
        //С вероятностью 35% встретится Гоблин
        //С вероятностью 15% встретится Дракон

        if (random <= 10) return new Skeleton (
                "Скелетон",
                25,
                10,
                5,
                3,
                100,
                10
        );
        else if (random <= 16)return new Goblin(
                "Гоблин",
                50,
                20,
                10,
                6,
                200,
                20
        );
        else return new Dragon(
                    "Дракон",
                    100,
                    50,
                    30,
                    10,
                    500,
                    100
            );



    }


}
