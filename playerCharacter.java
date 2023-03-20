package dndeng;

public class playerCharacter 
{
    private String name;
    private int totalHealth;
    private int health;
    private String characterClass;
    private int characterNumber;
    private playerCharacter nextCharacter;
    
    public playerCharacter(String name, String pclass, int totalHealth, int damage)
    {
        this.name = name;
        characterClass = pclass;
        this.totalHealth = totalHealth;
        health = totalHealth - damage;
    }
    
    public int damage(int dam)
    {
    health = health - dam;
    return health;
    }
    
    public int heal(int hp)
    {
        health = health + hp;
        if(health > totalHealth)
            health = totalHealth;
        return health;
    }
    
    public void edit(String input, String var)
    {
        switch(input)
        {
            case "name":
                ename(var);
                break;
            case "totalHealth":
                etot(var);
                break;
            case "health":
                ehealth(var);
            case "stats":
                System.out.printf("CharacterNumber:\tName:\t\t\t\t\t\t\tClass:\t\t\tHealth:\tMaxhealth:\t\n");
                System.out.println(getCharacterData());
                break;
        }
    }
    
    public String getName()
    {
        return name;
    }    
    public int getHealth()
    {
        return health;
    }
    public int getTopHealth()
    {
        return totalHealth;
    }
    public String getCharacterClass()
    {
        return characterClass;
    }
    public int getCharacterNumber()
    {
        return characterNumber;
    }
    public playerCharacter getNextCharacter()
    {
        return nextCharacter;
    }
    public String getCharacterData()
    {
        String build = characterNumber + "\t\t\t"+name;
        int nameLength = 6 - name.length()/8;
        for(int i = 0; i<= nameLength; i++)
            build = build + "\t";
        
        build = build + characterClass;
        int classLength = 2-characterClass.length()/8;
        for(int i = 0; i<= classLength; i++)
            build = build + "\t";
        build = build + health + "\t" + totalHealth;
        
        
        return build;
    }
    
    private void ename(String name)
    {
        this.name = name;
        System.out.println("NAME CHANGED!!!");
    }
    private void etot(String num)
    {
        int var = Integer.parseInt(num);
        totalHealth = var;
        if(health > totalHealth)
        {
            health = totalHealth;
            System.out.printf("TOP HEALTH AND HEALTH CHANGED!! NEW HEALTH IS :%d%n", health);
        }
        else
            System.out.printf("TOP HEALTH HAS CHANGED!!! NEW TOP HEALTH: %d%n", totalHealth);
        
    }
    private void ehealth(String input)
    {
        int hp = Integer.parseInt(input);
        if(hp>totalHealth)
        {
            health = totalHealth;
            System.out.printf("HEALTH EDITED!!! HEALTH WAS CAPPED AT TOTAL HEALTH!! HEALTH IS: %d%n", totalHealth);
        }
        else
        {
            health = hp;
            System.out.printf("HEALTH EDITED!!! HEALTH IS: %d%n", health);
        }
    }
    
    
    protected void editCharacterNumber(int var)
    {
        characterNumber = var;
        
        if(nextCharacter != null)
            nextCharacter.edit("cnumber", (characterNumber +1)+"");
    }
    protected void editNextCharacterLink(playerCharacter newCharacter)
    {
        nextCharacter = newCharacter;
    }
}
