package com.example.testinginterfaces;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public interface MicrophoneTest<T extends Microphone> {

    T createMicrophone();

    @Test
    default void defaultGain() {
        var micro = createMicrophone();

        assertEquals(0, micro.getGain());
    }

    @Test
    default void setGain() {
        var micro = createMicrophone();

        micro.setGain(10);

        assertEquals(10, micro.getGain());
    }

    @Test
    default void increaseGainByRaisesError() {
        var micro = createMicrophone();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> micro.increaseGainBy(-1));

        assertAll(
                () -> assertEquals("Gain addition must be positive", exception.getMessage()),
                () -> assertNull(exception.getCause()));
    }

    @Test
    default void decreaseGainByRaisesError() {
        var micro = createMicrophone();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> micro.decreaseGainBy(-1));

        assertAll(
                () -> assertEquals("Gain addition must be positive", exception.getMessage()),
                () -> assertNull(exception.getCause()));
    }

    @Test
    default void increaseGainBy() {
        var micro = createMicrophone();

        float result = assertDoesNotThrow(() -> micro.increaseGainBy(10));

        assertAll(
                () -> assertEquals(10, result),
                () -> assertEquals(10, micro.getGain()));
    }

    @Test
    default void decreaseGainBy() {
        var micro = createMicrophone();

        float result = assertDoesNotThrow(() -> micro.decreaseGainBy(10));

        assertAll(
                () -> assertEquals(-10, result),
                () -> assertEquals(-10, micro.getGain()));
    }

    @Test
    default void increaseGainBy0() {
        var micro = createMicrophone();

        float result = assertDoesNotThrow(() -> micro.increaseGainBy(0));

        assertAll(
                () -> assertEquals(0, result),
                () -> assertEquals(0, micro.getGain()));
    }

    @Test
    default void decreaseGainBy0() {
        var micro = createMicrophone();

        float result = assertDoesNotThrow(() -> micro.decreaseGainBy(0));

        assertAll(
                () -> assertEquals(0, result),
                () -> assertEquals(0, micro.getGain()));
    }

    @Test
    default void stopBeforeStartRaisesException() {
        var micro = createMicrophone();

        IllegalStateException exception = assertThrows(IllegalStateException.class, micro::stopRecording);

        assertAll(
                () -> assertNull(exception.getCause()),
                () -> assertEquals("Microphone was not even started", exception.getMessage()));
    }

    @Test
    default void startRecordingTwiceRaisesException() {
        var micro = createMicrophone();

        micro.startRecording();
        IllegalStateException exception = assertThrows(IllegalStateException.class, micro::startRecording);

        assertAll(
                () -> assertNull(exception.getCause()),
                () -> assertEquals("Microphone is already recording", exception.getMessage()));
    }

    @Test
    default void startRecording() {
        var micro = createMicrophone();

        micro.startRecording();
        Recording recording = micro.stopRecording();

        assertAll(
                () -> assertNotNull(recording),
                () -> assertNotNull(recording.length()),
                () -> assertNotEquals(0, recording.length().toNanos()));
    }

    @Test
    default void startRecordingAfterStop() {
        var micro = createMicrophone();

        micro.startRecording();
        Recording recording = micro.stopRecording();

        assertAll(
                () -> assertNotNull(recording),
                () -> assertNotNull(recording.length()),
                () -> assertNotEquals(0, recording.length().toNanos()),
                () -> assertDoesNotThrow(micro::startRecording));
    }
}
