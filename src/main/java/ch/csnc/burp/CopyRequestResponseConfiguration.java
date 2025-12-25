package ch.csnc.burp;

import burp.api.montoya.ui.settings.SettingsPanelBuilder;
import burp.api.montoya.ui.settings.SettingsPanelPersistence;
import burp.api.montoya.ui.settings.SettingsPanelSetting;
import burp.api.montoya.ui.settings.SettingsPanelWithData;

public class CopyRequestResponseConfiguration {


    private static final String cutTextLabel = "Cut Text";
    private static final String cutTextDefault = "[...]";

    private static final String copyFullFullOrSelectionHotKeyLabel = "HotKey for Copy Full/Full or Full/Header + Selected Data";
    private static final String copyFullFullOrSelectionHotKeyDefault = "Ctrl+Shift+C";

    private static final String copyFullHeaderLabel = "HotKey for Copy Full/Header";
    private static final String copyFullHeaderDefault = "Ctrl+Alt+C";

    private static final String useNbspCheckbox = "Use Non-Breakable Spaces";
    private static final boolean useNbspCheckboxDefault = false;

    private static final String markdownCodeBlockHeaderLabel = "Markdown Code Block Header";
    private static final String markdownCodeBlockHeaderDefault = "http highlight-manual";

    public static String cutText() {
        var cutText = panel.getString(cutTextLabel);

        if (useNonBreakableSpace()) {
            cutText = cutText.replaceAll(" ", "\u00a0");
        }
        return cutText;
    }


    public static boolean useNonBreakableSpace() {
        return panel.getBoolean(useNbspCheckbox);
    }


    public static String copyFullFullOrSelectionHotKey() {
        return panel.getString(copyFullFullOrSelectionHotKeyLabel);
    }


    public static String copyFullHeaderHotKey() {
        return panel.getString(copyFullHeaderLabel);
    }

    public static String markdownCodeBlockHeader() {
        return panel.getString(markdownCodeBlockHeaderLabel);
    }


    private CopyRequestResponseConfiguration() {
        // static class
    }

    // Define setting panel entries
    private final static SettingsPanelSetting[] settings = new SettingsPanelSetting[] {
            SettingsPanelSetting.stringSetting(cutTextLabel, cutTextDefault),
            SettingsPanelSetting.booleanSetting(useNbspCheckbox, useNbspCheckboxDefault),
            SettingsPanelSetting.stringSetting(copyFullFullOrSelectionHotKeyLabel, copyFullFullOrSelectionHotKeyDefault),
            SettingsPanelSetting.stringSetting(copyFullHeaderLabel, copyFullHeaderDefault),
            SettingsPanelSetting.stringSetting(markdownCodeBlockHeaderLabel, markdownCodeBlockHeaderDefault)
    };

    // Create settings panel
    private final static SettingsPanelWithData panel = SettingsPanelBuilder.settingsPanel()
                                                                           .withPersistence(SettingsPanelPersistence.USER_SETTINGS)
                                                                           .withTitle("CopyRequestResponse Configuration")
                                                                           .withDescription("The extension's behavior can be customized here.")
                                                                           .withKeywords("CopyRequestResponse", "Settings", "CutText")
                                                                           .withSettings(settings)
                                                                           .build();

    public static void register() {
        CopyRequestResponseExtension.api().userInterface().registerSettingsPanel(panel);
    }
}
