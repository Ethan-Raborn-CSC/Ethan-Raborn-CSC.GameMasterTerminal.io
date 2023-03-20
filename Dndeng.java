package dndeng;

import java.util.Scanner;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author ethan
 */
public class Dndeng 
{

    
    public static void main(String[] args) 
    {
        startMessage();
        
        Scanner kin = new Scanner(System.in);
        
        boolean exit = false;
        String input;
        char letterCommand;
        
        playerCharacter zero = characterStarter();
        
        while(!exit)
        {
            input = kin.nextLine();
            
            if(input.equals(""))
                endLine();
            else
            {
                switch (input) 
                {
                    case " ":
                    case "space":
                        for(int o = 0; o<50; o++)
                            System.out.println("");
                    case "line":
                        endLine();
                        break;
                    case "quit":
                    case "exit":
                        exit = true;
                        break;
                    case "save":
                        characterDriver("save", zero, kin);
                        break;
                    case "help":
                        startMessage();
                        break;
                    default:
                        {

                            letterCommand = input.charAt(0);
                            switch(letterCommand)
                            {
                                case 'd':
                                {
                                    System.out.println(diceRun(input));
                                    endLine();
                                }
                                    break;
                                case 'c':
                                    characterDriver(input, zero, kin);
                                    break;
                                default:
                                    errorOutput("");
                            }

                        }   
                        break;
                }
            }
        }
    }
    
    
    
    public static void startMessage()
    {
        System.out.printf("Welcome to Ethans DnD engine!\nList of commands below!\n");
        System.out.printf("Command:\t\tDescription:\n");
        System.out.printf("d\t\t\tdice. Q = d100, w = d20, e = d12, r = d10, t = d8, y = d6, u = d4\n");
        System.out.printf("c\t\t\tcharacter. 'c[#]i' = info, 'c[#]d=[d/#]' = damage, 'c[#]h=[d/#]' = heal, 'c[#]e' = edit, 'cstats' = list character info\n");
        System.out.printf("\t\t\t'cl' = load character file, 'c(#)u' delete character(s means safe delete!), 'cn' new character\n");
        System.out.printf("save\t\t\tSave character data to files.\n");
        System.out.printf("unload\t\t\tDrop all characters.\n");
        System.out.printf("exit\t\t\texit game without saving.\n");
        System.out.printf("help(command)\t\t\tgives help with command.\n");
        endLine();
    }
    
    public static void errorOutput(String group)
    {
        if (group.length() == 0)
        {
            System.out.printf("ERROR!! UNRECOGNIZED COMMAND!!\n");
            endLine();
        }
        else
        {
            char firChar = group.charAt(0);
            switch(firChar)
            {
                case 'd':
                {
                    {
                        
                    }
                }
                    break;
                default:
                    System.out.println("UNRECOGNIZED ERROR!!");
                    endLine();
            }
        }
    }
    
    public static void endLine()
    {
        System.out.printf("------------------------------------------------------------------------------------------------------------------------------------------\n>");
    }
    
    public static int diceRun(String input)
    {
        System.out.println("Dicerun!");
        
        if(input.length() == 0)
        {
            errorOutput("dlength0");
            return 0;
        }
        
        Random rand = new Random();
        
        if(input.charAt(0) == 'd')
            input = input.substring(1);
        
        int diceTotal = 0;
        boolean inputEnd = false;
        char dice;
        
        while(!inputEnd)
        {
            //System.out.println("+");
            
            if(input.length() !=0)
            {
                dice = input.charAt(0);
                
                
                switch(dice)
                {
                    case 'q':
                        diceTotal = diceTotal + rand.nextInt(100)+1;
                        input = input.substring(1);
                        break;
                    case 'w':
                        diceTotal = diceTotal + rand.nextInt(20)+1;
                        input = input.substring(1);
                        break;
                    case 'e':
                        diceTotal = diceTotal + rand.nextInt(12)+1;
                        input = input.substring(1);
                        break;
                    case 'r':
                        diceTotal = diceTotal + rand.nextInt(10)+1;
                        input = input.substring(1);
                        break;
                    case 't':
                        diceTotal = diceTotal + rand.nextInt(8)+1;
                        input = input.substring(1);
                        break;
                    case 'y':
                        diceTotal = diceTotal + rand.nextInt(6)+1;
                        input = input.substring(1);
                        break;
                    case 'u':
                        diceTotal = diceTotal + rand.nextInt(4)+1;
                        input = input.substring(1);
                        break;
                    case '+':
                        input = input.substring(1);
                        diceTotal = diceTotal + diceRun(input);
                        input = "";
                        break;
                    case '-':
                        input = input.substring(1);
                        diceTotal = diceTotal - diceRun(input);
                        input = "";
                        break;
                    case '1':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '0':
                    case '2':
                    case '3':
                        int plus = input.indexOf('+');
                        int minus = input.indexOf('-');
                        
                        if((plus == -1) && (minus == -1))
                        {
                            diceTotal = diceTotal + Integer.parseInt(input);
                        }
                        else
                        {
                            if(plus == -1)
                            {
                                String snip = input.substring(minus);
                                input = input.substring(0, minus);
                                
                                diceTotal = diceTotal + Integer.parseInt(input) + diceRun(snip);
                            }
                            else if(minus == -1)
                            {
                                String snip = input.substring(plus);
                                input = input.substring(0, plus);
                                
                                diceTotal = diceTotal + Integer.parseInt(input) + diceRun(snip);
                            }
                            else
                            {
                                if(minus < plus)
                                {
                                    String snip = input.substring(minus);
                                    input = input.substring(0, minus);
                                    
                                    diceTotal = diceTotal + Integer.parseInt(input) + diceRun(snip);
                                }
                                else
                                {
                                    String snip = input.substring(plus);
                                    input = input.substring(0, plus);
                                
                                    diceTotal = diceTotal + Integer.parseInt(input) + diceRun(snip);
                                }
                            }
                        }
                        
                        input = "";
                        break;
                }
            }
            
            if(input.length() == 0)
                inputEnd=true;
            
        }
        
        return diceTotal;
    }
    
