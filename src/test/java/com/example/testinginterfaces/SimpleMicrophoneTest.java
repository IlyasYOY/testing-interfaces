package com.example.testinginterfaces;

public class SimpleMicrophoneTest implements MicrophoneTest<SimpleMicrophone> {

    @Override
    public SimpleMicrophone createMicrophone() {
        return new SimpleMicrophone();
    }
}
