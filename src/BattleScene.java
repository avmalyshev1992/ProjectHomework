public class BattleScene {
    public void fight(FantasyCharacter hero, FantasyCharacter monster, Realm.FightCallback fightCallback) {
        Runnable runnable = () -> {

            //Мы сначала видим противника, и уже потом вступаем в бой
            System.out.println("На Вашем пути встал " + monster);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    private Boolean makeHit(FantasyCharacter defender, FantasyCharacter attacker, Realm.FightCallback fightCallback) {
        int hit = attacker.attack();

        int defenderArmor = defender.getArmor();
        int defenderHealth = defender.getHealthPoints();

        // Броня

        if(defenderArmor >= hit){
            defenderArmor = defenderArmor - hit;
        } else if (defenderArmor < hit) {
            defenderHealth = defenderHealth + defenderArmor - hit;
            defenderArmor = 0;
        }


        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья и %d прочности брони...", defender.getName(), defenderHealth, defenderArmor));
        } else {
            System.out.println(String.format("Удар %sа блокирован!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Ваш персонаж пал в бою, попробуйте снова, удача ещё будет на вашей стороне.");
            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            defender.setArmor(defenderArmor);
            return false;
        }
    }
}
