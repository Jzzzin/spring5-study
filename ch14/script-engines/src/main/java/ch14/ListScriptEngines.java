package ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngineManager;

public class ListScriptEngines {
    private static Logger logger = LoggerFactory.getLogger(ListScriptEngines.class);

    public static void main(String... args) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        mgr.getEngineFactories().forEach(factory -> {
            String engineName = factory.getEngineName();
            String languageName = factory.getLanguageName();
            String version = factory.getLanguageVersion();
            logger.info("엔진 이름: " + engineName + ", 언어: " + languageName + ", 버전: " + version);
        });
    }
}
