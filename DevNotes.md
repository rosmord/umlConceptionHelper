# Development notes

## Participants

When we extract participants, the class name is found in `participant.getDisplay().asList()`. It's *probably* the last value (the stereotype might come first).

Note that in some diagram, we might find things like "t: Ticket" as name for the participant. So, we must extract the end of line.