    public static void characterDriver(String input, playerCharacter zero, Scanner kin)
    {
        if (input.charAt(0) == 'c')
            input = input.substring(1);
        
        char driver = input.charAt(0);
        
        switch(driver)
        {
            case 'n':
                System.out.print("Name: ");
                String name = kin.nextLine();
                System.out.print("Class: ");
                String pClass = kin.nextLine();
                System.out.print("Maximum Health: ");
                int mHealth = kin.nextInt();
                System.out.print("Initial Damage: ");
                int dam = kin.nextInt();
                
                playerCharacter runner = zero;
                while(runner.getNextCharacter() != null)
                    runner = runner.getNextCharacter();
                
                playerCharacter k = new playerCharacter(name, pClass, mHealth, dam);
                k.editCharacterNumber(runner.getCharacterNumber()+1);
                runner.editNextCharacterLink(k);
                break;
            case 's':
                playerCharacter z = zero.getNextCharacter();
                System.out.printf("CharacterNumber:\tName:\t\t\t\t\t\t\tClass:\t\t\tHealth:\tMaxhealth:\t\n");
                while(z != null)
                {
                    System.out.println(z.getCharacterData());
                    z = z.getNextCharacter();
                }
                break;
            default:
                Pair<playerCharacter, String> p = charSpec(input, zero);
                playerCharacter e = p.getKey();
                String s = p.getValue();
                char a = s.charAt(0);
                String dice;
                
                switch(a)
                {
                    case 'i':
                        System.out.printf("CharacterNumber:\tName:\t\t\t\t\t\t\tClass:\t\t\tHealth:\tMaxhealth:\t\n");
                        System.out.println(e.getCharacterData());
                        break;
                    case 'd':
                        dice = s.substring(1);
                        dice = dice.substring(s.indexOf("d"));
                        e.damage(diceRun(dice));
                        break;
                    case 'h':
                        dice = s.substring(1);
                        dice = dice.substring(s.indexOf("d"));
                        e.heal(diceRun(dice));
                        break;
                    case 'e':
                        System.out.print("Edited Data: ");
                        String val = kin.nextLine();
                        System.out.print("New Value: ");
                        e.edit(val, kin.nextLine());
                        break;
                    case 'u':
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("ARE YOU TRYING TO DELETE A CHARACTER??!! IF SO, TYPE '1234567890ABCDEFG'");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        String del = kin.nextLine();
                        if(del.equals("1234567890ABCDEFG"))
                        {
                            playerCharacter runnerd = zero;
                            while(runnerd.getCharacterNumber() != e.getCharacterNumber()-1)
                                runnerd = runnerd.getNextCharacter();
                            runnerd.editNextCharacterLink(e.getNextCharacter());
                            runnerd = runnerd.getNextCharacter();
                            if(runnerd != null)
                            {
                                int i = runnerd.getCharacterNumber()-1;
                                runnerd.editCharacterNumber(i);
                            }
                        }
                        break;
                    default:
                        errorOutput("");
                        break;
                }
                
                
                break;
                
        }
        
    }

    private static playerCharacter characterStarter() 
    {
        playerCharacter zero = new playerCharacter("", "", 0, 0);
        zero.editCharacterNumber(-1);
        return zero;
    }
    
    private static Pair charSpec(String input, playerCharacter zero)
    {
        int index = 0;
        boolean number = true;
        char c;
        while(number)
        {
            c = input.charAt(index);
            switch(c)
            {
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    index++;
                    break;
                default:
                    number = false;
            }
        }
            
            int cnum = Integer.parseInt(input.substring(0,index));
            input = input.substring(index);
            
            playerCharacter runner = zero;
            while(runner.getCharacterNumber() != cnum)
                runner = runner.getNextCharacter();
            
            return new Pair<>(runner, input);
        
    }
}
