package by.it.marchenko.calc.services;


import by.it.marchenko.calc.constant.LanguageConst;
import by.it.marchenko.calc.entity.Var;
import by.it.marchenko.calc.utility.ResourceManager;

public class Printer implements LanguageConst {
    private final ResourceManager resourceManager;

    public Printer(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public static void print(String out) {
        System.out.println(out);
    }

    public void print(Input inputString, Var result) {

        if (inputString.getExpression() != null) {
            if (inputString.runEnabled()) {
                System.out.printf("%s % 2d: %s%n",
                        resourceManager.getString(MESSAGE_PRINT_RESULT),
                        inputString.getExpressionID(),
                        result);
            }
        }
    }

    public void greeting() {
        System.out.println(resourceManager.getString(MESSAGE_GREETING));
    }
}
