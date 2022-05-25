package by.it.marchenko.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.it.marchenko.calc.MessageConst.*;

public class Parser {

    private final Repository repository;
    private final VarCreator varCreator;
    private final Operands operands;


    public Parser(Repository repository, VarCreator varCreator, Operands operands) {
        this.repository = repository;
        this.varCreator = varCreator;
        this.operands = operands;

    }

    List<String> operatorList;
    List<Var> operandList;

    @SuppressWarnings("ConstantConditions")
    public Var calc(String inputString) throws CalcException {

        //VarRepositoryMap mapRepo = new VarRepositoryMap();

        if (inputString != null) {

            String[] myOperands = operands.createOperands(inputString);
            //checkUnknownVariable(myOperands);
            //TODO
            // check variable assignment - if more than one not assigned variable - exception

            //varCreator.createOperands(inputString);
            //String[] operands = inputString.split(OPERATOR_REGEX);  //, MAXIMUM_ALLOWED_OPERANDS);
//                    inputString.split(OPERATOR_REGEX);  //, MAXIMUM_ALLOWED_OPERANDS);



            //System.out.println(Arrays.toString(myOperands));
            operandList = new ArrayList<>(myOperands.length);
            operatorList = new ArrayList<>(myOperands.length - 1);

            Pattern operatorPattern = Pattern.compile(OPERATOR_REGEX);
            Matcher operatorMatcher = operatorPattern.matcher(inputString);
            // A + B C = 3 + 5 - 2
            //TODO implement saveVariable()
            // find amount of "=" in expression. If only one - saveVariable enabled
            // create Variable pattern
            // find amount of variables in the expression
            // change assigned variables to corresponding values
            // calculate amount of not assigned variables. If only one - saveVariable Enabled.
            //


            for (String operand : myOperands) {
                //operand = operand.trim();
                operandList.add(varCreator.createVar(operand));
                if (operatorMatcher.find()) {
                    operatorList.add(operatorMatcher.group());
                }
            }
            if (isUnique(operatorList, ASSIGN_OPERATOR) && isUnique(operandList, null)) {
                //TODO transferToLeft(operandList,operatorList);
                //TODO performLeftSideEvaluation(operandList,operatorList);
                //Var.saveVariable(operands[operandList.indexOf(null)],operandList.get(1));
                //if (!repository.saveVariable(operands[operandList.indexOf(null)], operandList.get(1))) {
                //     throw new RuntimeException("Variable not save");
                // }
                //Var.saveVariable(operands[operandList.indexOf(null)], operandList.get(1));
                repository.saveVariable(myOperands[operandList.indexOf(null)], operandList.get(1));

                operandList.remove(0);
                operatorList.remove(0);
            }
            Var tempResult = operandList.get(0);

            for (int i = 0; i < operatorList.size(); i++) {
                tempResult = switch (operatorList.get(i)) {
                    // TODO NullPointerException during invocation
                    case ADD_OPERATOR -> tempResult.add(operandList.get(i + 1));
                    case SUB_OPERATOR -> tempResult.sub(operandList.get(i + 1));
                    case MUL_OPERATOR -> tempResult.mul(operandList.get(i + 1));
                    case DIV_OPERATOR -> tempResult.div(operandList.get(i + 1));
                    //case ASSIGN_OPERATOR -> AssignMethod();
                    default -> null;
                };
            }
            return tempResult;
        }
        return null;
    }

    private void checkUnknownVariable(String[] myOperands) {

    }

    private boolean isUnique(List<?> list, String element) {
        int firstAppearance = list.indexOf(element);
        return firstAppearance >= 0 && firstAppearance == list.lastIndexOf(element);
    }
}
    /*
    private List<String> AssignMethod(List<String> operatorList) {
        int assignPosition = operatorList.indexOf(ASSIGN_OPERATOR);
        //int lastAssignPosition = operatorList.lastIndexOf(ASSIGN_OPERATOR);

        if (isUnique(operatorList, ASSIGN_OPERATOR) &&
                isUnique(operandList, null)) {
            //assignation
            //transferToLeft(assignPosition);
            System.out.println("Perform assignation");
        }
        return operatorList;
    }


    private void transferToLeft(int position) {
        List<String> tempOperatorList = new ArrayList<>(operatorList.size());

        String[] tempOperands = Arrays.copyOf(operands, operands.length);
        //a: 1+2*A = 3-4/5      b: A+1*2 = 3-4/5
        //    + *  =  - /           + *  =  - /
        //   1 2 n   3 4 5         n 1 2   3 4 5
        //   3 4 5 -(1) (2)
        //   = - / (+) (*)
        tempOperatorList.addAll(operatorList.subList(position, operatorList.size()));
        System.arraycopy(operands, position, tempOperands, 0, operands.length - position);
        System.arraycopy(operands, 0, tempOperands, operands.length - position, position);

        System.out.println(Arrays.toString(tempOperands));

        System.out.println(tempOperatorList);
    }


 */

