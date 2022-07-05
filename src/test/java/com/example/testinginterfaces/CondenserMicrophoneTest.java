package com.example.testinginterfaces;

public class CondenserMicrophoneTest implements MicrophoneTest<CondenserMicrophone> {

    @Override
    public CondenserMicrophone createMicrophone() {
        return new CondenserMicrophone();
    }
}
