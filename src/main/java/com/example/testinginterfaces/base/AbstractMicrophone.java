package com.example.testinginterfaces.base;

import com.example.testinginterfaces.Microphone;
import com.example.testinginterfaces.Recording;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NonNull;

public abstract class AbstractMicrophone implements Microphone {
    public static final float DEFAULT_GAIN = 0.0F;

    @Getter private float gain = DEFAULT_GAIN;

    private Instant startedAt;

    @Override
    public void setGain(float gain) {
        this.gain = gain;
    }

    @Override
    public float increaseGainBy(@PositiveOrZero float addition) {
        checkGainAddition(addition);

        gain += addition;

        return gain;
    }

    @Override
    public float decreaseGainBy(@PositiveOrZero float addition) {
        checkGainAddition(addition);

        gain -= addition;

        return gain;
    }

    @Override
    public void startRecording() {
        if (startedAt != null) {
            throw new IllegalStateException("Microphone is already recording");
        }

        startedAt = Instant.now();
    }

    @Override
    public @NonNull Recording stopRecording() {
        if (startedAt == null) {
            throw new IllegalStateException("Microphone was not even started");
        }

        float volume = ThreadLocalRandom.current().nextFloat(-100, -50);
        Duration duration = Duration.between(startedAt, Instant.now());

        startedAt = null;

        return new Recording(volume + gain, duration);
    }

    private void checkGainAddition(float gain) {
        if (gain < 0) {
            throw new IllegalArgumentException("Gain addition must be positive");
        }
    }
}
