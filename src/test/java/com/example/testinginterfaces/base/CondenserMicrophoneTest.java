package com.example.testinginterfaces.base;

import com.example.testinginterfaces.Microphone;
import com.example.testinginterfaces.MicrophoneTest;

public class CondenserMicrophoneTest implements MicrophoneTest {

    @Override
    public Microphone createMicrophone() {
        return new CondenserMicrophone();
    }
    
}
