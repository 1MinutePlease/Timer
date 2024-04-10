package de.paulflohr.timer.util;

public enum TimeFormat {

    Digital,
    Verbose;

    public String getTimeFormatted(int time) {
        return switch (this) {
            case Digital:
                yield formatDigital(time);
            case Verbose:
                yield formatVerbose(time);
        };
    }

    private String formatVerbose(int duration) {
        int days = duration / (60 * 60 * 24);
        duration %= (60 * 60 * 24);
        int hours = duration / (60 * 60);
        duration %= (60 * 60);
        int minutes = duration / 60;
        int seconds = duration % 60;

        StringBuilder builder = new StringBuilder();
        if (days > 0) builder.append(days).append("d ");
        if (hours > 0) builder.append(hours).append("h ");
        if (minutes > 0) builder.append(minutes).append("min ");
        if (builder.isEmpty() || seconds > 0) builder.append(seconds).append("s");

        return builder.toString();
    }

    private String formatDigital(int duration) {
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = duration % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
