package com.example.testinginterfaces;

import java.time.Duration;
import lombok.Value;

@Value
public class Recording {

    float volume;
    Duration length;

    public Duration length() {
        return length;
    }

    public float volume() {
        return volume;
    }

    public boolean isClipping() {
        return volume > 0;
    }
}
