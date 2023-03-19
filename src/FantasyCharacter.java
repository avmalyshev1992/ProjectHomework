public abstract class FantasyCharacter implements Fighter{
    private String name;
    private int healthPoints;
    private int armor;
    private int strength;
    private int dexterity;
    private int xp;
    private int gold;
    public FantasyCharacter(String name, int healthPoints, int armor, int strength, int dexterity, int xp, int gold) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.armor = armor;
        this.strength = strength;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
    }
    @Override
    public int attack() {
        if (dexterity * 3 > getRandomValue()) return strength;

        // Шанс Крит.Урона:
        // У скелета примерно 9%
        // У гоблина примерно 18%
        // У Дракона примерно 30%
        // У Героя 60% (героев отправляющихся на бой с нечестью без боевой подготовки не бывает =))
        else if (dexterity * 3 > getRandomValue()) return strength*3;
        else return  0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }
    @Override
    public String toString() {
        return String.format("%s здоровье: %d, броня: %d", name, healthPoints, armor);
    }
}
