package com.example.testinginterfaces;

import lombok.NonNull;
import lombok.experimental.Delegate;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class DynamicMicrophone implements Microphone {

    @Delegate(excludes = Overrides.class)
    private final Microphone simpleMicrophone;

    public DynamicMicrophone(@Nullable Microphone microphone) {
        this.simpleMicrophone = Objects.requireNonNullElse(microphone, new SimpleMicrophone());
    }

    public DynamicMicrophone() {
        this(null);
    }

    @Override
    public @NonNull Recording stopRecording() {
        Recording recording = simpleMicrophone.stopRecording();

        float newVolume = recording.volume() - 20;

        return new Recording(newVolume, recording.length());
    }

    private interface Overrides {
        @NonNull Recording stopRecording();
    }
}
