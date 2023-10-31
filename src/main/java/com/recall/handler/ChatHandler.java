package com.recall.handler;

import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatHandler {
    private static final Pattern LAST_RECALL_STORE_PATTERN = Pattern.compile("Your Crystal of memories stores a memory of your last teleport from .*");
    private static final Pattern LAST_RECALL_MEMORY_PATTERN = Pattern.compile("You Sigil of Last Recall contains a memory of teleporting from .*");

    private static final String LAST_RECALL_FORGET_MESSAGE = "You rub the Crystal of memories and it brings you back to a place you remember.";
    private static final String LAST_RECALL_FORGOTTEN_MESSAGE = "You don't remember teleporting anywhere recently, try teleporting somewhere to store a new memory.";

    private boolean isLastRecallSaved = false;

    // heavily borrowed from https://github.com/dekvall/runelite-external-plugins/blob/last-recall/src/main/java/dev/dkvl/lastrecall/LastRecallPlugin.java#L91
    public void onChatMessage(ChatMessage event) {

        if (event.getType() != ChatMessageType.GAMEMESSAGE) {
            return;
        }

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
        // TODO persist whether its saved or not so when they save something to orb, close runelite, reopen it still knows
        // TODO persist so when they save recall, put it in the bank, log off, and come back and take it out that it still remembers its saved
        return isLastRecallSaved;
    }
}
