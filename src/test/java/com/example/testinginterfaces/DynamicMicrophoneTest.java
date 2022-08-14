package com.example.testinginterfaces;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class DynamicMicrophoneTest implements MicrophoneTest<DynamicMicrophone> {

    private static final int HOW_MANY_TIMES_DO_I_HAVE_TO_REPEAT = 1000;

    @Override
    public DynamicMicrophone createMicrophone() {
        return new DynamicMicrophone();
    }

    @Test
    void dynamicMicroIsLessSound() {
        var dynamicAverage =
                IntStream.range(0, HOW_MANY_TIMES_DO_I_HAVE_TO_REPEAT)
                        .mapToDouble(i -> record(createMicrophone()).volume())
                        .average();

        var condenserAverage =
                IntStream.range(0, 1000)
                        .mapToDouble(i -> record(new CondenserMicrophone()).volume())
                        .average();

        assertAll(
                () -> assertTrue(dynamicAverage.isPresent()),
                () -> assertTrue(condenserAverage.isPresent()),
                () -> assertTrue(dynamicAverage.getAsDouble() < condenserAverage.getAsDouble()));
    }

    @Test
    void dynamicMicroIsLessThanCondenserUsingMocks() {
        Microphone mock = mock(Microphone.class);
        DynamicMicrophone dynamicMicrophone = new DynamicMicrophone(mock);

        when(mock.stopRecording()).thenReturn(new Recording(40, Duration.ZERO));

        Recording dynamicMicRecording = dynamicMicrophone.stopRecording();

        assertEquals(20, dynamicMicRecording.volume());
        assertEquals(0, dynamicMicRecording.length().toNanos());
    }

    private Recording record(Microphone micro) {
        micro.startRecording();
        return micro.stopRecording();
    }
}
