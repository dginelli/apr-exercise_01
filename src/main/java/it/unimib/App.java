package it.unimib;

import fr.spoonlabs.flacoco.api.Flacoco;
import fr.spoonlabs.flacoco.api.result.FlacocoResult;
import fr.spoonlabs.flacoco.core.config.FlacocoConfig;

public class App {

    private static final String PROJECT_PATH = "/Users/davide/Development/APR/library/";

    public static void main(String[] args) {
        FlacocoConfig config = new FlacocoConfig();
        config.setProjectPath(PROJECT_PATH);
        config.setComputeSpoonResults(true);

        Flacoco flacoco = new Flacoco(config);
        FlacocoResult result = flacoco.run();

        result.getFailingTests().forEach(testMethod -> {
            System.out.println("Test class: " + testMethod.getFullyQualifiedClassName() +
                    ", Test method: " + testMethod.getFullyQualifiedMethodName());
        });

        result.getDefaultSuspiciousnessMap().forEach(((location, suspiciousness) -> {
            System.out.println("Class name: " + location.getClassName() +
                    ", Line number: " + location.getLineNumber() +
                    ", Suspiciousness score: " + suspiciousness.getScore());
        }));

        result.getSpoonSuspiciousnessMap().forEach(((ctStatement, suspiciousness) -> {
            System.out.println("Line: " + ctStatement.getPosition().getLine() +
                    ", Type: " + ctStatement.getShortRepresentation() +
                    ", Statement: " + ctStatement.prettyprint() +
                    ", Suspiciousness score:" + suspiciousness.getScore());
        }));
    }
}
