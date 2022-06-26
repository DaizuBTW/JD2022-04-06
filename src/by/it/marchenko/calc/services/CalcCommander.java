package by.it.marchenko.calc.services;

import by.it.marchenko.calc.ConsoleRunner;
import by.it.marchenko.calc.constant.LanguageConst;
import by.it.marchenko.calc.entity.Var;
import by.it.marchenko.calc.exception.CalcException;
import by.it.marchenko.calc.interfaces.CalcAppCommand;
import by.it.marchenko.calc.interfaces.Repository;
import by.it.marchenko.calc.utility.ResourceManager;

import java.util.*;

import static by.it.marchenko.calc.constant.MessageConst.*;

public class CalcCommander implements CalcAppCommand, LanguageConst {
    private static HashMap<String, Var> variables;
    private final ResourceManager resourceManager = ConsoleRunner.getResourceManager();
    private static final Set<String> commands = new HashSet<>(Arrays.asList(
            COMMAND_APP_EXIT, COMMAND_PRINT_VARIABLE, COMMAND_SORT_VARIABLE,
            COMMAND_SET_LANGUAGE_EN, COMMAND_SET_LANGUAGE_BE, COMMAND_SET_LANGUAGE_RU
    ));

    public CalcCommander(Repository repository) {
        variables = repository.getAllVariables();
    }

    @Override
    public String performCommand(String command) throws CalcException {
        if (command != null) {
            command = command.trim().toLowerCase();
        } else {
            throw new CalcException(EMPTY_EXPRESSION_ENTERED);
        }

        if (commands.contains(command)) {
            return switch (command) {
                case COMMAND_PRINT_VARIABLE -> printVariable();
                case COMMAND_SORT_VARIABLE -> sortVariable();
                case COMMAND_SET_LANGUAGE_BE -> setLanguage(COMMAND_SET_LANGUAGE_BE);
                case COMMAND_SET_LANGUAGE_RU -> setLanguage(COMMAND_SET_LANGUAGE_RU);
                case COMMAND_SET_LANGUAGE_EN -> setLanguage(COMMAND_SET_LANGUAGE_EN);
                case COMMAND_APP_EXIT -> resourceManager.getString(MESSAGE_FAREWELL);
                default -> null;
            };
        }
        return null;
    }

    private String setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        resourceManager.changeResource(locale);
        return resourceManager.getString(MESSAGE_CHANGE_LANGUAGE);
    }

    private static String printVariable() {
        StringBuilder out = new StringBuilder(AVAILABLE_VARIABLES);
        for (Map.Entry<String, Var> element : variables.entrySet()) {
            out.append("\n").append(element.getKey()).
                    append(ASSIGN_OPERATOR).append(element.getValue());
        }
        out.append("\n");
        return out.toString();
    }

    private static String sortVariable() {
        TreeMap<String, Var> sortedVariables = new TreeMap<>(variables);
        StringBuilder out = new StringBuilder(AVAILABLE_VARIABLES);
        for (Map.Entry<String, Var> element : sortedVariables.entrySet()) {
            out.append("\n").append(element.getKey()).
                    append(ASSIGN_OPERATOR).append(element.getValue());
        }
        out.append("\n");
        return out.toString();
    }
}
