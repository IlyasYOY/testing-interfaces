package com.example.testinginterfaces;

import lombok.experimental.Delegate;

public class CondenserMicrophone implements Microphone {

    @Delegate
    private final Microphone simpleMicrophone = new SimpleMicrophone();

}
