import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PokerPlay {

    public static void main(String[] args){

        String fileName = "fcl/poker_play.fcl";
        FIS fis = FIS.load(fileName,true);
        Scanner read = new Scanner(System.in);
        Map<String, String> map = new HashMap<String, String>();
        map.put("dog", "1.0");
        System.out.println(map.get("dog"));
        if (fis == null){
            System.err.println("Can't load the file: " + fileName + ".");
            return;
        }
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        JFuzzyChart.get().chart(functionBlock);

        while(true) {
            System.out.println("hands: ");
            float hand_rank = read.nextFloat();
            fis.setVariable("hand_rank", hand_rank);
            System.out.println("stage: ");
            float stage = read.nextFloat();
            fis.setVariable("stage", stage);
            System.out.println("position: ");
            float position = read.nextFloat();
            fis.setVariable("position", position);

            fis.evaluate();

            Variable var = functionBlock.getVariable("play");
            JFuzzyChart.get().chart(var, var.getDefuzzifier(), true);
            System.out.println(fis);
            System.out.println("Type: " + fis.getVariable("play").getValue());
            System.out.println("Type: " + fis.getVariable("play"));
        }

    }

}