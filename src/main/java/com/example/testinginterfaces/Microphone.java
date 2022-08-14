package com.example.testinginterfaces;

import javax.validation.constraints.PositiveOrZero;
import lombok.NonNull;

public interface Microphone {
    float getGain();

    void setGain(float gain);

    float increaseGainBy(@PositiveOrZero float gain);

    float decreaseGainBy(@PositiveOrZero float gain);

    void startRecording();

    @NonNull
    Recording stopRecording();
}
