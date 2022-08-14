package com.example.testinginterfaces.base;

import com.example.testinginterfaces.Microphone;
import com.example.testinginterfaces.MicrophoneTest;

class DynamicMicrophoneTest implements MicrophoneTest {

    @Override
    public Microphone createMicrophone() {
        return new DynamicMicrophone();
    }
}
