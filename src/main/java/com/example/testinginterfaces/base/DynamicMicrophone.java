package com.example.testinginterfaces.base;

import com.example.testinginterfaces.Recording;
import lombok.NonNull;

public class DynamicMicrophone extends AbstractMicrophone {

    @Override
    public @NonNull Recording stopRecording() {
        Recording recording = super.stopRecording();

        float newVolume = recording.volume() - 20;

        return new Recording(newVolume, recording.length());
    }
}
