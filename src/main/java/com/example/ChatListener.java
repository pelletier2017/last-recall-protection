package com.example;

import net.runelite.api.events.ChatMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener {
//    private static final Joiner pipe = Joiner.on("|");
//    private static final List<String> REGIONS = Stream.of(RegionShield.values()).map(RegionShield::getRegion).collect(Collectors.toList());
    private static final Pattern LAST_RECALL_STORE_PATTERN = Pattern.compile("Your Crystal of memories stores a memory of your last teleport from .*");
    private static final Pattern LAST_RECALL_MEMORY_PATTERN = Pattern.compile("You Sigil of Last Recall contains a memory of teleporting from .*");

    private static final String LAST_RECALL_FORGET_MESSAGE = "You rub the Crystal of memories and it brings you back to a place you remember.";
    private static final String LAST_RECALL_FORGOTTEN_MESSAGE = "You don't remember teleporting anywhere recently, try teleporting somewhere to store a new memory.";

    private boolean isLastRecallSaved = false;

    // heavily borrowed from https://github.com/dekvall/runelite-external-plugins/blob/last-recall/src/main/java/dev/dkvl/lastrecall/LastRecallPlugin.java#L91
    public void onChatMessage(ChatMessage event) {

        // TODO make sure this only does game messages in real league
//        if (event.getType() != ChatMessageType.GAMEMESSAGE) {
//            return;
//        }

        String message = event.getMessage();

        Matcher storingMemoryMatcher = LAST_RECALL_STORE_PATTERN.matcher(message);
        Matcher hasMemoryMatcher = LAST_RECALL_MEMORY_PATTERN.matcher(message);

        if (storingMemoryMatcher.find()) {
            isLastRecallSaved = true;
        } else if (hasMemoryMatcher.find()) {
            isLastRecallSaved = true;
        } else if (message.equals(LAST_RECALL_FORGET_MESSAGE) || message.equals(LAST_RECALL_FORGOTTEN_MESSAGE)) {
            isLastRecallSaved = false;
        }
    }

    public boolean isLastRecallSaved() {
        // TODO change this back later
//        return isLastRecallSaved;
        return true;
    }
}
